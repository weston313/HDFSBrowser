package com.geoway.hdfsbrowser.app.dialog;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by USER on 2017/4/15.
 */
public class ConnectionError extends MessageBox {

    private static final Logger LOGGER=Logger.getLogger(ConnectionError.class);

    private static ConnectionError ERROR=null;

    public static ConnectionError getERROR()
    {
        if(ERROR==null)
        {
            synchronized (ConnectionError.class)
            {
                if(ERROR==null)
                {
                    ERROR=new ConnectionError();
                }
            }
        }
        return ERROR;
    }

    private ConnectionError() {
        super(HDFSBrowserWindow.GetApp().getShell(), SWT.ICON_WARNING);
        this.setText("提示");
        this.setMessage("未连接");
        this.open();
    }

    @Override
    protected void checkSubclass() {

    }
}
