package com.ais.mobile.jhlee.aisdiary.app.aboutais;

import android.os.Handler;
import android.util.Log;

import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.AboutAisDataSource;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;

import java.util.List;

/**
 * Create: 26/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class ProgrammePresenter<VIEW extends ProgrammeView> extends MvpPresenter<VIEW> {

    private Thread thread;
    private Handler handler = new Handler();

    public ProgrammePresenter(VIEW view) {
        super(view);
    }

    public void load() {

        Log.d("DARBY", "ProgrammePresenter.load()");

        if (thread == null) {
            thread = new Thread(() -> {
                List<String> departments = AboutAisDataSource.instance().getDepartmentList();

                // run on ui thread which updated views.
                handler.post(() -> {
                    thread = null;
                    getView().updateProgrammeView(departments);
                });
            });
            thread.start();
        }
    }
}
