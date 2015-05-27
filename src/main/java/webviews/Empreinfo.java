/*
 * Copyright 2000-2014 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package webviews;

import java.util.ArrayList;
import model.Cliente;
import model.Cooperativa;
import views.Modalview;
import views.Rutaview;
import views.Tablesview;
import com.vaadin.data.Container;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import controllers.Coop;
import controllers.Loginctrl;

//@PreserveOnRefresh
public class Empreinfo extends VerticalLayout implements View {
	Rutaview objRutaview = new Rutaview();
	//SettingReadingSessionAttributesUI objsess = new SettingReadingSessionAttributesUI();
    final String f = Page.getCurrent().getUriFragment();          
    String uriParams[] = f.split("/");
    int empreid = Integer.parseInt( uriParams[1] );
    Loginctrl objLoginctrl = new Loginctrl();
    int userId = objLoginctrl.getSessionId();	
    
    final Container normalContainer = objRutaview.generateContainer(empreid,false);
    final Container hierarchicalContainer = objRutaview.generateContainer(empreid,true);
    
    public Empreinfo() {
    	/*VaadinSession.getCurrent().getSession();*/
        //Integer clientId = (Integer)UI.getCurrent().getSession().getAttribute("id");     
        Integer clientId = (Integer)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("id");
        setMargin(true);
        setSpacing(true);
                
        TestIcon testIcon = new TestIcon(60);
        ArrayList<Cooperativa> coopdata = new ArrayList<Cooperativa>();
        Coop objCoop = new Coop();
        coopdata = objCoop.getAll(empreid);           
        
        CssLayout head = new CssLayout();
        head.addStyleName("wrapping");        
        Resource res = new ThemeResource("../tests-valo/img/logoT.png");
        // Display the image without caption
        Image image = new Image(null, res);
        image.setWidth("150px");
        head.addComponent(image);
        
        Label h1 = new Label(coopdata.get(0).getNombreComercial());
        h1.addStyleName("h1");
        h1.setWidth("800px");
        head.addComponent(h1);

        addComponent(head);
        /* Fin cabecera */        
        
        CssLayout layout = new CssLayout();
        layout.setWidth("100%"); // Causes to wrap the contents      
        layout.addStyleName("wrapping");
        /*Label h1 = new Label(coopdata.get(0).getNombreComercial());
        h1.addStyleName("h1");
        addComponent(h1); */
        
        /* Reporte de rutas*/	   
	    	Table table = printHtmlTable("100%","250px");    
	    	addComponent(table);
	    	
	        Panel panelrutas = new Panel( "HORARIOS DE SALIDA" );
	        panelrutas.addStyleName("color1");                    
	        panelrutas.setIcon(testIcon.get());
	        panelrutas.addStyleName("color3");        
	        panelrutas.setContent(table); 
	        if( userId > 0 ){
	        	panelrutas.setWidth("100%"); 
	        }else{
		        panelrutas.setWidth("70%");
	        }
 /* Fin reporte rutas */            
	        
            layout.addComponent(panelrutas);
            
            /* Se presenta el formulario de login en caso q el usuario no este identificado */
            if( userId <= 0 ){
    	        //FormLayout form = new FormLayout();
            	FormLayout form = getLoginForm();
    	        Panel panellogin = new Panel( "LOGIN" );
    	        panellogin.addStyleName("wrapping");
    	        panellogin.addStyleName("color1");                    
    	        panellogin.setIcon(testIcon.get());    
    	        panellogin.setContent(form);
    	        panellogin.setWidth("30%");
                layout.addComponent(panellogin);
            }
            
            addComponent(layout);     
          
