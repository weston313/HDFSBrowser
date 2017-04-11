package com.geoway.hdfsbrowser.util;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

/**
 * Created by wozipa on 2017/4/8.
 */
public class FileUtils {

    private static final Logger LOGGER=Logger.getLogger(FileUtils.class);

    public static final String TYPE_DIR="文件夹";
    public static final String TYPE_SYM="快捷方式";
    public static final String TYPE_TXT="文本文档";
    public static final String TYPE_XML="XML文件";
    public static final String TYPE_PDF="PDF文档";
    public static final String TYPE_NONE="未知文件类型";

    public static String GetFileType(FileStatus fileStatus)
    {
        if(fileStatus.isDirectory())
        {
            return TYPE_DIR;
        }
        else if(fileStatus.isSymlink())
        {
            return TYPE_SYM;
        }
        else {
            String fileName=fileStatus.getPath().getName();
            String suffix=fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            switch (suffix)
            {
                case "txt":
                    return TYPE_TXT;
                case "xml":
                    return TYPE_XML;
                case "pdf":
                    return TYPE_PDF;
                default:
                    return TYPE_NONE;
            }
        }
    }

    public static String[] getPathSegment(String name)
    {
        return null;
    }
}
