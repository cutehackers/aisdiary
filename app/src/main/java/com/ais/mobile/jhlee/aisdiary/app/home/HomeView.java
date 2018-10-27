package com.ais.mobile.jhlee.aisdiary.app.home;

import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Event;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpView;

/**
 * Create: 27/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public interface HomeView extends MvpView {

    void showEmptyView();

    void showScheduleView();

    void showNewScheduleSuccessView(Event event);
}
