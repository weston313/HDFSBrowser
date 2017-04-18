package com.geoway.hdfsbrowser.app.view.table;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.action.operator.*;
import com.geoway.hdfsbrowser.app.view.table.action.Open;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;

/**
 * Created by USER on 2017/4/17.
 */
public class HTableContextMenu extends MenuManager {

    private HTableViewer tableViewer=null;
    private HDFSCore hdfsCore;

    private Open open;
    private Download download;
    private Delete delete;
    private Rename rename;
    private Copy copy;
    private Cut cut;
    private Paste paste;
    private Property property;

    public HTableContextMenu()
    {
        HDFSBrowserWindow window=HDFSBrowserWindow.GetApp();
        tableViewer=window.getTable();
        this.hdfsCore=window.getHdfsCore();
        //
        open=new Open();
        download=new Download();
        delete=new Delete();
        rename=new Rename();
        copy=new Copy();
        paste=new Paste();
        property=new Property();
        //
        this.setRemoveAllWhenShown(true);
        this.addMenuListener(new HTableMenuListern());
    }

    public class HTableMenuListern implements IMenuListener {

        @Override
        public void menuAboutToShow(IMenuManager iMenuManager) {
            iMenuManager.add(open);
            iMenuManager.add(download);
            iMenuManager.add(delete);
            iMenuManager.add(rename);
            iMenuManager.add(copy);
            iMenuManager.add(paste);
            iMenuManager.add(property);
        }
    }
}
