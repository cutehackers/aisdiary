package com.ais.mobile.jhlee.aisdiary.app.aboutais;

import android.os.AsyncTask;

import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.AboutAisDataSource;
import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Programme;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;

import java.util.List;

/**
 * Create: 26/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class CoursePresenter<VIEW extends CourseView> extends MvpPresenter<VIEW> {

    private CourseLoaderTask loader;

    public CoursePresenter(VIEW view) {
        super(view);
    }

    public void load() {
        if (loader != null) {
            loader.cancel(true);
        }

        loader = new CourseLoaderTask();
        loader.execute();
    }

    private class CourseLoaderTask extends AsyncTask<Void, Void, List<Programme>> {

        @Override
        protected List<Programme> doInBackground(Void... voids) {
            return AboutAisDataSource.instance().getProgrammeList();
        }

        @Override
        protected void onPostExecute(List<Programme> programmes) {
            getView().updateView(programmes);
        }
    }
}
