package com.geoway.hdfsbrowser.app.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class ConnList extends Action {

    public ConnList()
    {
        super();
        this.setText("连接列表");
        this.setAccelerator(SWT.CTRL+SWT.SHIFT+'L');
        this.setEnabled(true);
//        this.setImageDescriptor();
    }

    @Override
    public void run() {
        super.run();
    }
}
