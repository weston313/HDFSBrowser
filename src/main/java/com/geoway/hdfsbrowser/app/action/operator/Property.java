package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.dialog.ConnectionError;
import com.geoway.hdfsbrowser.app.dialog.InforDialog;
import com.geoway.hdfsbrowser.app.view.table.HTableViewer;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.hadoop.fs.FileStatus;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Property extends Action {

    private static final Logger LOGGER=Logger.getLogger(Property.class);

    public Property()
    {
        super();
        this.setText("属性");
        this.setEnabled(true);
        this.setAccelerator(SWT.CTRL+'P');
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
        HDFSCore hdfsCore=window.getHdfsCore();
        HTableViewer tableViewer=window.getTable();
        InforDialog inforDialog=new InforDialog(HDFSBrowserWindow.GetApp().getShell());
        inforDialog.create();
        IStructuredSelection selection= (IStructuredSelection) tableViewer.getSelection();
        if(!selection.isEmpty())
        {
            HNode node= (HNode) selection.getFirstElement();
            String path=node.getPath();
            try {
                FileStatus fileStatus=hdfsCore.infor(path);
                if(fileStatus!=null)
                {
                    LOGGER.info("set the file status");
                    inforDialog.setFileStatus(fileStatus);
                }
                else {
                    LOGGER.info("the filestatus is null");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        inforDialog.open();
    }
}
