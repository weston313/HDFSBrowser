package com.geoway.hdfsbrowser.app.action.menu;

import org.eclipse.jface.action.Action;

/**
 * Created by USER on 2017/4/7.
 */
public class ExitAction extends Action {

    public ExitAction()
    {
        super();
        this.setText("退出");
        this.setToolTipText("退出");
        this.setEnabled(true);
//        this.setImageDescriptor();
    }

    @Override
    public void run() {
        super.run();
    }
}
