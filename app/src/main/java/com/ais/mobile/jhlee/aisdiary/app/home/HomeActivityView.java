package com.ais.mobile.jhlee.aisdiary.app.home;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Event;
import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpActivityView;

/**
 * Created: 20/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class HomeActivityView extends MvpActivityView<HomeView, HomePresenter<HomeView>> implements
        HomeView,
        ScheduleDialogFragment.ScheduleDialogListener {

    private View container;
    private View emptyView;
    private View scheduleViewContainer;
    private RecyclerView scheduleView;


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
                AndroidContext.instance().navigator().navigateToAboutAisActivityView(this);
                return true;
            }
//            case R.id.action_schedule: {
//                return true;
//            }
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
    // implements: MvpActivityView

    @Override
    protected HomePresenter<HomeView> onCreatePresenter() {
        return new HomePresenter<>(this);
    }


    //----------------------------------------------------------------------------------------------
    // implements: HomeView

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
        scheduleViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void showScheduleView() {
        emptyView.setVisibility(View.GONE);
        scheduleViewContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNewScheduleSuccessView(Event event) {
        Snackbar.make(container, R.string.home_new_schedule_success, Snackbar.LENGTH_LONG)
                .setAction(R.string.home_new_schedule_confirm, v -> {
                    AndroidContext.instance().navigator().navigateToDiaryActivityView(this);
                }).show();
    }


    //----------------------------------------------------------------------------------------------
    // implements: ScheduleDialogFragment.ScheduleDialogListener

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, Event event) {
        presenter.sendScheduleToMyEvent(event);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog, Event event) {

    }


    //----------------------------------------------------------------------------------------------
    // methods

    private void setUpViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        container = findViewById(R.id.container);
        emptyView = findViewById(R.id.emptyView);
        scheduleView = findViewById(R.id.scheduleView);
        scheduleViewContainer = findViewById(R.id.scheduleViewContainer);

        scheduleView = findViewById(R.id.scheduleView);
        scheduleView.setHasFixedSize(true);
        scheduleView.setLayoutManager(new LinearLayoutManager(this));
        scheduleView.setAdapter(presenter.getOrCreateAdapter((holder, event) -> {
            // schedule item click event
//            FragmentManager fmtmgr = getSupportFragmentManager();
//            NewScheduleToMyEventDialogFragment.create(event).show(fmtmgr, "New event");

            showScheduleToEventAlertDialog(event);
        }));

        presenter.load();
    }

    private void showScheduleToEventAlertDialog(Event event) {

        ScheduleDialogFragment
                .create(event)
                .show(getSupportFragmentManager(), getString(R.string.home_schedule_title));

//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle(AndroidContext.instance().getString(R.string.home_schedule_title));
//        builder.setMessage(R.string.home_schedule_to_event_msg);
//        builder.setPositiveButton("OK", (dialog, which) -> {
//            // on success
//        });
//        builder.setNegativeButton("Cancel", (dialog, which) -> {
//            if (dialog != null) {
//                dialog.dismiss();
//            }
//        });
//
//        builder.create();
    }
}
