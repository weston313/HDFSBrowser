package com.geoway.hdfsbrowser.app.tree.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Copy extends Action {

    public Copy(){
        this.setEnabled(true);
        this.setText("复制");
        this.setAccelerator(SWT.CTRL+'C');
//        this.setImageDescriptor();
    }

    @Override
    public void run() {
        super.run();
    }
}
