package com.wy.ledindicator.utils;

import android.app.Activity;
import android.graphics.Typeface;
import java.util.ArrayList;
import java.util.List;

public class FontManager {

    public static List<String> fonts = new ArrayList<>();
    public static List<String> fonts_ttf = new ArrayList<>();

    public FontManager(){
        if(fonts.size()==0) {
            fonts.add("微软雅黑");
            fonts.add("苹果丽黑");
            fonts.add("楷体");
        }


        if(fonts_ttf.size()==0){
            fonts_ttf.add("wryh.ttf");
            fonts_ttf.add("pglh.ttf");
            fonts_ttf.add("kt.ttf");
        }
    }

    public static List<String> getFonts(){
        return fonts;
    }

    public static String getFontFromPosition(int position){
        return fonts.get(position);
    }

    public static String getFont(int position) {
        return fonts_ttf.get(position);
    }
}

