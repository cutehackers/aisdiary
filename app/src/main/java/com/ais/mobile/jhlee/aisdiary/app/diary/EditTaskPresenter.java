package com.ais.mobile.jhlee.aisdiary.app.diary;

import android.text.TextUtils;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.DiaryDataSource;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Task;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;

/**
 * Create: 24/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class EditTaskPresenter<VIEW extends EditTaskView> extends MvpPresenter<VIEW> {

    public EditTaskPresenter(VIEW view) {
        super(view);
    }

    public void update(Task task) {
        if (!TextUtils.isEmpty(task.getContent())) {
            if (DiaryDataSource.instance().update(task) > 0) {
                // handled
                getView().setSuccess();
            }
        }
    }

    public void delete(Task task) {
        int handled = DiaryDataSource.instance().delete(task);
        if (handled > 0) {
            getView().setSuccess();
            getView().finishView(R.string.msg_task_successfully_deleted);
        }
    }
}
