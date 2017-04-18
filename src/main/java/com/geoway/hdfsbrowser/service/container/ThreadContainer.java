package com.geoway.hdfsbrowser.service.container;

import com.geoway.hdfsbrowser.exception.TaskNotExistedException;
import com.geoway.hdfsbrowser.exception.TaskOverException;
import com.geoway.hdfsbrowser.service.transform.abs.TransformInfo;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by USER on 2017/4/16.
 */
public class ThreadContainer {

    private static final Logger LOGGER = Logger.getLogger(ThreadContainer.class);

    private Map<String,TransformInfo> running;
    private Map<String,TransformInfo> endding;
    public ThreadContainer()
    {
        running=new HashMap<>();
        endding=new HashMap<>();
    }

    /**
     * check the job existed or not accound to id
     * @param name
     * @return
     */
    public synchronized boolean exist(String name) {
        if(name==null || name.isEmpty())
        {
            return false;
        }
        if(running.containsKey(name) || endding.containsKey(name))
        {
            return true;
        }
        return false;
    }

    public synchronized void complete(String id) throws TaskNotExistedException, TaskOverException {
        if(id==null || id.isEmpty())
        {
            throw  new NullPointerException("the id is null");
        }
        //
        if(!running.containsKey(id))
        {
            if(!endding.containsKey(id))
            {
                throw new TaskNotExistedException("the upload with id "+id+" is not existed");
            }
            else {
                throw new TaskOverException("the upload task with id " + id + " is over.");
            }
        }
        TransformInfo info=running.remove(id);
        endding.put(id,info);
    }

    /**
     * add started job into the taskinfo
     * @param id
     * @param threadInfo
     */
    public synchronized void add(String id,TransformInfo threadInfo)
    {
        running.put(id,threadInfo);
    }

    public synchronized void add(TransformInfo info)
    {
        running.put(info.getId(),info);
    }

    /**
     * get the list of infos of the running and over thread
     * @return
     */
    public List<TransformInfo> list()
    {
        List<TransformInfo> list=new ArrayList<>();
        Set<String> keySet=running.keySet();
        for(String key:keySet)
        {
            TransformInfo info=running.get(key);
            list.add(info);
        }
        for(String key:endding.keySet())
        {
            list.add(endding.get(key));
        }
        return list;
    }

    /**
     * delete the threadinfo from container
     * @param id
     */
    public void remove(String id)
    {
        TransformInfo info=null;
        if(running.containsKey(id))
        {
            info=running.remove(id);
        }
        else if(endding.containsKey(id))
        {
            info=endding.remove(id);
        }
//        info.remove();
    }
}