package com.ais.mobile.jhlee.aisdiary.app.diary;

import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;
import com.ais.mobile.jhlee.aisdiary.ui.adapter.EventAdapter;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class EventPresenter<VIEW extends EventView> extends MvpPresenter<VIEW> {

    private EventAdapter adapter;

    public EventPresenter(VIEW view) {
        super(view);
    }

    public EventAdapter getOrCreateAdapter() {
        if (adapter == null) {
            adapter = new EventAdapter();
        }
        return adapter;
    }

    /**
     * load events from sqlite database
     */
    public void load() {

    }
}
