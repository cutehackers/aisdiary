package com.ais.mobile.jhlee.aisdiary.app.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;
import com.ais.mobile.jhlee.aisdiary.base.BaseActivity;
import com.ais.mobile.jhlee.aisdiary.base.Navigator;
import com.ais.mobile.jhlee.aisdiary.ui.adapter.DiaryPagerAdapter;

/**
 * Created: 24/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class DiaryActivityView extends BaseActivity {

    private ViewPager tabContents;
    private DiaryPagerAdapter tabAdapter;


    //----------------------------------------------------------------------------------------------
    // overrides

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_diary);

        setUpViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.diary_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_event: {
                tabContents.setCurrentItem(DiaryPagerAdapter.DIARY_CONTENT_EVENT);
                return true;
            }
            case R.id.action_task: {
                tabContents.setCurrentItem(DiaryPagerAdapter.DIARY_CONTENT_TASK);
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

        findViewById(R.id.closeView).setOnClickListener(view -> finish());

        tabContents = findViewById(R.id.tabContentsView);
        tabAdapter = new DiaryPagerAdapter(getSupportFragmentManager());
        tabContents.setAdapter(tabAdapter);

//        TabLayout tabs = findViewById(R.id.tabsView);
//        tabs.setupWithViewPager(tabContents);

        findViewById(R.id.fab).setOnClickListener(this::onFabClick);
    }

    private void onFabClick(View view) {
        int current = tabContents.getCurrentItem();

        switch (current) {
            case DiaryPagerAdapter.DIARY_CONTENT_EVENT: {
                Fragment fragment = tabAdapter.getFragment(DiaryPagerAdapter.DIARY_CONTENT_EVENT);
                AndroidContext.instance().navigator()
                        .requestToNewEventActivityView(fragment, Navigator.RC_HANDLE_NEW_EVENT);
            } break;

            case DiaryPagerAdapter.DIARY_CONTENT_TASK: {
                Fragment fragment = tabAdapter.getFragment(DiaryPagerAdapter.DIARY_CONTENT_TASK);
                AndroidContext.instance().navigator()
                        .requestToNewTaskActivityView(fragment, Navigator.RC_HANDLE_NEW_TASK);
            } break;
        }
    }
}
