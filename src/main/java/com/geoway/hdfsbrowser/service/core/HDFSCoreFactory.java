package com.geoway.hdfsbrowser.service.core;

import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;

import java.io.IOException;

/**
 * Created by USER on 2017/3/29.
 */
public class HDFSCoreFactory {

    public enum  TYPE{
        api,httpclient,curl
    }

    public static HDFSCore GetHDFSCore(TYPE type) throws Exception {
        switch (type)
        {
            case api:
                return new HAPICore();
            case curl:
                return new CurlCore();
            case httpclient:
                return new HClientCore();
            default:
                return null;
        }
    }
}
