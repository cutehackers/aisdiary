package com.ais.mobile.jhlee.aisdiary.app.diary;

import android.os.AsyncTask;

import com.ais.mobile.jhlee.aisdiary.app.diary.domain.DiaryDataSource;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Task;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;
import com.ais.mobile.jhlee.aisdiary.ui.adapter.TaskAdapter;
import com.ais.mobile.jhlee.aisdiary.ui.view.OnTaskItemCheckedChangedListener;
import com.ais.mobile.jhlee.aisdiary.ui.view.OnTaskItemClickListener;

import java.util.List;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class TaskPresenter<VIEW extends TaskView> extends MvpPresenter<VIEW> {

    private TaskAdapter adapter;
    private TaskAsyncTask loader;


    public TaskPresenter(VIEW view) {
        super(view);
    }

    public TaskAdapter getOrCreateAdapter(
            OnTaskItemClickListener onTaskItemClickListener,
            OnTaskItemCheckedChangedListener onTaskItemCheckedChangedListener) {

        if (adapter == null) {
            adapter = new TaskAdapter(onTaskItemClickListener, onTaskItemCheckedChangedListener);
        }
        return adapter;
    }

    /**
     * load task form sqlite database asynchronously
     */
    public void load() {
        if (loader != null) {
            loader.cancel(true);
        }
        loader = new TaskAsyncTask();
        loader.execute();
    }

    public void update(Task task) {
        if (DiaryDataSource.instance().update(task) > 0) {
            // handled
        }
    }


    private class TaskAsyncTask extends AsyncTask<Void, Void, List<Task>> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected List<Task> doInBackground(Void... voids) {
            List<Task> tasks = DiaryDataSource.instance().getTaskList();
            return tasks;
        }

        @Override
        protected void onPostExecute(List<Task> tasks) {
            loader = null;

            if (tasks.size() == 0) {
                getView().showEmptyView();
            } else {
                getView().showTaskView();
            }

            adapter.update(tasks);
        }
    }
}
