package webviews;

import java.util.Hashtable;
import views.Modalview;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import controllers.Coop;
import controllers.Loginctrl;

/*
 * Para administracion de las empresas
 */
public class Empresa  extends VerticalLayout implements View {
    public Empresa() {
        setSpacing(true);
        setMargin(true);

        Loginctrl objLoginctrl = new Loginctrl();
        int userId = objLoginctrl.getSessionId();  
        
        Label title = new Label();
        final FormLayout form = new FormLayout();        
        
        /* Si el usuario se ha identificado se le permite crear empresas */
        if( userId > 0 ){
        	title = new Label("Administración de Empresas de Transporte");        	
            addComponent(title);        	
            addComponent(form);        	        
        }else{
            title = new Label("No tiene permisos para acceder a este modulo");
            addComponent(title);
        }
        
        title.addStyleName("h1");
        
        form.setMargin(false);
        form.setWidth("800px");
        form.addStyleName("light");


        Label section = new Label("Datos Principales");
        section.addStyleName("h2");
        section.addStyleName("colored");
        form.addComponent(section);
        StringGenerator sg = new StringGenerator();

        final TextField name = new TextField("Nombre Comercial");
        name.setValue("");
        name.setWidth("50%");
        name.setRequired(true);
        form.addComponent(name);
        
        final TextField razon_social = new TextField("Razon Social");
        razon_social.setValue("");
        razon_social.setWidth("50%");
        razon_social.setRequired(true);
        form.addComponent(razon_social);        
        
        section = new Label("informacion de Contacto");
        section.addStyleName("h3");
        section.addStyleName("colored");
        form.addComponent(section);

        final TextField telefonos = new TextField("Telefonos");
        name.setWidth("50%");
        form.addComponent(telefonos);
        
        final TextField email = new TextField("Email");
        email.setValue("user@domain"+ ".com");
        email.setWidth("50%");
        email.setRequired(true);
        form.addComponent(email);

        section = new Label("Informacion Adicional");
        section.addStyleName("h4");
        section.addStyleName("colored");
        form.addComponent(section);  

        final RichTextArea mision = new RichTextArea("Mision");
        mision.setWidth("100%");
        form.addComponent(mision);

        final RichTextArea vision = new RichTextArea("Vision");
        vision.setWidth("100%");
        form.addComponent(vision);
        
        form.setReadOnly(false);
        mision.setReadOnly(false);
        vision.setReadOnly(false);      
        
        Button edit = new Button("Crear Nueva Empresa", new ClickListener() {
        	
            @Override
            public void buttonClick(ClickEvent event) {
            	
               /* Loginctrl objLoginctrl = new Loginctrl();
                objLoginctrl.getSessionVars(); */
                boolean readOnly = form.isReadOnly();
                
                Hashtable<String, String> coop_data = new Hashtable<String, String>();   
                
                coop_data.put("name",name.getValue());
                coop_data.put("razon_social",razon_social.getValue());
                coop_data.put("telefonos",telefonos.getValue());
                coop_data.put("email",email.getValue());                
                coop_data.put("mision",mision.getValue());
                coop_data.put("vision",vision.getValue());
                
                Coop objCoop = new Coop();   
                Modalview objModalview = new Modalview();
                objCoop.crearCooperativa(coop_data);                
                
                String htmlRes = "<h2>Datos Almacenados Correctamente.</h2>";
                Window win = objModalview.openModalView(htmlRes, "350px", "160px");
                getUI().addWindow(win);
                win.focus();
                
                /*event.getButton().setEnabled(false);  */              
                
                    form.setReadOnly(false);
                    mision.setReadOnly(false);
                    vision.setReadOnly(false);
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
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }
    
}
