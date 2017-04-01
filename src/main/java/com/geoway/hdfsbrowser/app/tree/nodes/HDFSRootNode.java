package com.geoway.hdfsbrowser.app.tree.nodes;

import org.apache.log4j.Logger;

/**
 * Created by USER on 2017/3/29.
 */
public class HDFSRootNode extends HDFSTreeNode {

    private static final Logger LOGGER=Logger.getLogger(HDFSRootNode.class);

    private static volatile  HDFSRootNode ROOT=null;

    public static HDFSRootNode GetRootNode()
    {
        if(ROOT==null)
        {
            synchronized (HDFSRootNode.class)
            {
                if(ROOT==null)
                {
                    ROOT=new HDFSRootNode();
                }
            }
        }
        return ROOT;
    }

    private HDFSRootNode()
    {
        this.name="/";
        this.parent=null;
    }
}
