package com.geoway.hdfsbrowser.service.core;

import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.permission.FsAction;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by USER on 2017/3/29.
 */
public class CurlCore implements HDFSCore{
    @Override
    public boolean exist(String path) throws Exception {
        return false;
    }

    @Override
    public boolean isDirectory(String path) throws Exception {
        return false;
    }

    @Override
    public void mkdirDirectory(String path) throws Exception {

    }

    @Override
    public void delete(String path) throws Exception {

    }

    @Override
    public void deleteRecusy(String path) throws Exception {

    }

    @Override
    public void createFile(String path) throws Exception {

    }

    @Override
    public void remove(String src, String dest) throws Exception {

    }

    @Override
    public void rename(String src, String dest) throws Exception {

    }

    @Override
    public void write(String path, InputStream reader) throws Exception {

    }

    @Override
    public void read(String hdfsFile, String otherFile) throws Exception {

    }

    @Override
    public void read(String hdfsFile, OutputStream writer) throws Exception {

    }

    @Override
    public void changePermession(String path, FsAction user, FsAction group, FsAction other) throws Exception {

    }

    @Override
    public FileStatus[] list(String path) throws Exception {
        return new FileStatus[0];
    }
}
