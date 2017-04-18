package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.dialog.ConnectionError;
import com.geoway.hdfsbrowser.app.view.ViewerMutual;
import com.geoway.hdfsbrowser.app.view.table.HTableViewer;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Fresh extends Action {

    private static final Logger LOGGER=Logger.getLogger(Fresh.class);

    private ViewerMutual proxy=null;

    public Fresh()
    {
        super();
        this.setText("刷新");
        this.setEnabled(true);
        this.setAccelerator(SWT.F5);
//        this.setImageDescriptor();
        this.proxy=ViewerMutual.getMutual();
    }

    @Override
    public void run() {
        if(!HDFSBrowserWindow.GetApp().isConnected())
        {
            ConnectionError.getERROR();
            return;
        }
        //
        
    }
}
