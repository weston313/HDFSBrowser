package com.geoway.hdfsbrowser.app.tree;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2017/3/29.
 */
public class HTreeNode {

    private static final Logger LOGGER=Logger.getLogger(HTreeNode.class);

    protected String name;
    protected String path;
    protected HTreeNode parent;
    protected List<HTreeNode> children;

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

    public HTreeNode getParent() {
        return parent;
    }

    public void setParent(HTreeNode parent) {
        this.parent = parent;
    }

    public List<HTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<HTreeNode> children) {
        this.children = children;
    }

    public static HTreeNode FromFileStatus(HTreeNode parent,FileStatus fileStatus)
    {
        HTreeNode node=new HTreeNode();
        Path path=fileStatus.getPath();
        node.setName(path.getName());
        node.setPath(path.toString());
        node.setParent(parent);
        return  node;
    }

    public static List<HTreeNode> FromFileStatues(HTreeNode parent,FileStatus[] fileStatuses)
    {
        List<HTreeNode> nodes=new ArrayList<>();
        for(FileStatus fileStatus:fileStatuses)
        {
            HTreeNode node=FromFileStatus(parent,fileStatus);
            nodes.add(node);
        }
        return nodes;
    }
}
