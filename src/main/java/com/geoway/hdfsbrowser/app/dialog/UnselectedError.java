package com.geoway.hdfsbrowser.app.dialog;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by USER on 2017/4/15.
 */
public class UnselectedError extends MessageBox {

    private static final Logger LOGGER=Logger.getLogger(UnselectedError.class);

    private static volatile UnselectedError ERROR=null;

    public static UnselectedError getERROR()
    {
        if(ERROR==null)
        {
            synchronized (UnselectedError.class)
            {
                if(ERROR==null)
                {
                    ERROR=new UnselectedError();
                }
            }
        }
        return ERROR;
    }

    private UnselectedError() {
        super(HDFSBrowserWindow.GetApp().getShell(), SWT.ICON_WARNING);
        this.setText("错误");
        this.setMessage("请选择一个文件");
        this.open();
    }

    @Override
    protected void checkSubclass() {

    }
}
