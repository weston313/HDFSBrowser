package com.geoway.hdfsbrowser.service.container;

import org.apache.log4j.Logger;
import java.util.List;

/**
 * Created by USER on 2017/4/17.
 */
public class DownloadsContainer {

    private static final Logger LOGGER=Logger.getLogger(DownloadsContainer.class);

    private static volatile  DownloadsContainer CONTAINER=null;

    public static DownloadsContainer GetContainer()
    {
        if(CONTAINER==null)
        {
            synchronized (DownloadsContainer.class)
            {
                if(CONTAINER==null)
                {
                    CONTAINER=new DownloadsContainer();
                }
            }
        }
        return CONTAINER;
    }

    public List list()
    {
        return null;
    }
}
