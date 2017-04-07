package com.geoway.hdfsbrowser.service.container;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXWriter;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2017/4/7.
 */
public class ConnectionContainer {

    private static final Logger LOGGER=Logger.getLogger(ConnectionContainer.class);
    private static final String FILE_NAME="connections.xml";
    private static final String CONN_ROOT_NAME="connections";
    private static final String CONN_NODE_NAME="connection";
    private static final String CONN_HOST_NAME="host";
    private static final String CONN_PORT_NAME="port";
    private static final String CONN_USER_NAME="user";

    private static volatile  ConnectionContainer connectionContainer=null;

    public static ConnectionContainer GetConnectionContainer()
    {
        if(connectionContainer==null)
        {
            synchronized (ConnectionContainer.class)
            {
                if(connectionContainer==null)
                {
                    connectionContainer=new ConnectionContainer();
                }
            }
        }
        return connectionContainer;
    }

    private List<Connection> container=null;

    private ConnectionContainer()
    {
        container=new ArrayList<>();
        initlize();
    }

    public void initlize()
    {
        String dataPath=(System.getProperty("HDFSBROWSER_DATA")!=null && !System.getProperty("HDFSBROWSER_DATA").isEmpty())?System.getProperty("HDFSBROWSER_DATA"):System.getenv("HDFSBROWSER_DATA");
        String filePath=dataPath+"/"+FILE_NAME;
        File file=new File(filePath);
        if(!file.exists())
        {
            return;
        }
        try {
            SAXReader reader=new SAXReader();
            Document document=reader.read(file);
            Element root=document.getRootElement();
            List<Element> conns=root.elements(CONN_NODE_NAME);
            for(Element conn:conns)
            {
                String host=conn.element(CONN_HOST_NAME).getText();
                String port=conn.element(CONN_PORT_NAME).getText();
                String user=conn.element(CONN_USER_NAME).getText();
                Connection connection=new Connection();
                connection.setContent(host,port,user);
                container.add(connection);
            }
            //
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void close()
    {
        Document document=DocumentHelper.createDocument();
        Element root=DocumentHelper.createElement(CONN_ROOT_NAME);
        document.setRootElement(root);
        //
        for(Connection connection:container)
        {
            Element connectionElement=root.addElement(CONN_NODE_NAME);
            Element hostElement=connectionElement.addElement(CONN_HOST_NAME);
            hostElement.setText(connection.getHost());
            connectionElement.addElement(CONN_PORT_NAME).setText(connection.getPort());
            connectionElement.addElement(CONN_USER_NAME).setText(connection.getUser());
        }
        //
        String dataPath=(System.getProperty("HDFSBROWSER_DATA")!=null && !System.getProperty("HDFSBROWSER_DATA").isEmpty())?System.getProperty("HDFSBROWSER_DATA"):System.getenv("HDFSBROWSER_DATA");
        if(dataPath==null|| dataPath.isEmpty())
        {
            LOGGER.info("the data path is null");
            return;
        }
        String filePath=dataPath+"/"+FILE_NAME;
        File file=new File(filePath);
        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OutputFormat opf=new OutputFormat("\t",true,"UTF-8");
        opf.setTrimText(true);
        XMLWriter writer= null;
        try {
            writer = new XMLWriter(new FileOutputStream(file),opf);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(writer==null)
        {
            LOGGER.info("the writer is null");
        }
        try {
            writer.write(document);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Connection> getContainer()
    {
        return container;
    }

    public void addConnection(String host,String port,String user)
    {
        Connection connection=new Connection();
        connection.setContent(host,port,user);
        this.container.add(connection);
    }

    public class  Connection{
        private String host;
        private String port;
        private String user;

        public void setContent(String host,String port,String user)
        {
            this.host=host;
            this.port=port;
            this.user=user;
        }


        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }
}
