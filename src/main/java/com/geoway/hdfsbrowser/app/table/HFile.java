package com.geoway.hdfsbrowser.app.table;

import org.apache.log4j.Logger;

/**
 * Created by wozipa on 2017/4/8.
 */
public class HFile {

    private static final Logger LOGGER=Logger.getLogger(HFile.class);

    private String name;
    private String type;
    private String time;
    private String size;

    public HFile()
    {
        this.name="新建文件夹";
        this.type="文件夹";
        this.time=System.currentTimeMillis()+"";
        this.size="";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String[] getItems()
    {
        return new  String[]{this.name,this.time,this.type,this.size};
    }
}
