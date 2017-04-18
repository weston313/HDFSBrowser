package com.geoway.hdfsbrowser.app.view.table;

import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.app.view.ViewerMutual;
import com.geoway.hdfsbrowser.app.view.tree.HTreeRootNode;
import com.geoway.hdfsbrowser.service.container.PathsContainer;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.hadoop.fs.FileStatus;
import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

/**
 * Created by wozipa on 2017/4/8.
 */
public class HTableViewer extends TableViewer {

    private static final Logger LOGGER = Logger.getLogger(HTableViewer.class);
    private static final String[] HEADERS = {"文件名称", "修改时间", "文件类型", "文件大小"};
    public static final int NAME_ID = 0;
    public static final int TIME_ID = 1;
    public static final int TYPE_ID = 2;
    public static final int SIZE_ID = 3;

    private HNode dataNode;
    private Table table = null;
    private HDFSCore hdfsCore;
    private ViewerMutual proxy;
    private PathsContainer pathsContainer;

    public HTableViewer(Composite parent, int style) {
        super(parent, style);
        table = this.getTable();
//        this.container=PathsContainer.getContainer();
        this.setContentProvider(new HTableContentProvider());
        this.setLabelProvider(new HTableLabelProvider());
        //
        this.proxy=ViewerMutual.getMutual();
        this.proxy.setTableViewer(this);
        this.pathsContainer=PathsContainer.getContainer();
        initDisplay();
        createHeader();
        addListern();
    }

    public HNode getDataNode() {
        return dataNode;
    }

    public void setDataNode(HNode dataNode) {
        this.dataNode = dataNode;
    }

    public void show(HNode parent)
    {
        try {
            String path=parent.getPath();
            FileStatus[] fileStatuses = hdfsCore.list(path);
            this.table.removeAll();
            for (FileStatus fileStatus : fileStatuses) {
                TableItem item = new TableItem(this.table, SWT.NONE);
                HNode node= HNode.FromFileStatus(parent,fileStatus);
                item.setText(node.toHFile());
                item.setData(HNode.FromFileStatus(parent,fileStatus));
            }
            this.dataNode=parent;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setHdfsCore(HDFSCore hdfsCore) {
        this.hdfsCore = hdfsCore;
    }

    public void initDisplay() {
        table.setHeaderVisible(true);
        table.setLinesVisible(false);
    }

    public void createHeader() {
        Table table = this.getTable();
        for (int i = 0; i < HEADERS.length; i++) {
            TableColumn tableColumn = new TableColumn(table, SWT.LEFT);
            tableColumn.setText(HEADERS[i]);
            table.getColumn(i).pack();
        }
    }

    public void addListern() {
        table.addPaintListener(new PaintListener() {

            @Override
            public void paintControl(PaintEvent arg0) {
                // TODO Auto-generated method stub
                TableColumn[] columns = table.getColumns();
                int clientWidth = table.getBounds().width;
                for (int i = 0; i < columns.length; i++) {
                    columns[i].setWidth((clientWidth) / columns.length);
                }
            }
        });
        //add context menu to table
        this.getControl().setMenu(new HTableContextMenu().createContextMenu(this.getControl()));
    }

    @Override
    protected void handleOpen(SelectionEvent event) {
        HNode node= (HNode) event.item.getData();
        try {
            if(hdfsCore.isDirectory(node.getPath()))
            {
                proxy.select(node,true,true);
                pathsContainer.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mkdir() {
        HFile hFile = new HFile();
        TableItem item = new TableItem(table, SWT.NONE);
        item.setText(hFile.getItems());
        //
    }

    public class HTableContentProvider implements IStructuredContentProvider {

        @Override
        public Object[] getElements(Object o) {
            return new HNode[]{HTreeRootNode.GetRootNode()};
        }

        @Override
        public void dispose() {

        }

        @Override
        public void inputChanged(Viewer viewer, Object o, Object o1) {

        }
    }

    /**
     * free the table and check it
     */
    public void fresh()
    {
        String path=this.dataNode.getPath();
        try {
            FileStatus[] fileStatuses = hdfsCore.list(path);
            this.table.removeAll();
            for (int i = 0; i < fileStatuses.length; i++) {
                FileStatus fileStatus = fileStatuses[i];
                String[] item = HFile.filestatusToItem(fileStatus);
                TableItem row=new TableItem(this.table,SWT.NONE);
                row.setText(item);
                row.setData(HNode.FromFileStatus(this.dataNode,fileStatus));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
