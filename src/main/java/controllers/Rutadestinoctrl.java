package controllers;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Asientovendido;
import model.Cliente;
import model.Cooperativa;
import model.Ruta;
import model.Rutadestino;

public class Rutadestinoctrl {

	MD5 objMD5 = new MD5();
	
    public EntityManager getSession() {
        return JPAUtil.getSession();
    }	
    
	   public void crearRutadestino( Rutadestino data ){
		      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("com.vaadin.tests.themes.valo_ticketysystem01_war_0.0.1-SNAPSHOTPU" );
		      EntityManager entitymanager = emfactory.createEntityManager( );
		      entitymanager.getTransaction( ).begin( );

		      Rutadestino objNewDest = new Rutadestino(); 
		      objNewDest.setIdRuta(data.getIdRuta());
		      objNewDest.setDestino(data.getDestino());
		      objNewDest.setCosto(data.getCosto());		      
		      
		      entitymanager.persist( objNewDest );
		      entitymanager.getTransaction( ).commit( );
		      entitymanager.close( );
		      emfactory.close( );		   
	   }
	   
	   /*
	    * Obtener el reporte de los destinos, con el costo
	    */	   
	   public ArrayList<Rutadestino> getRutaDestino(int id_cooperativa){
		    ArrayList<Rutadestino> data = new ArrayList<Rutadestino>();
		    EntityManager s = getSession();
		    EntityTransaction tx = null;
		    try {
		        tx = s.getTransaction();
		        tx.begin();
		        
		        Cooperativa objCooperativa = new Cooperativa();
		        objCooperativa.setId(id_cooperativa);
		        
		        Query us = null;
		        if( id_cooperativa > 0 ){
			        us= s.createQuery(
			        			"SELECT rd "
			        			+ "FROM Rutadestino rd "
			        			+ "JOIN Ruta r "
			        			+ "JOIN Cooperativa c "
			        			+ "WHERE c.id = :id_cooperativa"
			        		);
			        us.setParameter("id_cooperativa", id_cooperativa);		        	
		        }else{
			        us= s.createQuery(
		        			"SELECT rd "
		        			+ "FROM Rutadestino rd "
		        			+ "JOIN Ruta r "
		        			+ "JOIN Cooperativa c "
		        		);
		        }

		  		//coopdata = (ArrayList<Cooperativa>) us.getResultList(); 
		  		data =new ArrayList<Rutadestino> (us.getResultList());
		  		tx.commit();
		    } catch (Exception e) {
		        System.out.println(e);
		        tx.rollback();
		    }finally{
		        s.close();
		    }
		    /*System.out.println(data.get(0).getCooperativa().getNombreComercial());*/
		    return data;		    
	   }
	   
	   /***********************************************/
	   public Rutadestino getRutaDestinoById(int rutaDestinoId){
		   Rutadestino c = null;
		    ArrayList<Rutadestino> data = new ArrayList<Rutadestino>();
		    EntityManager s = getSession();
		    EntityTransaction tx = null;
		    try {
		        tx = s.getTransaction();
		        tx.begin();		        
		        
		        Query us = null;

			        us= s.createQuery(
			        			"SELECT rd "
			        			+ "FROM Rutadestino rd "
			        			+ "WHERE rd.id = :rutadestinoid"
			        		);
			        us.setParameter("rutadestinoid", rutaDestinoId);
 
		  		data =new ArrayList<Rutadestino> (us.getResultList());
		  		
	            for(Rutadestino e: data){
					 c = e;
				}		  		
		  		
		  		tx.commit();
		    } catch (Exception e) {
		        System.out.println(e);
		        tx.rollback();
		    }finally{
		        s.close();
		    }
		    /*System.out.println(data.get(0).getCooperativa().getNombreComercial());*/
		    return c;		    
	   }	   	   
	   
}

