package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.config.AppConfiguration;
import com.geoway.hdfsbrowser.app.table.HTable;
import com.geoway.hdfsbrowser.app.table.HTableViewer;
import com.geoway.hdfsbrowser.app.tree.HAction;
import com.geoway.hdfsbrowser.app.tree.HTreeNode;
import com.geoway.hdfsbrowser.app.tree.HTreeViewer;
import com.geoway.hdfsbrowser.service.core.HDFSCoreFactory;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;

/**
 * Created by wozipa on 2017/4/5.
 */
public class Mkdir extends HAction {

    private static final Logger LOGGER=Logger.getLogger(Mkdir.class);

    private HTreeViewer hTreeViewer=null;
    private HTableViewer hTableViewer=null;

    public Mkdir()
    {
        this(null);
    }

    public Mkdir(HTreeViewer treeViewer)
    {
        super(treeViewer);
        this.setText("创建文件夹");
        this.setAccelerator(SWT.CTRL+SWT.SHIFT+'M');
        this.setEnabled(true);
//        this.setImageDescriptor();
    }

    @Override
    public void run() {
        //create the directory
        LOGGER.info("create direcotry");
        InputDialog dialog=new InputDialog(window.getShell(),"请输入名称","请输入值","新建文件夹",null);
        dialog.open();
        String name=dialog.getValue();
        String path=null;
        LOGGER.info("the directory is "+name);
        //
        if(this.hTreeViewer!=null)
        {
            IStructuredSelection selection= (IStructuredSelection) tree.getSelection();
            path=((HTreeNode)selection.getFirstElement()).getPath();
        }
        else
        {
            path=table.getPath();
        }
        //
        try {
            hdfsCore.mkdirDirectory(path+"/"+name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
    }
}
