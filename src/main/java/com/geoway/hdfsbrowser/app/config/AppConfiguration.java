package com.geoway.hdfsbrowser.app.config;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wozipa on 2017/4/6.
 */
public class AppConfiguration {

    public static final Logger LOGGER=Logger.getLogger(AppConfiguration.class);

    private static volatile AppConfiguration configuration=null;
    public static final String HDFS_HOST="hb.app.hdfs.host";
    public static final String HDFS_USER="hb.app.hdfs.user";
    public static final String HDFS_PORT="hb.app.hdfs.port";

    public static AppConfiguration GetAppConfiguration()
    {
        if(configuration==null)
        {
            synchronized (AppConfiguration.class)
            {
                if(configuration==null)
                {
                    configuration=new AppConfiguration();
                }
            }
        }
        return configuration;
    }

    private Map<String,String> config=null;

    private AppConfiguration()
    {
        config=new HashMap<>();
    }

    public void setValue(String key,String value)
    {
        if(config==null)
        {
            config=new HashMap<>();
        }
        config.put(key,value);
    }

    public String getHdfsHost()
    {
        return config.get(HDFS_HOST);
    }

    public String getHdfsPort()
    {
        return config.get(HDFS_PORT);
    }

    public String getHdfsUser()
    {
        return config.get(HDFS_USER);
    }
}
