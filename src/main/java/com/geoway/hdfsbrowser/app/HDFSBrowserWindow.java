package com.geoway.hdfsbrowser.app;

import com.geoway.hdfsbrowser.app.action.menu.*;
import com.geoway.hdfsbrowser.app.action.operator.*;
import com.geoway.hdfsbrowser.app.config.AppConfiguration;
import com.geoway.hdfsbrowser.app.table.HTableViewer;
import com.geoway.hdfsbrowser.app.treeviewer.*;
import com.geoway.hdfsbrowser.service.container.ConnectionContainer;
import com.geoway.hdfsbrowser.service.core.HAPICore;
import com.geoway.hdfsbrowser.service.core.HDFSCoreFactory;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import com.geoway.hdfsbrowser.util.ColorUtils;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by USER on 2017/3/28.
 */
public class HDFSBrowserWindow extends ApplicationWindow {

    private static final Logger LOGGER=Logger.getLogger(HDFSBrowserWindow.class);

    private static HDFSBrowserWindow app=null;

    public static HDFSBrowserWindow GetApp()
    {
        return app;
    }

    //e
    private NewAction newAction;
    private ConfigAction configAction;
    private AboutAction aboutAction;
    //
    private Composite content;
    private Composite header;
    private Composite bottom;
    private Composite left;
    private Composite right;
    //
    private boolean connected=false;
    private HDFSCore hdfsCore;
    private HTreeViewer hdfsTree;
    private HTableViewer table;


    public HDFSBrowserWindow() {
        super(null);
        app=this;
        //
        newAction=new NewAction();
        configAction=new ConfigAction();
        aboutAction=new AboutAction();
        //
        this.addMenuBar();
        this.addToolBar(SWT.FILL);
        this.addStatusLine();
        LOGGER.info("start to initlize the windows");
    }

    public HTableViewer getTable()
    {
        return table;
    }

