package com.geoway.hdfsbrowser.app.tree;

import com.geoway.hdfsbrowser.app.tree.action.NewAction;
import javafx.scene.control.ContextMenu;
import org.apache.log4j.Logger;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

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

    private NewAction newAction;

    public HTreeContextMenu()
    {
        super();
        newAction=new NewAction();
        this.setRemoveAllWhenShown(true);
        this.addMenuListener(new HTreeMenuListen());
    }

    public class HTreeMenuListen implements IMenuListener {

        @Override
        public void menuAboutToShow(IMenuManager iMenuManager) {
            System.out.print("create the context menu");
            add(newAction);
        }
    }
}
