package com.geoway.hdfsbrowser.app.action.operator;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Upload extends Action {

    public Upload()
    {
        super();
        this.setText("上传");
        this.setEnabled(true);
        this.setAccelerator(SWT.CTRL+'U');
//        this.setImageDescriptor();
    }

    @Override
    public void run() {
        super.run();
    }
}
