package com.ais.mobile.jhlee.aisdiary.app.diary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpFragmentView;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class EventFragmentView extends MvpFragmentView<EventView, EventPresenter<EventView>> implements
        EventView {

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
        onCreateEventComponents();
    }

    //----------------------------------------------------------------------------------------------
    // implements: MvpFragmentView

    @Override
    protected EventPresenter<EventView> onCreatePresenter() {
        return new EventPresenter<>(this);
    }


    //----------------------------------------------------------------------------------------------
    // implements: EventView


    //----------------------------------------------------------------------------------------------
    // methods

    private void setUpViews(View container) {
        eventView = container.findViewById(R.id.eventView);

        eventLayoutManager = new LinearLayoutManager(getContext());
        eventView.setLayoutManager(eventLayoutManager);
        eventView.setHasFixedSize(true);
    }

    private void onCreateEventComponents() {
        eventView.setAdapter(presenter.getOrCreateAdapter());
        presenter.load();
    }
}
