package com.geoway.hdfsbrowser.tree;

import java.io.File;

import javafx.scene.control.Tab;
import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;


public class DragTree {

	private static final Logger LOGGER=Logger.getLogger(DragTree.class);



	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		//
		Tree tree=new Tree(shell,SWT.VIRTUAL);
		TreeItem item=new TreeItem(tree,SWT.Expand);
		item.setText("E");
		tree.addListener(SWT.SetData, new Listener() {
			@Override
			public void handleEvent(Event event) {

			}
		});
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}