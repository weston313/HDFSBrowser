package com.geoway.hdfsbrowser.service.core;

import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by USER on 2017/3/29.
 */
public class HAPICore implements HDFSCore{

    private static final Logger LOGGER=Logger.getLogger(HAPICore.class);

    public HAPICore() throws IOException {
        this("file:///");
    }

    public HAPICore(String hdfs) throws IOException {
        this.configuration=new Configuration();
        configuration.set(FileSystem.FS_DEFAULT_NAME_KEY,hdfs);
    }

    public void setConfiguration(Configuration configuration)
    {
        this.configuration=configuration;
    }

    public void setHDFS(String hdfs)
    {
        this.configuration.set(FileSystem.FS_DEFAULT_NAME_KEY,hdfs);
    }

    private FileSystem fileSystem;
    private Configuration configuration;

    public void initFileSystem() {
        if(this.configuration==null)
        {
            LOGGER.info("the configuration is null");
            throw  new NullPointerException("the configuration is null");
        }
        try {
            this.fileSystem=FileSystem.get(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean exist(String path) throws Exception {
        if(path==null || path.isEmpty())
        {
            return false;
        }
        return this.fileSystem.exists(new Path(path));
    }

    public boolean isDirectory(String path) throws Exception {
        if(path==null || path.isEmpty())
        {
            return false;
        }
        Path url=new Path(path);
        return fileSystem.isDirectory(url);
    }

    public void mkdirDirectory(String path) throws Exception {
        if(path==null || path.isEmpty())
        {
            return;
        }
        //
        fileSystem.mkdirs(new Path(path));
    }

    public void delete(String path) throws Exception {
        if(path==null || path.isEmpty())
        {
            return;
        }
        //
        fileSystem.delete(new Path(path),false);
    }

    public void deleteRecusy(String path) throws Exception {
        if(path==null || path.isEmpty())
        {
            return;
        }
        //
        fileSystem.delete(new Path(path),true);
    }

    public OutputStream createFile(String path) throws Exception {
        if(path==null || path.isEmpty())
        {
            return null;
        }
        //
        FSDataOutputStream outputStream=fileSystem.create(new Path(path));
        return outputStream;
    }

    public void remove(String src, String dest) throws Exception {
        if(src==null || src.isEmpty() || dest==null || dest.isEmpty())
        {
            return;
        }
        //
        fileSystem.rename(new Path(src),new Path(dest));
    }

    public void rename(String src, String dest) throws Exception {
        remove(src,dest);
    }

    /**
     * @param path
     * @param reader
     * @throws Exception
     */
    public void write(String path, InputStream reader) throws Exception {
        if(path==null || path.isEmpty() || reader==null)
        {
            return;
        }
        if(reader==null)
        {
            throw new NullPointerException("the InputStream reader is null");
        }
        //
        Path url=new Path(path);
        if(fileSystem.exists(url))
        {
            throw new PathExistsException("the path "+path+" is existed");
        }
        FSDataOutputStream writer=fileSystem.create(url);
        int eof=-1;
        byte[] buffer=new byte[4096];
        while((eof=reader.read())>0)
        {
            writer.write(buffer,0,eof);
        }
        writer.flush();
        writer.close();
        //

    }

    public void read(String hdfsFile, String otherFile) throws PathNotFoundException,FileAlreadyExistsException,IOException {
        if(hdfsFile==null || hdfsFile.isEmpty() || otherFile==null || otherFile.isEmpty())
        {
            return;
        }
        //
        Path hdfsPath= new Path(hdfsFile);
        if(!fileSystem.exists(hdfsPath))
        {
            throw new PathNotFoundException("the hdfs file is not exited");
        }
        FSDataInputStream reader=fileSystem.open(hdfsPath);
        //
        File other=new File(otherFile);
        if(other.exists())
        {
            throw new java.nio.file.FileAlreadyExistsException("the file is exited");
        }
        FileOutputStream writer=new FileOutputStream(other);
        //
        int oef=0;
        byte[] buffer=new byte[4096];
        while((oef=reader.read(buffer))>0)
        {
            writer.write(buffer,0,oef);
        }
        writer.flush();
        writer.close();
        reader.close();
    }


    public void read(String hdfsFile, OutputStream writer) throws NullPointerException,PathNotFoundException,IOException {
        if(hdfsFile==null || hdfsFile.isEmpty())
        {
            return;
        }
        //
        if(writer==null)
        {
            throw new NullPointerException("the OutputStream writer is null");
        }
        //
        Path url=new Path(hdfsFile);
        if(!fileSystem.exists(url))
        {
            throw new PathNotFoundException("the hdfs file is not existed");
        }
        //
        int oef=0;
        byte[] buffer=new byte[4096];
        FSDataInputStream reader=fileSystem.open(url);
        while((oef=reader.read(buffer))>0)
        {
            writer.write(buffer,0,oef);
        }
        reader.close();
    }


    public void changePermession(String path,FsAction user,FsAction group,FsAction other)throws NullPointerException,IOException {
        if(path==null || path.isEmpty())
        {
            throw  new NullPointerException("the parameter path be null");
        }
        Path url=new Path(path);
        if(!fileSystem.exists(url))
        {
            throw new PathNotFoundException("the Path "+path+" is not existed");
        }
        FsPermission permission=new FsPermission(user,group,other);
        fileSystem.setPermission(new Path(path),permission);
    }

    @Override
    public FileStatus[] list(String path) throws Exception {
        if(path==null || path.isEmpty())
        {
            return null;
        }
        Path url=new Path(path);
        if(!fileSystem.exists(url))
        {
            throw  new PathNotFoundException("the path "+path+" in hdfs is not exited");
        }
        FileStatus[] fileStatuses=fileSystem.listStatus(url);
        return fileStatuses;
    }

    public FileStatus[] listDirectories(String path) throws  Exception
    {
        LOGGER.info("load path "+path);
        if(path==null || path.isEmpty())
        {
            return null;
        }
        Path url=new Path(path);
        if(!fileSystem.exists(url))
        {
            throw  new PathNotFoundException("the path "+path+" in hdfs is not exited");
        }
        FileStatus[] fileStatuses=this.fileSystem.listStatus(url, new PathFilter() {

            @Override
            public boolean accept(Path path) {
                try {
                    FileStatus fileStatus=fileSystem.getFileStatus(path);
                    if(!fileStatus.isDirectory())
                    {
                        return false;
                    }
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
        return fileStatuses;
    }

    @Override
    public FileStatus infor(String path) throws Exception {
        if(path==null || path.isEmpty())
        {
            return null;
        }
        if(!fileSystem.exists(new Path(path)))
        {
            throw new PathNotFoundException("the path "+path+" is not existed");
        }
        FileStatus fileStatus=fileSystem.getFileStatus(new Path(path));
        return fileStatus;
    }

    @Override
    public void copy(String src, String dst) throws Exception {

    }

    @Override
    public InputStream open(String path) throws Exception {
        if(path==null || path.isEmpty())
        {
            return null;
        }
        Path p=new Path(path);

        if(!fileSystem.exists(p))
        {
            return null;
        }
        FSDataInputStream reader=fileSystem.open(p);
        return reader;
    }


}
