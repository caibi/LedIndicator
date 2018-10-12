package com.wy.ledindicator.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.wy.ledindicator.MyApplication;


/**
 * Created by zhangweijian on 2016/10/12.
 */

public class ToastUtils {

    private static Toast toast;

    public static void showToast(String content, int duration) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance(),content,duration);
        } else {
            toast.setText(content);
        }
        if (!TextUtils.isEmpty(content)){
            toast.show();
        }
    }

    public static void showToast(String content) {
        showToast(content,Toast.LENGTH_SHORT);
    }
}
