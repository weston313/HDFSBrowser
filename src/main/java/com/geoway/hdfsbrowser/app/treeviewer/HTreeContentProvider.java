package com.geoway.hdfsbrowser.app.treeviewer;

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
        HTreeNode parent=(HTreeNode)o;
        String path=parent.getPath();
        try {
            FileStatus[] statuses=hdfsCore.listDirectories(path);
            List list=HTreeNode.FromFileStatues(parent,statuses);
            parent.setChildren(list);
            HTreeNode[] nodes= (HTreeNode[]) list.toArray(new HTreeNode[list.size()]);
            return nodes;
        } catch (Exception e) {
            e.printStackTrace();
            List<HTreeNode> empty=new ArrayList<>();
            parent.setChildren(empty);
            return empty.toArray();
        }
    }

    public Object getParent(Object o) {
        HTreeNode parent=((HTreeNode)o).getParent();
        return parent;
    }

    public boolean hasChildren(Object o) {
        HTreeNode parent=(HTreeNode)o;
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
        return new HTreeNode[]{HTreeRootNode.GetRootNode()};
    }

    public void dispose() {
        LOGGER.info("dispose");
    }

    public void inputChanged(Viewer viewer, Object o, Object o1) {
    }
}
