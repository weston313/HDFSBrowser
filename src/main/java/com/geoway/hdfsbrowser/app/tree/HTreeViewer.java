package com.geoway.hdfsbrowser.app.tree;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.service.core.HAPICore;
import com.geoway.hdfsbrowser.service.core.HDFSCoreFactory;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import com.geoway.hdfsbrowser.util.ColorUtils;
import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

/**
 * Created by wozipa on 2017/4/1.
 */
public class HTreeViewer extends TreeViewer {

    private static final Logger LOGGER=Logger.getLogger(HTreeViewer.class);

    private Composite parent;
    private int style;
    private HDFSCore hdfsCore=null;
    private Table table;

    public HTreeViewer(Composite parent,int style,HDFSCore hdfsCore) {
        super(parent,style);
        this.parent=parent;
        this.style=style;
        //
        this.table=HDFSBrowserWindow.GetApp().getTable();
        //
        this.hdfsCore=hdfsCore;
        this.getControl().setBounds(parent.getBounds());
        createContent();
    }

    public void createContent()
    {
        LOGGER.info("create the content of the tree view");
        this.setContentProvider(new HTreeContentProvider(hdfsCore));
        this.setLabelProvider(new HTreeLabelProvider());
        this.setInput("HDFS浏览框");
        LOGGER.info("add selection changed action");
        this.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent selectionChangedEvent) {
                if(selectionChangedEvent.getSelection().isEmpty())
                {
                    return;
                }
                if(selectionChangedEvent.getSelection() instanceof IStructuredSelection)
                {
                    IStructuredSelection selection = (IStructuredSelection)selectionChangedEvent.getSelection();
                    if(selection.size()==1)
                    {
                        HTreeNode node= (HTreeNode) selection.getFirstElement();
                        LOGGER.info(node.getPath());
                    }
                }
            }
        });
        this.getControl().setMenu(HTreeContextMenu.getContextMenu().createContextMenu(this.getControl()));
    }
}
