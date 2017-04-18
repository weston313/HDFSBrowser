package com.geoway.hdfsbrowser.service.container;

import com.geoway.hdfsbrowser.app.view.HNode;

/**
 * Created by USER on 2017/4/15.
 */
public class ClipboardContainer{

    private static volatile ClipboardContainer CONTAINER=null;

    public static final int TYPE_CP=1;
    public static final int TYPE_MV=2;

    public static ClipboardContainer GetContainer()
    {
        if(CONTAINER==null)
        {
            synchronized (ClipboardContainer.class)
            {
                CONTAINER=new ClipboardContainer();
            }
        }
        return CONTAINER;
    }

    private HNode node=null;
    private int type=1;

    private ClipboardContainer()
    {
    }

    public void add(HNode node, int type)
    {
        this.node=node;
        this.type=type;
    }

    public int getType()
    {
        return type;
    }

    public HNode getNode()
    {
        return this.node;
    }

}
