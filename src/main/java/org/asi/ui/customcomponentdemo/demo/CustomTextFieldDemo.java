/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.asi.ui.customcomponentdemo.demo;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.asi.ui.customcomponentdemo.Custom;
import org.asi.ui.customcomponentdemo.CustomUI;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.customwindow.CustomWindow;

/**
 *
 * @author Abhiram
 */
public class CustomTextFieldDemo extends VerticalLayout implements View{
    public static final String NAME="customtextfield-demo";
    CustomUI ui;
    public CustomTextFieldDemo(CustomUI ui){
        this.ui=ui;
        init();
    }
    
    private void init() {
        Button menu=new Button("Main Menu");
        addComponent(menu);
        menu.setDescription("Go to Main Menu");
        setComponentAlignment(menu, Alignment.MIDDLE_CENTER);
        menu.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
               getUI().getNavigator().navigateTo(Custom.NAME);
            }
        });
        CustomTextField component = new CustomTextField();
        addComponent(component);        
        setComponentAlignment(component, Alignment.MIDDLE_CENTER);
        component.addClickListener(new CustomTextField.ClickListener() {

            @Override
            public void click(CustomTextField.ClickEvent event) {
                CustomWindow ob = new CustomWindow("Window");
                ui.makeWindowStyles(ob);
                ob.setWidth("200px");
                ob.setHeight("200px");
                UI.getCurrent().addWindow(ob);
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
}
