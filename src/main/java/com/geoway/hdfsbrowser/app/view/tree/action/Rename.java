package com.geoway.hdfsbrowser.app.view.tree.action;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.app.view.tree.HTreeViewer;
import com.geoway.hdfsbrowser.app.view.tree.action.impl.TreeAction;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Created by USER on 2017/4/11.
 */
public class Rename extends TreeAction {

    public Rename(HTreeViewer treeViewer, HDFSCore hdfsCore) {
        super(treeViewer, hdfsCore);
        this.setText("重命名");
    }

    @Override
    public void run() {
        InputDialog inputDialog=new InputDialog(HDFSBrowserWindow.GetApp().getShell(),"修改名称","请输入新名称","新建文件夹",null);
        inputDialog.open();
        String name=inputDialog.getValue();
        IStructuredSelection selection= (IStructuredSelection) tree.getSelection();
        if(selection.isEmpty())
        {
           return;
        }
        HNode node= (HNode) selection.getFirstElement();
        String srcPath=node.getPath();
        String destPath=srcPath.replace(node.getName(),name);
        try {
            hdfsCore.rename(srcPath,destPath);
//            tree.fresh((HNode) node.getParentTreeItem().getData());
            //fresh the table content
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
