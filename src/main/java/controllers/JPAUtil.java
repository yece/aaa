/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author ceci
 */
public class JPAUtil {

   
    private static final EntityManagerFactory sessionFactory;
    
    static {
        try {
           
        	sessionFactory = Persistence.createEntityManagerFactory("valotheme");
        	
        } catch (Throwable ex) {
            
            System.err.println("Inicio de sesi�n ha fallado." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static EntityManagerFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static EntityManager getSession() {
        return getSessionFactory().createEntityManager();
    }
}
