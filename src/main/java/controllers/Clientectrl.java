package controllers;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Cliente;
import model.Cooperativa;
import model.Ruta;

public class Clientectrl {

	MD5 objMD5 = new MD5();
	
    public EntityManager getSession() {
        return JPAUtil.getSession();
    }	
    
	   public void crearCliente( Cliente objClientData ){
		      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("valotheme" );
		      EntityManager entitymanager = emfactory.createEntityManager( );
		      entitymanager.getTransaction( ).begin( );

		      Cliente objNewClient = new Cliente(); 
		      objNewClient.setCedula( objClientData.getCedula() );
		      objNewClient.setNombres( objClientData.getNombres() );
		      objNewClient.setApellidos( objClientData.getApellidos() );
		      objNewClient.setNickname( objClientData.getNickname() );
		      objNewClient.setEmail( objClientData.getEmail() );
		      objNewClient.setClave( objMD5.getMD5(objClientData.getClave()) );
		      objNewClient.setTiposusuarioId(objClientData.getTiposusuarioId());
//		      objNewClient.setIdCooperativa(objClientData.getIdCooperativa());
		      
		      objNewClient.setIdCooperativa(objClientData.getIdCooperativa());
		      
		      entitymanager.persist( objNewClient );
		      entitymanager.getTransaction( ).commit( );
		      entitymanager.close( );
		      emfactory.close( );		   
	   }
	   
	   public ArrayList<Cliente> getAll(int id_cooperativa){
		    ArrayList<Cliente> data = new ArrayList<Cliente>();
		    EntityManager s = getSession();
		    EntityTransaction tx = null;
		    try {
		        tx = s.getTransaction();
		        tx.begin();
		        
		        Cooperativa objCooperativa = new Cooperativa();
		        objCooperativa.setId(id_cooperativa);
		        
		        Query us = null;
		        if( id_cooperativa > 0 ){
			        us= s.createQuery("Select c FROM Cliente c JOIN c.cooperativa co WHERE co.id = :id_cooperativa");		        
			        us.setParameter("id_cooperativa", id_cooperativa);		        	
		        }else{
			        us= s.createQuery("Select c FROM Cliente c");		        	
		        }

		  		//coopdata = (ArrayList<Cooperativa>) us.getResultList(); 
		  		data =new ArrayList<Cliente> (us.getResultList());
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
}

