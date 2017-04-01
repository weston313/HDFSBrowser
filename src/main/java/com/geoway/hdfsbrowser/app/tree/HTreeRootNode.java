package com.geoway.hdfsbrowser.app.tree;

import org.apache.log4j.Logger;

/**
 * Created by USER on 2017/3/29.
 */
public class HTreeRootNode extends HTreeNode {

    private static final Logger LOGGER=Logger.getLogger(HTreeRootNode.class);

    private static volatile HTreeRootNode ROOT=null;

    public static HTreeRootNode GetRootNode()
    {
        if(ROOT==null)
        {
            synchronized (HTreeRootNode.class)
            {
                if(ROOT==null)
                {
                    ROOT=new HTreeRootNode();
                }
            }
        }
        return ROOT;
    }

    private HTreeRootNode()
    {
        this.name="我的电脑";
        this.path="/";
        this.parent=null;
    }
}
