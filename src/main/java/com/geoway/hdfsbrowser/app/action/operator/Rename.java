package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.dialog.ConnectionError;
import com.geoway.hdfsbrowser.app.dialog.UnselectedError;
import com.geoway.hdfsbrowser.app.view.ViewerMutual;
import com.geoway.hdfsbrowser.app.view.table.HTableViewer;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Rename extends Action {

    private static final Logger LOGGER=Logger.getLogger(Rename.class);

    private ViewerMutual proxy=null;

    public Rename()
    {
        this.setText("重命名");
        this.setEnabled(true);
        this.setAccelerator(SWT.CTRL+SWT.SHIFT+'R');
//        this.setImageDescriptor();
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
        HTableViewer tableViewer=window.getTable();
        IStructuredSelection selection= (IStructuredSelection) tableViewer.getSelection();
        if(selection.isEmpty())
        {
            UnselectedError.getERROR();
           return;
        }
        InputDialog dialog=new InputDialog(window.getShell(),"重命名","请输出新名称","新建文件夹",null);
        dialog.open();
        String name=dialog.getValue();
        HNode node= (HNode) selection.getFirstElement();
        String src=node.getPath();
        String dest=src.replace(node.getName(),name);
        HDFSCore hdfsCore=window.getHdfsCore();
        try {
            hdfsCore.rename(src,dest);
            proxy.fresh(tableViewer.getDataNode(),true,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
