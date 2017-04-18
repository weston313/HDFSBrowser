package com.geoway.hdfsbrowser.app.tab;

import com.geoway.hdfsbrowser.app.action.operator.Delete;
import com.geoway.hdfsbrowser.service.container.UploadsContainer;
import com.geoway.hdfsbrowser.service.thread.info.ThreadInfo;
import com.geoway.hdfsbrowser.util.ColorUtils;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.internal.provisional.action.ToolBarManager2;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.List;
import sun.security.krb5.internal.APRep;

import java.util.*;

/**
 * Created by USER on 2017/4/16.
 */
public class UploadListTab extends TabItem {

    private static final Logger LOGGER=Logger.getLogger(UploadListTab.class);

    private static final String[] HEADERS={"任务ID","任务进度","任务类型","文件列表","任务状态","操作"};

    private UploadsContainer container=null;

    private Composite composite;
    private Table table=null;

    public UploadListTab(TabFolder parent, int style) {
        super(parent, style);
        this.setText("上传列表");
        //
        this.composite=new Composite(parent,style);
        this.composite.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        this.setControl(composite);
        composite.setLayout(new GridLayout(1,false));
        //
        this.container=UploadsContainer.GetContainer();
        initDisplay();
    }

    public void initDisplay()
    {
        initMenu();
        this.table = new Table(this.composite, SWT.FULL_SELECTION);
        this.table.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        this.table.setHeaderVisible(true);
        this.table.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
        for (String header:HEADERS)
        {
            TableColumn tableColumn=new TableColumn(this.table,SWT.NONE);
            tableColumn.setMoveable(true);
            tableColumn.setText(header);
            tableColumn.pack();
        }
        //
        this.table.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                TableColumn[] columns=table.getColumns();
                int width=table.getBounds().width;
                for(TableColumn column:columns)
                {
                    column.setWidth(width/columns.length);
                }
            }
        });
        ;
        this.table.pack();
    }

    public void initMenu()
    {
        //
        Action delete=new Action() {
            @Override
            public void setText(String text) {
                super.setText("删除");
            }

            @Override
            public void run() {
                table.remove(table.getSelectionIndex());
            }
        };
        //
        ToolBarManager toolBarManager=new ToolBarManager(SWT.NONE);
        toolBarManager.createControl(composite);
    }

    /**
     * load data into the data list
     */
    public void loadData()
    {
        java.util.List<ThreadInfo> list=container.list();
        for(ThreadInfo info:list)
        {
            String[] items=info.getItems();
            TableItem item=new TableItem(this.table,SWT.NONE);
            item.setText(items);
        }
    }

    @Override
    protected void checkSubclass() {

    }

    public class Delete extends Action
    {
        public Delete()
        {
            super();
            this.setText("删除");
        }

        @Override
        public void run() {
            super.run();
        }
    }
}
