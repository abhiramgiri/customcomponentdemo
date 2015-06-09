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
import org.asi.ui.customwindow.MinimizeTray;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author Abhiram
 */
public class CustomWindowDemo extends VerticalLayout implements View{
    public static final String NAME="customwindow-demo";
    static int i=1;
    CustomUI ui;
    public CustomWindowDemo(CustomUI ui){
        this.ui=ui;
        init();
    }
    
    private void init() {

        // Initialize our new UI component
        final LookUp win = new LookUp("Abhiram");
        final LookUp win1 = new LookUp();
        
        Button x=new Button("First Window");
        Button y=new Button("Second Window");
        setSizeFull();
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
        addComponent(x);
        x.setDescription("Added minimize to tray");
        setComponentAlignment(x, Alignment.MIDDLE_CENTER);
        x.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if(win.getParent()==null){
                    win.setClosable(false);
                    win.setMinimizeToTray(); 
                    ui.makeWindowStyles(win);
                UI.getCurrent().addWindow(win);
                }else{
                    
                    LookUp win3 = new LookUp("Abhi"+(i++));
                    win3.setMinimizeToTray();
                    ui.makeWindowStyles(win3);
                    UI.getCurrent().addWindow(win3);
                }
            }
        });
        y.setDescription("Not Added minimize to tray");
        addComponent(y);
        setComponentAlignment(y, Alignment.MIDDLE_CENTER);
        y.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                 if(win1.getParent()==null){
                     ui.makeWindowStyles(win1);
                     win1.setMinimizeToTray(new MiniM());
                UI.getCurrent().addWindow(win1);
                }
            }
        });
    }
   
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }    
    
    class MiniM extends MinimizeTray{
        @Override
        protected void windowsCloseClick() {
        ConfirmDialog.show(getUI(), "Please Confirm:", "You are Closing. Are you sure?",
                "OK", "Cancel", new ConfirmDialog.Listener() {

                    @Override
                    public void onClose(ConfirmDialog dialog) {
                        if (dialog.isConfirmed()) {
                            windowsCloseConfirmed();
                        }
                    }
                });
    }
    }
}
