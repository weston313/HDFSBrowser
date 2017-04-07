package com.wozipa.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by USER on 2017/3/27.
 */
public class TestPath {

    @Test
    public void testList()
    {
        System.out.println(System.getenv("HADOOP_HOME"));
        Path path=new Path("file:///");
        try {
            FileSystem fileSystem=path.getFileSystem(new Configuration());
            FileStatus[] fileStatuses=fileSystem.listStatus(path);
            for(FileStatus fileStatus:fileStatuses)
            {
                System.out.println(fileStatus.getPath().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMkdir()
    {
        Path path=new Path("hdfs://192.98.19.11:9000/Test/heheda");
        try {
            FileSystem fileSystem=path.getFileSystem(new Configuration());
            System.out.println(fileSystem.mkdirs(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
