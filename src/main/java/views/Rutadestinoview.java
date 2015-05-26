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
import model.Rutadestino;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.data.util.IndexedContainer;
import webviews.StringGenerator;
import webviews.TestIcon;
import controllers.Rutadestinoctrl;
import controllers.Utils;

public class Rutadestinoview {

    public static String CAPTION_PROPERTY = "Ruta";
    public static String DESCRIPTION_PROPERTY = "Destino";
	public static String CAPTION_COSTO = "Costo";

    public Container generateContainer( int id_cooperativa, final boolean hierarchical) {
        final TestIcon testIcon = new TestIcon(90);
        final IndexedContainer container = hierarchical ? new HierarchicalContainer()
                : new IndexedContainer();
        final StringGenerator sg = new StringGenerator();
        container.addContainerProperty(CAPTION_PROPERTY, String.class, null);
        container.addContainerProperty(DESCRIPTION_PROPERTY, String.class, null);
        container.addContainerProperty(CAPTION_COSTO, String.class, null);        
        
        ArrayList<Rutadestino> coopdata = new ArrayList<Rutadestino>();
        Rutadestinoctrl objRutacontroller = new Rutadestinoctrl();
        coopdata = objRutacontroller.getRutaDestino(id_cooperativa);
        
        int j = 0;

        Utils objUtils = new Utils();
        /*System.out.println("HOLA: "+coopdata.get(0).getNombreComercial());*/
        for (Rutadestino ruta : coopdata) {
            final Item item = container.addItem(j);            
            item.getItemProperty(CAPTION_PROPERTY).setValue( ruta.getIdRuta().getNombre() );
            item.getItemProperty(DESCRIPTION_PROPERTY).setValue( ruta.getDestino() );
            
            String costo = objUtils.redondear(ruta.getCosto());
            /*Double costod = Double.parseDouble(costo);*/
            item.getItemProperty(CAPTION_COSTO).setValue( costo );            
            j++;        	
		}
        return container;
    }
    
}
