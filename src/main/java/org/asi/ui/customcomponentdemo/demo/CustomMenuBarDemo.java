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
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import org.asi.ui.customcomponentdemo.Custom;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.CustomMenuBar.CustomMenuItem;
import org.asi.ui.custommenubar.MenuItemDTO;

/**
 *
 * @author Abhiram
 */
public class CustomMenuBarDemo extends VerticalLayout implements View{
    public static final String NAME="custommenubar-demo";
    public CustomMenuBarDemo(){
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
        // Initialize our new UI component
        final CustomMenuBar barmenu = new CustomMenuBar();
        // A top-level menu item that opens a submenu
        final CustomMenuItem drinks = barmenu.addItem("Beverages", null, null);
        drinks.setCloseable(true);
        drinks.setItemClickable(true);
        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.addComponent(barmenu);
        CustomMenuBar.Command mycommand = new CustomMenuBar.Command() {
            private static final long serialVersionUID = 4483012525105015694L;
            @Override
            public void menuSelected(CustomMenuBar.CustomMenuItem selectedItem) {
                
                Notification.show("select--"+selectedItem.getId()+"--"+selectedItem.getMenuItem().getCaption()+"--"+selectedItem.getMenuItem().getId());
            }
        };
        barmenu.addItemClickListener(new CustomMenuBar.ItemClickListener() {

            @Override
            public void itemClick(CustomMenuBar.ItemClickEvent event) {
                Notification.show("item--"+event.getMenuItem().getId()+"--"+event.getMenuItem().getMenuItem().getCaption()+"--"+event.getMenuItem().getMenuItem().getId());
            }
        });
        barmenu.addItemCloseClickListener(new CustomMenuBar.ItemCloseClickListener() {

            @Override
            public void itemCloseClick(CustomMenuBar.ItemCloseClickEvent event) {
                Notification.show("close--"+event.getMenuItem().getId()+"--"+event.getMenuItem().getMenuItem().getCaption()+"--"+event.getMenuItem().getMenuItem().getId());
                if(event.getMenuItem().getParent()!=null){
                    event.getMenuItem().getParent().removeChild(event.getMenuItem());
                }else{
                    barmenu.removeItem(event.getMenuItem());
                }
                
            }
        });
        
        
        // Submenu item with a sub-submenu
        CustomMenuItem hots = drinks.addItem(new MenuItemDTO("Hot"), null, null);
        hots.setCloseable(false);
        hots.setItemClickable(true);
        hots.addItem("Tea", null,    mycommand);
        hots.addItem(new MenuItemDTO("Coffee"), null, mycommand);

        // Another submenu item with a sub-submenu
        CustomMenuItem colds = drinks.addItem(new MenuItemDTO("Cold"), null, null);
        colds.addItem(new MenuItemDTO("Milk"),      null, mycommand);
        colds.addItem(new MenuItemDTO("Weissbier"), null, mycommand);

        // A sub-menu item after a separator
        drinks.addSeparator();
        drinks.addItem(new MenuItemDTO("Quit Drinking"), null, null);
        
        // Another top-level item
        CustomMenuItem snacks = barmenu.addItem(new MenuItemDTO("Snacks"), null, null);
        snacks.addItem(new MenuItemDTO("Weisswurst"), null, mycommand);
        snacks.addItem(new MenuItemDTO("Bratwurst"),  null, mycommand);
        snacks.addItem(new MenuItemDTO("Currywurst"), null, mycommand);
        
        // Yet another top-level item
        CustomMenuItem servs = barmenu.addItem(new MenuItemDTO("Services"), null, null);
        CustomMenuItem servs1 =servs.addItem(new MenuItemDTO("Car Service"), null, mycommand);   
        servs1.setCloseable(true);
        servs1.setItemClickable(true);
        addComponent(layout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
}
