package com.example.x_etc_47_53;

import android.app.Application;

import org.litepal.LitePal;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/15 17:50
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
