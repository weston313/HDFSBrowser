package com.geoway.hdfsbrowser.app.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by USER on 2017/3/27.
 */
public class ConfigAction extends Action {

    public ConfigAction()
    {
        super();
        this.setText("Config");
        this.setAccelerator(SWT.SHIFT+SWT.ALT+'c');
        this.setEnabled(true);
    }

    @Override
    public void run() {
        super.run();
    }
}
