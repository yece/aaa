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

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.shared.ui.label.ContentMode;
import webviews.TestIcon;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;

public class Modalview {
    
    public Window openModalView(String htmlContent, String width, String height) {
    	
        Window win = new Window("Nueva Empresa");                    
        win.setWidth(width);
        win.setHeight(height);
        win.setClosable(true);
        win.setResizable(true);                
        
	        VerticalLayout root = new VerticalLayout();
	        Component content = null;
	
	        content = new Label(
	        		htmlContent,
	                ContentMode.HTML);
	        root.setMargin(true);        
	        root.addComponent(content);        
	        win.setContent(root);	
	        
            win.setCloseShortcut(KeyCode.ESCAPE, null);                
            win.center();
        return win;
    }   
    
    public static TabSheet getTabSheet(String htmlcontent,boolean caption, String style,
            boolean closable, boolean scrolling, boolean icon, boolean disable) {
        TestIcon testIcon = new TestIcon(60);

        TabSheet ts = new TabSheet();
        ts.addStyleName(style);
       /* StringGenerator sg = new StringGenerator();*/

        /*for (int i = 1; i <= (scrolling ? 10 : 3); i++) {*/
            String tabcaption = "Nueva Empresa";
            VerticalLayout content = new VerticalLayout();
            content.setMargin(true);
            content.setSpacing(true);
            content.addComponent(new Label("Content for tab " ));
            Tab t = ts.addTab(content, tabcaption);
            t.setClosable(closable);
            t.setEnabled(!disable);


            if (icon) {
                t.setIcon(testIcon.get(false));
            }
        /*}*/

        ts.addSelectedTabChangeListener(new SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(SelectedTabChangeEvent event) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        return ts;
    }    
    
}
