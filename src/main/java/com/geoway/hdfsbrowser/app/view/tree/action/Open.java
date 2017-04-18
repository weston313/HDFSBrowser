package com.geoway.hdfsbrowser.app.view.tree.action;

import com.geoway.hdfsbrowser.app.view.tree.HTreeViewer;
import com.geoway.hdfsbrowser.app.view.tree.action.impl.TreeAction;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;

/**
 * Created by USER on 2017/4/15.
 */
public class Open extends TreeAction {

    public Open(HTreeViewer treeViewer, HDFSCore hdfsCore) {
        super(treeViewer, hdfsCore);
        this.setText("打开");
    }

    @Override
    public void run() {

    }
}
