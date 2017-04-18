package com.geoway.hdfsbrowser.app.view.tree;

import com.geoway.hdfsbrowser.app.view.HNode;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;

/**
 * Created by USER on 2017/3/29.
 */
public class HTreeLabelProvider implements ILabelProvider {

    @Override
    public Image getImage(Object o) {
        return null;
    }

    @Override
    public String getText(Object o) {
        HNode parent=(HNode)o;
        return parent.getName();
    }

    @Override
    public void addListener(ILabelProviderListener iLabelProviderListener) {
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean isLabelProperty(Object o, String s) {
        return false;
    }

    @Override
    public void removeListener(ILabelProviderListener iLabelProviderListener) {

    }
}
