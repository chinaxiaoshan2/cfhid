package com.example.materialtest.dataprocessing;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePalApplication;



/**
 * Created by xzs on 2017/6/1.
 */

public class MyApplication extends Application {

    private static Context context;

    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        context=getApplicationContext();
        LitePalApplication.initialize(context);
        super.onCreate();
    }
}
