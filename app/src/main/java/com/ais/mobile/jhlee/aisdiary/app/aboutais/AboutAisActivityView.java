package com.ais.mobile.jhlee.aisdiary.app.aboutais;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.base.BaseActivity;

/**
 * Created: 25/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class AboutAisActivityView extends BaseActivity {

//    private static final int FRAG_TYPE_AIS = 0;
    private static final int FRAG_TYPE_PROGRAMME = 0;
    private static final int FRAG_TYPE_LECTURER = 1;
    private static final int FRAG_TYPE_COUNT = 2;

    private Fragment[] fragments;

    //----------------------------------------------------------------------------------------------
    // overrides

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about_ais);

        setUpViews();
    }


    //----------------------------------------------------------------------------------------------
    // methods

    private void setUpViews() {
        TabLayout tabsView = findViewById(R.id.tabsView);
        tabsView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                showFragmentByType(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FragmentManager fmtmgr = getSupportFragmentManager();

        fragments = new Fragment[] {
//                fmtmgr.findFragmentById(R.id.ais),
                fmtmgr.findFragmentById(R.id.programmes),
                fmtmgr.findFragmentById(R.id.lecturers)
        };

        showFragmentByType(FRAG_TYPE_PROGRAMME);
    }

    private void showFragmentByType(int fragType) {
        if (fragments == null) {
            throw new IllegalStateException("Fragment is not initialize yet!");
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);

        for (int i=0; i<FRAG_TYPE_COUNT; i++) {
            if (i == fragType) {
                transaction.show(fragments[i]);
            } else {
                transaction.hide(fragments[i]);
            }
        }

        transaction.commit();
    }
}
