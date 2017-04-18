package com.geoway.hdfsbrowser.app.view.tree.action;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.app.view.tree.HTreeViewer;
import com.geoway.hdfsbrowser.app.view.tree.action.impl.TreeAction;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Created by USER on 2017/4/11.
 */
public class Mkdir extends TreeAction {

    private static final Logger LOGGER=Logger.getLogger(Mkdir.class);


    public Mkdir(HTreeViewer treeViewer, HDFSCore hdfsCore)
    {
        super(treeViewer,hdfsCore);
        this.setText("新建文件夹");
        this.setEnabled(true);
    }

    @Override
    public void run() {
        //
        InputDialog dialog=new InputDialog(HDFSBrowserWindow.GetApp().getShell(), "创建文件", "请输入名称", "新建文件夹",null);
        dialog.open();
        String name=dialog.getValue();
        IStructuredSelection selection= (IStructuredSelection) tree.getSelection();
        if(!selection.isEmpty())
        {
            HNode node= (HNode) selection.getFirstElement();
            String path=node.getPath();
            try {
                hdfsCore.mkdirDirectory(path+"/"+name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //
            tree.fresh(node);
        }
    }
}
