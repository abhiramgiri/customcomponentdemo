/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.asi.ui.customcomponentdemo.demo;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTable.RowHeaderMode;
import com.vaadin.ui.Field;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.Map;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.customcomponentdemo.Custom;
import org.asi.ui.customcomponentdemo.demo.dto.MyTableDTO;
import org.asi.ui.customcomponentdemo.demo.util.Utils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.REINDER_THEME_EXTFILTERING_TABLE;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezeFilterTreeTable;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedFilterTable;
import org.asi.ui.extfilteringtable.freezetable.listener.TableCollapseListener;
import org.asi.ui.extfilteringtable.freezetable.listener.TableExpandListener;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;

/**
 *
 * @author Abhiram
 */
public class ExtFilteringTableDemo extends VerticalLayout implements View{
    public static final String NAME="extfilteringtable-demo";
    
    private final BeanItemContainer<MyTableDTO> phasedProjectionBean = new BeanItemContainer<MyTableDTO>(MyTableDTO.class);
    ExtTreeContainer<MyTableDTO> availableProductsBean = new ExtTreeContainer<MyTableDTO>(MyTableDTO.class);
    
    ExtTreeContainer<MyTableDTO> availableProductsBean2 = new ExtTreeContainer<MyTableDTO>(MyTableDTO.class);
    ExtTreeContainer<MyTableDTO> availableProductsBean1 = new ExtTreeContainer<MyTableDTO>(MyTableDTO.class);
    
    private final Map<Object, Object[]> dMapVisibleColumnsMore = new HashMap<Object, Object[]>();
    private final Map<Object, Object[]> tMapVisibleColumnsMore = new HashMap<Object, Object[]>();
    private final Map<Object, Object[]> dMapVisibleColumnsLess = new HashMap<Object, Object[]>();
    private final Map<Object, Object[]> tMapVisibleColumnsLess = new HashMap<Object, Object[]>();
    public ExtCustomTable periodTableId=new ExtCustomTable();
        NativeSelect frequency=new NativeSelect("frequency");
        NativeSelect history=new NativeSelect("history");
        OptionGroup actualOrProj=new OptionGroup("actualOrProj");
        OptionGroup pivotView=new OptionGroup("pivotView");
        Button generate=new Button("GENERATE");
        TabSheet tabsheet=new TabSheet();
    public ExtFilteringTableDemo(){
        init();
    }
    
    private void init() {
        setMargin(true);
        setSpacing(true);
        loadData();
        getMapVisibleCols();
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
        loadTables();
        
    }
    private void loadData() {
        for (int i = 0; i < 48; i++) {
            MyTableDTO ob = new MyTableDTO();
            MyTableDTO om = new MyTableDTO();
            om.setProjectionType(i);
            om.setProjCol1Sales("" + i);
            phasedProjectionBean.addBean(om);

            if (i == 1) {
                ob.setProjectionType(500);
                ob.setProjCol1Sales("I m parent");
                availableProductsBean.addBean(ob);
                
                availableProductsBean.setChildrenAllowed(ob, true);
                MyTableDTO oc = new MyTableDTO();
                oc.setProjCol1Sales("I m 1st child");
                oc.setProjectionType(600);
                availableProductsBean.addBean(oc);
                availableProductsBean.setParent(oc, ob);
                MyTableDTO oe = new MyTableDTO();
                oe.setProjCol1Sales("I m 2nd child");
                oe.setProjectionType(700);

                availableProductsBean.addBean(oe);
                availableProductsBean.setParent(oe, ob);
                availableProductsBean.setChildrenAllowed(oe, false);
                availableProductsBean.setChildrenAllowed(oc, true);
                MyTableDTO od = new MyTableDTO();
                od.setProjCol1Sales("I m child of 1st child");

                availableProductsBean.addBean(od);
                availableProductsBean.setParent(od, oc);
            } else {
                ob.setProjectionType(i);
                availableProductsBean.addBean(ob);
                availableProductsBean.setChildrenAllowed(ob, false);
            }
        }

    }

