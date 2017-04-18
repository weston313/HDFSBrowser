package com.geoway.hdfsbrowser.service.transform;

import com.geoway.hdfsbrowser.exception.ParamsRepeatException;
import com.geoway.hdfsbrowser.service.container.DownloadsContainer;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import com.geoway.hdfsbrowser.service.transform.abs.Transform;
import com.geoway.hdfsbrowser.service.transform.abs.TransformInfo;
import com.geoway.hdfsbrowser.util.FileUtils;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by USER on 2017/4/18.
 */
public class DownloadsThread extends Transform {

    private static final Logger LOGGER=Logger.getLogger(DownloadsThread.class);

    private boolean work=true;
    private boolean cache=true;
    private Map<String,TransformInfo> threadInfos=null;

    private HDFSCore hdfsCore;
    private String hdfsDirPath;
    private String[] hdfsFilePaths;
    private String localDirPath;

    public DownloadsThread(String hdfsDirPath,String localDirPath,HDFSCore hdfsCore)
    {
        this.hdfsDirPath=hdfsDirPath;
        this.localDirPath=localDirPath;
        this.hdfsCore=hdfsCore;
        init();
    }

    public DownloadsThread(String[] hdfsFilePaths,String localDirPath,HDFSCore hdfsCore)
    {
        this.hdfsFilePaths=hdfsFilePaths;
        this.localDirPath=localDirPath;
        this.hdfsCore=hdfsCore;
        init();
    }

    @Override
    public void init() {
        super.init();
        this.info.setType(TransformInfo.TYPE.download);
        this.threadInfos=new HashMap<>();
    }

    @Override
    public void run() {
        if((hdfsDirPath==null || hdfsDirPath.isEmpty()) && (hdfsFilePaths==null || hdfsFilePaths.length<=0))
        {
            throw new NullPointerException("the hdfs dir path and hdfs files path is null");
        }
        if(hdfsDirPath!=null && !hdfsDirPath.isEmpty() && hdfsFilePaths!=null && hdfsFilePaths.length>0)
        {
            throw new ParamsRepeatException("the hdfs directory path and hdfs files path repeat");
        }
        if(cache)
        {
//            DownloadsContainer.GetContainer().add(info)
        }
        Map<String,String> fileCup=new HashMap<>();
        if(hdfsFilePaths!=null && hdfsFilePaths.length>0)
        {
            for(String hdfsFilePath:hdfsFilePaths)
            {
                String name=FileUtils.getHdfsName(hdfsFilePath);
                String localFilePath=FileUtils.JointPath(localDirPath,name);
                fileCup.put(hdfsFilePath,localFilePath);
            }
        }
        else if(hdfsDirPath!=null && !hdfsDirPath.isEmpty())
        {
            String name=FileUtils.getHdfsName(hdfsDirPath);
            String childDirPath=FileUtils.JointPath(localDirPath,name);
            recursyHdfsDir(hdfsDirPath,childDirPath,fileCup);
        }
        //
        Set<String> keySet=fileCup.keySet();
        for(String hdfsFilePath:keySet)
        {
            String localFilePath=fileCup.get(hdfsFilePath);
            DownloadThread thread=new DownloadThread(localFilePath,hdfsFilePath,hdfsCore);
            thread.closeCache();
            thread.start();
            threadInfos.put(thread.getThreadId(),info);
        }
    }

    /**
     * recursy hdfs directory to get file list and create the locao file path and put into fileCup
     * @param hdfsDirPath the hdfs directory path
     * @param localDirPath the local directory path
     * @param fileCup the file cup
     */
    public void recursyHdfsDir(String hdfsDirPath,String localDirPath,Map fileCup)
    {
        try {
            FileStatus[] fileStatuses=this.hdfsCore.list(hdfsDirPath);
            for(FileStatus fileStatus:fileStatuses)
            {
                if(fileStatus.isDirectory())
                {
                    String childHdfsPath=fileStatus.getPath().toString();
                    String dirName=fileStatus.getPath().getName();
                    String childLocalPath=FileUtils.JointPath(localDirPath,dirName);
                    recursyHdfsDir(childHdfsPath,childLocalPath,fileCup);
                }
                else if(fileStatus.isFile())
                {
                    String fileName=fileStatus.getPath().getName();
                    String localFilePath=FileUtils.JointPath(localDirPath,fileName);
                    fileCup.put(fileStatus.getPath().toString(),localFilePath);
                }
                else if(fileStatus.isSymlink())
                {
                    Path symlink=fileStatus.getSymlink();
                    String hdfsFilePath=symlink.toString();
                    String localFilePath=FileUtils.JointPath(localDirPath,symlink.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void closeCache() {

    }
}
