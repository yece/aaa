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
import views.Rutaview;
import views.Tablesview;
import com.vaadin.data.Container;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class Rutasreport extends VerticalLayout implements View {

	Rutaview objRutaview = new Rutaview();
	
    final Container normalContainer = objRutaview.generateContainer(0,false);
    final Container hierarchicalContainer = objRutaview.generateContainer(0,true);

   /* CheckBox footer = new CheckBox("Footer", true);*/

    public Rutasreport() {
        setMargin(true);
        setSpacing(true);

        Label h1 = new Label("Lista de Rutas Creadas");
        h1.addStyleName("h1");
        addComponent(h1);
        
    	Table table = printHtmlTable();    
    	addComponent(table);
    }
    
    public Table printHtmlTable(){
        Table table = null;
                if (table == null) {
                    table = new Table();
                    table.setContainerDataSource(normalContainer);                    
                }                

                Tablesview objTablesview = new Tablesview();                
                table.addActionHandler(this.getActionHandler());                
                objTablesview.configure(table, true, "100%", "400px",
                        true, true, true, true, true, false, false, true);
          return table;
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

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
