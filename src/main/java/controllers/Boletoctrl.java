package controllers;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import views.PrintBoleto;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;

import model.Asientovendido;
import model.Boleto;
import model.Ruta;
import model.Rutadestino;

public class Boletoctrl {

	MD5 objMD5 = new MD5();
	Utils objUtils = new Utils();
	
    public EntityManager getSession() {
        return JPAUtil.getSession();
    }	
    
	   public int crearBoleto( Boleto data  ){
		   int res = 0;
		   String asientos = data.getAsientos();
		   
		   try{
			      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("valotheme" );
			      EntityManager entitymanager = emfactory.createEntityManager( );
			      entitymanager.getTransaction( ).begin( );				      
			      
			      /* 
			       * Desde ruta destino obtenemos el id de la ruta, el costo 
			       * y la hora de salida para guardar en boleto
			       */		      
			      Destinoctrl objDestinoctrl = new Destinoctrl();
			      Rutadestino Rutadestino = objDestinoctrl.getRutaDestinoById(data.getRutadestinoId().getId());
			      int rutaId = Rutadestino.getIdRuta().getId();
			      String destino = Rutadestino.getDestino();
			      double costo = Rutadestino.getCosto();
			      
			      Rutacontroller objRutacontroller = new Rutacontroller();
			      Ruta ruta = objRutacontroller.getRutaById(Rutadestino.getIdRuta().getId());
			      Date horaSalida = ruta.getHoraSalida();

			      boolean estaVendido = true;
			      String asientosArray[] = asientos.split(",");			      
			      Asientovendidoctrl objAvctrl = new Asientovendidoctrl();			      
			      
			      
			      for (String nroasiento : asientosArray) {
			    	  Asientovendido objAsientoVendido = new Asientovendido();
			    	  System.out.println("Asiento : "+nroasiento);
			    	  int nasiento = Integer.parseInt(nroasiento);
				      estaVendido = objAvctrl.checkAsientoVendido(data.getFechaSalida(), rutaId, nasiento);
				      if(estaVendido){
				    	  break;
				      }else{
				    	  objAsientoVendido.setFechasalida(data.getFechaSalida());
				    	  objAsientoVendido.setNroasiento(nasiento);
				    	  objAsientoVendido.setRutaId(ruta);
				    	  entitymanager.persist( objAsientoVendido );
				      }
			      }
			      
			      /* Si ninguno de los asientos esta vendido guardamos el boleto */
			      if(!estaVendido){
				      Boleto objNewDest = new Boleto(); 
				      objNewDest.setIdCliente(data.getIdCliente());
				      objNewDest.setRutadestinoId(data.getRutadestinoId());
				      objNewDest.setUserId(data.getUserId());
				      objNewDest.setBusId(data.getBusId());
				      objNewDest.setCosto(data.getCosto());
				      objNewDest.setEstado(data.getEstado());
				      objNewDest.setFechaSalida(data.getFechaSalida());
				      objNewDest.setHoraSalida(horaSalida);
				      objNewDest.setCooperativaId(data.getCooperativaId());			    	  
				      objNewDest.setAsientos(data.getAsientos());
				      
				      DateFormat dateFormat = new SimpleDateFormat("Y-MM-dd");
				      objNewDest.setFecha( objUtils.getCurrentDate() );
				      objNewDest.setHora( objUtils.getCurrentTime() );
				      entitymanager.persist( objNewDest );
				      
				      entitymanager.getTransaction( ).commit( );
				      res = objNewDest.getId();
			      }else{
				      entitymanager.getTransaction( ).rollback();			    	  
			      }
			      			      
			      entitymanager.close( );
			      emfactory.close( );	
			      
		   }catch(Exception e){
	           	Notification notification = new Notification("");
	           	notification.setStyleName("error closable");
	           	notification.setCaption("NUEVO BOLETO");
	           	notification.setDescription("Alguno de los asientos seleccionados no se encuentran disponibles.");
	           	notification.setPosition(Position.TOP_RIGHT);
	           	notification.setDelayMsec(500);
	           	notification.show(Page.getCurrent());  			   
		   }
		   
		      return res;
	   }	   
	      
}

