package com.geoway.hdfsbrowser.app.tree.nodes;

import org.apache.hadoop.fs.FileStatus;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by USER on 2017/3/29.
 */
public class HDFSTreeNode {

    private static final Logger LOGGER=Logger.getLogger(HDFSTreeNode.class);

    protected String name;
    protected String path;
    protected HDFSTreeNode parent;
    protected List<HDFSTreeNode> children;

    public boolean hasChildren()
    {
        if(this.children!=null && this.children.size()>0)
        {
            return false;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HDFSTreeNode getParent() {
        return parent;
    }

    public void setParent(HDFSTreeNode parent) {
        this.parent = parent;
    }

    public List<HDFSTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<HDFSTreeNode> children) {
        this.children = children;
    }
}
