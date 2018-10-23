package com.ais.mobile.jhlee.aisdiary.app.diary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpActivityView;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class NewEventActivityView extends MvpActivityView<NewEventView, NewEventPresenter<NewEventView>> implements
        NewEventView {

    //----------------------------------------------------------------------------------------------
    // overrides

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_new_event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_event_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_done: {

                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    //----------------------------------------------------------------------------------------------
    // implements: MvpFragmentView

    @Override
    protected NewEventPresenter<NewEventView> onCreatePresenter() {
        return new NewEventPresenter<>(this);
    }


    //----------------------------------------------------------------------------------------------
    // implements: NewEventView


    //----------------------------------------------------------------------------------------------
    // methods
}