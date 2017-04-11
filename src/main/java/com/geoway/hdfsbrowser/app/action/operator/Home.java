package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.treeviewer.HTreeNode;
import com.geoway.hdfsbrowser.app.treeviewer.HTreeViewer;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Home extends Action {

    private static final Logger LOGGER=Logger.getLogger(Home.class);

    public Home()
    {
        this(null);
    }

    public Home(HTreeViewer treeViewer)
    {
        super();
        this.setText("Home");
        this.setEnabled(true);
        this.setToolTipText("家目录");
//        this.setImageDescriptor();
    }

}
