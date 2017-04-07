package com.geoway.hdfsbrowser.app.action.operator;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Delete extends Action {

    public Delete()
    {
        super();
        this.setText("删除");
        this.setEnabled(true);
        this.setAccelerator(SWT.CTRL+SWT.SHIFT+'D');
    }

    @Override
    public void run() {
        super.run();
    }
}
