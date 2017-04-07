package com.geoway.hdfsbrowser.app.tree;

import com.geoway.hdfsbrowser.app.action.operator.Mkdir;
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

    public static HTreeContextMenu getContextMenu()
    {
        if(contextMenu==null)
        {
            synchronized (HTreeContextMenu.class)
            {
                if(contextMenu==null)
                {
                    contextMenu=new HTreeContextMenu();
                }
            }
        }
        return contextMenu;
    }

    private Mkdir mkdir;

    public HTreeContextMenu()
    {
        super();
        mkdir=new Mkdir();
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
