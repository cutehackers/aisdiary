package com.ais.mobile.jhlee.aisdiary.app.diary;

import android.text.TextUtils;

import com.ais.mobile.jhlee.aisdiary.app.diary.domain.DiaryDataSource;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Task;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;

/**
 * Create: 24/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class NewTaskPresenter<VIEW extends NewTaskView> extends MvpPresenter<VIEW> {

    public NewTaskPresenter(VIEW view) {
        super(view);
    }

    public void newTask(String content) {
        if (!TextUtils.isEmpty(content)) {
            Task task = new Task();
            task.setContent(content);

            long id = DiaryDataSource.instance().add(task);
            if (id > -1) {
                getView().finishViewWithOkResult();
                return;
            }
        }

        getView().finishView();
    }
}
