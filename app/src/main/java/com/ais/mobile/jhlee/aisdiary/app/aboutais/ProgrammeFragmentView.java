package com.ais.mobile.jhlee.aisdiary.app.aboutais;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Programme;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created: 25/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class ProgrammeFragmentView extends MvpFragmentView<ProgrammeView, ProgrammePresenter<ProgrammeView>> implements
        ProgrammeView {

    private ListView programmeView;


    public ProgrammeFragmentView() {

    }

    public static ProgrammeFragmentView create() {
        return new ProgrammeFragmentView();
    }


    //----------------------------------------------------------------------------------------------
    // overrides


    @Override
    protected int getLayoutResourceId() { return R.layout.fragment_programme; }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews(view);
    }


    //----------------------------------------------------------------------------------------------
    // implements: MvpFragmentView


    @Override
    protected ProgrammePresenter<ProgrammeView> onCreatePresenter() {
        return new ProgrammePresenter<>(this);
    }


    //----------------------------------------------------------------------------------------------
    // implements: ProgrammeView

    @Override
    public void updateProgrammeView(List<String> departments) {
        Log.d("DARBY", "updateProgrammeView: " + departments.size());
        programmeView.setAdapter(new ProgrammeListAdapter(getContext(), departments, (view, data) -> {
            // on item click -> show department data.
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, CourseFragmentView.create(data))
                    .addToBackStack(null)
                    .commit();
        }));
    }


    //----------------------------------------------------------------------------------------------
    // methods

    private void setUpViews(View container) {
        programmeView = container.findViewById(R.id.programmeView);
        presenter.load();
    }


    private class ProgrammeListAdapter extends ArrayAdapter<String> {

        private OnDepartmentItemClickListener onDepartmentItemClickListener;

        public ProgrammeListAdapter(@NonNull Context context,
                                    @NonNull List<String> departments,
                                    @NonNull OnDepartmentItemClickListener onDepartmentItemClickListener) {

            super(context, 0, departments);
            this.onDepartmentItemClickListener = onDepartmentItemClickListener;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View view = null;
            ProgrammeTag tag = null;

            if (convertView == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.department_item_layout, parent, false);
                tag = new ProgrammeTag(view);
                view.setTag(tag);
            } else {
                view = convertView;
                tag = (ProgrammeTag) view.getTag();
            }

            Log.d("DARBY", "getView: ");

            String item = getItem(position);

            tag.departmentView.setText(item);
            view.setOnClickListener(itemView -> onDepartmentItemClickListener.onDepartmentItemClick(itemView, item));

            return view;
        }
    }

    private class ProgrammeTag {
        private View itemView;
        private TextView departmentView;

        public ProgrammeTag(View view) {
            itemView = view;
            departmentView = view.findViewById(R.id.departmentView);
        }
    }

    private interface OnDepartmentItemClickListener {
        void onDepartmentItemClick(View view, String department);
    }
}
