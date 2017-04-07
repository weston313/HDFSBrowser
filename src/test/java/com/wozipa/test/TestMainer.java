package com.wozipa.test;

import org.junit.Test;

/**
 * Created by wozipa on 2017/4/4.
 */
public class TestMainer {

    @Test
    public void testSqjJdbc()
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
