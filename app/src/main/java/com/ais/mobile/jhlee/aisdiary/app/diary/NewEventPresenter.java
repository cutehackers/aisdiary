package com.ais.mobile.jhlee.aisdiary.app.diary;

import android.util.Log;

import com.ais.mobile.jhlee.aisdiary.app.diary.domain.DiaryDataSource;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.EventDao;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Event;
import com.ais.mobile.jhlee.aisdiary.base.Database;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;

import java.util.Calendar;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class NewEventPresenter<VIEW extends NewEventView> extends MvpPresenter<VIEW> {

    public NewEventPresenter(VIEW view) {
        super(view);
    }

    public void newEvent() {
        Event event = new Event();
        event.setType(EventDao.TYPE_PERSONAL);
        event.setTitle(getView().getTitleText());
        event.setStartTime(Database.ISO8601.format(getView().getStartTime()));
        event.setEndTime(Database.ISO8601.format(getView().getEndTime()));
        event.setLocation(getView().getLocationText());
        event.setDescription(getView().getDescriptionText());
        event.setCreated(Database.ISO8601.format(Calendar.getInstance().getTime()));

        long id = DiaryDataSource.instance().add(event);
        if (id > -1) {
            getView().finishView();
        }
    }
}
