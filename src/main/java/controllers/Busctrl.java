package controllers;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Bus;
import model.Cliente;
import model.Cooperativa;
import model.Ruta;
import model.Rutadestino;

public class Busctrl {

	MD5 objMD5 = new MD5();
	
    public EntityManager getSession() {
        return JPAUtil.getSession();
    }	
    
	   public void crearBus( Bus data ){
		      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("valotheme" );
		      EntityManager entitymanager = emfactory.createEntityManager( );
		      entitymanager.getTransaction( ).begin( );

		      Bus objNewDest = new Bus(); 
		      objNewDest.setAsientos(data.getAsientos());
		      objNewDest.setNumero(data.getNumero());		      
		      objNewDest.setNombresDuenio(data.getNombresDuenio());
		      objNewDest.setIdCooperativa(data.getIdCooperativa());
		      objNewDest.setPlaca(data.getPlaca());
		      
		      entitymanager.persist( objNewDest );
		      entitymanager.getTransaction( ).commit( );
		      entitymanager.close( );
		      emfactory.close( );		   
	   }
	   
	   public ArrayList<Bus> getBuses(int id_cooperativa){
		    ArrayList<Bus> data = new ArrayList<Bus>();
		    EntityManager s = getSession();
		    EntityTransaction tx = null;
		    try {
		        tx = s.getTransaction();
		        tx.begin();
		        
		        Cooperativa objCooperativa = new Cooperativa();
		        objCooperativa.setId(id_cooperativa);
		        
		       Query us = null;
		         if( id_cooperativa > 0 ){
			        us= s.createQuery("Select b FROM Bus b JOIN b.cooperativa c WHERE c.id =:id_cooperativa");		        
			        us.setParameter("id_cooperativa", id_cooperativa);		        	
		        }else{
			        us= s.createQuery("Select b FROM Bus b JOIN b.cooperativa c");
		        }

		  		//coopdata = (ArrayList<Cooperativa>) us.getResultList(); 
		  		data = new ArrayList<Bus> (us.getResultList());
		  		tx.commit();
		    } catch (Exception e) {
		        System.out.println(e);
		        tx.rollback();
		    }finally{
		        s.close();
		    }
		   /* System.out.println(data.get(0).getNombre());*/
		    return data;		    
	   }	   
	      
}

