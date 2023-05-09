/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.connection;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import models.entitys.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import validations.Validation;


/**
 *
 * @author Albert
 */
public class UserControl {
    
    private Session session;
    private Transaction tx;
    private boolean saved;
    private int removed;
    private SessionControl sessionControl = new SessionControl();

    public boolean createUser(User user, Label label) {        

        session = sessionControl.createConnection();

        try {

            session.beginTransaction();

            session.save(user);

            saved = true;

        } catch (ConstraintViolationException e) {
            label.setText("El número de licencia ya existe en la base de datos");
            saved = false;
        } finally {

            sessionControl.closeConnection(session);

        }

        return saved;

    }

    public int removeUser(User user) {
        
        session = sessionControl.createConnection();

        try {

            tx = session.beginTransaction();

            session.delete(user);

            tx.commit();

            removed = 1;

        } catch (Exception e) {
            
            e.printStackTrace();

            tx.rollback();

            removed = 0;

        } finally {

            sessionControl.closeConnection(session);

        }

        return removed;
    }

    public void updateUser(TableColumn.CellEditEvent<User, String> event, String type, Label label) {
        

        session = sessionControl.createConnection();

        Transaction tx =  null;

        try {

            tx = session.beginTransaction();

            User user = event.getRowValue();

            String hql = "from User where id=" + user.getId();

            User temporalUser = (User) session.createQuery(hql).getSingleResult();
            
            boolean found = false;

            switch (type) {
                case "name":
                    temporalUser.setName(event.getNewValue());
                    break;
                case "surname":
                    temporalUser.setSurname(event.getNewValue());
                    break;
                case "userName":
                    
                    for (String temporalUserName : getUnique("userName")){
                        if (event.getNewValue().equals(temporalUserName)){
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        temporalUser.setUserName(event.getNewValue());
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Usuario repetido");
                        alert.setContentText("El nombre de usuario que está introduciendo ya se encuentra en la base de datos");
                        alert.showAndWait();
                    }
                    break;
                    
                case "licenseNumber":                   
                    
                    for (String temporalLicenseNumber : getUnique("licenseNumber")){
                        if (event.getNewValue().equals(temporalLicenseNumber)){
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        if(new Validation().licenseNumberValidate(event.getNewValue())){
                            temporalUser.setLicenseNumber(event.getNewValue());
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Formato de licencia incorrecto");
                        alert.setContentText("La licencia debe ser un numero de cinco cifras");
                        alert.showAndWait();
                        }
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Licencia repetida");
                        alert.setContentText("La licencia que está introduciendo ya se encuentra en la base de datos");
                        alert.showAndWait();
                    }
                    
                    break;
            }

            user = temporalUser;            

            session.update(user);

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();

        } finally {

            sessionControl.closeConnection(session);

        }

    }
    
    public void updateUser(int id, boolean admin) {
        

        session = sessionControl.createConnection();

        Transaction tx =  null;

        try {

            tx = session.beginTransaction(); 
            
            String hql = "from User where id=" + id;
            
            User user = (User) session.createQuery(hql).getSingleResult();
            
            user.setAdmin(admin);

            session.update(user);

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();

        } finally {

            sessionControl.closeConnection(session);

        }

    }   
    
    
    private ArrayList<String> getUnique(String type){        
            
            String hql = "select " + type + " from User";
            
            return (ArrayList<String>) session.createQuery(hql).getResultList();
            
    }

}
