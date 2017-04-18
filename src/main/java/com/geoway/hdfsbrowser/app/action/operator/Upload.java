package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.dialog.ConnectionError;
import com.geoway.hdfsbrowser.app.view.table.HTableViewer;
import com.geoway.hdfsbrowser.service.container.ThreadContainer;
import com.geoway.hdfsbrowser.service.container.UploadsContainer;
import com.geoway.hdfsbrowser.service.thread.UploadThread;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import javax.swing.text.html.HTML;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Upload extends Action {

    private static final Logger LOGGER=Logger.getLogger(Upload.class);

    private UploadsContainer container=null;

    public Upload()
    {
        super();
        this.setText("上传");
        this.setEnabled(true);
        this.setAccelerator(SWT.CTRL+'U');
        container=UploadsContainer.GetContainer();
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
        //'
        HTableViewer tableViewer=window.getTable();
        FileDialog fileDialog=new FileDialog(window.getShell(),SWT.MULTI);
        fileDialog.open();
        String[] names=fileDialog.getFileNames();
        String path=fileDialog.getFilterPath();
        for(String name:names)
        {
            UploadThread thread=new UploadThread(path+"\\"+name,tableViewer.getDataNode().getPath()+"\\"+name,window.getHdfsCore());
            thread.run();
            String id=thread.getTag();
            container.add(id,thread.getInfo());
        }
        //
    }
}
