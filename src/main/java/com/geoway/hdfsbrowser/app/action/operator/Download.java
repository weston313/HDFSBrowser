package com.geoway.hdfsbrowser.app.action.operator;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Download extends Action {

    public Download()
    {
        super();
        this.setText("下载");
        this.setEnabled(true);
        this.setAccelerator(SWT.CTRL+'D');
//        this.setImageDescriptor();
    }

    @Override
    public void run() {
        super.run();
    }
}
