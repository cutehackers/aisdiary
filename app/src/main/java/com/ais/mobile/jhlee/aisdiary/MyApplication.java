package com.ais.mobile.jhlee.aisdiary;

import android.app.Application;

import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;
import com.facebook.stetho.Stetho;

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

        /**
         * to check sqlite database from chrome browser
         */
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
