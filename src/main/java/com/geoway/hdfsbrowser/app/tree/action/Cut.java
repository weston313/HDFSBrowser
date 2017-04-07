package com.geoway.hdfsbrowser.app.tree.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Cut extends Action {

    public Cut()
    {
        this.setEnabled(true);
        this.setText("剪切");
        this.setAccelerator(SWT.CTRL+'X');
//        this.setImageDescriptor();
    }

    @Override
    public void run() {
        super.run();
    }
}
