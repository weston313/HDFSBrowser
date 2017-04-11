package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.treeviewer.HTreeNode;
import com.geoway.hdfsbrowser.app.treeviewer.HTreeViewer;
import com.geoway.hdfsbrowser.service.container.PathsContainer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Back extends Action {

    private PathsContainer container=null;

    public Back()
    {
        this(null);
    }

    public Back(HTreeViewer treeViewer)
    {
        super();
        this.setText("后退");
        this.setToolTipText("后退");
        this.setEnabled(true);
//        this.setImageDescriptor();
//        container=PathsContainer.getContainer();
    }
}
