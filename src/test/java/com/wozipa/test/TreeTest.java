package com.wozipa.test;

import org.apache.htrace.commons.logging.Log;
import org.apache.htrace.commons.logging.LogFactory;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by USER on 2017/4/1.
 */
public class TreeTest extends ApplicationWindow {

    private static final Log LOG= LogFactory.getLog(TreeTest.class);

    public TreeTest() {
        super(null);
    }

    @Override
    protected Control createContents(Composite parent) {
        Tree tree=new Tree(parent, SWT.BORDER|SWT.SINGLE);
        for(int i=0;i<10;i++)
        {
            TreeItem item=new TreeItem(tree,SWT.NONE);
            item.setText("root "+i);
        }
        return parent;
    }

    public static void main(String[] args)
    {
        TreeTest test=new TreeTest();
        test.setBlockOnOpen(true);
        test.open();
        Display.getCurrent().dispose();
    }

    @Test
    public void testLog()
    {

    }
}
