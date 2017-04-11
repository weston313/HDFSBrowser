package com.geoway.hdfsbrowser.app.treeviewer.action.impl;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.treeviewer.HTreeNode;
import com.geoway.hdfsbrowser.app.treeviewer.HTreeViewer;
import com.geoway.hdfsbrowser.app.windows.WindowButtonListen;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;

/**
 * Created by USER on 2017/4/11.
 */
public class TreeAction extends Action {

    private static final Logger LOGGER=Logger.getLogger(TreeAction.class);

    private HTreeViewer tree;
    private HDFSCore hdfsCore;
    public TreeAction(HTreeViewer treeViewer,HDFSCore hdfsCore)
    {
        super();
        this.tree=treeViewer;
        this.hdfsCore=hdfsCore;
        this.hdfsCore= HDFSBrowserWindow.GetApp().getHdfsCore();
    }
}
