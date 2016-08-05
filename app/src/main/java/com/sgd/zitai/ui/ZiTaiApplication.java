package com.sgd.zitai.ui;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.sgd.zitai.R;
import com.sgd.zitai.ui.activity.MainActivity;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * Created by Allen Liu on 2016/8/4.
 */
public class ZiTaiApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        CustomActivityOnCrash.install(this);
        CustomActivityOnCrash.setLaunchErrorActivityWhenInBackground(false);
        CustomActivityOnCrash.setShowErrorDetails(true);
        //CustomActivityOnCrash.setDefaultErrorActivityDrawable(R.mipmap.ceshi);
        CustomActivityOnCrash.setEnableAppRestart(true);
        CustomActivityOnCrash.setRestartActivityClass(MainActivity.class);


    }
}
