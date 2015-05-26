/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * @author ceci
 */
public class Utils {

   
	  public String redondear( double numero ) {
		    DecimalFormat df = new DecimalFormat("#.00");
		    String angleFormated = df.format(numero);
		    System.out.println(angleFormated); //output 20.30
		   /*double newnum = Double.parseDouble(angleFormated);*/
		    return angleFormated;
	  }
	  
	  public Date getCurrentDate(){
		  DateFormat dateFormat = new SimpleDateFormat("Y-MM-dd");
		  
		  /*Calendar date = Calendar.getInstance();
	       System.out.println(dateFormat.format(date.getTime()));*/
		  
		  Date date = new Date();
		  System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
		  return date;
	  }
	  
	  public Time getCurrentTime(){
		  DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		  Time date = new Time(0);
		  System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
		  return date;
	  }
	  
	  
	    Panel notifications() {
	        Panel p = new Panel("Notifications");
	        VerticalLayout content = new VerticalLayout() {
	            Notification notification = new Notification("");
	            TextField title = new TextField("Title");
	            TextArea description = new TextArea("Description");
	            MenuBar style = new MenuBar();
	            MenuBar type = new MenuBar();
	            String typeString = "";
	            String styleString = "";
	            TextField delay = new TextField();
	            {
	                setSpacing(true);
	                setMargin(true);

	                title.setInputPrompt("Title for the notification");
	                title.addValueChangeListener(new ValueChangeListener() {
	                    @Override
	                    public void valueChange(final ValueChangeEvent event) {
	                        if (title.getValue() == null
	                                || title.getValue().length() == 0) {
	                            notification.setCaption(null);
	                        } else {
	                            notification.setCaption(title.getValue());
	                        }
	                    }
	                });
	                title.setValue("Notification Title");
	                title.setWidth("100%");
	                addComponent(title);

	                description.setInputPrompt("Description for the notification");
	                description.addStyleName("small");
	                description.addValueChangeListener(new ValueChangeListener() {
	                    @Override
	                    public void valueChange(final ValueChangeEvent event) {
	                        if (description.getValue() == null
	                                || description.getValue().length() == 0) {
	                            notification.setDescription(null);
	                        } else {
	                            notification.setDescription(description.getValue());
	                        }
	                    }
	                });
	                description
	                        .setValue("A more informative message about what has happened. Nihil hic munitissimus habendi senatus locus, nihil horum? Inmensae subtilitatis, obscuris et malesuada fames. Hi omnes lingua, institutis, legibus inter se differunt.");
	                description.setWidth("100%");
	                addComponent(description);

	                Command typeCommand = new Command() {
	                    @Override
	                    public void menuSelected(final MenuItem selectedItem) {
	                        if (selectedItem.getText().equals("Humanized")) {
	                            typeString = "";
	                            notification.setStyleName(styleString.trim());
	                        } else {
	                            typeString = selectedItem.getText().toLowerCase();
	                            notification
	                                    .setStyleName((typeString + " " + styleString
	                                            .trim()).trim());
	                        }
	                        for (MenuItem item : type.getItems()) {
	                            item.setChecked(false);
	                        }
	                        selectedItem.setChecked(true);
	                    }
	                };

	                type.setCaption("Type");
	                MenuItem humanized = type.addItem("Humanized", typeCommand);
	                humanized.setCheckable(true);
	                humanized.setChecked(true);
	                type.addItem("Tray", typeCommand).setCheckable(true);
	                type.addItem("Warning", typeCommand).setCheckable(true);
	                type.addItem("Error", typeCommand).setCheckable(true);
	                type.addItem("System", typeCommand).setCheckable(true);
	                addComponent(type);
	                type.addStyleName("small");

	                Command styleCommand = new Command() {
	                    @Override
	                    public void menuSelected(final MenuItem selectedItem) {
	                        styleString = "";
	                        for (MenuItem item : style.getItems()) {
	                            if (item.isChecked()) {
	                                styleString += " "
	                                        + item.getText().toLowerCase();
	                            }
	                        }
	                        if (styleString.trim().length() > 0) {
	                            notification
	                                    .setStyleName((typeString + " " + styleString
	                                            .trim()).trim());
	                        } else if (typeString.length() > 0) {
	                            notification.setStyleName(typeString.trim());
	                        } else {
	                            notification.setStyleName(null);
	                        }
	                    }
	                };

	                style.setCaption("Additional style");
	                style.addItem("Dark", styleCommand).setCheckable(true);
	                style.addItem("Success", styleCommand).setCheckable(true);
	                style.addItem("Failure", styleCommand).setCheckable(true);
	                style.addItem("Bar", styleCommand).setCheckable(true);
	                style.addItem("Small", styleCommand).setCheckable(true);
	                style.addItem("Closable", styleCommand).setCheckable(true);
	                addComponent(style);
	                style.addStyleName("small");

	                CssLayout group = new CssLayout();
	                group.setCaption("Fade delay");
	                group.addStyleName("v-component-group");
	                addComponent(group);

	                delay.setInputPrompt("Infinite");
	                delay.addStyleName("align-right");
	                delay.addStyleName("small");
	                delay.setWidth("7em");
	                delay.addValueChangeListener(new ValueChangeListener() {
	                    @Override
	                    public void valueChange(final ValueChangeEvent event) {
	                        try {
	                            notification.setDelayMsec(Integer.parseInt(delay
	                                    .getValue()));
	                        } catch (Exception e) {
	                            notification.setDelayMsec(-1);
	                            delay.setValue("");
	                        }

	                    }
	                });
	                delay.setValue("1000");
	                group.addComponent(delay);

	                Button clear = new Button(null, new ClickListener() {
	                    @Override
	                    public void buttonClick(final ClickEvent event) {
	                        delay.setValue("");
	                    }
	                });
	                clear.setIcon(FontAwesome.TIMES_CIRCLE);
	                clear.addStyleName("last");
	                clear.addStyleName("small");
	                clear.addStyleName("icon-only");
	                group.addComponent(clear);
	                group.addComponent(new Label("&nbsp; msec", ContentMode.HTML));

	                GridLayout grid = new GridLayout(3, 3);
	                grid.setCaption("Show in position");
	                addComponent(grid);
	                grid.setSpacing(true);

	                Button pos = new Button("", new ClickListener() {
	                    @Override
	                    public void buttonClick(final ClickEvent event) {
	                        notification.setPosition(Position.TOP_LEFT);
	                        notification.show(Page.getCurrent());
	                    }
	                });
	                pos.addStyleName("small");
	                grid.addComponent(pos);

	                pos = new Button("", new ClickListener() {
	                    @Override
	                    public void buttonClick(final ClickEvent event) {
	                        notification.setPosition(Position.TOP_CENTER);
	                        notification.show(Page.getCurrent());
	                    }
	                });
	                pos.addStyleName("small");
	                grid.addComponent(pos);

	                pos = new Button("", new ClickListener() {
	                    @Override
	                    public void buttonClick(final ClickEvent event) {
	                        notification.setPosition(Position.TOP_RIGHT);
	                        notification.show(Page.getCurrent());
	                    }
	                });
	                pos.addStyleName("small");
	                grid.addComponent(pos);

	                pos = new Button("", new ClickListener() {
	                    @Override
	                    public void buttonClick(final ClickEvent event) {
	                        notification.setPosition(Position.MIDDLE_LEFT);
	                        notification.show(Page.getCurrent());
	                    }
	                });
	                pos.addStyleName("small");
	                grid.addComponent(pos);

	                pos = new Button("", new ClickListener() {
	                    @Override
	                    public void buttonClick(final ClickEvent event) {
	                        notification.setPosition(Position.MIDDLE_CENTER);
	                        notification.show(Page.getCurrent());
	                    }
	                });
	                pos.addStyleName("small");
	                grid.addComponent(pos);

	                pos = new Button("", new ClickListener() {
	                    @Override
	                    public void buttonClick(final ClickEvent event) {
	                        notification.setPosition(Position.MIDDLE_RIGHT);
	                        notification.show(Page.getCurrent());
	                    }
	                });
	                pos.addStyleName("small");
	                grid.addComponent(pos);

	                pos = new Button("", new ClickListener() {
	                    @Override
	                    public void buttonClick(final ClickEvent event) {
	                        notification.setPosition(Position.BOTTOM_LEFT);
	                        notification.show(Page.getCurrent());
	                    }
	                });
	                pos.addStyleName("small");
	                grid.addComponent(pos);

	                pos = new Button("", new ClickListener() {
	                    @Override
	                    public void buttonClick(final ClickEvent event) {
	                        notification.setPosition(Position.BOTTOM_CENTER);
	                        notification.show(Page.getCurrent());
	                    }
	                });
	                pos.addStyleName("small");
	                grid.addComponent(pos);

	                pos = new Button("", new ClickListener() {
	                    @Override
	                    public void buttonClick(final ClickEvent event) {
	                        notification.setPosition(Position.BOTTOM_RIGHT);
	                        notification.show(Page.getCurrent());
	                    }
	                });
	                pos.addStyleName("small");
	                grid.addComponent(pos);

	            }
	        };
	        p.setContent(content);

	        return p;
	    }	  
	  
}
