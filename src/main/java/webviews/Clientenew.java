package webviews;

import java.util.ArrayList;
import model.Cliente;
import model.Cooperativa;
import model.Tiposusuario;
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
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import controllers.Clientectrl;
import controllers.Coop;
import controllers.Tiposusuarioctrl;

/*
 * Para administracion de las empresas
 */
public class Clientenew  extends VerticalLayout implements View {
	
    public Clientenew() {    	
        setSpacing(true);
        setMargin(true);

        Label title = new Label("Agregar Nuevo Cliente/Usuario");
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
	            final int idcooperativa = (Integer)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("idcooperativa");		        
		        if(tipousuario == 3){
			        //selectcoop = new NativeSelect("Seleccionar Empresa");		        
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
		        		        		        
		        /*---------------------------------------*/        
		    	Tiposusuarioctrl objTiposusuarioctrl = new Tiposusuarioctrl();
		        ArrayList<Tiposusuario> tiposusuario = objTiposusuarioctrl.getAll();
		        
		        final NativeSelect select = new NativeSelect("Tipo Cliente/Usuario");		        
		        row.addComponent(select);

		        for (Tiposusuario tu : tiposusuario) {
		        	select.addItem(tu.getId()+"");
		        	select.setItemCaption(tu.getId()+"",tu.getTipo());
				}
		              
        /****************************************************/
        
        final FormLayout form = new FormLayout();
        form.setMargin(false);
        form.setWidth("800px");
        form.addStyleName("light");
        addComponent(form);

        final TextField cedula = new TextField("Cedula");
        cedula.setValue("");
        cedula.setWidth("50%");
        cedula.setRequired(true);
        form.addComponent(cedula);        
        
        final TextField nombres = new TextField("Nombres");
        nombres.setValue("");
        nombres.setWidth("50%");
        nombres.setRequired(true);
        form.addComponent(nombres);

        final TextField apellidos = new TextField("Apellidos");
        apellidos.setValue("");
        apellidos.setWidth("50%");
        apellidos.setRequired(true);
        form.addComponent(apellidos);        

        final TextField email = new TextField("E-mail");
        email.setValue("");
        email.setWidth("50%");
        email.setRequired(true);
        form.addComponent(email);

        final TextField nickname = new TextField("Nickname");
        nickname.setValue("");
        nickname.setWidth("50%");
        nickname.setRequired(true);
        form.addComponent(nickname);        

        final PasswordField clave = new PasswordField("Clave");
        clave.setValue("");
        clave.setWidth("50%");
        clave.setRequired(true);
        form.addComponent(clave);
                    
        form.setReadOnly(false);
        
        Button edit = new Button("Crear Cliente/Usuario", new ClickListener() {
        	
            @Override
            public void buttonClick(ClickEvent event) {
                boolean readOnly = form.isReadOnly();
                
                int coopId = 0;
                if( tipousuario == 3){
                	coopId = Integer.parseInt( selectcoop.getValue().toString() );
                }else{
                	coopId = idcooperativa;                	
                }

                
                Tiposusuario objTipousuario = new Tiposusuario();
                int tipoUsuarioId = Integer.parseInt(select.getValue().toString());
                objTipousuario.setId(tipoUsuarioId);
                
                Cliente objCliente = new Cliente();
                objCliente.setCedula(cedula.getValue());
                objCliente.setNombres(nombres.getValue());
                objCliente.setApellidos(apellidos.getValue());
                objCliente.setNickname(nickname.getValue());
                objCliente.setClave(clave.getValue());
                objCliente.setEmail(email.getValue());
                
                Cooperativa objCooperativa = new Cooperativa();
                objCooperativa.setId(coopId);
                objCliente.setIdCooperativa(objCooperativa);
                objCliente.setTiposusuarioId(objTipousuario);
                
                Clientectrl objClientctrl = new Clientectrl();
                objClientctrl.crearCliente(objCliente);                               
                
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
