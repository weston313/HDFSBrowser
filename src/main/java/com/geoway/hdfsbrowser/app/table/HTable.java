package com.geoway.hdfsbrowser.app.table;

import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import sun.nio.cs.ext.TIS_620;

/**
 * Created by wozipa on 2017/4/8.
 */
public class HTable extends Table {

    private static final Logger LOGGER=Logger.getLogger(HTable.class);
    private static final String[] HEADERS={"名称","修改时间","文件类型","大小"};

    private HDFSCore hdfsCore;
    private String path;

    public HTable(Composite composite, int i) {
        super(composite, SWT.FULL_SELECTION);
        this.setLinesVisible(false);
        this.setHeaderVisible(true);
        createHeader();
        addListen();
    }

    @Override
    protected void checkSubclass() {

    }

    public void createHeader()
    {
        for(int i=0;i<HEADERS.length;i++)
        {
            TableColumn tableColumn=new TableColumn(this,SWT.LEFT);
            tableColumn.setText(HEADERS[i]);
            this.getColumn(i).pack();
        }
    }

    public void addListen()
    {

        this.addPaintListener(new PaintListener() {

            @Override
            public void paintControl(PaintEvent arg0) {
                // TODO Auto-generated method stub
                TableColumn[] columns = getColumns();
                int clientWidth = getBounds().width;
                for(int i=0;i<columns.length;i++){
                    columns[i].setWidth((clientWidth)/columns.length);
                }
            }
        });
        //
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDoubleClick(MouseEvent mouseEvent) {
                super.mouseDoubleClick(mouseEvent);
            }

            @Override
            public void mouseDown(MouseEvent mouseEvent) {
                super.mouseDown(mouseEvent);
            }

            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                super.mouseUp(mouseEvent);
            }
        });
    }

    public void setHdfsCore(HDFSCore hdfsCore)
    {
        this.hdfsCore=hdfsCore;
    }

}
