package com.geoway.hdfsbrowser.app.treeviewer;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.treeviewer.action.Mkdir;
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
    private Mkdir mkdir;
    private HDFSCore hdfsCore=null;

    public HTreeContextMenu(HTreeViewer treeViewer)
    {
        super();
        this.treeViewer=treeViewer;
        this.hdfsCore= HDFSBrowserWindow.GetApp().getHdfsCore();
        mkdir=new Mkdir(treeViewer,hdfsCore);
        this.setRemoveAllWhenShown(true);
        this.addMenuListener(new HTreeMenuListen());
    }

    public class HTreeMenuListen implements IMenuListener {

        @Override
        public void menuAboutToShow(IMenuManager iMenuManager) {
            System.out.print("create the context menu");
            add(mkdir);
        }
    }
}
