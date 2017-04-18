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
public class Copy extends Action {

    public Copy(){
        this.setEnabled(true);
        this.setText("复制");
        this.setAccelerator(SWT.CTRL+'C');
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
        IStructuredSelection selection= (IStructuredSelection) tableViewer.getSelection();
        if(selection.isEmpty())
        {
            UnselectedError.getERROR();
            return;
        }
        //
        HNode node= (HNode) selection.getFirstElement();
        ClipboardContainer.GetContainer().add(node,ClipboardContainer.TYPE_CP);
    }
}
