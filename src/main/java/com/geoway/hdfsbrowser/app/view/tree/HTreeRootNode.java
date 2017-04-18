package com.geoway.hdfsbrowser.app.view.tree;

import com.geoway.hdfsbrowser.app.config.AppConfiguration;
import com.geoway.hdfsbrowser.app.view.HNode;
import org.apache.log4j.Logger;

/**
 * Created by USER on 2017/3/29.
 */
public class HTreeRootNode extends HNode {

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
        this.path= AppConfiguration.GetAppConfiguration().getHDFSUri()+"/";
        this.parent=null;
    }
}
