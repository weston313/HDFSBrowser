package com.geoway.hdfsbrowser.app.view.tree;

import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.service.container.IndexContainer;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.hadoop.fs.FileStatus;
import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2017/3/29.
 */
public class HTreeContentProvider implements ITreeContentProvider {

    private static final Logger LOGGER=Logger.getLogger(HTreeContentProvider.class);

    private HDFSCore hdfsCore;

    public HTreeContentProvider(HDFSCore hdfsCore)
    {
        this.hdfsCore=hdfsCore;
    }

    public Object[] getChildren(Object o) {
        LOGGER.info("get the children from hdfs");
        HNode parent=(HNode)o;
        String path=parent.getPath();
        try {
            FileStatus[] statuses=hdfsCore.listDirectories(path);
            List list= HNode.FromFileStatues(parent,statuses);
            parent.setChildren(list);
            HNode[] nodes= (HNode[]) list.toArray(new HNode[list.size()]);
            return nodes;
        } catch (Exception e) {
            e.printStackTrace();
            List<HNode> empty=new ArrayList<>();
            parent.setChildren(empty);
            return empty.toArray();
        }
    }

    public Object getParent(Object o) {
        HNode parent=((HNode)o).getParent();
        return parent;
    }

    public boolean hasChildren(Object o) {
        HNode parent=(HNode)o;
        String path=parent.getPath();
        try {
            if(!hdfsCore.isDirectory(path))
            {
                return false;
            }
            //
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object[] getElements(Object o) {
        LOGGER.info("get the root node");
        return new HNode[]{HTreeRootNode.GetRootNode()};
    }

    public void dispose() {
        LOGGER.info("dispose");
    }

    public void inputChanged(Viewer viewer, Object o, Object o1) {
    }
}
