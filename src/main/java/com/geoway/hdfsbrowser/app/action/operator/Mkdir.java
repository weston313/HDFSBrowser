package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.table.HTableViewer;
import com.geoway.hdfsbrowser.app.treeviewer.HTreeNode;
import com.geoway.hdfsbrowser.app.treeviewer.HTreeViewer;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

/**
 * Created by wozipa on 2017/4/5.
 */
public class Mkdir extends Action {

    private static final Logger LOGGER=Logger.getLogger(Mkdir.class);

    private static final String TITLE="新建文件夹";
    private static final String NAME="请输入";
    private static final String DEFAULT_VALUE="默认值";

    private HTreeViewer hTreeViewer=null;
    private HTableViewer hTableViewer=null;


    public Mkdir()
    {
        super();
        this.setText("创建文件夹");
        this.setAccelerator(SWT.CTRL+SWT.SHIFT+'M');
        this.setEnabled(true);
//        this.setImageDescriptor();
    }

//    @Override
//    public boolean work(ColumnViewer columnViewer,String path, boolean isTree) {
//        if(path==null || path.isEmpty())
//        {
//            MessageBox messageBox=new MessageBox(window.getShell());
//            messageBox.setMessage("Error happend");
//            messageBox.open();
//            return false;
//        }
//        InputDialog dialog=new InputDialog(window.getShell(), TITLE, NAME, DEFAULT_VALUE,null);
//        dialog.open();
//        String name=dialog.getValue();
//        String filePath= path+name;
//        try {
//            if(hdfsCore.exist(filePath))
//            {
//                MessageBox messageBox=new MessageBox(window.getShell());
//                return false;
//            }
//            LOGGER.info("start to create the directory "+path);
//            hdfsCore.mkdirDirectory(filePath);
//            if(isTree)
//            {
//                //add node to tree
//                HTreeNode parent= (HTreeNode) tree.getTree().getSelection()[0].getData();
//                HTreeNode child=new HTreeNode();
//                child.setName(name);
//                child.setPath(path);
//                child.setParent(parent);
//                tree.add(parent,child);
//                tree.refresh(parent);
//                //the tree action
//                return  true;
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
