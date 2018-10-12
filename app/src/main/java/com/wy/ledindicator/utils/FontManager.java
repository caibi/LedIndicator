package com.wy.ledindicator.utils;

import android.app.Activity;
import android.graphics.Typeface;
import java.util.ArrayList;
import java.util.List;

public class FontManager {

    public static List<String> fonts = new ArrayList<>();
    public static List<String> fonts_ttf = new ArrayList<>();

    public FontManager(){
        fonts.add("微软雅黑");
        fonts.add("苹果丽黑");
        fonts.add("诺基亚古印");
        fonts.add("隶书");
        fonts.add("楷体");
        fonts.add("瘦金体");
        fonts.add("pop字体");
        fonts.add("华康中黑字体");
        fonts.add("华康娃娃体");
        fonts.add("华康少女体");
        fonts.add("华文彩云");
        fonts.add("华文新宋");
        fonts.add("华文新魏");
        fonts.add("华文行楷");
        fonts.add("幼圆");
        fonts.add("文泉驿微米黑");
        fonts.add("方正准圆");
        fonts.add("方正华隶");
        fonts.add("方正卡通简体");
        fonts.add("方正古隶");
        fonts.add("方正启体简体");
        fonts.add("方正小篆");
        fonts.add("方正正圆");
        fonts.add("方正流行体简体");
        fonts.add("方正硬笔行书");
        fonts.add("方正粗圆");
        fonts.add("方正胖头鱼");
        fonts.add("明兰");

        fonts_ttf.add("wryh.ttf");
        fonts_ttf.add("pglh.ttf");
        fonts_ttf.add("njygy.ttf");
        fonts_ttf.add("ls.ttf");
        fonts_ttf.add("kt.ttf");
        fonts_ttf.add("sjt.ttf");
        fonts_ttf.add("popzt.ttf");
        fonts_ttf.add("hkzhzt.ttf");
        fonts_ttf.add("hkwwt.ttf");
        fonts_ttf.add("hksnt.ttf");
        fonts_ttf.add("hwcy.ttf");
        fonts_ttf.add("hwxs.ttf");
        fonts_ttf.add("hwxw.ttf");
        fonts_ttf.add("hwxk.ttf");
        fonts_ttf.add("yy.ttf");
        fonts_ttf.add("wqywmh.ttf");
        fonts_ttf.add("fzzy.ttf");
        fonts_ttf.add("fzhl.ttf");
        fonts_ttf.add("fzktjt.ttf");
        fonts_ttf.add("fzgl.ttf");
        fonts_ttf.add("fzqtjt.ttf");
        fonts_ttf.add("fzxz.ttf");
        fonts_ttf.add("fzzhy.ttf");
        fonts_ttf.add("fzlxtjt.ttf");
        fonts_ttf.add("fzybxs.ttf");
        fonts_ttf.add("fzcy.ttf");
        fonts_ttf.add("fzpty.ttf");
        fonts_ttf.add("ml.ttf");
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

