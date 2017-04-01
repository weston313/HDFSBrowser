package com.geoway.hdfsbrowser.app.tree;

import com.geoway.hdfsbrowser.service.core.HDFSCoreFactory;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Created by USER on 2017/3/29.
 */
public class HTreeContentProvider implements ITreeContentProvider {

    private HDFSCore hdfsCore;

    public HTreeContentProvider(HDFSCore hdfsCore)
    {
        this.hdfsCore=hdfsCore;
    }

    public Object[] getChildren(Object o) {

        return new Object[0];
    }

    public Object getParent(Object o) {
        return null;
    }

    public boolean hasChildren(Object o) {

        return false;
    }

    public Object[] getElements(Object o) {
        return new Object[0];
    }

    public void dispose() {

    }

    public void inputChanged(Viewer viewer, Object o, Object o1) {

    }
}
