package com.geoway.hdfsbrowser.service.message;

/**
 * Created by USER on 2017/3/29.
 */
public class HBMessage {

    public enum  TYPE{
        MSG,ERROR,EXPECTION
    }

    private TYPE type;
    private String content;
    private String user;
    private String time;

}
