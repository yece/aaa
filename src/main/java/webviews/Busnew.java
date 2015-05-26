package webviews;

import java.util.ArrayList;
import model.Bus;
import model.Cooperativa;
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
import controllers.Busctrl;
import controllers.Coop;

/*
 * Para administracion de las empresas
 */
public class Busnew  extends VerticalLayout implements View {
	
    public Busnew() {
    	
        setSpacing(true);
        setMargin(true);

        Label title = new Label("Agregar Nuevo Bus");
        title.addStyleName("h1");
        addComponent(title);                      
        
        /****************************************************/		
		        HorizontalLayout row = new HorizontalLayout();
		        row.addStyleName("wrapping");
		        row.setSpacing(true);
		        addComponent(row);
		        
		        /* Si es usuario root (1=cliente, 2=usuario, 3=root)*/
		        final NativeSelect selectcoop = new NativeSelect("Seleccionar Empresa");
		        
	            final int idcooperativa = (Integer)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("idcooperativa");		        
		            ArrayList<Cooperativa> coopdata = new ArrayList<Cooperativa>();
		            Coop objCoop = new Coop();		             
		            coopdata = objCoop.getAll(idcooperativa);
		            for (Cooperativa cooperativa : coopdata) {
		                Label coopname = new Label(cooperativa.getNombreComercial());
		                coopname.addStyleName("h4");
		                addComponent(coopname);		                		            	
		    		}		        		        		        		        		      
		              
        /****************************************************/
        
        final FormLayout form = new FormLayout();
        form.setMargin(false);
        form.setWidth("800px");
        form.addStyleName("light");
        addComponent(form);

        final TextField nroBus = new TextField("Nro. Bus");
        nroBus.setValue("");
        nroBus.setWidth("50%");
        nroBus.setRequired(true);
        form.addComponent(nroBus);        
        
        final TextField nroAsientos = new TextField("Nro. Asientos");
        nroAsientos.setValue("");
        nroAsientos.setWidth("50%");
        nroAsientos.setRequired(true);
        form.addComponent(nroAsientos);                    
        form.setReadOnly(false);
        
        final TextField placa = new TextField("Placa");
        placa.setValue("");
        placa.setWidth("50%");
        placa.setRequired(true);
        form.addComponent(placa);                    
        form.setReadOnly(false); 
        
        final TextField nombreDuenio = new TextField("Nombre del Dueño");
        nombreDuenio.setValue("");
        nombreDuenio.setWidth("50%");
        nombreDuenio.setRequired(true);
        form.addComponent(nombreDuenio);                    
        form.setReadOnly(false);           
        
        Button edit = new Button("Crear Destino", new ClickListener() {        	
            @Override
            public void buttonClick(ClickEvent event) {
                boolean readOnly = form.isReadOnly();
                
                int coopId = idcooperativa;
                Cooperativa objCooperativa = new Cooperativa();
                objCooperativa.setId(coopId);
                
                Bus objBus = new Bus();
                objBus.setAsientos( Integer.parseInt(nroAsientos.getValue()) );
                objBus.setNumero(nroBus.getValue());
                objBus.setNombresDuenio(nombreDuenio.getValue());
                objBus.setIdCooperativa(objCooperativa);
                objBus.setPlaca(placa.getValue());                              
                
                Busctrl objBusctrl = new Busctrl();
                objBusctrl.crearBus(objBus);                              
                
                /*Coop objCoop = new Coop();   */
                Modalview objModalview = new Modalview();
                /*objCoop.crearCooperativa(coop_data);   */             
                
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
