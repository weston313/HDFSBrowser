package com.geoway.hdfsbrowser.app.view;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.view.table.HTableViewer;
import com.geoway.hdfsbrowser.app.view.tree.HTreeViewer;
import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Created by USER on 2017/4/15.
 */
public class ViewerMutual {

    private static final Logger LOGGER=Logger.getLogger(ViewerMutual.class);

    private static volatile ViewerMutual mutual=null;

    public static ViewerMutual getMutual()
    {
        if(mutual==null)
        {
            synchronized (ViewerMutual.class)
            {
                if(mutual==null)
                {
                    mutual=new ViewerMutual();
                }
            }
        }
        return mutual;
    }

    private HTreeViewer treeViewer;
    private HTableViewer tableViewer;

    private ViewerMutual(){}

    public void setTreeViewer(HTreeViewer treeViewer)
    {
        this.treeViewer=treeViewer;
    }

    public void setTableViewer(HTableViewer tableViewer)
    {
        this.tableViewer=tableViewer;
    }

    /**
     * fresh the content of the table accond to the tree
     */
    public void mutual2Table()
    {
        HNode node=tableViewer.getDataNode();
        treeViewer.fresh(node);
    }

    /**
     * change the content of the tree accoud to the table
     */
    public void mutual2Tree()
    {
        HNode tableNode=tableViewer.getDataNode();
        IStructuredSelection selection= (IStructuredSelection) treeViewer.getSelection();
        HNode treeNode= (HNode) selection.getFirstElement();
        if(tableNode.equals(treeNode))
        {
            treeViewer.fresh(tableNode);
        }
    }

    public void show(HNode node)
    {
        tableViewer.show(node);
    }

    public void select(HNode node,boolean tree,boolean table)
    {
        //select the data path
        if(tree)
        {
            treeViewer.select(node);
        }
        if(table)
        {
            tableViewer.show(node);
        }
    }

    public void fresh(HNode node,boolean tree,boolean table)
    {
        if(tree)
        {
            treeViewer.fresh(node);
        }
        if(table)
        {
            tableViewer.fresh();
        }
    }
}
