package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.treeviewer.HTreeNode;
import com.geoway.hdfsbrowser.app.treeviewer.HTreeViewer;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Delete extends Action {

    private static final Logger LOGGER=Logger.getLogger(Delete.class);

    public Delete()
    {
        this(null);
    }

    public Delete(HTreeViewer treeViewer)
    {
        super();
        this.setText("删除");
        this.setEnabled(true);
        this.setAccelerator(SWT.CTRL+SWT.SHIFT+'D');
    }
}
