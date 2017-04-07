package com.geoway.hdfsbrowser.app.windows;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.config.AppConfiguration;
import com.geoway.hdfsbrowser.service.container.ConnectionContainer;
import com.geoway.hdfsbrowser.util.ColorUtils;
import com.geoway.hdfsbrowser.util.NetworkUtils;
import org.apache.log4j.Logger;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by USER on 2017/4/7.
 */
public class ConnectionWindow extends ApplicationWindow {

    private static final Logger LOGGER=Logger.getLogger(ConnectionWindow.class);

    public static final String BTN_CONN_TEST="conn_test";
    public static final String BTN_CONN="conn";
    public static final String BTN_CANCEL="conn_cancel";

    private Shell parent;
    private Text host;
    private Text port;
    private Text user;

    public ConnectionWindow(Shell parentShell) {
        super(parentShell);
        this.parent=parentShell;
    }

    @Override
    protected void configureShell(Shell shell) {
        shell.setSize(300,170);
    }

    @Override
    protected Control createContents(Composite parent) {
        parent.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        parent.setLayout(new GridLayout(3,false));
        host=createInput(parent,"HDFS地址");
        port=createInput(parent,"HDFS端口");
        user=createInput(parent,"HDFS用户");
        host.setText("192.98.19.11");
        port.setText("9000");
        user.setText("hadoop");
        createButtons(parent);
        return parent;
    }

    public Text createInput(Composite parent,String name)
    {
        Label label=new Label(parent, SWT.NONE);
        label.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        label.setText(name);
        label.setLayoutData(new GridData(SWT.LEFT,SWT.CENTER,false,false,1,1));
        //
        Text text=new Text(parent,SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,1));
        text.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        return text;
    }

    public void createButtons(Composite parent)
    {
        ConnectionButtonAdapter adapter=new ConnectionButtonAdapter();
        Composite composite=new Composite(parent,SWT.NONE);
        composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,3,1));
        composite.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        composite.setLayout(new GridLayout(4, true));
        GridData btnGridData=new GridData(SWT.FILL,SWT.CENTER,false,false,1,1);
        Button testConn=new Button(composite,SWT.NONE);
        testConn.setText("测试连接");
        testConn.setSize(100,100);
        testConn.setLayoutData(btnGridData);
        testConn.addSelectionListener(adapter);
        testConn.setData("id",BTN_CONN_TEST);
        Button conn=new Button(composite,SWT.NONE);
        conn.setText("连接");
        conn.setLayoutData(btnGridData);
        conn.addSelectionListener(adapter);
        conn.setData("id",BTN_CONN);
        Button cancel=new Button(composite,SWT.NONE);
        cancel.setText("取消");
        cancel.setLayoutData(btnGridData);
        cancel.addSelectionListener(adapter);
        cancel.setData("id",BTN_CANCEL);
    }

    public String[] getParams()
    {
        String hostStr=host.getText();
        String portStr=port.getText();
        String userStr=user.getText();
        if(hostStr==null || hostStr.isEmpty() || portStr==null || portStr.isEmpty() || userStr==null || userStr.isEmpty())
        {
            return null;
        }
       return new String[]{hostStr, portStr, userStr};
    }

    public void testConnection()
    {
        MessageBox messageBox=new MessageBox(Display.getCurrent().getActiveShell());
        messageBox.setText("测试连接");
        String[] params=getParams();
        if(params==null)
        {
            messageBox.setMessage("参数为填写完整");
        }
        else {
            boolean flag=NetworkUtils.telnet(params[0],params[1]);
            if(flag)
            {
                LOGGER.info("can connection the server");
                messageBox.setMessage("可以连接");
            }
            else
            {
                messageBox.setMessage("无法连接");
            }
        }
        messageBox.open();
    }

    public void connection()
    {
        HDFSBrowserWindow window=HDFSBrowserWindow.GetApp();
        if(window.isConnected())
        {
            MessageBox messageBox=new MessageBox(this.getShell());
            messageBox.setMessage("请先断开已有连接");
            return;
        }
        //
        String[] params=getParams();
        if(params==null)
        {
            MessageBox messageBox=new MessageBox(this.getShell());
            messageBox.setMessage("请将参数填写完整");
            return;
        }
        AppConfiguration configuration=AppConfiguration.GetAppConfiguration();
        configuration.clear();
        configuration.setValue(AppConfiguration.HDFS_HOST,params[0]);
        configuration.setValue(AppConfiguration.HDFS_PORT,params[1]);
        configuration.setValue(AppConfiguration.HDFS_USER,params[2]);
        window.connection();
        ConnectionContainer connectionContainer=ConnectionContainer.GetConnectionContainer();
        connectionContainer.addConnection(params[0],params[1],params[2]);
        this.close();
    }

    public void cancel()
    {
        this.close();
    }

    public class  ConnectionButtonAdapter extends SelectionAdapter
    {
        @Override
        public void widgetSelected(SelectionEvent selectionEvent) {
            Button button= (Button) selectionEvent.getSource();
            String buttonId= (String) button.getData("id");
            switch (buttonId)
            {
                case BTN_CONN_TEST:
                    testConnection();
                    break;
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
