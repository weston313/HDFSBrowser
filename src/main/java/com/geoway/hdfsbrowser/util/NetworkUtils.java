package com.geoway.hdfsbrowser.util;

import org.apache.commons.net.telnet.TelnetClient;
import sun.net.TelnetProtocolException;

import java.io.IOException;
import java.net.Inet4Address;

/**
 * Created by USER on 2017/4/7.
 */
public class NetworkUtils {

    public static boolean telnet(String host,String port)
    {
        TelnetClient telnetClient=new TelnetClient("VT100");
        try {
            telnetClient.connect(host,Integer.parseInt(port));
            if(telnetClient.isAvailable())
            {
                telnetClient.disconnect();
                return true;
            }
            else return false;
        } catch (IOException e) {
            return false;
        }
    }
}