    public HTreeViewer getTree()
    {
        return hdfsTree;
    }

    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("HDFS Browser");
        shell.setMaximized(true);
        shell.setSize(1000, 800);
    }

    @Override
    protected MenuManager createMenuManager() {
        MenuManager menu = new MenuManager();
        //
        MenuManager fileMenu = new MenuManager("文件");
        MenuManager connMenu=new MenuManager("连接");
        MenuManager confMenu = new MenuManager("配置");
        MenuManager helpMenu = new MenuManager("帮助");
        menu.add(fileMenu);
        menu.add(connMenu);
        menu.add(confMenu);
        menu.add(helpMenu);
        //
        fileMenu.add(this.newAction);
        fileMenu.add(new Separator());
        fileMenu.add(new ExitAction());
        //
        connMenu.add(new ConnectionAction());
        connMenu.add(new ConnListAction());
        connMenu.add(new DisconnectionAction());
        //
        confMenu.add(configAction);
        //
        helpMenu.add(aboutAction);
        //
        return menu;
    }

    @Override
    protected ToolBarManager createToolBarManager(int style) {
       ToolBarManager toolBarManager=new ToolBarManager(SWT.NONE);
       toolBarManager.add(new Back());
       toolBarManager.add(new Prev());
       toolBarManager.add(new Home());
       toolBarManager.add(new Fresh());
       toolBarManager.add(new HBSeparator());
       toolBarManager.add(new Mkdir());
       toolBarManager.add(new Delete());
       toolBarManager.add(new Rename());
       toolBarManager.add(new Copy());
       toolBarManager.add(new Cut());
       toolBarManager.add(new Paste());
       toolBarManager.add(new Property());
       toolBarManager.add(new HBSeparator());
       toolBarManager.add(new Download());
       toolBarManager.add(new Upload());
       toolBarManager.add(new HBSeparator());
       toolBarManager.add(new Search());
       return toolBarManager;
    }

    @Override
    protected Control createContents(Composite parent) {
        LOGGER.info("start to create the content of the windows");
        parent.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        parent.setBackgroundMode(SWT.MOD2);
        //
        this.content=new Composite(parent,SWT.BORDER);
        this.content.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        FormLayout formLayout=new FormLayout();
        this.content.setLayout(formLayout);
        //
        this.header=new Composite(this.content,SWT.BORDER);
        createHeaderArea(header);
        //
        this.bottom=new Composite(this.content,SWT.BORDER);
        createBottomArea(bottom);
        //
        this.left=new Composite(this.content,SWT.BORDER);
        createLeftArea(left);

        //
        this.right=new Composite(this.content,SWT.BORDER);
        createRightArea(right);
        //
        createRightContent();
//        createLeftContent(this.left);
        return parent;
    }

    public void connection()
    {
        connected=true;
        AppConfiguration configuration=AppConfiguration.GetAppConfiguration();
        String hdfsUrl="hdfs://"+configuration.getHdfsHost()+":"+configuration.getHdfsPort()+"/";
        LOGGER.info("the hdfs url is "+hdfsUrl);
        try {
            hdfsCore=HDFSCoreFactory.GetHDFSCore(HDFSCoreFactory.TYPE.api);
            ((HAPICore)hdfsCore).setHDFS(hdfsUrl);
            ((HAPICore)hdfsCore).initFileSystem();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        createRightContent();
        createLeftContent();
        //
        table.setHdfsCore(hdfsCore);
    }

    public boolean isConnected()
    {
        return connected;
    }

    /**
     * close the connection with hdfs url
     */
    public void disconnection()
    {
        hdfsCore=null;
        this.hdfsTree.getControl().dispose();
        this.hdfsTree=null;
        System.gc();
        this.connected=false;
    }

    public void createHeaderArea(Composite header)
    {
        FormData headerForm=new FormData();
        headerForm.left=new FormAttachment(0);
        headerForm.top=new FormAttachment(0);
        headerForm.right=new FormAttachment(100);
        headerForm.bottom=new FormAttachment(0);
        header.setLayoutData(headerForm);
        header.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
    }

    public void createBottomArea(Composite bottom)
    {
        FormData formData=new FormData();
        formData.left=new FormAttachment(0);
        formData.right=new FormAttachment(100);
        formData.bottom=new FormAttachment(100);
        formData.top=new FormAttachment(100);
        bottom.setLayoutData(formData);
        bottom.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
    }

    public void createLeftArea(Composite left)
    {
        FormData leftFormData=new FormData();
        leftFormData.top=new FormAttachment(this.header);
        leftFormData.left=new FormAttachment(0);
        leftFormData.right=new FormAttachment(0,250);
        leftFormData.bottom=new FormAttachment(this.bottom);
        left.setLayoutData(leftFormData);
        left.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        left.setLayout(new FillLayout());
    }

    /**
     * @author wozipa
     * @date 2017-3-28
     * @param right
     */
    public void createRightArea(Composite right)
    {
        FormData formData=new FormData();
        formData.top=new FormAttachment(this.header);
        formData.bottom=new FormAttachment(this.bottom);
        formData.left=new FormAttachment(this.left);
        formData.right=new FormAttachment(100);
        right.setLayoutData(formData);
        right.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        right.setLayout(new FillLayout());
    }

    public void createLeftContent()
    {
        LOGGER.info(this.left.getSize().toString());
        this.hdfsTree=new HTreeViewer(this.left,SWT.H_SCROLL|SWT.V_SCROLL|SWT.MULTI|SWT.FULL_SELECTION,this.hdfsCore);
        this.hdfsTree.setExpandedElements(new HTreeNode[]{HTreeRootNode.GetRootNode()});
    }

    public void createRightContent()
    {
       table=new HTableViewer(this.right,SWT.FULL_SELECTION);
    }

    @Override
    public boolean close() {
        LOGGER.info("close the hdfs browser");
        ConnectionContainer.GetConnectionContainer().close();
        return super.close();
    }

    public HDFSCore getHdfsCore()
    {
        return hdfsCore;
    }

    public static void main(String[] args)
    {
        HDFSBrowserWindow window=new HDFSBrowserWindow();
        window.setBlockOnOpen(true);
        window.open();
        Display.getCurrent().dispose();
    }
}
