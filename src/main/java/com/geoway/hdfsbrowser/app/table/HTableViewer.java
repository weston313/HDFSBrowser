package com.geoway.hdfsbrowser.app.table;

import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import javax.ws.rs.HEAD;

/**
 * Created by wozipa on 2017/4/8.
 */
public class HTableViewer extends TableViewer {

    private static final Logger LOGGER=Logger.getLogger(HTableViewer.class);
    private static final String[] HEADERS={"文件名称","修改时间","文件类型","文件大小"};
    public static final int NAME_ID=0;
    public static final int TIME_ID=1;
    public static final int TYPE_ID=2;
    public static final int SIZE_ID=3;

    private String path;
    private Table table=null;
    private HDFSCore hdfsCore;

    public HTableViewer(Composite parent,int style) {
        super(parent,style);
        table=this.getTable();
        this.setContentProvider(new HTableContentProvider());
        this.setLabelProvider(new HTableLabelProvider());
        //
        initDisplay();
        createHeader();
        addListern();

    }

    public String getPath()
    {
        return path;
    }

    public void setHdfsCore(HDFSCore hdfsCore)
    {
        this.hdfsCore=hdfsCore;
    }

    public void initDisplay()
    {
        table.setHeaderVisible(true);
        table.setLinesVisible(false);
    }

    public void createHeader()
    {
        Table table=this.getTable();
        for(int i=0;i<HEADERS.length;i++)
        {
            TableColumn tableColumn=new TableColumn(table, SWT.LEFT);
            tableColumn.setText(HEADERS[i]);
            table.getColumn(i).pack();
        }
    }

    public void addListern()
    {
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
    }

    public void mkdir()
    {
        HFile hFile=new HFile();
        TableItem item=new TableItem(table,SWT.NONE);
        item.setText(hFile.getItems());
        //
    }

    public class HTableContentProvider implements IStructuredContentProvider
    {

        @Override
        public Object[] getElements(Object o) {
            return new Object[0];
        }

        @Override
        public void dispose() {

        }

        @Override
        public void inputChanged(Viewer viewer, Object o, Object o1) {
        }
    }
}
