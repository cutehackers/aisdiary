package com.ais.mobile.jhlee.aisdiary.app.diary;

import com.ais.mobile.jhlee.aisdiary.mvp.MvpView;

import java.util.Date;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public interface NewEventView extends MvpView {

    String getTitleText();

    Date getStartTime();

    Date getEndTime();

    String getLocationText();

    String getDescriptionText();

    void finishView();
}
