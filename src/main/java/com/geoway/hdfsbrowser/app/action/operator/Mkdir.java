package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.dialog.ConnectionError;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.app.view.ViewerMutual;
import com.geoway.hdfsbrowser.app.view.table.HTableViewer;
import com.geoway.hdfsbrowser.app.view.tree.HTreeViewer;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import com.geoway.hdfsbrowser.util.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * Created by wozipa on 2017/4/5.
 */
public class Mkdir extends Action {

    private static final Logger LOGGER=Logger.getLogger(Mkdir.class);

    private static final String TITLE="新建文件夹";
    private static final String NAME="请输入";
    private static final String DEFAULT_VALUE="默认值";

    private HTableViewer hTableViewer=null;
    private ViewerMutual proxy=null;

    public Mkdir()
    {
        super();
        this.setText("创建文件夹");
        this.setAccelerator(SWT.CTRL+SWT.SHIFT+'M');
        this.setEnabled(true);

        this.proxy=ViewerMutual.getMutual();
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
        if(hTableViewer==null)
        {
            hTableViewer=HDFSBrowserWindow.GetApp().getTable();
        }
        //
        InputDialog inputDialog=new InputDialog(HDFSBrowserWindow.GetApp().getShell(),"新建文件夹","请输入","新建文件夹",null);
        inputDialog.open();
        String name=inputDialog.getValue();
        HDFSCore hdfsCore=window.getHdfsCore();
        if(hdfsCore!=null)
        {
            HNode node=hTableViewer.getDataNode();
            String path=node.getPath();
            try {
                String dirPath= FileUtils.JointPath(path,name);
                hdfsCore.mkdirDirectory(dirPath);
                proxy.fresh(node,true,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
