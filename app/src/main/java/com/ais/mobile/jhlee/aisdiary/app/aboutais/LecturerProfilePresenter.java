package com.ais.mobile.jhlee.aisdiary.app.aboutais;

import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;

/**
 * Create: 25/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class LecturerProfilePresenter<VIEW extends LecturerProfileView> extends MvpPresenter<VIEW> {

    public LecturerProfilePresenter(VIEW view) {
        super(view);
    }
}
