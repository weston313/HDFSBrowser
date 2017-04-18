package com.geoway.hdfsbrowser.service.transform;

import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import com.geoway.hdfsbrowser.service.transform.abs.Transform;
import com.geoway.hdfsbrowser.service.transform.abs.TransformInfo;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by USER on 2017/4/18.
 */
public class DownloadThread extends Transform {

    private static final Logger LOGGER=Logger.getLogger(DownloadThread.class);

    private boolean work;
    private boolean cache=true;

    private HDFSCore hdfsCore;
    private String localFilePath;
    private String hdfsFilePath;

    public DownloadThread(String localFilePath,String hdfsFilePath,HDFSCore hdfsCore)
    {
        this.localFilePath=localFilePath;
        this.hdfsCore=hdfsCore;
        this.hdfsFilePath=hdfsFilePath;
        init();
    }

    @Override
    public void init()
    {
        super.init();
        this.info.setType(TransformInfo.TYPE.download);
    }

    @Override
    public void run() {
        if(localFilePath==null || localFilePath.isEmpty() || hdfsFilePath==null ||hdfsFilePath.isEmpty())
        {
            throw new NullPointerException("the local or hdfs file path is null");
        }
        try {
            if(!hdfsCore.exist(hdfsFilePath))
            {
                throw new FileNotFoundException("the hdfs file path is not existed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        File localFile=new File(localFilePath);
        if(localFile.exists())
        {
            try {
                throw new FileAlreadyExistsException("the local file "+localFilePath+" is exsited");
            } catch (FileAlreadyExistsException e) {
                e.printStackTrace();
            }
        }
        //
        try {
            FileOutputStream writer=new FileOutputStream(localFile);
            InputStream reader=hdfsCore.open(hdfsFilePath);
            byte[] buffer=new byte[4096];
            int oef=-1;
            while((oef=reader.read(buffer))>0 && work)
            {
                writer.write(buffer,0,oef);
            }
            reader.close();
            writer.flush();
            writer.close();
            if(!work)
            {
                if(hdfsCore.exist(hdfsFilePath))
                {
                    hdfsCore.delete(hdfsFilePath);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
