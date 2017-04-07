package com.geoway.hdfsbrowser.app.windows;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.action.menu.ConnectionAction;
import com.geoway.hdfsbrowser.app.action.menu.NewAction;
import com.geoway.hdfsbrowser.app.action.operator.Delete;
import com.geoway.hdfsbrowser.app.config.AppConfiguration;
import com.geoway.hdfsbrowser.service.container.ConnectionContainer;
import com.geoway.hdfsbrowser.util.ColorUtils;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;


/**
 * Created by USER on 2017/4/7.
 */
public class ConnectionListWindow extends ApplicationWindow {

    private static final Logger LOGGER=Logger.getLogger(ConnectionListWindow.class);

    private static final String BTN_CLEAR="list_clear_btn";
    private static final String BTN_CONN="list_conn_btn";
    private static final String BTN_CANCEL="list_cancel_btn";
    private static final String BTN_DELETE="list_delete_btn";


    private Shell parent;
    private Composite composite=null;
    private Table table=null;

    public ConnectionListWindow(Shell parentShell) {
        super(parentShell);
        this.parent= parentShell;
        this.parent.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        //
        this.addToolBar(SWT.NONE);
    }

    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setSize(400,300);
        shell.setText("连接列表");
    }

    @Override
    protected ToolBarManager createToolBarManager(int style) {
        LOGGER.info("create the toolbar for the windows");
        ToolBarManager toolBarManager=new ToolBarManager(style);
        toolBarManager.add(new NewAction());
        toolBarManager.add(new Delete());
        return toolBarManager;
    }

    @Override
    protected Control createContents(Composite parent) {
        LOGGER.info("create the content of the windows");
        this.composite=new Composite(parent,SWT.NONE);
        this.composite.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        this.composite.setLayout(new GridLayout(5,true));

        //
        createTable(this.composite);
        createButtons(this.composite);
        return composite;
    }

    public void createTable(final Composite parent)
    {
        this.table=new Table(parent,SWT.NONE);
        this.table.setHeaderVisible(true);
        this.table.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,5,1));
        String[] headers=new String[]{"","IP地址","端口号","用户名"};
        for(String header:headers)
        {
            TableColumn tableColumn=new TableColumn(this.table,SWT.NONE);
            tableColumn.setText(header);
            tableColumn.setMoveable(true);
            tableColumn.pack();
        }
        //
        this.table.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent paintEvent) {
                TableColumn[] columns = table.getColumns();
                int clientWidth = table.getBounds().width;
                for(int i=0;i<columns.length;i++){
                    columns[i].setWidth((clientWidth)/columns.length);
                }
            }
        });
        //
        ConnectionContainer connectionContainer=ConnectionContainer.GetConnectionContainer();
        for(ConnectionContainer.Connection connection:connectionContainer.getContainer())
        {
            TableItem item=new TableItem(this.table,SWT.NONE);
            item.setText(new String[]{"",connection.getHost(),connection.getPort(),connection.getUser()});
        }
        //
    }

    public void createButtons(Composite parent)
    {
        LOGGER.info("create the buttons ");
        GridData gridData=new GridData(SWT.FILL,SWT.FILL,false,false,1,1);
        for(int i=0;i<3;i++)
        {
            Label label=new Label(parent,SWT.NONE);
            label.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
            label.setLayoutData(gridData);
        }
        ConnListBtnAdater adater=new ConnListBtnAdater();
        //
        Button connction=new Button(parent,SWT.NONE);
        connction.setLayoutData(gridData);
        connction.setText("连接");
        connction.setData("id",BTN_CONN);
        connction.addSelectionListener(adater);
        //
        Button cancel=new Button(parent,SWT.NONE);
        cancel.setLayoutData(gridData);
        cancel.setText("取消");
        cancel.setData("id",BTN_CANCEL);
        cancel.addSelectionListener(adater);
    }

    public void connection()
    {
        HDFSBrowserWindow app=HDFSBrowserWindow.GetApp();
        if(app.isConnected())
        {
            MessageBox box=new MessageBox(Display.getCurrent().getActiveShell());
            box.setMessage("连接中");
            box.open();
            return;
        }
        //
        TableItem tableItem = this.table.getSelection()[0];
        String host=tableItem.getText(1);
        String port=tableItem.getText(2);
        String user=tableItem.getText(3);
        AppConfiguration configuration=AppConfiguration.GetAppConfiguration();
        configuration.clear();
        configuration.setValue(AppConfiguration.HDFS_HOST,host);
        configuration.setValue(AppConfiguration.HDFS_PORT,port);
        configuration.setValue(AppConfiguration.HDFS_USER,user);
        app.connection();
        this.close();
    }

    public void cancel()
    {
        this.close();
    }

    public class  ConnListBtnAdater extends SelectionAdapter
    {
        @Override
        public void widgetSelected(SelectionEvent selectionEvent) {
            Button button= (Button) selectionEvent.getSource();
            String id= (String) button.getData("id");
            switch (id)
            {
                case BTN_CONN:
                    connection();
                    break;
                case BTN_CANCEL:
                    cancel();
                    break;
            }
        }
    }


}
