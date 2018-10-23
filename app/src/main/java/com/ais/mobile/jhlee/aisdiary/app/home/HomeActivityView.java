package com.ais.mobile.jhlee.aisdiary.app.home;

import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;
import com.ais.mobile.jhlee.aisdiary.base.BaseActivity;

/**
 * Created: 20/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class HomeActivityView extends BaseActivity {

    //----------------------------------------------------------------------------------------------
    // overrides

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        setUpViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_about_ais: {
                return true;
            }
            case R.id.action_schedule: {
                return true;
            }
            case R.id.action_diary: {
                AndroidContext.instance().navigator().navigateToDiaryActivityView(this);
                return true;
            }
            case R.id.action_contact_us: {
                AndroidContext.instance().navigator().navigateToContactUsActivityView(this);
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }


    //----------------------------------------------------------------------------------------------
    // methods

    private void setUpViews() {
        BottomAppBar appbar = findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        findViewById(R.id.fab).setOnClickListener(this::onFabClick);
    }

    private void onFabClick(View view) {

    }
}
