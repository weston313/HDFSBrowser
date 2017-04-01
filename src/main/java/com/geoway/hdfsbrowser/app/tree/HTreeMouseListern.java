package com.geoway.hdfsbrowser.app.tree;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.directory.shared.kerberos.codec.adAndOr.AdAndOrGrammar;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;

import java.awt.event.MouseAdapter;

/**
 * Created by USER on 2017/4/1.
 */
public class HTreeMouseListern implements MouseListener{


    @Override
    public void mouseDoubleClick(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDown(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseUp(MouseEvent mouseEvent) {
        int buttonNum=mouseEvent.stateMask;
        Tree tree= (Tree) mouseEvent.getSource();
        switch (buttonNum)
        {
            case SWT.BUTTON3:
                break;

            default:
                break;
        }
    }

}
