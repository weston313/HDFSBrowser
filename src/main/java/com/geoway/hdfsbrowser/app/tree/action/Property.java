package com.geoway.hdfsbrowser.app.tree.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Property extends Action {

    public Property()
    {
        super();
        this.setText("属性");
        this.setEnabled(true);
        this.setAccelerator(SWT.CTRL+'P');
//        this.setImageDescriptor();
    }

    @Override
    public void run() {
        super.run();
    }
}
