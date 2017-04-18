package com.geoway.hdfsbrowser.service.container;

import com.geoway.hdfsbrowser.app.view.HNode;
import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2017/4/10.
 */
public class PathsContainer {

    private static final Logger LOGGER=Logger.getLogger(PathsContainer.class);

    public static volatile PathsContainer CONTAINER=null;

    public static PathsContainer getContainer()
    {
        if(CONTAINER==null)
        {
            synchronized (PathsContainer.class)
            {
                if(CONTAINER==null)
                {
                    CONTAINER=new PathsContainer();
                }
            }
        }
        return CONTAINER;
    }

    private List<HNode> container=null;
    private int index;

    private PathsContainer()
    {
        container=new ArrayList<>(20);
        index=0;
    }

    /**
     * create the index of the file
     * @param node
     */
    public synchronized void add(HNode node)
    {
        container.add(node);
        index=container.size()-1;
    }

    /**
     * when click the back buttion to get the hnode from the paths container
     * @return
     */
    public synchronized HNode back()
    {
        index--;
        if(index<0)
        {
            index=0;
        }
        return container.get(index);
    }

    /**
     * when click the prve buttion to get the hnode from the index
     * @return
     */
    public synchronized HNode prev()
    {
        index++;
        if(index>=container.size())
        {
            index=container.size()-1;
        }
        return container.get(index);
    }
}
