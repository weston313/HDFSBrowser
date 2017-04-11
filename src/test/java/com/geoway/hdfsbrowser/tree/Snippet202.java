package com.geoway.hdfsbrowser.tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by USER on 2017/4/11.
 */
public class Snippet202 {

    public static void main(String[] args) {
        Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setLayout (new FillLayout());
        final Tree tree = new Tree(shell, SWT.VIRTUAL | SWT.BORDER);
        // 生成节点的总是
        final int[] iArr = new int[1];
        tree.addListener(SWT.SetData, new Listener() {
            public void handleEvent(Event event) {
                System.out.println("Create the "+(++iArr[0]) +" item.");
                TreeItem item = (TreeItem)event.item;
                TreeItem parentItem = item.getParentItem();
                String text = null;
                if (parentItem == null) {
                    text = "node "+tree.indexOf(item);
                } else {
                    text = parentItem.getText()+" - "+parentItem.indexOf(item);
                }
                item.setText(text);
                // 设置可以接受几个子节点
                item.setItemCount(3);
            }
        });
        // 自动生成几个根节点
        tree.setItemCount(5);
        shell.setSize(400, 300);
        shell.open();
        while (!shell.isDisposed ()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
    }
}
