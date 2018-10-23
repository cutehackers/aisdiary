package com.ais.mobile.jhlee.aisdiary.app.diary;

import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class NewEventPresenter<VIEW extends NewEventView> extends MvpPresenter<VIEW> {

    public NewEventPresenter(VIEW view) {
        super(view);
    }
}
