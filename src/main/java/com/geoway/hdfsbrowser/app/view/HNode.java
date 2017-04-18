package com.geoway.hdfsbrowser.app.view;

import com.geoway.hdfsbrowser.util.FileUtils;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2017/3/29.
 */
public class HNode {

    private static final Logger LOGGER=Logger.getLogger(HNode.class);

    protected String name;
    protected String path;
    protected String size;
    protected String time;
    protected String type;
    protected String owner;
    protected HNode parent;
    protected boolean needChange=true;
    protected List<HNode> children;

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

    public HNode getParent() {
        return parent;
    }

    public void setParent(HNode parent) {
        this.parent = parent;
    }

    public List<HNode> getChildren() {
        return children;
    }

    public void setChildren(List<HNode> children) {
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

    public static HNode FromFileStatus(HNode parent, FileStatus fileStatus)
    {
        HNode node=new HNode();
        Path path=fileStatus.getPath();
        node.setName(path.getName());
        node.setPath(path.toString());
        node.setParent(parent);
        node.setSize(fileStatus.getLen()+"");
        node.setTime(fileStatus.getModificationTime()+"");
        node.setType(FileUtils.GetFileType(fileStatus));
        return  node;
    }

    public static List<HNode> FromFileStatues(HNode parent, FileStatus[] fileStatuses)
    {
        List<HNode> nodes=new ArrayList<>();
        for(FileStatus fileStatus:fileStatuses)
        {
            HNode node=FromFileStatus(parent,fileStatus);
            nodes.add(node);
        }
        return nodes;
    }



    public static HNode FromTreeItem(TreeItem item)
    {
        return null;
    }

    public String[] toHFile()
    {
        return new String[]{this.name,this.time,this.type,this.size};
    }

}