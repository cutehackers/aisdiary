package com.ais.mobile.jhlee.aisdiary.app.welcome;

import android.os.Bundle;

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

        AndroidContext.instance().navigator().navigateToHomeActivityView(this);
        finish();
    }
}
