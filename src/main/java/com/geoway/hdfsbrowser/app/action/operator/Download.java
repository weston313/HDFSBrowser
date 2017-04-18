package com.geoway.hdfsbrowser.app.action.operator;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.dialog.ConnectionError;
import com.geoway.hdfsbrowser.app.dialog.UnselectedError;
import com.geoway.hdfsbrowser.app.view.table.HTableViewer;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import com.geoway.hdfsbrowser.service.transform.DownloadThread;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Download extends Action {

    private static final Logger LOGGER=Logger.getLogger(Download.class);

    public Download()
    {
        super();
        this.setText("下载");
        this.setEnabled(true);
        this.setAccelerator(SWT.CTRL+'D');
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
        HTableViewer tableViewer=window.getTable();
        IStructuredSelection selection= (IStructuredSelection) tableViewer.getSelection();
        if(selection.isEmpty())
        {
            LOGGER.info("the selection is null");
            UnselectedError.getERROR();
            return;
        }
        //
        DirectoryDialog directoryDialog=new DirectoryDialog(window.getShell(),SWT.NONE|SWT.SINGLE);
        directoryDialog.open();
        String directory=directoryDialog.getFilterPath();
        HDFSCore hdfsCore=window.getHdfsCore();
        HNode node= (HNode) selection.getFirstElement();
        String src=node.getPath();
        LOGGER.info("the directory is "+directory);
        LOGGER.info("the hdfs file is "+src);
        DownloadThread downloadThread=new DownloadThread(src,directory,hdfsCore);
        downloadThread.run();
    }

//    public void downloadRecursy(String directory,String path,HDFSCore hdfsCore)
//    {
//        try {
//            if(hdfsCore.isDirectory(path))
//            {
//                FileStatus[] fileStatuses=hdfsCore.list(path);
//                for(FileStatus fileStatus:fileStatuses)
//                {
//                    String childDir=directory+"\\"+fileStatus.getPath().getName();
//                    downloadRecursy(childDir,fileStatus.getPath().toString(),hdfsCore);
//                }
//            }
//            else
//            {
//                File dir=new File(directory);
//                if(!dir.exists())
//                {
//                    dir.mkdir();
//                }
//                String name=new Path(path).getName();
//                String localFilePath=directory+"\\"+name;
//                File localFile=new File(localFilePath);
//                localFile.createNewFile();
//                FileOutputStream writer=new FileOutputStream(localFile);
//                InputStream reader=hdfsCore.open(path);
//                byte[] buffer=new byte[4096];
//                int oef=-1;
//                while((oef=reader.read(buffer))>0)
//                {
//
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
