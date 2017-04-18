package com.geoway.hdfsbrowser.app.view.tree.action;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.app.view.tree.HTreeViewer;
import com.geoway.hdfsbrowser.app.view.tree.action.impl.TreeAction;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Created by USER on 2017/4/11.
 */
public class Delete extends TreeAction {
    public Delete(HTreeViewer treeViewer, HDFSCore hdfsCore) {
        super(treeViewer, hdfsCore);
        this.setText("删除");
    }

    @Override
    public void run() {
        boolean flag=MessageDialog.openConfirm(HDFSBrowserWindow.GetApp().getShell(),"删除","是否删除该文件");
        if(flag)
        {
            IStructuredSelection selection= (IStructuredSelection) tree.getSelection();
            if(selection.isEmpty())
            {
                return;
            }
            HNode node=(HNode)selection.getFirstElement();
            String path=node.getPath();
            try {
                hdfsCore.delete(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
            tree.fresh(node);
        }

    }
}
