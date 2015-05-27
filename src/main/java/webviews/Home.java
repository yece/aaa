/*
 * Copyright 2000-2013 Vaadin Ltd.
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

import model.Cooperativa;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import controllers.Coop;

public class Home extends VerticalLayout implements View {
    public Home() {
        setMargin(true);
        setSpacing(true);

        CssLayout head = new CssLayout();        
        Resource res = new ThemeResource("../tests-valo/img/logoT.png");
        // Display the image without caption
        Image image = new Image(null, res);
        image.setWidth("150px");
        head.addComponent(image);  
        
        Label h1 = new Label("Sistema de Transportes");
        h1.addStyleName("h1");
        h1.setWidth("800px");
        head.addComponent(h1);

        addComponent(head);
        /* Fin cabecera */
       
        HorizontalLayout row = new HorizontalLayout();
        row.addStyleName("wrapping");
        row.setSpacing(true);
        addComponent(row);
        
        ArrayList<Cooperativa> coopdata1 = new ArrayList<Cooperativa>();
        Coop objCoop = new Coop();
        coopdata1 = objCoop.getAll(0);
//        
        Panel panel = new Panel( "HOLA" );
        int j = 0;
        
        for (Cooperativa cooperativa : coopdata1) {        	
            panel = new Panel( cooperativa.getRazonSocial() );
            panel.setIcon(FontAwesome.PAGELINES);
            panel.setWidth("300px");
            panel.setHeight("250px");       

            Link link = new Link("Ver Detalles", new ExternalResource(
                    Page.getCurrent().toString()+"/#!empreinfo/"+cooperativa.getId() )); 
            link.addStyleName("large");
            link.setIcon(FontAwesome.LINK);
            if( j == 0 ){
                panel.addStyleName("color1");
                j = 1;
            }else{
            	panel.addStyleName("color2");
            	j = 0;
            }
            panel.setContent(panelContent(cooperativa.getRazonSocial(), link));
            row.addComponent(panel);
        }
  
    }

    Component panelContent(String emprename, Link link) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        layout.setSpacing(true);
        Label content = new Label( emprename );
        content.setWidth("100%");
        layout.addComponent(content);
        /*Button button = new Button("Button");*/
        /*button.setSizeFull();*/
        layout.addComponent(link);
        return layout;
    }
    
    /*Component panelContent(String htmlContent, Link linkEmpre) {
    	CssLayout layout = new CssLayout();*/
        /*layout.setSizeFull();*/
        /*layout.setMargin(true);
        layout.setSpacing(true);*/
       /* Label content = new Label( htmlContent, ContentMode.HTML );
        content.addStyleName("h1");
        content.setWidth("100%");
        layout.addComponent(content);*/
       /* Button button = new Button("Button");
        button.setSizeFull();*/
       /* layout.addComponent(linkEmpre);
        
        return layout;
    }
*/
    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }

}
