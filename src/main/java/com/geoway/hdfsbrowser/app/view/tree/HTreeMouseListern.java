package com.geoway.hdfsbrowser.app.view.tree;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Tree;

/**
 * Created by USER on 2017/4/1.
 */
public class HTreeMouseListern implements MouseListener{

    private static final Logger LOGGER=Logger.getLogger(HTreeMouseListern.class);

    private static final int BUTTON_LEFT=1;
    private static final int BUTTON_RIGHT=3;
    private static final int BUTTON_CENTER=2;

    @Override
    public void mouseDoubleClick(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDown(MouseEvent mouseEvent) {
//        LOGGER.info("the button num is "+mouseEvent.button);
//        Tree tree= (Tree) mouseEvent.getSource();
//        TreeItem beforeItem=tree.getSelection()[0];
//        LOGGER.info(((HTreeNode)beforeItem.getData()).getName());
//        if(mouseEvent.stateMask==SWT.BUTTON3 || mouseEvent.button==BUTTON_RIGHT)
//        {
//            LOGGER.info("right click");
//            TreeItem nowItem=tree.getItem(new Point(mouseEvent.x,mouseEvent.y));
//            String beforeName=((HTreeNode)beforeItem.getData()).getName();
//            String nowName=((HTreeNode)nowItem.getData()).getName();
//            LOGGER.info("the before name "+beforeName+" the now name "+nowName);
//            tree.select(nowItem);
//            tree.select(beforeItem);
//        }
//        else if(mouseEvent.stateMask==SWT.BUTTON1 || mouseEvent.button==BUTTON_LEFT)
//        {
//            TreeItem nowItem=tree.getItem(new Point(mouseEvent.x,mouseEvent.y));
//            tree.deselectAll();
//            tree.select(nowItem);
//        }
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent) {
        int buttonNum=mouseEvent.stateMask;
        Tree tree= (Tree) mouseEvent.getSource();
        switch (buttonNum)
        {
            case SWT.BUTTON3:
                //right button click
                break;
            case SWT.BUTTON1:
                //left click button
                break;
            default:
                break;
        }
    }

}
