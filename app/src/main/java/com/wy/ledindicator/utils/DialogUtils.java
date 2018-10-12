package com.wy.ledindicator.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wy.ledindicator.R;


public class DialogUtils extends Dialog {

    public DialogUtils(Context context) {
        super(context, R.style.my_dialog_style);
    }

    public static DialogUtils getDialog(Context content, @LayoutRes int resource){
        final DialogUtils dialog = new DialogUtils(content);
        View view = LayoutInflater.from(content).inflate(resource,new LinearLayout(content));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        return dialog;
    }

    public static DialogUtils getDialogNotTouchOutside(Context content, @LayoutRes int resource){
        final DialogUtils dialog = new DialogUtils(content);
        View view = LayoutInflater.from(content).inflate(resource,new LinearLayout(content));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        return dialog;
    }

    /**
     * @param content       上下文
     * @param view          布局视图
     * @param isCancel      是否返回键可取消
     * @return
     */
    public static DialogUtils getDialog(Context content, View view,boolean isCancel){
        final DialogUtils dialog = new DialogUtils(content);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.setCancelable(isCancel);
        return dialog;
    }
}
