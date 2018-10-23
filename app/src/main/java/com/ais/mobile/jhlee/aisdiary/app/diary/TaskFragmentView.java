package com.ais.mobile.jhlee.aisdiary.app.diary;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpFragmentView;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class TaskFragmentView extends MvpFragmentView<TaskView, TaskPresenter<TaskView>> implements
        TaskView {

    public TaskFragmentView() {

    }

    public static TaskFragmentView create() {
        return new TaskFragmentView();
    }


    //----------------------------------------------------------------------------------------------
    // overrides

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_task;
    }


    //----------------------------------------------------------------------------------------------
    // implements: MvpFragmentView

    @Override
    protected TaskPresenter<TaskView> onCreatePresenter() {
        return null;
    }


    //----------------------------------------------------------------------------------------------
    // implements: TaskView


    //----------------------------------------------------------------------------------------------
    // methods
}
