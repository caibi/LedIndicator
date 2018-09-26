package com.wy.ledindicator.utils;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FontManager {

    public static List<String> fonts = new ArrayList<>();

    static {
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
        fonts.add("方正静蕾简体");
        fonts.add("明兰");
    }


    public static void changeFonts(ViewGroup root, Activity act,int position) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(fonts.get(0),"wryh.ttf");
        hashMap.put(fonts.get(1),"pglh.ttf");
        hashMap.put(fonts.get(2),"njygy.ttf");
        hashMap.put(fonts.get(3),"ls.ttf");
        hashMap.put(fonts.get(4),"kt.ttf");
        hashMap.put(fonts.get(5),"sjt.ttf");
        hashMap.put(fonts.get(6),"popzt.ttf");
        hashMap.put(fonts.get(7),"hkzhzt.ttf");
        hashMap.put(fonts.get(8),"hkwwt.ttf");
        hashMap.put(fonts.get(9),"hksnt.ttf");
        hashMap.put(fonts.get(10),"hwcy.ttf");
        hashMap.put(fonts.get(11),"hwxs.ttf");
        hashMap.put(fonts.get(12),"hwxw.ttf");
        hashMap.put(fonts.get(13),"hwxk.ttf");
        hashMap.put(fonts.get(14),"yy.ttf");
        hashMap.put(fonts.get(15),"wqywmh.ttf");
        hashMap.put(fonts.get(16),"fzzy.ttf");
        hashMap.put(fonts.get(17),"fzhl.ttf");
        hashMap.put(fonts.get(18),"fzktjt.ttf");
        hashMap.put(fonts.get(19),"fzgl.ttf");
        hashMap.put(fonts.get(20),"fzqtjt.ttf");
        hashMap.put(fonts.get(21),"fzxz.ttf");
        hashMap.put(fonts.get(22),"fzzhy.ttf");
        hashMap.put(fonts.get(23),"fzlxtjt.ttf");
        hashMap.put(fonts.get(24),"fzybxs.ttf");
        hashMap.put(fonts.get(25),"fzcy.ttf");
        hashMap.put(fonts.get(26),"fzpty.ttf");
        hashMap.put(fonts.get(27),"fzjljt.ttf");
        hashMap.put(fonts.get(28),"ml.ttf");

        Typeface tf = Typeface.createFromAsset(act.getAssets(),
                "fonts/"+hashMap.get(fonts.get(position)));

        for (int i = 0; i < root.getChildCount(); i++) {
            View v = root.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(tf);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(tf);
            } else if (v instanceof ViewGroup) {
                changeFonts((ViewGroup) v, act,position);
            }
        }

    }
}

