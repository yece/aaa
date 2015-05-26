package controllers;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;

import model.Cliente;
import model.Cooperativa;
import model.Ruta;

public class Loginctrl {

	MD5 objMD5 = new MD5();
	
    public EntityManager getSession() {
        return JPAUtil.getSession();
    }	
    
	   
	   public ArrayList<Cliente> identificar(int id_cooperativa, String username, String password){
		    ArrayList<Cliente> data = new ArrayList<Cliente>();
		    EntityManager s = getSession();
		    EntityTransaction tx = null;
		    try {
		        tx = s.getTransaction();
		        tx.begin();
		        
		        /*Cooperativa objCooperativa = new Cooperativa();
		        objCooperativa.setId(id_cooperativa);*/
		        password = objMD5.getMD5(password);
		        Query us = null;
		        System.out.println(id_cooperativa);
		        System.out.println(username);
		        System.out.println(password);
		        if( id_cooperativa > 0 ){
			        us= s.createQuery("Select c FROM Cliente c JOIN c.cooperativa co WHERE co.id = :id_cooperativa AND c.nickname = :username AND c.clave = :password");		        	
//			        us= s.createQuery("Select c FROM Cliente c WHERE c.idCooperativa = :id_cooperativa AND c.nickname = :username AND c.clave = :password");		        
			        us.setParameter("id_cooperativa", id_cooperativa);	
			        us.setParameter("username", username);	
			        us.setParameter("password", password);
		        }else{
			        us= s.createQuery("Select c FROM Cliente c");		        	
		        }

		  		//coopdata = (ArrayList<Cooperativa>) us.getResultList(); 
		  		data = new ArrayList<Cliente> (us.getResultList());
		  		tx.commit();
		    } catch (Exception e) {
		        System.out.println(e);
		        tx.rollback();
		    }finally{
		        s.close();
		    }
		    System.out.println("ID: "+data.get(0).getId());
		    return data;		    
	   }
	   
	   public void getSessionVars(){
	        Integer clientId = (Integer)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("id");
	        System.out.println("Client desde sessions: "+clientId);
	    	System.out.println(" HOLA FROM SESSIONS ");		   
	   }
	   
	   public int getSessionId(){
		   Integer clientId = 0;
	       
		   try{
			   clientId = (Integer)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("id");   
		   }catch(Exception e){
			   clientId = 0;
		   }
	        
	        if(clientId == null){
	        	return 0;	
	        }
	        System.out.println("El ID de getSessionId : "+clientId);
	        return clientId;
	   }
	   
	   /*public boolean logout(){
		   VaadinService.getCurrentRequest().getWrappedSession().invalidate();
		   getUI().getPage().open(Page.getCurrent().toString(), "");
		   return true;
	   }*/
}

