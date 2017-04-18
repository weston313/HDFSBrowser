package com.geoway.hdfsbrowser.service.transform.abs;

import java.util.UUID;

/**
 * Created by USER on 2017/4/18.
 */
public abstract class Transform extends Thread {

    protected String id;
    protected TransformInfo info;

    public abstract void shutdown();

    public abstract void pause();

    public abstract void closeCache();

    public void init()
    {
        this.id= UUID.randomUUID().toString();
        info=new TransformInfo(this.id,this);
    }

    public String getThreadId()
    {
        return this.id;
    }

    public TransformInfo getThreadInfo()
    {
        return info;
    }
}
