package com.ais.mobile.jhlee.aisdiary.app.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;
import com.ais.mobile.jhlee.aisdiary.base.BaseActivity;
import com.ais.mobile.jhlee.aisdiary.base.Navigator;
import com.ais.mobile.jhlee.aisdiary.ui.adapter.DiaryPagerAdapter;

public class DiaryActivityView extends BaseActivity {

    private ViewPager tabContents;


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

    //----------------------------------------------------------------------------------------------
    // methods

    private void setUpViews() {
        tabContents = findViewById(R.id.tabContentsView);
        tabContents.setAdapter(new DiaryPagerAdapter(getSupportFragmentManager()));

        TabLayout tabs = findViewById(R.id.tabsView);
        tabs.setupWithViewPager(tabContents);

        findViewById(R.id.fab).setOnClickListener(this::onFabClick);
    }

    private void onFabClick(View view) {
        int current = tabContents.getCurrentItem();

        switch (current) {
            case DiaryPagerAdapter.DIARY_CONTENT_EVENT: {
                AndroidContext.instance().navigator()
                        .requestToNewEventActivityView(this, Navigator.RC_HANDLE_NEW_EVENT);
            } break;

            case DiaryPagerAdapter.DIARY_CONTENT_TASK: {

            } break;
        }
    }
}
