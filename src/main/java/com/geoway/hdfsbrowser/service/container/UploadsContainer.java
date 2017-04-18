package com.geoway.hdfsbrowser.service.container;

import com.geoway.hdfsbrowser.service.transform.abs.TransformInfo;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by USER on 2017/4/17.
 */
public class UploadsContainer {

    private static final Logger LOGGER=Logger.getLogger(UploadsContainer.class);

    private static volatile UploadsContainer CONTAINER=null;

    public static  UploadsContainer GetContainer()
    {
        if(CONTAINER==null)
        {
            synchronized (UploadsContainer.class)
            {
                if(CONTAINER==null)
                {
                    CONTAINER=new UploadsContainer();
                }
            }
        }
        return CONTAINER;
    }

    private ThreadContainer container=null;
    private UploadsContainer(){
        container=new ThreadContainer();
    }

    public void add(String id,TransformInfo info)
    {
        container.add(id,info);
    }

    public void add(TransformInfo info)
    {
        container.add(info);
    }

    public List<TransformInfo> list()
    {
       return container.list();
    }

    public void remove(String id)
    {
        this.container.remove(id);
    }
}
