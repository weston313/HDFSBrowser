package com.geoway.hdfsbrowser.service.core.impl;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.permission.FsAction;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by USER on 2017/3/29.
 */
public interface HDFSCore {

    /**
     * get the path existed or not
     * @param path
     * @return
     */
    public boolean exist(String path) throws Exception;

    /**
     * get the path is directory
     * @param path
     * @return
     */
    public boolean isDirectory(String path) throws Exception;

    /**
     * create the directory
     * @param path
     */
    public void mkdirDirectory(String path) throws Exception;

    /**
     * delete the file or directory
     * @param path
     */
    public void delete(String path) throws Exception;

    /**
     * delete the files in the directory
     * @param path
     */
    public void deleteRecusy(String path) throws Exception;

    /**
     * create one empty file
     * @param path
     */
    public void createFile(String path) throws Exception;

    /**
     * remove the path
     * @param src
     * @param dest
     */
    public void remove(String src,String dest) throws Exception;

    /**
     * rename the path
     * @param src
     * @param dest
     */
    public void rename(String src,String dest) throws Exception;

    /**
     * write the file into hdfs,and the content in the reader inputstream
     * @param path
     * @param reader
     */
    public void write(String path, InputStream reader) throws Exception;

    /**
     * download the content from the hdfs file
     * @param hdfsFile
     * @param otherFile
     */
    public void read(String hdfsFile,String otherFile) throws Exception;

    /**
     * read the hdfs file content and write it into the writer
     * @param hdfsFile
     * @param writer
     */
    public void read(String hdfsFile, OutputStream writer) throws Exception;

    public void changePermession(String path, FsAction user, FsAction group, FsAction other) throws  Exception;

    public FileStatus[] list(String path) throws Exception;
}
