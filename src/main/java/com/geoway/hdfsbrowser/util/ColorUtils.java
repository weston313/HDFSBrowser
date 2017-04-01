package com.geoway.hdfsbrowser.util;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by USER on 2017/3/28.
 * Tht uuil object to get color object
 */
public class ColorUtils {

    public static final int WHITE=1;
    public static final int RED=2;
    public static final int BLACK=3;
    public static final int GREEN=4;
    public static final int BLUE=5;

    private  static Map<Integer,Color> CONTAINER=new HashMap<Integer, Color>();

    public static Color GetColor(int color)
    {
        return  CONTAINER.get(color);
    }

    static {
        CONTAINER.put(WHITE,new Color(null,new RGB(255,255,255)));
        CONTAINER.put(RED,new Color(null, new RGB(255,0,0)));
        CONTAINER.put(BLACK,new Color(null,new RGB(0,0,0)));
        CONTAINER.put(GREEN,new Color(null,new RGB(0,255,0)));
        CONTAINER.put(BLUE,new Color(null,new RGB(0,0,255)));
    }
}
