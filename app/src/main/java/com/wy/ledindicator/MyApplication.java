package com.wy.ledindicator;

import android.app.Application;

import org.xutils.x;

public class MyApplication extends Application {

    static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        x.Ext.init(this);
        x.Ext.setDebug(Constants.isDebug);
    }

    public static Application getInstance(){
        return mApplication;
    }
}
