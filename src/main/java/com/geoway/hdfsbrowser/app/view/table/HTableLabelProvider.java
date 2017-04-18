package com.geoway.hdfsbrowser.app.view.table;

import com.geoway.hdfsbrowser.app.view.HNode;
import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Created by wozipa on 2017/4/8.
 */
public class HTableLabelProvider implements ITableLabelProvider {

    private static final Logger LOGGER=Logger.getLogger(HTableLabelProvider.class);

    @Override
    public Image getColumnImage(Object o, int i) {
        return null;
    }

    @Override
    public String getColumnText(Object o, int i) {
        LOGGER.info("get the column text of the table");
        HNode file = (HNode) o;
        switch (i)
        {
            case HTableViewer.NAME_ID:
                return file.getName();
            case HTableViewer.TIME_ID:
                return file.getTime();
            case HTableViewer.TYPE_ID:
                return file.getType();
            case HTableViewer.SIZE_ID:
                return file.getSize();
            default:
                return "";
        }
    }

    @Override
    public void addListener(ILabelProviderListener iLabelProviderListener) {
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean isLabelProperty(Object o, String s) {
        return false;
    }

    @Override
    public void removeListener(ILabelProviderListener iLabelProviderListener) {

    }
}
