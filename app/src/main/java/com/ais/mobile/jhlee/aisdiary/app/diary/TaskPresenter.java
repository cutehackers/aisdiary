package com.ais.mobile.jhlee.aisdiary.app.diary;

import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class TaskPresenter<VIEW extends TaskView> extends MvpPresenter<VIEW> {

    public TaskPresenter(VIEW view) {
        super(view);
    }
}
