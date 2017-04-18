package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.dialog.ConnectionError;
import com.geoway.hdfsbrowser.app.dialog.UnselectedError;
import com.geoway.hdfsbrowser.app.view.table.HTableViewer;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.service.container.ClipboardContainer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Move extends Action {

    public Move()
    {
        super();
        this.setText("移动");
        this.setAccelerator(SWT.CTRL+SWT.SHIFT+'M');
        this.setEnabled(true);
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
        IStructuredSelection selection= (IStructuredSelection) tableViewer.getSelection();
        if(selection.isEmpty())
        {
            UnselectedError.getERROR();
            return;
        }
        //
        HNode node= (HNode) selection.getFirstElement();
        ClipboardContainer.GetContainer().add(node,ClipboardContainer.TYPE_MV);
    }
}
