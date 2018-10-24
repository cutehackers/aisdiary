package com.ais.mobile.jhlee.aisdiary.app.diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.DiaryDataSource;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Task;
import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;
import com.ais.mobile.jhlee.aisdiary.base.Navigator;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpFragmentView;
import com.ais.mobile.jhlee.aisdiary.ui.view.OnTaskItemCheckedChangedListener;
import com.ais.mobile.jhlee.aisdiary.ui.view.OnTaskItemClickListener;
import com.ais.mobile.jhlee.aisdiary.ui.view.TaskViewHolder;

import java.util.LinkedList;
import java.util.List;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class TaskFragmentView extends MvpFragmentView<TaskView, TaskPresenter<TaskView>> implements
        TaskView {

    private View emptyView;
    private RecyclerView taskView;

    private List<Runnable> cache = new LinkedList<>();

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews(view);
        presenter.load();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Navigator.RC_HANDLE_NEW_TASK && resultCode == Activity.RESULT_OK) {
            // reload
            cache.add(() -> presenter.load());
        }

        if (requestCode == Navigator.RC_HANDLE_EDIT_TASK) {
            // reload
            cache.add(() -> presenter.load());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        for (Runnable runner : cache) {
            runner.run();
        }
        cache.clear();
    }

    //----------------------------------------------------------------------------------------------
    // implements: MvpFragmentView

    @Override
    protected TaskPresenter<TaskView> onCreatePresenter() {
        return new TaskPresenter<>(this);
    }


    //----------------------------------------------------------------------------------------------
    // implements: TaskView

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
        taskView.setVisibility(View.GONE);
    }

    @Override
    public void showTaskView() {
        emptyView.setVisibility(View.GONE);
        taskView.setVisibility(View.VISIBLE);
    }


    //----------------------------------------------------------------------------------------------
    // methods

    private void setUpViews(View container) {
        emptyView = container.findViewById(R.id.emptyView);
        taskView = container.findViewById(R.id.taskView);

        LinearLayoutManager linearlayoutManager = new LinearLayoutManager(getContext());
        taskView.setLayoutManager(linearlayoutManager);
        taskView.setHasFixedSize(true);
        taskView.setAdapter(presenter.getOrCreateAdapter(
                new OnTaskItemClickListener() {
                    @Override
                    public void onTaskItemClick(TaskViewHolder holder, Task task) {
                        AndroidContext.instance().navigator()
                                .requestToEditTaskActivityView(TaskFragmentView.this, task, Navigator.RC_HANDLE_EDIT_TASK);
                    }
                },
                new OnTaskItemCheckedChangedListener() {
                    @Override
                    public void onTaskItemCheckChanged(TaskViewHolder holder, Task task, boolean isChecked) {
                        task.setCompleted(isChecked);
                        presenter.update(task);
                    }
                }
        ));
    }
}
