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

import java.util.Hashtable;
import java.util.List;

public class Coop {

	    public EntityManager getSession() {
	        return JPAUtil.getSession();
	    }	
	    
	   public ArrayList<Cooperativa> getAll(int coopid){
		    ArrayList<Cooperativa> coopdata = new ArrayList<Cooperativa>();
		    EntityManager s = getSession();
		    EntityTransaction tx = null;
		    try {
		        tx = s.getTransaction();
		        tx.begin();
		        Query us = null;
		        if(coopid > 0){
			        us= s.createQuery("SELECT c FROM Cooperativa c WHERE c.id = :id");
			        us.setParameter("id", coopid);		        	
		        }else{
			        us= s.createQuery("SELECT c FROM Cooperativa c");
		        }

		  		coopdata = new ArrayList<Cooperativa> (us.getResultList());

                                List<Cooperativa> personList = us.getResultList();
                                
                                    for (Cooperativa p : personList) {
                                        System.out.println(p.toString());
                                    }
                                    
		  		tx.commit();
		    } catch (Exception e) {
		        System.out.println(e);
		        tx.rollback();
		    }finally{
		        s.close();
		    }
		    
//		    System.out.println("HOLA: "+coopdata.get(0).getNombreComercial());
		    return coopdata;		    
	   }
		   
	   
	   public void crearCooperativa( Hashtable coop_data ){
		      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ticketysystem01" );
		      EntityManager entitymanager = emfactory.createEntityManager( );
		      entitymanager.getTransaction( ).begin( );

		      Cooperativa coop = new Cooperativa( ); 
		      coop.setNombreComercial( coop_data.get("name").toString() );
		      coop.setRazonSocial( coop_data.get("razon_social").toString() );
		      coop.setTelefonos( coop_data.get("telefonos").toString() );
		      coop.setEmail( coop_data.get("email").toString() );
		      
		      byte[] mision = coop_data.get("mision").toString().getBytes();
		      byte[] vision = coop_data.get("vision").toString().getBytes();		      
		      coop.setMision( mision );
		      coop.setVision( vision );
		      
		      entitymanager.persist( coop );
		      entitymanager.getTransaction( ).commit( );
		      entitymanager.close( );
		      emfactory.close( );		   
	   }
}

