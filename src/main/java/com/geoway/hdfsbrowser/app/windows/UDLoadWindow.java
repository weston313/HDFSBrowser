package com.geoway.hdfsbrowser.app.windows;

import com.geoway.hdfsbrowser.app.tab.DownloadListTab;
import com.geoway.hdfsbrowser.app.tab.UploadListTab;
import com.geoway.hdfsbrowser.util.ColorUtils;
import com.sun.tools.corba.se.idl.PragmaEntry;
import org.apache.log4j.Logger;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by USER on 2017/4/17.
 * the window of the upload lost and download list
 */
public class UDLoadWindow extends ApplicationWindow {

    private static final Logger LOGGER=Logger.getLogger(UDLoadWindow.class);

    private static final int LIST_WINDOW_WIDTH=500;
    private static final int LIST_WINDOW_HEIGHT=400;

    private TabFolder content;
    private TabItem upload;
    private TabItem download;

    public UDLoadWindow(Shell parentShell) {
        super(parentShell);
    }

    @Override
    protected void configureShell(Shell shell) {
        shell.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        shell.setText("文件传输列表");
        shell.setSize(LIST_WINDOW_WIDTH,LIST_WINDOW_HEIGHT);
    }

    @Override
    protected Control createContents(Composite parent) {
        parent.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        parent.setLayout(new FillLayout());
        //
        content=new TabFolder(parent,SWT.NONE);
        content.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        //
        upload=new UploadListTab(content,SWT.NONE);
        download=new DownloadListTab(content,SWT.NONE);
        return parent;
    }
}
