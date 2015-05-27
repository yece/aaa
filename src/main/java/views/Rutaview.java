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
import model.Ruta;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.data.util.IndexedContainer;
import webviews.StringGenerator;
import controllers.Rutacontroller;

public class Rutaview {

    public static String CAPTION_PROPERTY = "Nombre";
    public static String DESCRIPTION_PROPERTY = "Hora Salida";
	public static String CAPTION_EMPRESA = "Empresa";    

    public Container generateContainer( int id_cooperativa, final boolean hierarchical) {
                System.out.println("... AQUI ANTES DE INGRESAR A LA LISTA DE RUTAS: " + id_cooperativa);
        final IndexedContainer container = hierarchical ? new HierarchicalContainer()
                : new IndexedContainer();
        final StringGenerator sg = new StringGenerator();
        container.addContainerProperty(CAPTION_PROPERTY, String.class, null);
        container.addContainerProperty(DESCRIPTION_PROPERTY, String.class, null);
        container.addContainerProperty(CAPTION_EMPRESA, String.class, null);        
        
        ArrayList<Ruta> coopdata = new ArrayList<Ruta>();
        Rutacontroller objRutacontroller = new Rutacontroller();
        coopdata = objRutacontroller.getAll(id_cooperativa);
        
        System.out.println("... EL ID DE LA COOPERATIVA ES: " + id_cooperativa);
        
        int j = 0;

        for (Ruta cooperativa : coopdata) {
            final Item item = container.addItem(j);            
            item.getItemProperty(CAPTION_PROPERTY).setValue( cooperativa.getNombre() );
            item.getItemProperty(DESCRIPTION_PROPERTY).setValue( cooperativa.getHoraSalida().toString() );
            item.getItemProperty(CAPTION_EMPRESA).setValue( cooperativa.getCooperativaId().getNombreComercial() );            
            j++;        	
		}
        return container;
    }
    
}
