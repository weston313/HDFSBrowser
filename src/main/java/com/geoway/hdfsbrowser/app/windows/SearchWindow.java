package com.geoway.hdfsbrowser.app.windows;

import com.geoway.hdfsbrowser.util.ColorUtils;
import org.apache.log4j.Logger;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.internal.win32.SHELLEXECUTEINFO;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by USER on 2017/4/18.
 */
public class SearchWindow extends ApplicationWindow {

    private static final Logger LOGGER=Logger.getLogger(SearchWindow.class);

    private static final int SEARCH_WIDTH=500;
    private static final int SEARCH_HEIGHT=400;

    public SearchWindow(Shell parentShell) {
        super(parentShell);
    }

    @Override
    protected void configureShell(Shell shell) {
        shell.setText("搜索文件");
        shell.setSize(SEARCH_WIDTH,SEARCH_HEIGHT);
        shell.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
    }

    @Override
    protected Control createContents(Composite parent) {
        parent.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        GridLayout layout=new GridLayout(3,false);
        layout.marginWidth=layout.marginHeight=20;
        parent.setLayout(layout);
        //
        
        return  parent;
    }
}