    private void getMapVisibleCols() {
        dMapVisibleColumnsLess.put(Utils.dviscolumnless[0], new Object[]{Utils.viscolumnless[0]});
        dMapVisibleColumnsLess.put(Utils.dviscolumnless[1], new Object[]{Utils.viscolumnless[1], Utils.viscolumnless[2]});
        
        tMapVisibleColumnsLess.put(Utils.tviscolumnless[0], new Object[]{Utils.dviscolumnless[0], Utils.dviscolumnless[1]});
        
        dMapVisibleColumnsMore.put(Utils.dviscolumnmore[0], new Object[]{Utils.viscolumnmore[0],Utils.viscolumnmore[1]});
        dMapVisibleColumnsMore.put(Utils.dviscolumnmore[1], new Object[]{Utils.viscolumnmore[2]});
        dMapVisibleColumnsMore.put(Utils.dviscolumnmore[2], new Object[]{Utils.viscolumnmore[3],Utils.viscolumnmore[4],Utils.viscolumnmore[5]});
        int j = 6;
        for (int i = 3; i < Utils.dviscolumnmore.length; i++) {
            dMapVisibleColumnsMore.put(Utils.dviscolumnmore[i], new Object[]{Utils.viscolumnmore[j], Utils.viscolumnmore[j + 1], Utils.viscolumnmore[j + 2], Utils.viscolumnmore[j + 3], Utils.viscolumnmore[j + 4], Utils.viscolumnmore[j + 5]});
            j = j + 6;
        }
        tMapVisibleColumnsMore.put(Utils.tviscolumnmore[0], new Object[]{Utils.dviscolumnmore[0], Utils.dviscolumnmore[1]});
        tMapVisibleColumnsMore.put(Utils.tviscolumnmore[1], new Object[]{Utils.dviscolumnmore[2]});
        tMapVisibleColumnsMore.put(Utils.tviscolumnmore[2], new Object[]{Utils.dviscolumnmore[3],Utils.dviscolumnmore[4],Utils.dviscolumnmore[5]});
        tMapVisibleColumnsMore.put(Utils.tviscolumnmore[3], new Object[]{Utils.dviscolumnmore[6], Utils.dviscolumnmore[7], Utils.dviscolumnmore[8],Utils.dviscolumnmore[9]});
    }
    private void loadTables() {
        
        addComponent(tabsheet);
        tabsheet.setImmediate(true);
        tabsheet.addTab(loadExtFilterTable(), "Ext Filter Table");
        tabsheet.addTab(loadExtFilterTreeTable(), "Ext Filter Tree Table");
        tabsheet.addTab(loadFreezeFilterTreeTable(), "Freeze Filter Tree Table");
        tabsheet.addTab(loadPagedFilterTable(), "Paged Filter Table");
        tabsheet.addTab(loadFreezePagedFilterTable(), "Freeze Paged Filter Table");
//        tabsheet.addTab(loadExtFilterTestingTable(), "Testing Table");
        
    }
    private VerticalLayout loadExtFilterTestingTable() {
        Object visCol[]=new Object[]{"projCol1Sales","projCol2Sales","projCol3Sales"};
        String visHead[]=new String[]{"projCol1Sales","projCol2Sales","projCol3Sales"};
        Map<Object,Object[]> map=new HashMap<Object,Object[]>();
        map.put("projCol1Sales", new Object[]{"projCol1Sales"});
        map.put("projCol2Sales", new Object[]{"projCol2Sales"});
        map.put("projCol3Sales", new Object[]{"projCol3Sales"});
        
        
        
        
        VerticalLayout layout=new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        final ExtFilterTable extFilterTable = new ExtFilterTable();
        extFilterTable.setSizeFull();
        extFilterTable.setCaption("ExtFilterTable");
        extFilterTable.setContainerDataSource(phasedProjectionBean);
        extFilterTable.setVisibleColumns(visCol);
        extFilterTable.setColumnHeaders(visHead);
        extFilterTable.setFilterBarVisible(true);
        extFilterTable.setDoubleHeaderVisible(true);
        extFilterTable.setDoubleHeaderVisibleColumns(visCol);
        extFilterTable.setDoubleHeaderColumnHeaders(visHead);
        extFilterTable.setDoubleHeaderMap(map);
        
        extFilterTable.setColumnCollapsingAllowed(true);
        extFilterTable.setTripleHeaderVisible(true);

        extFilterTable.setTripleHeaderVisibleColumns(visCol);
        extFilterTable.setTripleHeaderColumnHeaders(visHead);
        extFilterTable.setTripleHeaderMap(map);
        extFilterTable.setSelectable(true);
        extFilterTable.setEditable(true);
        extFilterTable.setPageLength(10);
        extFilterTable.setRowHeaderMode(ExtCustomTable.RowHeaderMode.ICON_ONLY);
        
        for(int i=0;i<visCol.length;i++){
            extFilterTable.setColumnCheckBox(visCol[i], true);
            extFilterTable.setDoubleHeaderColumnRadioButton(visCol[i], visHead[0]);
            extFilterTable.setTripleHeaderColumnRadioButton(visCol[i], visHead[0]);
        }
        
        extFilterTable.setImmediate(true);
        extFilterTable.addDoubleHeaderColumnRadioCheckListener(new ExtCustomTable.DoubleHeaderColumnRadioCheckListener() {

            @Override
            public void doubleHeaderColumnRadioCheck(ExtCustomTable.DoubleHeaderColumnRadioCheckEvent event) {
                Notification.show("Double Radio RadioButtonName: " + event.getRadioButtonName()+" Cur: " + event.getCurrentValue()+" Pre: " + event.getPreviousValue());
            }
        });
        extFilterTable.addTripleHeaderColumnRadioCheckListener(new ExtCustomTable.TripleHeaderColumnRadioCheckListener() {

            @Override
            public void tripleHeaderColumnRadioCheck(ExtCustomTable.TripleHeaderColumnRadioCheckEvent event) {                
                Notification.show("Triple Radio RadioButtonName: " + event.getRadioButtonName()+" Cur: " + event.getCurrentValue()+" Pre: " + event.getPreviousValue());
            }
        });
        
        extFilterTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                extFilterTable.setTripleHeaderColumnRadioButtonDisable(event.getPropertyId(), !event.isChecked());
                extFilterTable.setDoubleHeaderColumnRadioButtonDisable(event.getPropertyId(), !event.isChecked());
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        layout.addComponent(extFilterTable);
      
        return layout;
    }
    private VerticalLayout loadExtFilterTable() {
        VerticalLayout layout=new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        final ExtFilterTable extFilterTable = new ExtFilterTable();
        extFilterTable.setSizeFull();
        extFilterTable.setCaption("ExtFilterTable");
        extFilterTable.setContainerDataSource(phasedProjectionBean);
        extFilterTable.setVisibleColumns(Utils.viscolumnmore);
        extFilterTable.setColumnHeaders(Utils.visheadermore);
        extFilterTable.setFilterBarVisible(true);
        extFilterTable.setDoubleHeaderVisible(true);
        extFilterTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnmore);
        extFilterTable.setDoubleHeaderColumnHeaders(Utils.dvisheadermore);
        extFilterTable.setDoubleHeaderMap(dMapVisibleColumnsMore);
        
