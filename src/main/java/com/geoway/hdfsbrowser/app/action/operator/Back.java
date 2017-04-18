package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.dialog.ConnectionError;
import com.geoway.hdfsbrowser.app.view.ViewerMutual;
import com.geoway.hdfsbrowser.app.view.table.HTableViewer;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.service.container.PathsContainer;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Back extends Action {

    private PathsContainer container=null;
    private ViewerMutual proxy=null;

    public Back()
    {
        super();
        this.setText("后退");
        this.setToolTipText("后退");
        this.setEnabled(true);
//        this.setImageDescriptor();
        container=PathsContainer.getContainer();
        this.proxy=ViewerMutual.getMutual();
    }

    @Override
    public void run() {
        HDFSBrowserWindow window=HDFSBrowserWindow.GetApp();
        if(!window.isConnected())
        {
            ConnectionError.getERROR();
            return;
        }
        //
        HNode node=container.back();
        this.proxy.select(node,true,true);
    }
}
