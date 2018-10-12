package com.wy.ledindicator.utils;

import com.wy.ledindicator.MyApplication;

/**
 * Created by 44905 on 2018/3/22.
 */

public class DensityUtil {

    private static float scale;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        if (scale == 0) {
            scale = MyApplication.getInstance().getApplicationContext().getResources().getDisplayMetrics().density;
        }
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        if (scale == 0) {
            scale = MyApplication.getInstance().getApplicationContext().getResources().getDisplayMetrics().density;
        }
        return (int) (pxValue / scale + 0.5f);
    }

    /*
     *
     *将sp转换为px
     */

    public static int sp2px(float spValue) {
        if (scale == 0) {
            scale = MyApplication.getInstance().getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        }
        return (int) (spValue * scale + 0.5f);
    }

}