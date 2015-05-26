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
import model.Cooperativa;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.data.util.IndexedContainer;
import webviews.StringGenerator;
import webviews.TestIcon;
import controllers.Coop;

public class Cooperativaview {

    public static String CAPTION_PROPERTY = "Nombre Comercial";
    public static String DESCRIPTION_PROPERTY = "Razon Social";
    public static String ICON_PROPERTY = "icon";
    public static String INDEX_PROPERTY = "index";    

    public Container generateContainer( final boolean hierarchical) {
        final TestIcon testIcon = new TestIcon(90);
        final IndexedContainer container = hierarchical ? new HierarchicalContainer()
                : new IndexedContainer();
        final StringGenerator sg = new StringGenerator();
        container.addContainerProperty(CAPTION_PROPERTY, String.class, null);
        container.addContainerProperty(ICON_PROPERTY, String.class, null);
        container.addContainerProperty(INDEX_PROPERTY, String.class, null);
        container.addContainerProperty(DESCRIPTION_PROPERTY, String.class, null);
        
        ArrayList<Cooperativa> coopdata = new ArrayList<Cooperativa>();
        Coop objCoop = new Coop();
        coopdata = objCoop.getAll(0);
        
        int j = 1;

        /*System.out.println("HOLA: "+coopdata.get(0).getNombreComercial());*/
        for (Cooperativa cooperativa : coopdata) {
            final Item item = container.addItem(j);
            item.getItemProperty(CAPTION_PROPERTY).setValue( cooperativa.getNombreComercial() );
            item.getItemProperty(ICON_PROPERTY).setValue( cooperativa.getRazonSocial() );            
            item.getItemProperty(INDEX_PROPERTY).setValue( cooperativa.getTelefonos() );
            item.getItemProperty(DESCRIPTION_PROPERTY).setValue( cooperativa.getEmail() );            
            j++;        	
		}
        return container;
    }
    
}
