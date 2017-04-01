package com.geoway.hdfsbrowser.app.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by USER on 2017/3/27.
 */
public class NewAction extends Action {

    public NewAction()
    {
        super();
        this.setText("新建");
        this.setAccelerator(SWT.SHIFT+SWT.ALT+'N');
        this.setEnabled(true);
    }

    @Override
    public void run() {
        super.run();
    }
}
