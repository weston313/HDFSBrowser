package com.geoway.hdfsbrowser.service.transform;

import com.geoway.hdfsbrowser.service.container.UploadsContainer;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import com.geoway.hdfsbrowser.service.thread.info.ThreadInfo;
import com.geoway.hdfsbrowser.service.transform.abs.Transform;
import com.geoway.hdfsbrowser.service.transform.abs.TransformInfo;
import javafx.scene.paint.Stop;
import org.apache.hadoop.fs.PathExistsException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * upload one file to hdfs path
 * Created by USER on 2017/4/18.
 */
public class UploadThread extends Transform{

    private static final Logger LOGGER=Logger.getLogger(UploadThread.class);

    private boolean work=true;
    private boolean cached=true;

    private HDFSCore hdfsCore;
    private String src;
    private String dest;


    public UploadThread(String localFilePath,String hdfsFilePath,HDFSCore hdfsCore)
    {
        this.hdfsCore=hdfsCore;
        this.src=localFilePath;
        this.dest=hdfsFilePath;
    }

    @Override
    public void init()
    {
        super.init();
        this.info.setType(TransformInfo.TYPE.upload);
    }

    @Override
    public void run() {
        if(src==null || src.isEmpty() || dest==null || dest.isEmpty())
        {
            throw new NullPointerException("the src or dest is null");
        }
        File srcFile=new File(src);
        if(!srcFile.exists())
        {
            try {
                throw new FileNotFoundException("the file "+src+" not existed");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(cached)
        {
//            UploadsContainer.GetContainer().add(this.id,info);
        }
        //
        try {
            if(hdfsCore.exist(dest))
            {
                throw new PathExistsException("the file "+dest+" is existed");
            }
            //statr to upload file to data
            OutputStream writer=hdfsCore.createFile(dest);
            FileInputStream reader=new FileInputStream(srcFile);
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
                if(hdfsCore.exist(dest))
                {
                    hdfsCore.delete(dest);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * shutdown the upload
     */
    @Override
    public void shutdown()
    {
        this.work=false;
    }

    /**
     * pause the upload
     */
    @Override
    public void pause()
    {

    }

    @Override
    public void closeCache() {
        this.cached=false;
    }
}
