/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.connection;

import java.util.List;
import models.entitys.User;
import models.entitys.encryption.SHAEncryption;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Albert
 */
public class CreateUser {

    private SessionFactory sessionFactory;
    private Session session;

    public void user() {

        sessionFactory = new Configuration().configure("models/connection/hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();

        session = sessionFactory.openSession();
        
        String password = new SHAEncryption().Sha256("1234");

        try {

            session.beginTransaction();

            User user = new User("Albert", "Ros", "85247", "albros", password);
            
            session.save(user);
            
        } finally {

            session.close();
            sessionFactory.close();

        }

    }

}
