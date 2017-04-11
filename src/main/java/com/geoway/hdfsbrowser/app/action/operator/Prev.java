package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.treeviewer.HTreeNode;
import com.geoway.hdfsbrowser.app.treeviewer.HTreeViewer;
import com.geoway.hdfsbrowser.service.container.PathsContainer;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.*;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Prev extends Action {

    private static final Logger LOGGER=Logger.getLogger(Prev.class);

    private PathsContainer container=null;

    public Prev()
    {
        this(null);
    }

    public Prev(HTreeViewer treeViewer)
    {
        super( );
        this.setText("前进");
        this.setToolTipText("前进");
//        this.setImageDescriptor();
        this.container=PathsContainer.getContainer();
    }
}
