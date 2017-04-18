package com.geoway.hdfsbrowser.app.tab;

import com.geoway.hdfsbrowser.app.action.operator.Download;
import com.geoway.hdfsbrowser.service.container.DownloadsContainer;
import com.geoway.hdfsbrowser.service.container.UploadsContainer;
import com.geoway.hdfsbrowser.service.thread.info.ThreadInfo;
import com.geoway.hdfsbrowser.util.ColorUtils;
import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by USER on 2017/4/16.
 */
public class DownloadListTab extends TabItem {

    private static final Logger LOGGER=Logger.getLogger(DownloadsContainer.class);

    private static final String[] HEADERS={"任务ID","任务进度","任务类型","文件列表","任务状态","操作"};

    private Table table=null;
    private Composite composite;
    private DownloadsContainer container;

    public DownloadListTab(TabFolder parent, int style) {
        super(parent, style);
        this.setText("下载列表");
        //
        this.composite=new Composite(parent,style);
        this.composite.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        this.setControl(composite);
        composite.setLayout(new FillLayout());
        //
        this.container= DownloadsContainer.GetContainer();
        initDisplay();
    }

    public void initDisplay()
    {
        this.table = new Table(this.composite, SWT.FULL_SELECTION);
        this.table.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        this.table.setHeaderVisible(true);
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
        this.table.pack();
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
}
