package com.geoway.hdfsbrowser.app.action.operator;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Rename extends Action {

    public Rename()
    {
        this.setText("重命名");
        this.setEnabled(true);
        this.setAccelerator(SWT.CTRL+SWT.SHIFT+'R');
//        this.setImageDescriptor();
    }

    @Override
    public void run() {
        super.run();
    }
}