        extFilterTable.setColumnCollapsingAllowed(true);
        extFilterTable.setTripleHeaderVisible(true);

        extFilterTable.setTripleHeaderVisibleColumns(Utils.tviscolumnmore);
        extFilterTable.setTripleHeaderColumnHeaders(Utils.tvisheadermore);
        extFilterTable.setTripleHeaderMap(tMapVisibleColumnsMore);
        extFilterTable.setSelectable(true);
        extFilterTable.setEditable(true);
        extFilterTable.setPageLength(10);
        extFilterTable.setRowHeaderMode(ExtCustomTable.RowHeaderMode.ICON_ONLY);
        extFilterTable.setTripleHeaderColumnCheckBox(Utils.tviscolumnmore[0], true, true);
        extFilterTable.setTripleHeaderColumnIcon(Utils.tviscolumnmore[1], Utils.logoimg);
        extFilterTable.setTripleHeaderColumnExpandIcon(Utils.tviscolumnmore[0], true);
        extFilterTable.setTripleHeaderColumnRadioButton(Utils.tviscolumnmore[2], "asit");
        extFilterTable.setTripleHeaderColumnRadioButton(Utils.tviscolumnmore[0], "asit");
        
        extFilterTable.setDoubleHeaderColumnRadioButton(Utils.dviscolumnmore[2], "asit1");
        extFilterTable.setDoubleHeaderColumnRadioButton(Utils.dviscolumnmore[0], "asit1");
        extFilterTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[0], true, true);
        extFilterTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[1], Utils.logoimg);
        extFilterTable.setColumnCheckBox(Utils.viscolumnmore[0], true, true);
        extFilterTable.setColumnRadioButton(Utils.viscolumnmore[0], "asit111");
        extFilterTable.setColumnIcon(Utils.viscolumnmore[2], Utils.logoimg);
        
        extFilterTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[2], true, true);
        extFilterTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[2], Utils.logoimg);
        extFilterTable.setColumnCheckBox(Utils.viscolumnmore[1], true, false);
        extFilterTable.setColumnIcon(Utils.viscolumnmore[1], Utils.logoimg);
        extFilterTable.setColumnIcon(Utils.viscolumnmore[3], Utils.logoimg);
        extFilterTable.setImmediate(true);
        extFilterTable.addTripleHeaderColumnCheckListener(new ExtCustomTable.TripleHeaderColumnCheckListener() {

            @Override
            public void tripleHeaderColumnCheck(ExtCustomTable.TripleHeaderColumnCheckEvent event) {
                Notification.show("Triple Check PrropertyId: " + event.getPropertyId()+" Pre: " + event.isChecked());
            }
        });
        extFilterTable.addTripleHeaderColumnExpandIconListener(new ExtCustomTable.TripleHeaderColumnExpandIconListener() {

            @Override
           public void tripleHeaderColumnExpandIcon(ExtCustomTable.TripleHeaderColumnExpandIconEvent event) {
               if(event.getPropertyId().equals(Utils.tviscolumnmore[0])){
                   extFilterTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !event.isExpanded());
               }
            }
        });
        extFilterTable.addTripleHeaderColumnRadioCheckListener(new ExtCustomTable.TripleHeaderColumnRadioCheckListener() {

            @Override
            public void tripleHeaderColumnRadioCheck(ExtCustomTable.TripleHeaderColumnRadioCheckEvent event) {
                Notification.show("Triple Radio RadioButtonName: " + event.getRadioButtonName()+" Cur: " + event.getCurrentValue()+" Pre: " + event.getPreviousValue());
            }
        });
        extFilterTable.addTripleHeaderColumnResizeListener(new ExtCustomTable.TripleHeaderColumnResizeListener() {

            @Override
            public void tripleHeaderColumnResize(ExtCustomTable.TripleHeaderColumnResizeEvent event) {
                 Notification.show("Triple PrropertyId: " + event.getPropertyId()+" Pre: " + event.getPreviousWidth()+" Cur: " + event.getCurrentWidth());
            }
        });
        extFilterTable.addDoubleHeaderColumnResizeListener(new ExtCustomTable.DoubleHeaderColumnResizeListener() {

            @Override
            public void doubleHeaderColumnResize(ExtCustomTable.DoubleHeaderColumnResizeEvent event) {
                Notification.show("Double PrropertyId: " + event.getPropertyId()+" Pre: " + event.getPreviousWidth()+" Cur: " + event.getCurrentWidth());
            }

            
        });
        extFilterTable.addColumnResizeListener(new ExtCustomTable.ColumnResizeListener() {

            @Override
            public void columnResize(ExtCustomTable.ColumnResizeEvent event) {
                 Notification.show("PrropertyId: " + event.getPropertyId()+" Pre: " + event.getPreviousWidth()+" Cur: " + event.getCurrentWidth());
            }
        });
        extFilterTable.addTripleHeaderClickListener(new ExtCustomTable.TripleHeaderClickListener() {

            @Override
            public void tripleHeaderClick(ExtCustomTable.TripleHeaderClickEvent event) {
                Notification.show("Triple PrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTable.addDoubleHeaderClickListener(new ExtCustomTable.DoubleHeaderClickListener() {

            @Override
            public void doubleHeaderClick(ExtCustomTable.DoubleHeaderClickEvent event) {
                Notification.show("Double PrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTable.addHeaderClickListener(new ExtCustomTable.HeaderClickListener() {

            @Override
            public void headerClick(ExtCustomTable.HeaderClickEvent event) {
                Notification.show("PrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                 extFilterTable.setDoubleHeaderColumnRadioButtonDisable(Utils.dviscolumnmore[2], event.isChecked());
                 extFilterTable.setDoubleHeaderColumnRadioButtonDisable(Utils.dviscolumnmore[0], event.isChecked());
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        layout.addComponent(extFilterTable);        
        return layout;
    }
   private VerticalLayout loadExtFilterTreeTable() {
        VerticalLayout layout=new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        final ExtFilterTreeTable extFilterTreeTable = new ExtFilterTreeTable();
        extFilterTreeTable.setEditable(true);
        extFilterTreeTable.setSizeFull();
        extFilterTreeTable.setCaption("ExtFilterTreeTable");
        extFilterTreeTable.setContainerDataSource(availableProductsBean);
        extFilterTreeTable.setVisibleColumns(Utils.viscolumnmore);
        extFilterTreeTable.setColumnHeaders(Utils.visheadermore);
        extFilterTreeTable.setFilterBarVisible(true);
        extFilterTreeTable.setDoubleHeaderVisible(true);
        extFilterTreeTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnmore);
        extFilterTreeTable.setDoubleHeaderColumnHeaders(Utils.dvisheadermore);
        extFilterTreeTable.setDoubleHeaderMap(dMapVisibleColumnsMore);
        
        extFilterTreeTable.setColumnCollapsingAllowed(true);
        extFilterTreeTable.setTripleHeaderVisible(true);

        extFilterTreeTable.setTripleHeaderVisibleColumns(Utils.tviscolumnmore);
        extFilterTreeTable.setTripleHeaderColumnHeaders(Utils.tvisheadermore);
        extFilterTreeTable.setTripleHeaderMap(tMapVisibleColumnsMore);
        extFilterTreeTable.setSelectable(true);
        extFilterTreeTable.setPageLength(10);
        extFilterTreeTable.setRowHeaderMode(ExtCustomTable.RowHeaderMode.ICON_ONLY);
        extFilterTreeTable.setTripleHeaderColumnCheckBox(Utils.tviscolumnmore[0], true, true);
        extFilterTreeTable.setTripleHeaderColumnIcon(Utils.tviscolumnmore[1], Utils.logoimg);
        extFilterTreeTable.setTripleHeaderColumnExpandIcon(Utils.tviscolumnmore[0], true);
        extFilterTreeTable.setTripleHeaderColumnRadioButton(Utils.tviscolumnmore[2], "asit");
        extFilterTreeTable.setTripleHeaderColumnRadioButton(Utils.tviscolumnmore[0], "asit");
        
        extFilterTreeTable.setDoubleHeaderColumnRadioButton(Utils.dviscolumnmore[2], "asit1");
        extFilterTreeTable.setDoubleHeaderColumnRadioButton(Utils.dviscolumnmore[0], "asit1");
        extFilterTreeTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[0], true, true);
        extFilterTreeTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[1], Utils.logoimg);
        extFilterTreeTable.setColumnCheckBox(Utils.viscolumnmore[0], true, true);
        extFilterTreeTable.setColumnRadioButton(Utils.viscolumnmore[0], "asit111");
        extFilterTreeTable.setColumnIcon(Utils.viscolumnmore[2], Utils.logoimg);
        
        extFilterTreeTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[2], true, true);
        extFilterTreeTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[2], Utils.logoimg);
        extFilterTreeTable.setColumnCheckBox(Utils.viscolumnmore[1], true, false);
        extFilterTreeTable.setColumnIcon(Utils.viscolumnmore[1], Utils.logoimg);
        extFilterTreeTable.setColumnIcon(Utils.viscolumnmore[3], Utils.logoimg);
        extFilterTreeTable.setImmediate(true);
//        rightTable.setSortEnabled(false);
        extFilterTreeTable.addTripleHeaderColumnCheckListener(new ExtCustomTable.TripleHeaderColumnCheckListener() {

            @Override
            public void tripleHeaderColumnCheck(ExtCustomTable.TripleHeaderColumnCheckEvent event) {
                Notification.show("Triple Check PrropertyId: " + event.getPropertyId()+" Pre: " + event.isChecked());
            }
        });
        extFilterTreeTable.addTripleHeaderColumnExpandIconListener(new ExtCustomTable.TripleHeaderColumnExpandIconListener() {

            @Override
           public void tripleHeaderColumnExpandIcon(ExtCustomTable.TripleHeaderColumnExpandIconEvent event) {
               if(event.getPropertyId().equals(Utils.tviscolumnmore[0])){
                   extFilterTreeTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !event.isExpanded());
               }
            }
        });
        extFilterTreeTable.addTripleHeaderColumnRadioCheckListener(new ExtCustomTable.TripleHeaderColumnRadioCheckListener() {

            @Override
            public void tripleHeaderColumnRadioCheck(ExtCustomTable.TripleHeaderColumnRadioCheckEvent event) {
                Notification.show("Triple Radio RadioButtonName: " + event.getRadioButtonName()+" Cur: " + event.getCurrentValue()+" Pre: " + event.getPreviousValue());
            }
        });
        extFilterTreeTable.addTripleHeaderColumnResizeListener(new ExtCustomTable.TripleHeaderColumnResizeListener() {

            @Override
            public void tripleHeaderColumnResize(ExtCustomTable.TripleHeaderColumnResizeEvent event) {
                 Notification.show("Triple PrropertyId: " + event.getPropertyId()+" Pre: " + event.getPreviousWidth()+" Cur: " + event.getCurrentWidth());
            }
        });
        extFilterTreeTable.addDoubleHeaderColumnResizeListener(new ExtCustomTable.DoubleHeaderColumnResizeListener() {

            @Override
            public void doubleHeaderColumnResize(ExtCustomTable.DoubleHeaderColumnResizeEvent event) {
                Notification.show("Double PrropertyId: " + event.getPropertyId()+" Pre: " + event.getPreviousWidth()+" Cur: " + event.getCurrentWidth());
            }

            
        });
        extFilterTreeTable.addColumnResizeListener(new ExtCustomTable.ColumnResizeListener() {

            @Override
            public void columnResize(ExtCustomTable.ColumnResizeEvent event) {
                 Notification.show("PrropertyId: " + event.getPropertyId()+" Pre: " + event.getPreviousWidth()+" Cur: " + event.getCurrentWidth());
            }
        });
        extFilterTreeTable.addTripleHeaderClickListener(new ExtCustomTable.TripleHeaderClickListener() {

            @Override
            public void tripleHeaderClick(ExtCustomTable.TripleHeaderClickEvent event) {
                Notification.show("Triple PrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTreeTable.addDoubleHeaderClickListener(new ExtCustomTable.DoubleHeaderClickListener() {

            @Override
            public void doubleHeaderClick(ExtCustomTable.DoubleHeaderClickEvent event) {
                Notification.show("Double PrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTreeTable.addHeaderClickListener(new ExtCustomTable.HeaderClickListener() {

            @Override
            public void headerClick(ExtCustomTable.HeaderClickEvent event) {
                Notification.show("PrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTreeTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTreeTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        layout.addComponent(extFilterTreeTable);
        return layout;
    }
    

    private VerticalLayout loadFreezeFilterTreeTable() {
        VerticalLayout layout=new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        FreezeFilterTreeTable freezeFilterTreeTable = new FreezeFilterTreeTable();
    TableExpandListener filtertableExpandListener = new TableExpandListener(freezeFilterTreeTable.getRightFreezeAsTable());
    TableCollapseListener filtertableCollapseListener = new TableCollapseListener(freezeFilterTreeTable.getRightFreezeAsTable());
    TableExpandListener filterfreezeTableExpandListener = new TableExpandListener(filtertableExpandListener);
    TableCollapseListener filterfreezeTableCollapseListener = new TableCollapseListener(filtertableCollapseListener);
        freezeFilterTreeTable.setCaption("FreezeFilterTreeTable");
        freezeFilterTreeTable.setSplitPosition(Utils.splitPosition, Unit.PIXELS);
        freezeFilterTreeTable.setMinSplitPosition(Utils.minSplitPosition, Unit.PIXELS);
        freezeFilterTreeTable.setMaxSplitPosition(Utils.maxSplitPosition, Unit.PIXELS);
        freezeFilterTreeTable.setContainerDataSource(availableProductsBean);
        freezeFilterTreeTable.setPageLength(6);
        freezeFilterTreeTable.setSelectable(true);
        ExtFilterTreeTable leftTable = freezeFilterTreeTable.getLeftFreezeAsTable();
        final ExtFilterTreeTable rightTable = freezeFilterTreeTable.getRightFreezeAsTable();
        leftTable.setVisibleColumns(Utils.viscolumnless);
        leftTable.setColumnHeaders(Utils.visheaderless);
        rightTable.setVisibleColumns(Utils.viscolumnmore);
        rightTable.setColumnHeaders(Utils.visheadermore);
        freezeFilterTreeTable.setDoubleHeaderVisible(true);
        freezeFilterTreeTable.setFilterBarVisible(true);

        freezeFilterTreeTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnless,Utils.dviscolumnmore);
        freezeFilterTreeTable.setDoubleHeaderColumnHeaders(Utils.dvisheaderless,Utils.dvisheadermore);
        freezeFilterTreeTable.setDoubleHeaderMap(dMapVisibleColumnsLess,dMapVisibleColumnsMore);
        rightTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[0], true, true);
        rightTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[1], Utils.logoimg);
        rightTable.setColumnCheckBox(Utils.viscolumnmore[0], true, true);
        rightTable.setColumnIcon(Utils.viscolumnmore[2], Utils.logoimg);

        rightTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[2], true, true);
        rightTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[2], Utils.logoimg);
        rightTable.setColumnCheckBox(Utils.viscolumnmore[1], true, false);
        rightTable.setColumnIcon(Utils.viscolumnmore[1], Utils.logoimg);
        rightTable.setColumnIcon(Utils.viscolumnmore[3], Utils.logoimg);
        rightTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        rightTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        leftTable.addExpandListener(filterfreezeTableExpandListener);
        leftTable.addCollapseListener(filterfreezeTableCollapseListener);
        rightTable.addExpandListener(filtertableExpandListener);
        rightTable.addCollapseListener(filtertableCollapseListener);
        layout.addComponent(freezeFilterTreeTable);
        freezeFilterTreeTable.setImmediate(true);
        return layout;
    }
    
private VerticalLayout loadPagedFilterTable() {
        VerticalLayout layout=new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        final ExtPagedFilterTable<IndexedContainer> pagedFilterTable = new ExtPagedFilterTable<IndexedContainer>();
        pagedFilterTable.setCaption("ExtPagedFilterTable");
        pagedFilterTable.setWidth("100%");
        pagedFilterTable.setFilterBarVisible(true);

        pagedFilterTable.setSelectable(true);
        pagedFilterTable.setImmediate(true);
        pagedFilterTable.setMultiSelect(true);
        pagedFilterTable.setPageLength(5);
        pagedFilterTable.setRowHeaderMode(RowHeaderMode.INDEX);

        pagedFilterTable.setColumnReorderingAllowed(true);

        pagedFilterTable.setContainerDataSource(phasedProjectionBean);

        pagedFilterTable.setVisibleColumns(Utils.viscolumnmore);
        pagedFilterTable.setColumnHeaders(Utils.visheadermore);
        pagedFilterTable.setDoubleHeaderVisible(true);
        pagedFilterTable.sinkItemPerPageWithPageLength(false);
        pagedFilterTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnmore);
        pagedFilterTable.setDoubleHeaderColumnHeaders(Utils.dvisheadermore);
        pagedFilterTable.setDoubleHeaderMap(dMapVisibleColumnsMore);
        pagedFilterTable.setColumnIcon(Utils.viscolumnmore[3], Utils.logoimg);
        pagedFilterTable.setColumnCheckBox(Utils.viscolumnmore[2], true, true);
        pagedFilterTable.addColumnResizeListener(new ExtCustomTable.ColumnResizeListener() {

            @Override
            public void columnResize(ExtCustomTable.ColumnResizeEvent event) {
                Notification.show("Pro:" + event.getPropertyId() + "\n Pre:" + event.getPreviousWidth() + "\n Cur: " + event.getCurrentWidth());
            }
        });
        pagedFilterTable.addDoubleHeaderColumnResizeListener(new ExtCustomTable.DoubleHeaderColumnResizeListener() {

            @Override
            public void doubleHeaderColumnResize(ExtCustomTable.DoubleHeaderColumnResizeEvent event) {
                Notification.show("Pro:" + event.getPropertyId() + "\n Pre:" + event.getPreviousWidth() + "\n Cur: " + event.getCurrentWidth());
            }

        });
        pagedFilterTable.setEditable(true);
        pagedFilterTable.setImmediate(true);
        pagedFilterTable.setTableFieldFactory(new DefaultFieldFactory() {

            @Override
            public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                if(propertyId.equals(Utils.viscolumnmore[2])){
                    TextField ob=new TextField();
                    ob.setImmediate(true);
                    return ob;
                }
                return null;
            }
        });
        layout.addComponent(pagedFilterTable);
        layout.addComponent(pagedFilterTable.createControls());
        final CheckBox sink=new CheckBox("Sink ItemPerPage With PageLength");
        sink.setImmediate(true);
        layout.addComponent(sink);
        sink.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                pagedFilterTable.sinkItemPerPageWithPageLength(sink.getValue());
            }
        });
        return layout;
    }
    
    
    private VerticalLayout loadFreezePagedFilterTable() {
        VerticalLayout layout=new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        final FreezePagedFilterTable<IndexedContainer> pagedFreezeFilterTable = new FreezePagedFilterTable<IndexedContainer>();

        pagedFreezeFilterTable.setCaption("FreezePagedFilterTable");
        pagedFreezeFilterTable.setSplitPosition(Utils.splitPosition, Unit.PIXELS);
        pagedFreezeFilterTable.setMinSplitPosition(Utils.minSplitPosition, Unit.PIXELS);
        pagedFreezeFilterTable.setMaxSplitPosition(Utils.maxSplitPosition, Unit.PIXELS);
        pagedFreezeFilterTable.setContainerDataSource(phasedProjectionBean);
        pagedFreezeFilterTable.setPageLength(6);
        
//        pagedFilterTable.setHeight("210px");
        pagedFreezeFilterTable.setSelectable(true);
        final ExtPagedFilterTable leftTable = pagedFreezeFilterTable.getLeftFreezeAsTable();
        final ExtPagedFilterTable rightTable = pagedFreezeFilterTable.getRightFreezeAsTable();
        leftTable.setVisibleColumns(Utils.viscolumnless);
        leftTable.setColumnHeaders(Utils.visheaderless);
        rightTable.setVisibleColumns(Utils.viscolumnmore);
        rightTable.setColumnHeaders(Utils.visheadermore);
        pagedFreezeFilterTable.setDoubleHeaderVisible(true);
        pagedFreezeFilterTable.setFilterBarVisible(true);
        pagedFreezeFilterTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnless,Utils.dviscolumnmore);
        pagedFreezeFilterTable.setDoubleHeaderColumnHeaders(Utils.dvisheaderless,Utils.dvisheadermore);
        pagedFreezeFilterTable.setDoubleHeaderMap(dMapVisibleColumnsLess, dMapVisibleColumnsMore);
        rightTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[0], true, true);
        rightTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[1], Utils.logoimg);
        rightTable.setColumnCheckBox(Utils.viscolumnmore[0], true, true);
        rightTable.setColumnIcon(Utils.viscolumnmore[2], Utils.logoimg);

        rightTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[2], true, true);
        rightTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[2], Utils.logoimg);
        rightTable.setColumnCheckBox(Utils.viscolumnmore[1], true, false);
        rightTable.setColumnIcon(Utils.viscolumnmore[1], Utils.logoimg);
        rightTable.setColumnIcon(Utils.viscolumnmore[3], Utils.logoimg);
        
        pagedFreezeFilterTable.sinkItemPerPageWithPageLength(false);
//        rightTable.setSortEnabled(false);
        rightTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        rightTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        layout.addComponent(pagedFreezeFilterTable);
        layout.addComponent(pagedFreezeFilterTable.createControls());
        final CheckBox sink=new CheckBox("Sink ItemPerPage With PageLength");
        sink.setImmediate(true);
        layout.addComponent(sink);
        sink.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                pagedFreezeFilterTable.sinkItemPerPageWithPageLength(sink.getValue());
            }
        });
        return layout;
    }
    
   @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if(getUI().getTheme().equalsIgnoreCase(Utils.customValoTheme)){
            tabsheet.removeStyleName(REINDER_THEME_EXTFILTERING_TABLE);
        tabsheet.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
    }else{
            
            tabsheet.removeStyleName(VALO_THEME_EXTFILTERING_TABLE);
            tabsheet.addStyleName(REINDER_THEME_EXTFILTERING_TABLE);
        }
    }
}