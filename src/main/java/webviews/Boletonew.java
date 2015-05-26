package webviews;

import java.util.ArrayList;
import java.util.Date;
import model.Boleto;
import model.Bus;
import model.Cliente;
import model.Cooperativa;
import model.Rutadestino;
import views.PrintBoleto;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import controllers.Boletoctrl;
import controllers.Busctrl;
import controllers.Clientectrl;
import controllers.Coop;
import controllers.Loginctrl;
import controllers.Rutadestinoctrl;
import controllers.Utils;

/*
 * Para administracion de las empresas
 */
public class Boletonew  extends VerticalLayout implements View {
	
	Utils objUtils = new Utils();
    Loginctrl objLoginctrl = new Loginctrl();
    int userId = objLoginctrl.getSessionId();	
    private final static TextField costo = new TextField("Precio por boleto");
    
    public Boletonew() {    	
        setSpacing(true);
        setMargin(true);

        HorizontalLayout head = new HorizontalLayout();    
        //head.addStyleName("wrapping");        
        Resource res = new ThemeResource("../tests-valo/img/logoT.png");
        // Display the image without caption
        Image image = new Image(null, res);
        image.setWidth("150px");
        head.addComponent(image);
        
        Label title = new Label("Nueva Reserva/Boleto");
        title.addStyleName("h1");
        head.addComponent(title);
        
        addComponent(head);
        /****************************************************/		
		        HorizontalLayout row = new HorizontalLayout();
		        addComponent(row);
		        
		        /* Si es usuario root (1=cliente, 2=usuario, 3=root)*/
		        
	            final int idcooperativa = (Integer)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("idcooperativa");		        
		            ArrayList<Cooperativa> coopdata = new ArrayList<Cooperativa>();
		            Coop objCoop = new Coop();		             
		            coopdata = objCoop.getAll(idcooperativa);
		            for (Cooperativa cooperativa : coopdata) {
		                Label coopname = new Label(cooperativa.getNombreComercial());
		                coopname.addStyleName("h3");
		                addComponent(coopname);		                		            	
		    		}		        		        		        		        		      
		              
        /**********************************************************************/		        
				    Clientectrl objClientectrl = new Clientectrl();
				    ArrayList<Cliente> clientedata = objClientectrl.getAll(idcooperativa);
				        		            		            
		            final ComboBox combocliente = new ComboBox("Cliente");
		            combocliente.setWidth("400px");
		            combocliente.setInputPrompt("Cliente");
		            for (Cliente client : clientedata) {
		            	combocliente.addItem(client.getId()+"");
		            	combocliente.setItemCaption(client.getId()+"",client.getNombres()+" "+client.getApellidos());
					}
		            /*row.addComponent(combocliente);	     */     

		            /** Combo rutas */
				    Rutadestinoctrl objRutadestinoctrl = new Rutadestinoctrl();
				    ArrayList<Rutadestino> rutadestinodata = objRutadestinoctrl.getRutaDestino(idcooperativa);				        		            		            
		            final ComboBox comborutadestino = new ComboBox("Ruta/Destino");
		            comborutadestino.setInputPrompt("Ruta/Destino");
		            comborutadestino.setWidth("400px");
		            for (Rutadestino rd : rutadestinodata) {
		            	comborutadestino.addItem(rd.getId()+"");
		            	comborutadestino.setItemCaption(rd.getId()+"",rd.getIdRuta().getNombre()+" - "+rd.getIdRuta().getHoraSalida()+" - $ "+rd.getCosto()+" "+rd.getDestino());
					}
		           /* row.addComponent(comborutadestino);		*/	            
		            
		            /** Combo Buses */
				    Busctrl objBusctrl = new Busctrl();
				    ArrayList<Bus> busdata = objBusctrl.getBuses(idcooperativa);				        		            		            
		            final ComboBox combobus = new ComboBox("Seleccionar Bus");
		            combobus.setInputPrompt("Seleccionar Bus");
		            combobus.setWidth("200px");
		            for (Bus b : busdata) {
		            	combobus.addItem(b.getId()+"");
		            	combobus.setItemCaption(b.getId()+"", "Bus # " + b.getNumero());
					}
		            /*row.addComponent(combobus);	*/		            
		            
        /**********************************************************************/
        final FormLayout form = new FormLayout();
        form.setMargin(false);
        form.setWidth("800px");
        row.addStyleName("wrapping");
        addComponent(form); 
        
        form.addComponent(combocliente);
        form.addComponent(comborutadestino);
        form.addComponent(combobus);
        
        final DateField fechaSalida = new DateField("Fecha Salida");
        fechaSalida.setDateFormat("yyyy-MM-dd");
        fechaSalida.setRequired(true);   
        fechaSalida.setWidth("200px");
        Date currdate = objUtils.getCurrentDate();
        fechaSalida.setValue(currdate);                
        form.addComponent(fechaSalida);        
        
        
        final TextField nroAsiento = new TextField("Nro. Asiento");
        nroAsiento.setValue("");
        nroAsiento.setWidth("200px");
        nroAsiento.setRequired(true);
        form.addComponent(nroAsiento);

        costo.setValue("");
        costo.setWidth("200px");
        costo.setRequired(true);
        form.addComponent(costo);        
        
        final OptionGroup estado = new OptionGroup("Tipo:");
        estado.addStyleName("horizontal");
        estado.addItem(1);
        estado.setItemCaption(1, "Nuevo Boleto");
        estado.addItem(2);
        estado.setItemCaption(2, "Nueva Reserva");        
        estado.select(1);
        form.addComponent(estado);
        
        setPriceBoleto(comborutadestino);
        
        /*BrowserWindowOpener opener =
                new BrowserWindowOpener(ValoThemeUI.class);
        opener.setFeatures("height=200,width=400,resizable");*/
                    
        // A button to open the printer-friendly page.
        /*Button print = new Button("Click to Print");
        opener.extend(print);
        addComponent(print);*/
        
        Button edit = new Button("Guardar", new ClickListener() {        	
            @Override
            public void buttonClick(ClickEvent event) {
            	Boletoctrl objBoletoctrl = new Boletoctrl();
                Boleto objBoleto = new Boleto();
                
                Cliente cliente1 = new Cliente();
                cliente1.setId( Integer.parseInt(combocliente.getValue().toString()) );
                objBoleto.setIdCliente(cliente1);
                
                Rutadestino rutadestino = new Rutadestino();
                rutadestino.setId(Integer.parseInt(comborutadestino.getValue().toString()));
                objBoleto.setRutadestinoId(rutadestino);
                
                Cliente cliente2 = new Cliente();
                cliente2.setId( userId );
                objBoleto.setIdCliente(cliente2);
                
                Bus bus = new Bus();
                bus.setId( Integer.parseInt(combobus.getValue().toString()) );
                objBoleto.setBusId(bus);
                
                objBoleto.setCosto(Double.parseDouble(costo.getValue().toString()));
                objBoleto.setEstado( Integer.parseInt(estado.getValue().toString()) );
                objBoleto.setFechaSalida(fechaSalida.getValue());
                
                int coopId = idcooperativa;
                Cooperativa objCooperativa = new Cooperativa();
                objCooperativa.setId(coopId);               
                objBoleto.setCooperativaId(objCooperativa);
                
                
                objBoleto.setAsientos(nroAsiento.getValue());
                int ok = 0;    
                
                	ok = objBoletoctrl.crearBoleto(objBoleto);                     
                	
                if(ok > 0){
                	Notification notification = new Notification("");
                	notification.setStyleName("success closable");
                	notification.setCaption("NUEVO BOLETO");
                	notification.setDescription("Datos Almacenados Correctamente");
                	notification.setPosition(Position.TOP_RIGHT);
                	notification.setDelayMsec(500);
                    notification.show(Page.getCurrent());  
                    
                   // PrintBoleto objPrintB = new PrintBoleto();
                    // Create an opener extension
                    BrowserWindowOpener opener =
                            new BrowserWindowOpener(PrintBoleto.class);
                    opener.setFeatures("height=200,width=400,resizable");
                    opener.setParameter("boletoid", ok+"");
                    // A button to open the printer-friendly page.
                    Button print = new Button("Imprimir Boleto Nro. "+ok);
                    form.addComponent(print);
                    opener.extend(print);
                    
                }else{
                	Notification notification = new Notification("");
                	notification.setStyleName("warning closable");
                	notification.setCaption("NUEVO BOLETO");
                	notification.setDescription("Alguno de los asientos seleccionados no se encuentran disponibles.");
                	notification.setPosition(Position.TOP_RIGHT);
                	notification.setDelayMsec(500);
                    notification.show(Page.getCurrent());  
                }
            }
        });
        form.addComponent(edit); 

        //================================        
    }


	 public static  void setPriceBoleto(final ComboBox sample){
		    sample.addValueChangeListener(new ValueChangeListener() {		       
		        @Override
		        public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
		        	
		            /*final String valueString = String.valueOf(event.getProperty()
		                    .getValue());*/
		            Rutadestinoctrl objRdc = new Rutadestinoctrl();
		            Rutadestino objRd = new Rutadestino();
		            objRd = objRdc.getRutaDestinoById(Integer.parseInt(sample.getValue().toString()));
		            
		            costo.setValue(objRd.getCosto()+"");
		            //Notification.show(objRd.getCosto()+"");
		        }
		    });
	 }    
    
    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }
    
}
