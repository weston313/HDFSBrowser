package com.geoway.hdfsbrowser.app.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Connection extends Action {

    public Connection()
    {
        super();
        this.setText("创建连接");
        this.setEnabled(true);
        this.setAccelerator(SWT.CTRL+SWT.SHIFT+'C');
//        this.setImageDescriptor();
    }

    @Override
    public void run() {
        super.run();
    }
}
