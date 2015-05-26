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
import model.Ruta;

import java.util.Hashtable;

public class Rutacontroller {

	    public EntityManager getSession() {
	        return JPAUtil.getSession();
	    }	
	    
	    
		public Ruta getRutaById(int id){
			Ruta c = null;
			try{
				 Query q = getSession().createQuery("Select r from Ruta r where r.id=:id");
		            q.setParameter("id", id);
		            ArrayList<Ruta> ep = new ArrayList<Ruta>(q.getResultList());
		            for(Ruta e: ep){
						c = e;
					}
			}catch(Exception e){
			}
			return c;
	    }	    
	    
	   public ArrayList<Ruta> getAll(int id_cooperativa){
		    ArrayList<Ruta> data = new ArrayList<Ruta>();
		    EntityManager s = getSession();
		    EntityTransaction tx = null;
		    try {
		        tx = s.getTransaction();
		        tx.begin();
		        
		        Cooperativa objCooperativa = new Cooperativa();
		        objCooperativa.setId(id_cooperativa);
		        
		       Query us = null;
		         if( id_cooperativa > 0 ){
			        us= s.createQuery("Select u FROM Ruta u JOIN u.cooperativa c WHERE c.id = :id_cooperativa");		        
			        us.setParameter("id_cooperativa", id_cooperativa);		        	
		        }else{
			        us= s.createQuery("Select u FROM Ruta u");		        	
		        }

		  		//coopdata = (ArrayList<Cooperativa>) us.getResultList(); 
		  		data =new ArrayList<Ruta> (us.getResultList());
		  		tx.commit();
		    } catch (Exception e) {
		        System.out.println(e);
		        tx.rollback();
		    }finally{
		        s.close();
		    }
		    /*System.out.println(data.get(0).getNombre());*/
		    return data;		    
	   }
		   
	   
	   public void crearRuta( Ruta objRuta ){
		      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("com.vaadin.tests.themes.valo_ticketysystem01_war_0.0.1-SNAPSHOTPU" );
		      EntityManager entitymanager = emfactory.createEntityManager( );
		      entitymanager.getTransaction( ).begin( );

		      Ruta ruta = new Ruta(); 
		      ruta.setCooperativaId(objRuta.getCooperativaId());
		      ruta.setHoraSalida(objRuta.getHoraSalida());		      
		      ruta.setNombre(objRuta.getNombre());
		      
		      entitymanager.persist( ruta );
		      entitymanager.getTransaction( ).commit( );
		      entitymanager.close( );
		      emfactory.close( );		   
	   }
}

