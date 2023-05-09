/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.connection;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.entitys.Owner;
import models.entitys.Pet;
import models.entitys.User;
import models.entitys.Visit;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Albert
 */
public class VisitControl {

    private Session session;
    private Transaction tx;
    private SessionControl sessionControl = new SessionControl();

    public boolean createVisit(Visit visit) {

        session = sessionControl.createConnection();

        try {

            tx = session.beginTransaction();

            session.save(visit);

            tx.commit();

            return true;

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sessionControl.closeConnection(session);
        }

    }

    public ObservableList<Visit> getVisit(int id) {

        session = sessionControl.createConnection();

        ObservableList<Visit> obList = FXCollections.observableArrayList();

        try {

            tx = session.beginTransaction();

            List<Visit> list = session.createQuery("from Visit where userId = ':id' order by date, hour").setParameter("id", id).getResultList();

            for (Visit visit : list) {

                String type = visit.getType();
                LocalTime hour = visit.getHour();
                LocalDate date = visit.getDate();
                String comment = visit.getComment();
                User user = visit.getUser();
                Pet pet = visit.getPet();
                Owner owner = visit.getOwner();

                obList.add(new Visit(type, hour, date, comment, user, pet, owner));

            }

            return obList;

        } finally {
            sessionControl.closeConnection(session);
        }

    }

    public List<LocalTime> getHours(LocalDate date) {

        session = sessionControl.createConnection();

        try {

            tx = session.beginTransaction();

            String hql = "select hour from Visit where date = :date";

            Query query = session.createQuery(hql);

            query.setParameter("date", date);

            List<LocalTime> hours = query.getResultList();

            return query.getResultList();

        } finally {

            sessionControl.closeConnection(session);

        }

    }

    public void deleteVisitPrevious() {

        session = sessionControl.createConnection();

        try {

            tx = session.beginTransaction();

            String hql = "delete from Visit where date < :currentDate";
            Query query = session.createQuery(hql);
            query.setParameter("currentDate", LocalDate.now());

            query.executeUpdate();

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            sessionControl.closeConnection(session);
        }
    }

}
