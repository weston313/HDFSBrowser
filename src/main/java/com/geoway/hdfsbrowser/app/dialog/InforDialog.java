package com.geoway.hdfsbrowser.app.dialog;

import com.geoway.hdfsbrowser.util.ColorUtils;
import com.geoway.hdfsbrowser.util.FileUtils;
import org.apache.hadoop.fs.FileStatus;
import org.apache.log4j.lf5.LF5Appender;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by USER on 2017/4/15.
 */
public class InforDialog extends Dialog {

    private Text nameText;
    private Text timeText;
    private Text typeText;
    private Text sizeText;
    private Text pathText;
    private Text ownerText;
    private Text premissionText;

    public InforDialog(Shell parentShell) {
        super(parentShell);
    }

    @Override
    protected void configureShell(Shell newShell) {
        newShell.setText("属性");
        newShell.setSize(200,250);
    }

    public void setFileStatus(FileStatus fileStatus)
    {
        nameText.setText(fileStatus.getPath().getName());
        timeText.setText(fileStatus.getModificationTime()+"");
        typeText.setText(FileUtils.GetFileType(fileStatus));
        sizeText.setText(fileStatus.getLen()+"");
        pathText.setText(fileStatus.getPath().toString());
        ownerText.setText(fileStatus.getOwner());
        premissionText.setText(fileStatus.getPermission().toString());
    }

    @Override
    protected Control createContents(Composite parent) {
        GridLayout gridLayout=new GridLayout(2,false);
        gridLayout.marginLeft=10;
        gridLayout.marginRight=10;
        gridLayout.marginTop=10;
        gridLayout.marginBottom=10;
        parent.setLayout(gridLayout);
        parent.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        //
        //
        nameText=createInput(parent,"名称");
        typeText=createInput(parent,"类型");
        pathText=createInput(parent,"路径");
        sizeText=createInput(parent,"大小");
        timeText=createInput(parent,"创建时间");
        ownerText=createInput(parent,"拥有者");
        premissionText=createInput(parent,"文件权限");
        //
        return parent;
    }

    public Text createInput(Composite parent,String name)
    {
        Label label=new Label(parent, SWT.NONE);
        label.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        label.setLayoutData(new GridData(SWT.LEFT,SWT.CENTER,false, false,1,1));
        label.setText(name);
        //
        Text text=new Text(parent,SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false,1,1));
        text.setBackground(ColorUtils.GetColor(ColorUtils.WHITE));
        return text;
    }
}
