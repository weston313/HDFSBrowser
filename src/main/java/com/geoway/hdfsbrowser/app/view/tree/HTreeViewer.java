package com.geoway.hdfsbrowser.app.view.tree;

import com.geoway.hdfsbrowser.app.HDFSBrowserWindow;
import com.geoway.hdfsbrowser.app.view.HNode;
import com.geoway.hdfsbrowser.app.view.ViewerMutual;
import com.geoway.hdfsbrowser.app.view.table.HTableViewer;
import com.geoway.hdfsbrowser.service.container.IndexContainer;
import com.geoway.hdfsbrowser.service.container.PathsContainer;
import com.geoway.hdfsbrowser.service.core.impl.HDFSCore;
import com.geoway.hdfsbrowser.util.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.widgets.*;

import java.lang.reflect.Proxy;

/**
 * Created by wozipa on 2017/4/1.
 */
public class HTreeViewer extends TreeViewer {

    private static final Logger LOGGER=Logger.getLogger(HTreeViewer.class);

    private Composite parent;
    private int style;
    private HDFSCore hdfsCore=null;
    private IndexContainer container=null;
    private Tree tree;
    private ViewerMutual proxy;
    private PathsContainer pathsContainer=null;

    public HTreeViewer(Composite parent,int style,HDFSCore hdfsCore) {
        super(parent,style);
        this.parent=parent;
        this.style=style;
        //
        container=IndexContainer.GetContainer();
        this.proxy=ViewerMutual.getMutual();
        this.proxy.setTreeViewer(this);
        this.tree=this.getTree();
        this.pathsContainer=PathsContainer.getContainer();
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
        this.getControl().setMenu(HTreeContextMenu.getContextMenu(this).createContextMenu(this.getControl()));
    }

    @Override
    protected void handleTreeExpand(TreeEvent event) {
        TreeItem parent= (TreeItem) event.item;
        HNode node= (HNode) parent.getData();
        ITreeContentProvider contentProvider= (ITreeContentProvider) this.getContentProvider();
        ILabelProvider labelProvider= (ILabelProvider) this.getLabelProvider();
        if(contentProvider.hasChildren(node) && node.isNeedChange())
        {

            node.setNeedChange(false);
            HNode[] children=(HNode[]) contentProvider.getChildren(node);
            parent.setItemCount(0);
            for(HNode child:children)
            {
                TreeItem treeItem=new TreeItem(parent,SWT.NONE);
                treeItem.setText(labelProvider.getText(child));
                treeItem.setImage(labelProvider.getImage(child));
                treeItem.setData(child);
                treeItem.setItemCount(1);
                container.addIndex(treeItem);
            }
        }
    }

    @Override
    protected void handleSelect(SelectionEvent event) {
        LOGGER.info("select action");
        if(event.stateMask==SWT.BUTTON1)
        {
            LOGGER.info("change content of table");
            TreeItem item= (TreeItem) event.item;
            HNode node= (HNode) item.getData();
            proxy.show(node);
            this.pathsContainer.add(node);
        }
    }

    public void fresh(TreeItem item)
    {
        HNode node= (HNode) item.getData();
        fresh(node);
    }

    public void fresh(HNode node)
    {
        if(this.getExpandedState(node))
        {
            this.setExpandedState(node,false);
            this.setExpandedState(node,true);
        }
    }

    public void select(HNode node)
    {
        LOGGER.info("select tree item node from the path");
        String path=node.getPath();
        if(path==null || path.isEmpty())
        {
            return ;
        }
        TreeItem item=null;
        if(node instanceof HTreeRootNode)
        {
            item=tree.getItem(0);
        }
        else
        {
            item=container.get(FileUtils.CorrectPath(path));
        }
        if(item!=null)
        {
            tree.deselectAll();
            tree.select(item);
        }
    }
}
