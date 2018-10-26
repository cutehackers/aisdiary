package com.ais.mobile.jhlee.aisdiary.app.aboutais;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Programme;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpFragmentView;
import com.ais.mobile.jhlee.aisdiary.ui.adapter.CourseAdapter;

import java.util.List;

/**
 * Create: 26/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class CourseFragmentView extends MvpFragmentView<CourseView, CoursePresenter<CourseView>> implements CourseView {

    public static final String ARGS_DEPARTMENT = "args_department";

    private String department;

    private RecyclerView courseView;
    private CourseAdapter adapter;

    public CourseFragmentView() {

    }

    public static CourseFragmentView create(String department) {
        CourseFragmentView fragment = new CourseFragmentView();

        Bundle args = new Bundle();
        args.putString(ARGS_DEPARTMENT, department);
        fragment.setArguments(args);
        return fragment;
    }


    //----------------------------------------------------------------------------------------------
    // overrides


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Bundle args = getArguments();
            if (args != null) {
                department = args.getString(ARGS_DEPARTMENT);
            }
        } else {
            department = savedInstanceState.getString(ARGS_DEPARTMENT);
        }
    }

    @Override
    protected int getLayoutResourceId() { return R.layout.fragment_course; }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView(view);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (!TextUtils.isEmpty(department)) {
            outState.putString(ARGS_DEPARTMENT, ARGS_DEPARTMENT);
        }
    }


    //----------------------------------------------------------------------------------------------
    // implements: MvpFragmentView

    @Override
    protected CoursePresenter<CourseView> onCreatePresenter() {
        return new CoursePresenter<>(this);
    }


    //----------------------------------------------------------------------------------------------
    // implements: CourseView

    @Override
    public void updateView(List<Programme> programmes) {
        adapter.update(programmes);
    }


    //----------------------------------------------------------------------------------------------
    // methods

    private void setUpView(View container) {
        adapter = new CourseAdapter();
        courseView = container.findViewById(R.id.courseView);
        courseView.setHasFixedSize(true);
        courseView.setLayoutManager(new LinearLayoutManager(getContext()));
        courseView.setAdapter(adapter);

        presenter.load();
    }
}
