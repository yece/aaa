
package controllers;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

//Remove comment to preserve UI value when reloading
//@PreserveOnRefresh
public class InterfaceUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

    }

    public void redirect() {
    	// Redirect from the page
        getPage().setLocation("/home");
    }


}