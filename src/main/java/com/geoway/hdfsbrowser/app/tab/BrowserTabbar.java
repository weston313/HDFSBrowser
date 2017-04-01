package com.geoway.hdfsbrowser.app.tab;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * Created by USER on 2017/3/27.
 */
public class BrowserTabbar extends TabItem {

    private TabFolder parent;
    private int style;
    private Composite composite=null;

    public BrowserTabbar(TabFolder tabFolder, int i) {
        super(tabFolder, i);
        this.parent=tabFolder;
        this.style=i;
        this.composite=new Composite(parent,style);
        this.setControl(composite);
        this.setText("File Broswer");
        //
        this.composite.setBackground(new Color(null,new RGB(255,255,255)));
        this.composite.setLayout(new GridLayout(10,false));
    }

    /**
     * @author wozipa
     * @date 2017-3-27
     * the content of the tabbat
     */
    public void createContent()
    {

    }

    @Override
    protected void checkSubclass() {
        //super.checkSubclass();
    }
}
