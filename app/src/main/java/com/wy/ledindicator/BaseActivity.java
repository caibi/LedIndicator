package com.wy.ledindicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.wy.ledindicator.utils.FontManager;
import com.wy.ledindicator.utils.SharedPreferencesUtil;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        changeFront();
        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        changeFront();
    }

    /**
     * 切换字体
     */
    private void changeFront() {
        int position = (int)SharedPreferencesUtil.get(this,Constants.ShareprefrencesParams.TEXT_font,0);
        FontManager.changeFonts((ViewGroup)findViewById(android.R.id.content),this,position);
    }


    public abstract int setContentView();       //获取子类的加载布局

    public abstract void init();                //子类初始化数据

}
