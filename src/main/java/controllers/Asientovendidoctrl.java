package controllers;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.*;
public class Asientovendidoctrl {

	MD5 objMD5 = new MD5();
	
    public EntityManager getSession() {
        return JPAUtil.getSession();
    }	
    	     
	public boolean checkAsientoVendido(Date fechasalida, int rutaId, int nroasiento){
		// Asientovendido c = null;
		boolean vendido = false;
		
	 	System.out.println("Fecha Salida: "+fechasalida); 
	 	System.out.println("rutaId: "+rutaId); 
	 	System.out.println("nroasiento: "+nroasiento); 
	 	
		try{
			 Query q = getSession().createQuery(
					 	"Select a from Asientovendido a JOIN Ruta r "
					 	+ "WHERE a.fechasalida=:fechasalida "
					 	+ "AND r.id=:ruta_id "
					 	+ "AND a.nroasiento=:nroasiento"
					 );
			 
			 	System.out.println("B Fecha Salida: "+fechasalida); 
			 	System.out.println("B rutaId: "+rutaId); 
			 	System.out.println("B nroasiento: "+nroasiento); 
			 	
			 	q.setParameter("fechasalida", fechasalida);
	            q.setParameter("ruta_id", rutaId);
	            q.setParameter("nroasiento", nroasiento);
	            ArrayList<Asientovendido> ep = new ArrayList<Asientovendido>(q.getResultList());
	            for(Asientovendido e: ep){
					// c = e;
	            	System.out.println("Asiento Vendido"+e.getNroasiento());
					vendido = true;
				}
	    } catch (Exception e) {
	        System.out.println(e);
	    }finally{
	      //  q.close();
	    }
		return vendido;
    }
	
	/* Guardar asiento */
}

