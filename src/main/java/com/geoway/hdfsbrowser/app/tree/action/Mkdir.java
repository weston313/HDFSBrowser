package com.geoway.hdfsbrowser.app.tree.action;

import com.geoway.hdfsbrowser.service.core.HDFSCoreFactory;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;

/**
 * Created by wozipa on 2017/4/5.
 */
public class Mkdir extends Action {

    public Mkdir()
    {
        super();
        this.setText("MkDir");
        this.setAccelerator(SWT.CTRL+SWT.SHIFT+'M');
        this.setEnabled(true);
    }

    @Override
    public void run() {
        try {
            HDFSCoreFactory.GetHDFSCore(HDFSCoreFactory.TYPE.api);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
