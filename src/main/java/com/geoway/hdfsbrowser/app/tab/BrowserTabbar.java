package com.geoway.hdfsbrowser.app.tab;

import com.geoway.hdfsbrowser.app.action.operator.Mkdir;
import com.geoway.hdfsbrowser.util.ColorUtils;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by USER on 2017/3/27.
 */
public class BrowserTabbar extends TabItem {

    private TabFolder parent;
    private int style;
    private Composite composite=null;
    private Table table=null;

    public BrowserTabbar(TabFolder tabFolder, int i) {
        super(tabFolder,i);
        this.parent=tabFolder;
        this.style=i;
        this.composite=new Composite(parent,style);
        this.setControl(composite);
        this.setText("File Broswer");
        //
        this.composite.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        this.composite.setLayout(new GridLayout(10,false));
        //
        createContent();
    }

    /**
     * @author wozipa
     * @date 2017-3-27
     * the content of the tabbat
     */
    public void createContent()
    {
        ToolBarManager toolBarManager=new ToolBarManager();
        toolBarManager.add(new Mkdir());
        ToolBar toolBar=toolBarManager.createControl(this.composite);
        toolBar.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,10,1));
        toolBar.setBackground(new Color(null,new RGB(220,220,220)));
        toolBar.pack();
        //
        table=new Table(this.composite,SWT.NONE);
        this.table.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,10,1));
        table.setHeaderVisible(true);
        createTableColumns(new String[]{"name","date","type","size"});
        table.addPaintListener(new PaintListener() {

            @Override
            public void paintControl(PaintEvent arg0) {
                // TODO Auto-generated method stub
                TableColumn[] columns = table.getColumns();
                int clientWidth = table.getBounds().width;
                for(int i=0;i<columns.length;i++){
                    columns[i].setWidth((clientWidth)/columns.length);
                }
            }
        });
        this.table.pack();
    }

    public void createTableColumns(String[] tableNames){
        for(String name:tableNames)
        {
            TableColumn tableColumn=new TableColumn(this.table,SWT.NONE);
            tableColumn.setText(name);
            tableColumn.setMoveable(true);
            tableColumn.pack();
        }
    }

    @Override
    protected void checkSubclass() {
    }
}
