/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.asi.ui.customcomponentdemo.demo;

import com.vaadin.data.Property;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.UI;
import org.asi.ui.customcomponentdemo.demo.util.Utils;

/**
 *
 * @author Abhiram
 */
public class ActionButtonLayout extends HorizontalLayout{
    public ActionButtonLayout(){
        init();
    }
    public void setTheme(String theme) {
        UI.getCurrent().setTheme(theme);
    }
    public String getTheme() {
        return UI.getCurrent().getTheme();
    }
    private void init(){
        OptionGroup og=new OptionGroup();
        og.addItem(Utils.customTheme);
        og.addItem(Utils.customValoTheme);
        og.select(Utils.customTheme);
        og.setImmediate(true);
        addComponent(new Label("Select Theme:"));
        addComponent(og);
        og.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                setTheme(event.getProperty().getValue().toString());
            }
        });
    }
    
}
