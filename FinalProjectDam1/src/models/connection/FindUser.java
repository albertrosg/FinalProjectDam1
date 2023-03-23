/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.entitys.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Albert
 */
public class FindUser {
    
    private int found = 0;
    private SessionFactory sessionFactory;
    private Session session;
    
    public int getUser(String userName, String password){        
        
        
            sessionFactory = new Configuration().configure("models/connection/hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
            
            session = sessionFactory.openSession();
            
            try{
            
            session.beginTransaction();           

            List<User> list = session.createQuery("from User us where us.userName='"+userName+"' and us.password='"+password+"'").getResultList();
            
            for(User user : list){
            System.out.println(user.getId());
            found=1;
            }

            return found;
            
            }finally{
            
            session.close();
            sessionFactory.close();
            
            }
            
       
        
    }
    
}
