package com.sgd.zitai.ui;

import android.app.Application;
import android.content.Context;

/**
 * Created by Allen Liu on 2016/8/4.
 */
public class ZiTaiApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
}
