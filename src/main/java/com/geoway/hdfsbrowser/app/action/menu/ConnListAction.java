package com.geoway.hdfsbrowser.app.action.menu;

import com.geoway.hdfsbrowser.app.windows.ConnectionListWindow;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * Created by wozipa on 2017/4/6.
 */
public class ConnListAction extends Action {

    public ConnListAction()
    {
        super();
        this.setText("连接列表");
        this.setAccelerator(SWT.CTRL+SWT.SHIFT+'L');
        this.setEnabled(true);
//        this.setImageDescriptor();
    }

    @Override
    public void run()
    {
        ConnectionListWindow window=new ConnectionListWindow(Display.getCurrent().getActiveShell());
        window.setBlockOnOpen(true);
        window.open();
    }
}
