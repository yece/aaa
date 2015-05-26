package controllers;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.internal.queries.ArrayListContainerPolicy;

import model.Cooperativa;
import model.Tiposusuario;

import java.util.Hashtable;

public class Tiposusuarioctrl {

	    public EntityManager getSession() {
	        return JPAUtil.getSession();
	    }	
	    
	   public ArrayList<Tiposusuario> getAll(){
		    ArrayList<Tiposusuario> coopdata = new ArrayList<Tiposusuario>();
		    EntityManager s = getSession();
		    EntityTransaction tx = null;
		    try {
		        tx = s.getTransaction();
		        tx.begin();
		        
		        Query us= s.createQuery("Select u from Tiposusuario u");
		  		//coopdata = (ArrayList<Cooperativa>) us.getResultList(); 
		  		coopdata =new ArrayList<Tiposusuario> (us.getResultList());
		  		tx.commit();
		    } catch (Exception e) {
		        System.out.println(e);
		        tx.rollback();
		    }finally{
		        s.close();
		    }
		    return coopdata;		    
	   }

}

