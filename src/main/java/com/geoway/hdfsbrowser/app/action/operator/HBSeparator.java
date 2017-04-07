package com.geoway.hdfsbrowser.app.action.operator;

import org.eclipse.jface.action.Action;

/**
 * Created by USER on 2017/4/7.
 */
public class HBSeparator extends Action {

    public HBSeparator()
    {
        super();
        this.setText("|");
        this.setEnabled(false);
    }
}
