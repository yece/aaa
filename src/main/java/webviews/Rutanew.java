package webviews;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Cooperativa;
import model.Ruta;
import views.Modalview;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import controllers.Coop;
import controllers.Rutacontroller;

/*
 * Para administracion de las empresas
 */
public class Rutanew  extends VerticalLayout implements View {
	
	
	 public static  NativeSelect crearNativeSelectArrayList(String etiqueta,ArrayList<ArrayList<String>> opciones,boolean habilitar,boolean requerido,boolean focus){
	        NativeSelect sample = new NativeSelect(etiqueta);
	        for (int i = 0; i < opciones.size(); i++) {
	            sample.addItem(opciones.get(i).get(0));
	            System.out.print(opciones.get(i).get(0));
	            sample.setItemCaption(opciones.get(i).get(0),opciones.get(i).get(1));
	        }
	        if(focus==true){
	            sample.focus();
	        }
	        //sample.setValue(3);
	        
	        
	        sample.setNullSelectionAllowed(false);
	        
	        sample.setEnabled(habilitar);
	        sample.setRequired(requerido);
	        sample.setImmediate(true);
	        //=========================================
	        
	        return sample;
	    }	

    public Rutanew() {
        setSpacing(true);
        setMargin(true);

        Label title = new Label("Agregar Nueva Ruta");
        title.addStyleName("h1");
        addComponent(title);
        
        /****************************************************/		
		        HorizontalLayout row = new HorizontalLayout();
		        row.addStyleName("wrapping");
		        row.setSpacing(true);
		        addComponent(row);
		
		        /*final NativeSelect select = new NativeSelect("Seleccionar Empresa");*/
		        
		        final int tipousuario = (Integer)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("tipousuario");		        
		        /* Si es usuario root (1=cliente, 2=usuario, 3=root)*/
		        final NativeSelect selectcoop = new NativeSelect("Seleccionar Empresa");
	            final int idcooperativa = (Integer)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("idcooperativa");		        
		        if(tipousuario == 3){
			        row.addComponent(selectcoop);		        
			    	Coop objCoop = new Coop();
			        ArrayList<Cooperativa> coopdata = objCoop.getAll(0);
			        for (Cooperativa cooperativa : coopdata) {
			        	selectcoop.addItem(cooperativa.getId()+"");
			        	selectcoop.setItemCaption(cooperativa.getId()+"",cooperativa.getId()+"--"+cooperativa.getNombreComercial());
					}		        	
		        }else{
		            ArrayList<Cooperativa> coopdata = new ArrayList<Cooperativa>();
		            Coop objCoop = new Coop();		             
		            coopdata = objCoop.getAll(idcooperativa);
		            for (Cooperativa cooperativa : coopdata) {
		                Label coopname = new Label(cooperativa.getNombreComercial());
		                coopname.addStyleName("h4");
		                addComponent(coopname);		                		            	
		    		}		        	
		        }

        /****************************************************/
        
        final FormLayout form = new FormLayout();
        form.setMargin(false);
        form.setWidth("800px");
        form.addStyleName("light");
        addComponent(form);

        final TextField nombre = new TextField("Nombre Ruta");
        nombre.setValue("");
        nombre.setWidth("50%");
        nombre.setRequired(true);
        form.addComponent(nombre);
        
        final TextField hsalida = new TextField("Hora Salida");
        hsalida.setValue("");
        hsalida.setWidth("50%");
        hsalida.setRequired(true);
        form.addComponent(hsalida);
    
        
        form.setReadOnly(false);
    
        
        Button edit = new Button("Crear Nueva Ruta", new ClickListener() {
        	
            @Override
            public void buttonClick(ClickEvent event) {
                boolean readOnly = form.isReadOnly();
                
                Cooperativa objCooperativa = new Cooperativa();
                
                /*int coopId = Integer.parseInt( select.getValue().toString() );
                objCooperativa.setId( coopId );*/
                
                int coopId = 0;
                if( tipousuario == 3){
                	coopId = Integer.parseInt( selectcoop.getValue().toString() );                	              
                }else{
                	coopId = idcooperativa;                	
                }
                objCooperativa.setId( coopId );                  
                
                Ruta objRuta = new Ruta();
                objRuta.setCooperativaId(objCooperativa);
                objRuta.setNombre(nombre.getValue());
                Time hsalidaTime = null;
                try {
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // 12 hour format
                    Date d1 =(java.util.Date)format.parse(hsalida.getValue());
                    hsalidaTime = new java.sql.Time(d1.getTime());
                } catch(Exception e) {
                    System.out.println("Error al transformar a horas");
                }
                
                objRuta.setHoraSalida(hsalidaTime);
                
                Rutacontroller objRutacontroller = new Rutacontroller();
                objRutacontroller.crearRuta(objRuta);

                Modalview objModalview = new Modalview();             
                
                String htmlRes = "<h2>Datos Almacenados Correctamente.</h2>";
                Window win = objModalview.openModalView(htmlRes, "350px", "160px");
                getUI().addWindow(win);
                win.focus();
               
                /*event.getButton().setEnabled(false);  */              
                
                    form.setReadOnly(false);
                    form.addStyleName("light");
                    event.getButton().removeStyleName("primary");                
            }
        });

        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        
        form.addComponent(footer);
        footer.addComponent(edit);
        Label lastModified = new Label("Last modified by you a minute ago");
        lastModified.addStyleName("light");
        addComponent(lastModified);
        //================================        
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }
    
}
