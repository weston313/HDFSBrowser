package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.dialog.ConnectionError;
import com.geoway.hdfsbrowser.app.windows.UDLoadWindow;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

/**
 * Created by USER on 2017/4/16.
 */
public class List extends Action {

    private static final Logger LOGGER=Logger.getLogger(List.class);

    public List()
    {
        super();
        this.setText("任务列表");
        this.setEnabled(true);

    }

    @Override
    public void run() {
        HDFSBrowserWindow window=HDFSBrowserWindow.GetApp();
//        if(!window.isConnected())
//        {
//            ConnectionError.getERROR();
//            return;
//        }
        //
        UDLoadWindow udLoadWindow=new UDLoadWindow(window.getShell());
        udLoadWindow.setBlockOnOpen(true);
        udLoadWindow.open();
    }
}
