package com.geoway.hdfsbrowser.app.action.operator;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Fresh extends Action {

    public Fresh()
    {
        super();
        this.setText("刷新");
        this.setEnabled(true);
        this.setAccelerator(SWT.F5);
//        this.setImageDescriptor();
    }

    @Override
    public void run() {
        super.run();
    }
}
