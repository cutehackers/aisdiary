package com.ais.mobile.jhlee.aisdiary.app.aboutais;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Log;

import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.AboutAisDataSource;
import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.LecturerLoader;
import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Lecturer;
import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;

import java.util.List;

/**
 * Create: 25/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class LecturerPresenter<VIEW extends LecturerView> extends MvpPresenter<VIEW> {

    private LoadLecturerAsyncTask loader;

    public LecturerPresenter(VIEW view) {
        super(view);
    }

    LecturerLoader onCreateLoader(String searchQuery) {
        return new LecturerLoader(AndroidContext.instance().getApplication(), searchQuery);
    }

    void onLoadFinished(@NonNull Loader<List<Lecturer>> loader, List<Lecturer> lecturers) {
        if (getView() != null) {
            if (lecturers.size() == 0) {
                getView().showEmptyView();
            } else {
                getView().showLecturerView();
            }
            getView().update(lecturers);
        }
    }

    void onLoaderReset(@NonNull Loader<List<Lecturer>> loader) {
        if (getView() != null) {
            getView().update(null);
        }
    }

//    public void load() { load(""); }
//
//    public void load(String searchQuery) {
//        if (loader != null) {
//            loader.cancel(true);
//        }
//        loader = new LoadLecturerAsyncTask();
//        loader.execute(searchQuery);
//    }


    private class LoadLecturerAsyncTask extends AsyncTask<String, Void, List<Lecturer>> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected List<Lecturer> doInBackground(String... params) {
            String searchQuery = params[0];

            Log.d("DARBY", "loading lecturers by: " + searchQuery);

            List<Lecturer> lecturers = AboutAisDataSource.instance().getLecturerListByName(searchQuery);
//            List<Lecturer> lecturers;
//            if (TextUtils.isEmpty(searchQuery)) {
//                lecturers = AboutAisDataSource.instance().getLecturerList();
//            } else {
//                lecturers = AboutAisDataSource.instance().getLecturerListByName(searchQuery);
//            }
            return lecturers;
        }

        @Override
        protected void onPostExecute(List<Lecturer> lecturers) {
            loader = null;

            if (lecturers.size() == 0) {
                getView().showEmptyView();
            } else {
                getView().showLecturerView();
            }

            getView().update(lecturers);

            Log.d("DARBY", "result lecturers count: " + lecturers.size());
        }
    }

}
