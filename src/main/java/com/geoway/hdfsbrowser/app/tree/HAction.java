package com.geoway.hdfsbrowser.app.tree;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.table.HTable;
import com.geoway.hdfsbrowser.app.table.HTableViewer;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Tree;

/**
 * Created by wozipa on 2017/4/9.
 */
public class HAction extends Action {

    protected HDFSCore hdfsCore=null;
    protected HDFSBrowserWindow window;
    protected HTreeViewer tree;
    protected HTableViewer table;

    public HAction(HTreeViewer hTreeViewer)
    {
        super();
        this.window=HDFSBrowserWindow.GetApp();
        this.hdfsCore=window.getHdfsCore();
        this.tree=hTreeViewer;
        this.table=window.getTable();
        //
    }
}
