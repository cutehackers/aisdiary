package com.ais.mobile.jhlee.aisdiary.app.diary;

import android.os.AsyncTask;
import android.util.Log;

import com.ais.mobile.jhlee.aisdiary.app.diary.domain.DiaryDataSource;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.EventDao;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Event;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;
import com.ais.mobile.jhlee.aisdiary.ui.adapter.EventAdapter;
import com.ais.mobile.jhlee.aisdiary.ui.view.OnEventItemClickListener;

import java.util.List;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class EventPresenter<VIEW extends EventView> extends MvpPresenter<VIEW> {

    private EventAdapter adapter;
    private LoadEventAsyncTask loader;


    public EventPresenter(VIEW view) {
        super(view);
    }

    public EventAdapter getOrCreateAdapter(OnEventItemClickListener onItemClickListener) {
        if (adapter == null) {
            adapter = new EventAdapter(onItemClickListener);
        }
        return adapter;
    }

    /**
     * load events from sqlite database
     */
    public void load() {
        Log.d("DARBY", "start loading ");

        if (loader != null) {
            loader.cancel(true);
        }
        loader = new LoadEventAsyncTask();
        loader.execute();
    }

    public class LoadEventAsyncTask extends AsyncTask<Void, Void, List<Event>> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected List<Event> doInBackground(Void... voids) {
            List<Event> events = DiaryDataSource.instance().getEventList();
            return events;
        }

        @Override
        protected void onPostExecute(List<Event> events) {
            loader = null;

            if (events.size() == 0) {
                getView().showEmptyView();
            } else {
                getView().showEventView();
            }

            adapter.update(events);

            Log.d("DARBY", "finish loading items " + events.size() + " events size: " + DiaryDataSource.instance().getEventCount());
        }
    }
}
