package com.juzi.flowview;

import android.app.Application;

import com.juzi.flowview.global.Global;

/**
 * Created by liangzhongtai on 2017/2/5.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Global.init(this);
    }
}
