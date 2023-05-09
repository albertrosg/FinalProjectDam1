/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.connection;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import models.entitys.PetRace;
import models.entitys.PetType;
import models.entitys.User;
import org.hibernate.Session;

/**
 *
 * @author Albert
 */
public class FindInTables {

    private SessionControl sessionControl = new SessionControl();

    private Session session;

    public ObservableList<User> getUsers() {

        ObservableList<User> listView = FXCollections.observableArrayList();

        List<User> list;

        session = sessionControl.createConnection();

        try {

            session.beginTransaction();

            list = session.createQuery("from User").getResultList();

            for (User user : list) {

                int id = user.getId();
                String name = user.getName();
                String surname = user.getSurname();
                String licenseNumber = user.getLicenseNumber();
                String userN = user.getUserName();
                boolean admin = user.isAdmin();

                listView.add(new User(id, name, surname, licenseNumber, userN, admin));
            }

            return listView;

        } finally {

            sessionControl.closeConnection(session);

        }

    }

    public User getUser(String userName, String password, Label error) {

        User user = null;

        List<User> list;

        try {

            session = sessionControl.createConnection();

            session.beginTransaction();

            list = session.createQuery("from User us where us.userName='" + userName + "' and us.password='" + password + "'").getResultList();

            for (User userFound : list) {

                user = userFound;

            }

            return user;

        } catch (Exception e) {

            error.setText("A ocurrido un error en la conexión");
            e.printStackTrace();
            return user;

        } finally {

            sessionControl.closeConnection(session);

        }
    }

    public ObservableList<String> getRace(String typeName) {

        ObservableList<String> listView = FXCollections.observableArrayList();

        List<PetRace> list = null;

        session = sessionControl.createConnection();

        try {

            session.beginTransaction();

            PetType petType = (PetType) session.createQuery("from PetType where nameType='" + typeName + "'").getSingleResult();

            int type = petType.getTypeId();

            list = session.createQuery("from PetRace where typeId='" + type + "'").getResultList();

            for (PetRace petRace : list) {

                listView.add(petRace.getNameRace());
            }

        } finally {

            sessionControl.closeConnection(session);

        }

        return listView;

    }

    public ObservableList<String> getType() {

        ObservableList<String> listView = FXCollections.observableArrayList();

        List<PetType> list = null;

        session = sessionControl.createConnection();

        try {

            session.beginTransaction();

            list = session.createQuery("from PetType").getResultList();

            for (PetType petType : list) {

                listView.add(petType.getNameType());
            }

        } finally {

            sessionControl.closeConnection(session);

        }

        return listView;

    }

}
