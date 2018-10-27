package com.ais.mobile.jhlee.aisdiary.app.home;

import android.os.AsyncTask;
import android.util.Log;

import com.ais.mobile.jhlee.aisdiary.app.diary.domain.DiaryDataSource;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.EventDao;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Event;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;
import com.ais.mobile.jhlee.aisdiary.ui.adapter.ScheduleAdapter;
import com.ais.mobile.jhlee.aisdiary.ui.view.OnScheduleItemClickListener;

import java.util.List;

/**
 * Create: 27/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class HomePresenter<VIEW extends HomeView> extends MvpPresenter<VIEW> {

    private ScheduleAdapter adapter;
    private ScheduleLoaderAsyncTask loader;


    public HomePresenter(VIEW view) {
        super(view);
    }

    public ScheduleAdapter getOrCreateAdapter(OnScheduleItemClickListener onItemClickListener) {
        if (adapter == null) {
            adapter = new ScheduleAdapter(onItemClickListener);
        }
        return adapter;
    }


    /**
     * load academic or workshop events from sqlite database
     */
    public void load() {
        if (loader != null) {
            loader.cancel(true);
        }
        loader = new ScheduleLoaderAsyncTask();
        loader.execute();
    }

    public void sendScheduleToMyEvent(Event schedule) {
        Event event = new Event();
        event.setType(EventDao.TYPE_PERSONAL);
        event.setTitle(schedule.getTitle());
        event.setStartTime(schedule.getStartTime());
        event.setEndTime(schedule.getEndTime());
        event.setLocation(schedule.getLocation());
        event.setDescription(schedule.getDescription());

        long id = DiaryDataSource.instance().add(event);
        if (id > -1) {
            getView().showNewScheduleSuccessView(event);
        }
    }


    public class ScheduleLoaderAsyncTask extends AsyncTask<Void, Void, List<Event>> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected List<Event> doInBackground(Void... voids) {
            List<Event> schedules = DiaryDataSource.instance().getScheduleList();
            return schedules;
        }

        @Override
        protected void onPostExecute(List<Event> events) {
            loader = null;

            Log.d("DARBY", "schedules: " + events.size());

            if (events.size() == 0) {
                getView().showEmptyView();
            } else {
                getView().showScheduleView();
            }

            adapter.update(events);
        }
    }
}
