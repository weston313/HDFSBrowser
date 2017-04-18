package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.dialog.ConnectionError;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.app.view.ViewerMutual;
import com.geoway.hdfsbrowser.app.view.table.HTableViewer;
import com.geoway.hdfsbrowser.app.view.tree.HTreeRootNode;
import com.geoway.hdfsbrowser.app.view.tree.HTreeViewer;
import com.geoway.hdfsbrowser.service.container.PathsContainer;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Tree;

import javax.swing.text.View;
import java.lang.reflect.Proxy;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Home extends Action {

    private static final Logger LOGGER=Logger.getLogger(Home.class);

    private PathsContainer container;
    private ViewerMutual proxy=null;

    public Home()
    {
        super();
        this.setText("Home");
        this.setEnabled(true);
        this.setToolTipText("家目录");
        container=PathsContainer.getContainer();
        this.proxy=ViewerMutual.getMutual();
//        this.setImageDescriptor();
    }

    @Override
    public void run() {
        HDFSBrowserWindow window=HDFSBrowserWindow.GetApp();
        if(!window.isConnected())
        {
            ConnectionError.getERROR();
            return;
        }
        HTreeRootNode rootNode=HTreeRootNode.GetRootNode();
        proxy.select(rootNode,true,true);
        container.add(HTreeRootNode.GetRootNode());
    }
}
