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
package views;

import java.util.ArrayList;
import model.Cliente;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.data.util.IndexedContainer;
import webviews.StringGenerator;
import webviews.TestIcon;
import controllers.Clientectrl;

public class Clienteview {

    public static String CEDULA = "Cedula";
    public static String NOMBRES = "Nombres";
	public static String APELLIDOS = "Apellidos";    
	public static String EMAIL = "Email";
	public static String USUARIO = "Usuario";
	
    public Container generateContainer( int id_cooperativa, final boolean hierarchical) {
        final TestIcon testIcon = new TestIcon(90);
        final IndexedContainer container = hierarchical ? new HierarchicalContainer()
                : new IndexedContainer();
        final StringGenerator sg = new StringGenerator();
        container.addContainerProperty(CEDULA, String.class, null);
        container.addContainerProperty(NOMBRES, String.class, null);
        container.addContainerProperty(APELLIDOS, String.class, null);
        container.addContainerProperty(EMAIL, String.class, null);  
        container.addContainerProperty(USUARIO, String.class, null);          
        
        ArrayList<Cliente> data = new ArrayList<Cliente>();
        Clientectrl objClientectrl = new Clientectrl();
        data = objClientectrl.getAll(id_cooperativa);
        
        int j = 0;

        /*System.out.println("HOLA: "+coopdata.get(0).getNombreComercial());*/
        for (Cliente client : data) {
            final Item item = container.addItem(j);            
            item.getItemProperty(CEDULA).setValue( client.getCedula() );
            item.getItemProperty(NOMBRES).setValue( client.getNombres() );
            item.getItemProperty(APELLIDOS).setValue( client.getApellidos() );            
            item.getItemProperty(EMAIL).setValue( client.getEmail() );
            item.getItemProperty(USUARIO).setValue( client.getNickname() );
            j++;        	
		}
        return container;
    }
    
}
