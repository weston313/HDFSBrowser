package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.dialog.ConnectionError;
import com.geoway.hdfsbrowser.app.view.ViewerMutual;
import com.geoway.hdfsbrowser.app.view.table.HTableViewer;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.service.container.ClipboardContainer;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Paste extends Action {

    private static final Logger LOGGER=Logger.getLogger(Paste.class);

    private ViewerMutual proxy=null;

    public Paste()
    {
        super();
        this.setText("粘贴");
        this.setEnabled(true);

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
        //
        HTableViewer tableViewer=window.getTable();
        HDFSCore hdfsCore=window.getHdfsCore();
        //
        HNode node= ClipboardContainer.GetContainer().getNode();
        String src=node.getPath();
        String name=node.getName();
        String dest=tableViewer.getDataNode().getPath()+"/"+name;
        try {
            hdfsCore.copy(src,dest);
            proxy.fresh(tableViewer.getDataNode(),true,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
