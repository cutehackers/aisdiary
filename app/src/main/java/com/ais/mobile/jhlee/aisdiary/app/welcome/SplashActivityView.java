package com.ais.mobile.jhlee.aisdiary.app.welcome;

import android.os.Bundle;
import android.os.Handler;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;
import com.ais.mobile.jhlee.aisdiary.base.BaseActivity;

/**
 * Created: 20/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class SplashActivityView extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(() -> {
            AndroidContext.instance().navigator().navigateToHomeActivityView(this);
            finish();
        }, 1000);
    }
}
