package com.geoway.hdfsbrowser.app.tree.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Move extends Action {

    public Move()
    {
        super();
        this.setText("移动");
        this.setAccelerator(SWT.CTRL+SWT.SHIFT+'M');
        this.setEnabled(true);
    }

    @Override
    public void run() {
        super.run();
    }
}
