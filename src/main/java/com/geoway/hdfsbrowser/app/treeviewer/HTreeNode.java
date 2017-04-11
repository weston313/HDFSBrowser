package com.geoway.hdfsbrowser.app.treeviewer;

import com.geoway.hdfsbrowser.util.FileUtils;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.TreeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2017/3/29.
 */
public class HTreeNode{

    private static final Logger LOGGER=Logger.getLogger(HTreeNode.class);

    protected String name;
    protected String path;
    protected String size;
    protected String time;
    protected String type;
    protected String owner;
    protected HTreeNode parent;
    protected boolean needChange=true;
    protected List<HTreeNode> children;
    protected TreeItem parentTreeItem;


    public TreeItem getParentTreeItem() {
        return parentTreeItem;
    }

    public void setParentTreeItem(TreeItem parentTreeItem) {
        this.parentTreeItem = parentTreeItem;
    }

    public boolean isNeedChange() {
        return needChange;
    }

    public void setNeedChange(boolean needChange) {
        this.needChange = needChange;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


    public static HTreeNode FromFileStatus(HTreeNode parent,FileStatus fileStatus)
    {
        HTreeNode node=new HTreeNode();
        Path path=fileStatus.getPath();
        node.setName(path.getName());
        node.setPath(path.toString());
        node.setParent(parent);
        node.setSize(fileStatus.getLen()+"");
        node.setTime(fileStatus.getModificationTime()+"");
        node.setType(FileUtils.GetFileType(fileStatus));
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



    public static HTreeNode FromTreeItem(TreeItem item)
    {
        return null;
    }

    public String[] toHFile()
    {
        return new String[]{this.name,this.time,this.type,this.size};
    }

}