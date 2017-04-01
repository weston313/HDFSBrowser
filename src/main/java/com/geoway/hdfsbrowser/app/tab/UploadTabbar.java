package com.geoway.hdfsbrowser.app.tab;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;

/**
 * Created by USER on 2017/3/27.
 */
public class UploadTabbar extends TabItem{

    private TabFolder parent;
    private int style;
    private Composite composite;
    private Table table=null;

    public UploadTabbar(TabFolder tabFolder, int i) {
        super(tabFolder, i);
        this.parent=tabFolder;
        this.style=i;
        this.composite=new Composite(parent,style);
        this.setText("Upload List");
        this.setControl(composite);
        //
        this.composite.setBackground(new Color(null,new RGB(255,255,255)));
        this.composite.setLayout(new GridLayout(10,false));
    }

    public void createContent()
    {

    }

    @Override
    protected void checkSubclass() {
        //
    }
}
