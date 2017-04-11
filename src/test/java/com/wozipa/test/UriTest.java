package com.wozipa.test;

import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.net.URI;
import java.net.URL;

/**
 * Created by USER on 2017/4/11.
 */
public class UriTest {

    @Test
    public void testHashcode()
    {
        String path1="hdfs://192.98.19.11:9000/test";
        String path2="hdfs://192.98.19.11:9000/test/";
        Path p1=new Path(path1);
        Path p2=new Path(path2);
        if(p1.equals(p2))
        {
            System.out.print("yes");
        }
        else
        {
            System.out.print("no");
        }
    }
}
