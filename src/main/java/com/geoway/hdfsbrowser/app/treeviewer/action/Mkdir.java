package com.geoway.hdfsbrowser.app.treeviewer.action;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.treeviewer.HTreeViewer;
import com.geoway.hdfsbrowser.app.treeviewer.action.impl.TreeAction;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

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
    }
}
