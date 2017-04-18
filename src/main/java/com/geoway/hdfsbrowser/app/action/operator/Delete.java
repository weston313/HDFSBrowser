package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.dialog.ConnectionError;
import com.geoway.hdfsbrowser.app.dialog.UnselectedError;
import com.geoway.hdfsbrowser.app.view.ViewerMutual;
import com.geoway.hdfsbrowser.app.view.table.HTableViewer;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import javafx.scene.control.Tab;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Delete extends Action {

    private static final Logger LOGGER=Logger.getLogger(Delete.class);

    private HTableViewer tableViewer;
    private ViewerMutual proxy;

    public Delete()
    {
        super();
        this.setText("删除");
        this.setEnabled(true);
        this.setAccelerator(SWT.CTRL+SWT.SHIFT+'D');

        this.proxy=ViewerMutual.getMutual();
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
        if(tableViewer==null)
        {
            tableViewer=window.getTable();
        }
        //
        IStructuredSelection selection= (IStructuredSelection) tableViewer.getSelection();
        if(selection.isEmpty())
        {
            UnselectedError.getERROR();
            return;
        }
        boolean flag=MessageDialog.openConfirm(window.getShell(),"确认","是否删除该文件");
        HDFSCore hdfsCore=window.getHdfsCore();
        if(flag)
        {
            HNode node= (HNode) selection.getFirstElement();
            String path=node.getPath();
            try {
                hdfsCore.delete(path);
                proxy.fresh(tableViewer.getDataNode(),true,true);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
