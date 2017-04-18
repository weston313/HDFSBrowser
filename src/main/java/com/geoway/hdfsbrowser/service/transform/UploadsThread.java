package com.geoway.hdfsbrowser.service.transform;

import com.geoway.hdfsbrowser.exception.ParamsRepeatException;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import com.geoway.hdfsbrowser.service.transform.abs.Transform;
import com.geoway.hdfsbrowser.service.transform.abs.TransformInfo;
import com.geoway.hdfsbrowser.util.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * upload files to hdfs paths
 * Created by USER on 2017/4/18.
 */
public class UploadsThread extends Transform{
    private static final Logger LOGGER=Logger.getLogger(UploadsThread.class);

    private boolean work=true;
    private boolean cache=true;

    private Map<Long, TransformInfo> threadInfos=new HashMap<Long, TransformInfo>();


    private HDFSCore hdfsCore;
    private String localDirPath=null;
    private String[] localFilePaths=null;
    private String hdfsDirPath;

    public UploadsThread(String localDirPath,String hdfsDirPath,HDFSCore hdfsCore)
    {
        this.localDirPath=localDirPath;
        this.hdfsDirPath=hdfsDirPath;
        this.hdfsCore=hdfsCore;
        init();
    }

    public UploadsThread(String[] localFilePaths,String hdfsDirPath,HDFSCore hdfsCore)
    {
        this.localFilePaths=localFilePaths;
        this.hdfsDirPath=hdfsDirPath;
        this.hdfsCore=hdfsCore;
        init();
    }

    @Override
    public void init() {
        super.init();
        this.info.setType(TransformInfo.TYPE.upload);
    }

    @Override
    public void run() {
        if ((localFilePaths == null || localFilePaths.length == 0) && (localDirPath == null || localDirPath.isEmpty())) {
            throw new NullPointerException("not set the sources file path");
        }
        //
        if (localDirPath != null && !localDirPath.isEmpty() && localFilePaths != null && localFilePaths.length > 0) {
            throw new ParamsRepeatException("the params localDirPath and localFilePaths repeat");
        }
        //
        if (hdfsDirPath == null || hdfsDirPath.isEmpty()) {
            throw new NullPointerException("the hdfs dir path is null");
        }
        //
        Map<String, String> fileCup = new HashMap<>();
        if (localFilePaths != null && localFilePaths.length > 0) {
            for (String localFilePath : localFilePaths) {
                File localFile = new File(localFilePath);
                String hdfsFilePath = FileUtils.JointPath(hdfsDirPath, localFile.getName());
                fileCup.put(localFilePath, hdfsFilePath);
            }
        }
        if (localDirPath != null && !localDirPath.isEmpty()) {
            File localDirFile = new File(localDirPath);
            String localDirName = localDirFile.getName();
            String hdfsChildDirPath = FileUtils.JointPath(hdfsDirPath, localDirName);
            rescuryDirectory(fileCup, localDirPath, hdfsChildDirPath);
        }
        //
        Set<String> keySet=fileCup.keySet();
        for(String localFilePath:keySet)
        {
            String hdfsFilePath=fileCup.get(localFilePath);
            UploadThread thread=new UploadThread(localFilePath,hdfsFilePath,hdfsCore);
            thread.closeCache();
            threadInfos.put(thread.getId(),thread.getThreadInfo());
            thread.start();
        }
    }
    /**
     * read the directry recursive and create the file path and hdfs path puted into the mapcup
     * @param fileCup
     * @param localDirPath
     * @param hdfsDirPath
     */
    public void rescuryDirectory(Map<String ,String> fileCup,String localDirPath,String hdfsDirPath)
    {
        File localDirFile=new File(localDirPath);
        String[] fileNames=localDirFile.list();
        for(String fileName:fileNames)
        {
            String childFilePath=FileUtils.JointPath(localDirPath,fileName);
            File childFile=new File(childFilePath);
            if(childFile.isFile())
            {
                //
                String childHdfsPath=FileUtils.JointPath(hdfsDirPath,childFile.getName());
                fileCup.put(childFile.getPath(),childHdfsPath);
            }
            else if(childFile.isDirectory())
            {
                String childLocaDirName=childFile.getName();
                String childHdfsDirPath=FileUtils.JointPath(hdfsDirPath,childLocaDirName);
                rescuryDirectory(fileCup,childFile.getPath(),childHdfsDirPath);
            }
        }
    }

    @Override
    public void shutdown() {
        this.work=false;
    }

    @Override
    public void pause() {

    }

    @Override
    public void closeCache() {
        this.cache=false;
    }
}
