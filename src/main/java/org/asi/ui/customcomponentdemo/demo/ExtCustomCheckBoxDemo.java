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
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;

/**
 *
 * @author Abhiram
 */
public class ExtCustomCheckBoxDemo extends VerticalLayout implements View {

    public static final String NAME = "extcustomcheckbox-demo";
    CustomUI ui;

    public ExtCustomCheckBoxDemo(CustomUI ui) {
        this.ui = ui;
        init();
    }

    private void init() {
        Button menu = new Button("Main Menu");
        addComponent(menu);
        menu.setDescription("Go to Main Menu");
        setComponentAlignment(menu, Alignment.MIDDLE_CENTER);
        menu.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().navigateTo(Custom.NAME);
            }
        });
        ExtCustomCheckBox component = new ExtCustomCheckBox("ExtCustomCheckBox: ");
        addComponent(component);

        setComponentAlignment(component, Alignment.MIDDLE_CENTER);
        component.addClickListener(new ExtCustomCheckBox.ClickListener() {

            @Override
            public void click(ExtCustomCheckBox.ClickEvent event) {
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
