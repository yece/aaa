package webviews;

import java.util.ArrayList;
import model.Cooperativa;
import model.Ruta;
import model.Rutadestino;
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
import controllers.Destinoctrl;
import controllers.Rutacontroller;

/*
 * Para administracion de las empresas
 */
public class Destinonew  extends VerticalLayout implements View {
	
    public Destinonew() {
    	
        setSpacing(true);
        setMargin(true);

        Label title = new Label("Agregar Nuevo Destino");
        title.addStyleName("h1");
        addComponent(title);                      
        
        /****************************************************/		
		        HorizontalLayout row = new HorizontalLayout();
		        row.addStyleName("wrapping");
		        row.setSpacing(true);
		        addComponent(row);

		        final int tipousuario = (Integer)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("tipousuario");		        
		        /* Si es usuario root (1=cliente, 2=usuario, 3=root)*/
		        final NativeSelect selectcoop = new NativeSelect("Seleccionar Empresa");
		        final NativeSelect selectruta = new NativeSelect("Seleccionar Ruta");
		        
	            final int idcooperativa = (Integer)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("idcooperativa");		        
		        /*if(tipousuario == 3){	        
			        row.addComponent(selectcoop);		        
			    	Coop objCoop = new Coop();
			        ArrayList<Cooperativa> coopdata = objCoop.getAll(0);
			        for (Cooperativa cooperativa : coopdata) {
			        	selectcoop.addItem(cooperativa.getId()+"");
			        	selectcoop.setItemCaption(cooperativa.getId()+"",cooperativa.getId()+"--"+cooperativa.getNombreComercial());
					}
		        }else{*/
		            ArrayList<Cooperativa> coopdata = new ArrayList<Cooperativa>();
		            Coop objCoop = new Coop();		             
		            coopdata = objCoop.getAll(idcooperativa);
		            for (Cooperativa cooperativa : coopdata) {
		                Label coopname = new Label(cooperativa.getNombreComercial());
		                coopname.addStyleName("h4");
		                addComponent(coopname);		                		            	
		    		}
		       /* }*/
		        
		        
		        ArrayList<Ruta> rutasdata = new ArrayList<Ruta>();
		        Rutacontroller objRutacontroller = new Rutacontroller();
		        
			        row.addComponent(selectruta);
			        ArrayList<Ruta> rutasdata1 = objRutacontroller.getAll(idcooperativa);
			        for (Ruta ruta : rutasdata1) {
			        	selectruta.addItem(ruta.getId()+"");
			        	selectruta.setItemCaption(ruta.getId()+"",ruta.getNombre());
					}		        		        		       
		              
        /****************************************************/
        
        final FormLayout form = new FormLayout();
        form.setMargin(false);
        form.setWidth("800px");
        form.addStyleName("light");
        addComponent(form);

        final TextField destino = new TextField("Destino");
        destino.setValue("");
        destino.setWidth("50%");
        destino.setRequired(true);
        form.addComponent(destino);        
        
        final TextField costo = new TextField("Costo");
        costo.setValue("");
        costo.setWidth("50%");
        costo.setRequired(true);
        form.addComponent(costo);                    
        form.setReadOnly(false);
        
        Button edit = new Button("Crear Destino", new ClickListener() {
        	
            @Override
            public void buttonClick(ClickEvent event) {
                boolean readOnly = form.isReadOnly();
                
                int coopId = 0;
                /*if( tipousuario == 3){
                	coopId = Integer.parseInt( selectcoop.getValue().toString() );
                }else{*/
                	coopId = idcooperativa;                	
                /*}*/

                Rutadestino objDestinodata = new Rutadestino();
                objDestinodata.setDestino(destino.getValue());
                objDestinodata.setCosto(Double.parseDouble(costo.getValue()));
                
                Ruta objRuta = new Ruta();
                
                System.out.println("ID RUTA "+Integer.parseInt(selectruta.getValue().toString()));
                objRuta.setId( Integer.parseInt(selectruta.getValue().toString()) );
                objDestinodata.setIdRuta(objRuta);
                
                Destinoctrl objDestinoctrl = new Destinoctrl();
                objDestinoctrl.crearDestino(objDestinodata);                              
                
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
