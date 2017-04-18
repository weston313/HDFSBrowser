package com.geoway.hdfsbrowser.app.view.tree.action;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.dialog.InforDialog;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.app.view.tree.HTreeViewer;
import com.geoway.hdfsbrowser.app.view.tree.action.impl.TreeAction;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.hadoop.fs.FileStatus;
import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Created by USER on 2017/4/11.
 */
public class Infor extends TreeAction {

    private static final Logger LOGGER=Logger.getLogger(Infor.class);

    public Infor(HTreeViewer treeViewer, HDFSCore hdfsCore)
    {
        super(treeViewer,hdfsCore);
        this.setText("属性");
    }

    @Override
    public void run() {
        InforDialog inforDialog=new InforDialog(HDFSBrowserWindow.GetApp().getShell());

        inforDialog.create();
        IStructuredSelection selection= (IStructuredSelection) tree.getSelection();
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
