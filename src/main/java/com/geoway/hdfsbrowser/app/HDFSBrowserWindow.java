package com.geoway.hdfsbrowser.app;

import com.geoway.hdfsbrowser.app.action.AboutAction;
import com.geoway.hdfsbrowser.app.action.ConfigAction;
import com.geoway.hdfsbrowser.app.action.NewAction;
import com.geoway.hdfsbrowser.app.tab.BrowserTabbar;
import com.geoway.hdfsbrowser.app.tab.DownloadTabbar;
import com.geoway.hdfsbrowser.app.tab.UploadTabbar;
import com.geoway.hdfsbrowser.app.tree.*;
import com.geoway.hdfsbrowser.service.core.HAPICore;
import com.geoway.hdfsbrowser.service.core.HDFSCoreFactory;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import com.geoway.hdfsbrowser.util.ColorUtils;
import javafx.scene.Parent;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;
import sun.security.krb5.internal.APRep;
import sun.security.krb5.internal.TransitedEncoding;

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

    //
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
    private TreeViewer hdfsTree;


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
        MenuManager fileMenu = new MenuManager("File(&F)");
        MenuManager confMenu = new MenuManager("Config(&C)");
        MenuManager helpMenu = new MenuManager("Help(&H)");
        menu.add(fileMenu);
        menu.add(confMenu);
        menu.add(helpMenu);
//        //
        fileMenu.add(this.newAction);
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
       toolBarManager.add(newAction);
       return toolBarManager;
    }

    @Override
    protected Control createContents(Composite parent) {
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
//        //
        this.bottom=new Composite(this.content,SWT.BORDER);
        createBottomArea(bottom);
        //
        this.left=new Composite(this.content,SWT.BORDER);
        createLeftArea(left);

        //
        this.right=new Composite(this.content,SWT.BORDER);
        createRightArea(right);
        //
        createLeftContent(this.left);
        createRightContent(this.right);
        //
        return parent;
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
    }

    public void createLeftContent(Composite parent)
    {
        parent.setLayout(new FillLayout());
        this.hdfsTree=new TreeViewer(parent,SWT.NONE);
        try {
            HDFSCore hdfsCore=HDFSCoreFactory.GetHDFSCore(HDFSCoreFactory.TYPE.api);
            ((HAPICore)hdfsCore).setHDFS("hdfs://192.98.19.11:9000");
            ((HAPICore)hdfsCore).initFileSystem();
            this.hdfsTree.setContentProvider(new HTreeContentProvider(hdfsCore));
            this.hdfsTree.setLabelProvider(new HTreeLabelProvider());
            this.hdfsTree.setInput("HDFS浏览框");
            this.hdfsTree.getControl().setMenu(HTreeContextMenu.getContextMenu().createContextMenu(this.hdfsTree.getControl()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //this.hdfsTree.getControl().setMenu(HTreeContextMenu.getContextMenu().createContextMenu(this.hdfsTree.getControl()));
        //
    }

    public void createRightContent(Composite parent)
    {
        parent.setLayout(new FillLayout());
        TabFolder tabFolder=new TabFolder(parent,SWT.NONE);
        BrowserTabbar browserTabbar=new BrowserTabbar(tabFolder,SWT.NONE);
        DownloadTabbar downloadTabbar=new DownloadTabbar(tabFolder,SWT.NONE);
        UploadTabbar uploadTabbar=new UploadTabbar(tabFolder,SWT.NONE);
    }


    public static void main(String[] args)
    {
        HDFSBrowserWindow window=new HDFSBrowserWindow();
        window.setBlockOnOpen(true);
        window.open();
        Display.getCurrent().dispose();
    }
}
