/*
 * Copyright 2014 Abhiram.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.asi.ui.customcomponentdemo.demo;


import com.vaadin.data.Container;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.Map;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.CustomMenuBar.CustomMenuItem;
import org.asi.ui.custommenubar.MenuItemDTO;
import org.asi.ui.customwindow.CustomWindow;

/**
 *
 * @author Abhiram
 */
public class LookUp extends CustomWindow {
    VerticalLayout layout=new VerticalLayout();
    LookUp(){
        init();
    }
    LookUp(String caption){
        super(caption);
        init();
    }
    private void init(){
        setContent(layout);
        addToContent();
    }
    private void addToContent(){
        this.center();
        setWidth("600px");
        setHeight("600px");
        CustomMenuBar barmenu=new CustomMenuBar();
        CustomMenuItem windows=barmenu.addItem(new MenuItemDTO("Windows"), null);
        CustomMenuItem item1=windows.addItem(new MenuItemDTO("Abhiram"), null);
        item1.setCloseable(true);
        item1.setItemClickable(true);
        CustomMenuItem item2=windows.addItem(new MenuItemDTO("Abhiram"), null);
        item2.setCloseable(true);
        item2.setItemClickable(true);
        CustomMenuItem item3=windows.addItem(new MenuItemDTO("Abhiram"), null);
        item3.setCloseable(true);
        item3.setItemClickable(true);
        CustomMenuItem item4=windows.addItem(new MenuItemDTO("Abhiram"), null);
        item4.setCloseable(true);
        item4.setItemClickable(true);
        Button x=new Button("Click Me");
        TextField y=new TextField();
        layout.addComponent(y);
        layout.addComponent(x);
        layout.addComponent(barmenu);
    }
}
