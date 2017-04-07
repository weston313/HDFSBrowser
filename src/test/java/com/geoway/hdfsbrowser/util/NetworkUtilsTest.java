package com.geoway.hdfsbrowser.util;

import org.junit.Test;

/**
 * Created by USER on 2017/4/7.
 */
public class NetworkUtilsTest {

    @Test
    public void testTelnet()
    {
        boolean falg=NetworkUtils.telnet("192.98.19.11","8080");
        System.out.println(falg);
    }
}
