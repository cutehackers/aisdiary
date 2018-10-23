package com.ais.mobile.jhlee.aisdiary;

import android.app.Application;

import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;

/**
 * Created: 20/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidContext.initialize(this);
    }
}
