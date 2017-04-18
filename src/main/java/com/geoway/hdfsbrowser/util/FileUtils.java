package com.geoway.hdfsbrowser.util;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import sun.plugin.dom.core.CoreConstants;

import javax.lang.model.element.Name;
import java.io.File;

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

    /**
     * corret the file path to same format
     * @param path
     * @return
     */
    public static String CorrectPath(String path)
    {
        if(path==null || path.isEmpty())
        {
            return null;
        }
        while(path.endsWith("\\") || path.endsWith("/"))
        {
            path=path.substring(0,path.length()-1);
        }
        return path;
    }

    /**
     * add directory path and file name to file path
     * @param directory
     * @param name
     * @return
     */
    public static String JointPath(String directory,String name)
    {
        if(directory==null || directory.isEmpty())
        {
            return null;
        }
        if(name==null || name.isEmpty())
        {
            return CorrectPath(directory);
        }
        String newDir=CorrectPath(directory);
        String newName=CorrectPath(name);
        return newDir+"\\"+newName;
    }

    public static String[] getPathSegment(String name)
    {
        return null;
    }

    /**
     * get name of the file in local filesyeten(ext4,ntfs)
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath)
    {
        if(filePath==null || filePath.isEmpty())
        {
            return null;
        }
        File file=new File(filePath);
        if(!file.exists())
        {
            return filePath;
        }
        String name=file.getName();
        return name;
    }

    /**
     * get name of the file on hdfs
     * @param hdfsPath
     * @return
     */
    public static String getHdfsName(String hdfsPath)
    {
        if(hdfsPath==null || hdfsPath.isEmpty())
        {
            return null;
        }
        Path path=new Path(hdfsPath);
        return path.getName();
    }
}