/***********************************************************************/
       String strmision = null;
       String strvision = null;            
       Panel panel = new Panel( "MISION" );
       Panel panelvision = new Panel( "VISION" );       
       if(coopdata.size() > 0){
           for (Cooperativa cooperativa : coopdata) {
               panel.setIcon(testIcon.get());
               panel.addStyleName("color1");               
               panelvision.setIcon(testIcon.get());
               panelvision.addStyleName("color1");
               
               panel.setContent(panelContent(strmision, "50%"));
               panelvision.setContent(panelContent(strvision, "50%"));
               
	   	        HorizontalSplitPanel sp2 = new HorizontalSplitPanel();
	   	        sp2.setCaption("MISION Y VISION");
	   	        sp2.setWidth("100%");
	   	        sp2.setHeight("300px");
	   	        sp2.addStyleName("large");
	   	        sp2.setFirstComponent( panel );
	   	        sp2.setSecondComponent( panelvision );
	   	        addComponent(sp2);            
           }    	   
       }else{
           Label msgLabel = new Label("No hay rutas para la empresa seleccionada");
           addComponent(msgLabel);
       }
    }
    
    
    VerticalLayout getContent() {
        return new VerticalLayout() {
            {
                setMargin(true);
                addComponent(new Label(
                        "Fictum,  deserunt mollit anim laborum astutumque!"));
            }
        };
    }    
    
    private FormLayout getLoginForm(){
        final FormLayout form = new FormLayout();
        form.setMargin(true);
        //form.setWidth("800px");

        addComponent(form); 
        //addComponent(form);

        final TextField usuario = new TextField("Usuario");
        usuario.setValue("");
        usuario.setWidth("100%");
        usuario.setRequired(true);
        form.addComponent(usuario);   
                
        final PasswordField clave = new PasswordField("Clave");
        clave.setValue("");
        clave.setWidth("100%");
        clave.setRequired(true);
        form.addComponent(clave); 
        
        Button loginbtn = new Button("LOGIN", new ClickListener() {        	
            @Override
            public void buttonClick(ClickEvent event) {
                boolean readOnly = form.isReadOnly();                  
                
                
                ArrayList<Cliente> data = new ArrayList<Cliente>();
                Loginctrl objLoginctrl = new Loginctrl();
                data = objLoginctrl.identificar(empreid, usuario.getValue(), clave.getValue());
                
                int j = 0;
                /*System.out.println("HOLA: "+coopdata.get(0).getNombreComercial());*/
                for (Cliente client : data) {
                	VaadinService.getCurrentRequest().getWrappedSession().setAttribute("id", client.getId());
                	VaadinService.getCurrentRequest().getWrappedSession().setAttribute("nombres", client.getNombres());
                	VaadinService.getCurrentRequest().getWrappedSession().setAttribute("apellidos", client.getApellidos());
                	VaadinService.getCurrentRequest().getWrappedSession().setAttribute("nickname", client.getNickname());
                	VaadinService.getCurrentRequest().getWrappedSession().setAttribute("tipousuario", client.getTiposusuarioId().getId());
                	VaadinService.getCurrentRequest().getWrappedSession().setAttribute("idcooperativa", client.getIdCooperativa().getId());
                	
                    Integer clientId = (Integer)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("id");
                    System.out.println("NUEVO ID CLIENT: "+clientId);                    
                    j++;        	
                }
                
                 getUI().getPage().open(Page.getCurrent().toString(), "");
                 objLoginctrl.getSessionVars();
                
                /*Coop objCoop = new Coop();   */
                Modalview objModalview = new Modalview();
                /*objCoop.crearCooperativa(coop_data);   */             
                
                String htmlRes = "<h2>Datos Almacenados Correctamente.</h2>";
                Window win = objModalview.openModalView(htmlRes, "350px", "160px");
                getUI().addWindow(win);
                win.focus();
            }
        });
        form.addComponent(loginbtn);        
        
        return form;
    }
    
    public Table printHtmlTable(String width, String height){	
    	
        Table table = null;
                if (table == null) {
                    table = new Table();
                    table.setContainerDataSource(normalContainer);
                    
                }                

                Tablesview objTablesview = new Tablesview();
                
                table.addActionHandler(this.getActionHandler());                
                objTablesview.configure(table, true, width, height,
                        true, true, true, true, true, false, false, true);
          return table;
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }
    
    Component panelContent(String htmlContent, String width) {
    	HorizontalLayout layout = new HorizontalLayout();
       /* layout.setSizeFull();*/
        layout.setMargin(true);
        layout.setSpacing(true);
        Label content = new Label( htmlContent, ContentMode.HTML );
        /*content.addStyleName("h1");*/
        content.setWidth(width);
        layout.addComponent(content);
       /* Button button = new Button("Button");
        button.setSizeFull();*/
        
        return layout;
    }    
    
    static Handler actionHandler = new Handler() {
        private final Action ACTION_ONE = new Action("HOLA CHECHI");
        private final Action ACTION_TWO = new Action("Action Two");
        private final Action ACTION_THREE = new Action("Action Three");
        private final Action[] ACTIONS = new Action[] { ACTION_ONE, ACTION_TWO,
                ACTION_THREE };

        @Override
        public void handleAction(final Action action, final Object sender,
                final Object target) {
            Notification.show(action.getCaption());
        }

        @Override
        public Action[] getActions(final Object target, final Object sender) {
            return ACTIONS;
        }
    };
    
    static Handler getActionHandler() {
        return actionHandler;
    }    

}
