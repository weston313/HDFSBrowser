package com.geoway.hdfsbrowser.app.view.tree;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.view.tree.action.*;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;

/**
 * Created by USER on 2017/4/1.
 */
public class HTreeContextMenu  extends MenuManager{

    private static final Logger LOGGER=Logger.getLogger(HTreeContextMenu.class);

    private static volatile HTreeContextMenu contextMenu=null;

    public static HTreeContextMenu getContextMenu(HTreeViewer treeViewer)
    {
        if(contextMenu==null)
        {
            synchronized (HTreeContextMenu.class)
            {
                if(contextMenu==null)
                {
                    contextMenu=new HTreeContextMenu(treeViewer);
                }
            }
        }
        return contextMenu;
    }

    private HTreeViewer treeViewer=null;
    private Open open;
    private Mkdir mkdir;
    private Infor infor;
    private Rename rename;
    private Delete delete;
    private HDFSCore hdfsCore=null;

    public HTreeContextMenu(HTreeViewer treeViewer)
    {
        super();
        this.treeViewer=treeViewer;
        this.hdfsCore= HDFSBrowserWindow.GetApp().getHdfsCore();
        mkdir=new Mkdir(treeViewer,hdfsCore);
        infor=new Infor(treeViewer,hdfsCore);
        open=new Open(treeViewer,hdfsCore);
        rename=new Rename(treeViewer,hdfsCore);
        delete=new Delete(treeViewer,hdfsCore);
        this.setRemoveAllWhenShown(true);
        this.addMenuListener(new HTreeMenuListen());
    }

    public class HTreeMenuListen implements IMenuListener {

        @Override
        public void menuAboutToShow(IMenuManager iMenuManager) {
            add(open);
            add(delete);
            add(mkdir);
            add(rename);
            add(infor);
        }
    }
}
