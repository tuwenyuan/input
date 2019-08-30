package com.twy.input.base;

import android.app.Application;
import android.os.Handler;
import android.support.multidex.MultiDexApplication;


/**
 * Created by twy on 2018/2/2.
 */

public class BaseApplication extends MultiDexApplication {

    public static Application instance;
    public static Handler mhandler = new Handler();
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


}
