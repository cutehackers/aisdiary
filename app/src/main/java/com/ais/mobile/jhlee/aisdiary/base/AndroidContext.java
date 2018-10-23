package com.ais.mobile.jhlee.aisdiary.base;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.ais.mobile.jhlee.aisdiary.MyApplication;

/**
 * Created: 20/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class AndroidContext {

    private static AndroidContext INSTANCE = null;

    private final MyApplication application;

    private Navigator navigator = new Navigator();


    private AndroidContext(MyApplication application) {
        this.application = application;
    }

    public static void initialize(@NonNull MyApplication application) {
        INSTANCE = new AndroidContext(application);
    }

    public static AndroidContext instance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("Android context was not initialized.");
        }
        return INSTANCE;
    }

    public MyApplication getApplication() {
        return application;
    }

    public String getString(int resId) {
        return application.getString(resId);
    }

    public Navigator navigator() {
        return this.navigator;
    }
}
