package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.dialog.ConnectionError;
import com.geoway.hdfsbrowser.app.view.ViewerMutual;
import com.geoway.hdfsbrowser.app.view.table.HTableViewer;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.service.container.PathsContainer;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Prev extends Action {

    private static final Logger LOGGER=Logger.getLogger(Prev.class);

    private PathsContainer container=null;
    private ViewerMutual proxy=null;
    private PathsContainer pathsContainer=null;

    public Prev()
    {
        super( );
        this.setText("前进");
        this.setToolTipText("前进");
//        this.setImageDescriptor();
        this.container=PathsContainer.getContainer();
        this.proxy=ViewerMutual.getMutual();
        this.pathsContainer=PathsContainer.getContainer();
    }

    @Override
    public void run() {
        HDFSBrowserWindow window = HDFSBrowserWindow.GetApp();
        if (!window.isConnected()) {
            ConnectionError.getERROR();
            return;
        }
        //
        HNode node=pathsContainer.prev();
        if(node==null)
        {
            MessageDialog.openInformation(window.getShell(),"提示","已经到达最顶端");
            return;
        }
        proxy.select(node,true,true);

    }
}
