package com.geoway.hdfsbrowser.app.action.operator;

import org.eclipse.jface.action.Action;

/**
 * Created by wozipa on 2017/4/6.
 */
public class Search extends Action{

    public Search()
    {
        super();
        this.setText("搜索");
        this.setToolTipText("搜索");
        this.setEnabled(true);
//        this.setImageDescriptor();
    }

    @Override
    public void run() {
        super.run();
    }
}
