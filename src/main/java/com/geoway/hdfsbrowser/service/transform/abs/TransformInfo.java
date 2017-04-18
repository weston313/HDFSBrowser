package com.geoway.hdfsbrowser.service.transform.abs;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by USER on 2017/4/16.
 */
public class TransformInfo {

    private static final Logger LOGGER=Logger.getLogger(TransformInfo.class);

    public enum TYPE{
        upload("上传任务"),download("下载任务");

        private String value;
        private TYPE(String _value)
        {
            this.value=_value;
        }

        public String getValue()
        {
            return this.value;
        }
    }
    public enum  STATE{
        creatting("创建中"),
        uploading("上传中"),
        downloading("下载中"),
        stop("停止"),
        killed("终止");

        private String value;

        private STATE(String _value)
        {
            this.value=_value;
        }

        public String getValue()
        {
            return this.value;
        }
    }

    private String id;
    private String src;
    private String dest;
    private String time;
    private double precent;
    private String state;
    private TYPE type;

    private Transform thread;

    public TransformInfo(String id,Transform transform)
    {
        this(id,null,transform);
    }

    public TransformInfo(String id, TYPE type, Transform transform)
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.time=sdf.format(new Date());
        //
        this.precent=0;
        this.state= STATE.creatting.getValue();
        this.type=type;
        this.thread=transform;
        this.id=id;
    }

    public void setUpOrDownLoad(String src,String dest)
    {
        this.src=src;
        this.dest=dest;
    }

    public double getPrecent() {
        return precent;
    }

    public void setPrecent(double precent) {
        this.precent = precent;
    }

    public String getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state.getValue();
    }

    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id=id;
    }

    public String[] getItems()
    {
        return new String[]{this.id,this.precent+"",this.type.toString(),this.src,this.state,""};
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
}
