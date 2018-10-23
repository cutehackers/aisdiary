package com.ais.mobile.jhlee.aisdiary.app.diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.base.Navigator;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpFragmentView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class EventFragmentView extends MvpFragmentView<EventView, EventPresenter<EventView>> implements
        EventView {

    private Handler handler = new Handler();

    private View emptyView;
    private RecyclerView eventView;
    private LinearLayoutManager eventLayoutManager;


    public EventFragmentView() {

    }

    public static EventFragmentView create() {
        return new EventFragmentView();
    }


    //----------------------------------------------------------------------------------------------
    // overrides

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_event;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Navigator.RC_HANDLE_NEW_EVENT && resultCode == Activity.RESULT_OK) {
            // reload
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // load events
        presenter.load();
    }


    //----------------------------------------------------------------------------------------------
    // implements: MvpFragmentView

    @Override
    protected EventPresenter<EventView> onCreatePresenter() {
        return new EventPresenter<>(this);
    }


    //----------------------------------------------------------------------------------------------
    // implements: EventView

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
        eventView.setVisibility(View.GONE);
    }

    @Override
    public void showEventView() {
        emptyView.setVisibility(View.GONE);
        eventView.setVisibility(View.VISIBLE);
    }


    //----------------------------------------------------------------------------------------------
    // methods

    private void setUpViews(View container) {
        emptyView = container.findViewById(R.id.emptyView);
        eventView = container.findViewById(R.id.eventView);

        eventLayoutManager = new LinearLayoutManager(getContext());
        eventView.setLayoutManager(eventLayoutManager);
        eventView.setHasFixedSize(true);
        eventView.setAdapter(presenter.getOrCreateAdapter());
    }

}
