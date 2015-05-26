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
			        us= s.createQuery("Select u from Cooperativa u WHERE u.id = :coopid");
			        us.setParameter("coopid", coopid);		        	
		        }else{
			        us= s.createQuery("Select u from Cooperativa u ");
		        }

		  		coopdata =new ArrayList<Cooperativa> (us.getResultList());
		  		tx.commit();
		    } catch (Exception e) {
		        System.out.println(e);
		        tx.rollback();
		    }finally{
		        s.close();
		    }
		    
		    /*System.out.println("HOLA: "+coopdata.get(0).getNombreComercial());*/
		    return coopdata;		    
	   }
		   
	   
	   public void crearCooperativa( Hashtable coop_data ){
		      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("valotheme" );
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

