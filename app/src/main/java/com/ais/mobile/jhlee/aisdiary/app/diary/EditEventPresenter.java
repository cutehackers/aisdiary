package com.ais.mobile.jhlee.aisdiary.app.diary;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.DiaryDataSource;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Event;
import com.ais.mobile.jhlee.aisdiary.base.Database;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;

import java.util.Calendar;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class EditEventPresenter<VIEW extends EditEventView> extends MvpPresenter<VIEW> {

    public EditEventPresenter(VIEW view) {
        super(view);
    }

    public void update(Event event) {
        event.setTitle(getView().getTitleText());
        event.setStartTime(Database.ISO8601.format(getView().getStartTime()));
        event.setEndTime(Database.ISO8601.format(getView().getEndTime()));
        event.setLocation(getView().getLocationText());
        event.setDescription(getView().getDescriptionText());
        event.setUpdated(Database.ISO8601.format(Calendar.getInstance().getTime()));

        long handled = DiaryDataSource.instance().update(event);
        if (handled > 0) {
            getView().finishView(R.string.msg_event_successfully_edited);
        }
    }

    public void delete(Event event) {
        int handled = DiaryDataSource.instance().delete(event);
        if (handled > 0) {
            getView().finishView(R.string.msg_event_successfully_deleted);
        }
    }
}
