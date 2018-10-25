package com.ais.mobile.jhlee.aisdiary.app.aboutais;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Lecturer;
import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpFragmentView;

import java.util.Objects;

/**
 * Create: 25/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class LecturerProfileFragmentView extends
        MvpFragmentView<LecturerProfileView, LecturerProfilePresenter<LecturerProfileView>> implements
        LecturerProfileView {

    public static final String ARGS_LECTURER = "args_lecturer";

    private Lecturer lecturer;

    private View nameViewContainer;
    private TextView nameView;
    private View mailViewContainer;
    private TextView mailView;
    private View phoneViewContainer;
    private TextView phoneView;
    private View qualificationViewContainer;
    private TextView qualificationView;
    private View mainTeachingFieldViewContainer;
    private TextView mainTeachingFeidlView;


    public LecturerProfileFragmentView() {

    }

    public static LecturerProfileFragmentView create(Lecturer lecturer) {
        LecturerProfileFragmentView fragment = new LecturerProfileFragmentView();

        Bundle args = new Bundle();
        args.putParcelable(ARGS_LECTURER, lecturer);
        fragment.setArguments(args);
        return fragment;
    }


    //----------------------------------------------------------------------------------------------
    // overrides


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args == null) {
            Toast.makeText(Objects.requireNonNull(getContext()).getApplicationContext(),
                    R.string.msg_invalid_arguments, Toast.LENGTH_SHORT).show();
        }
        lecturer = args.getParcelable(ARGS_LECTURER);
    }

    @Override
    protected int getLayoutResourceId() { return R.layout.fragment_lecturer_profile; }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews(view);
    }


    //----------------------------------------------------------------------------------------------
    // implements: MvpFragmentView

    @Override
    protected LecturerProfilePresenter<LecturerProfileView> onCreatePresenter() {
        return new LecturerProfilePresenter<>(this);
    }


    //----------------------------------------------------------------------------------------------
    // implements: LecturerProfileView

    //----------------------------------------------------------------------------------------------
    // methods

    private void setUpViews(View container) {
        nameViewContainer = container.findViewById(R.id.nameViewContainer);
        nameView = container.findViewById(R.id.nameView);
        mailViewContainer = container.findViewById(R.id.mailViewContainer);
        mailView = container.findViewById(R.id.mailView);
        phoneViewContainer = container.findViewById(R.id.phoneViewContainer);
        phoneView = container.findViewById(R.id.phoneView);
        qualificationViewContainer = container.findViewById(R.id.qualificationViewContainer);
        qualificationView = container.findViewById(R.id.qualificationView);
        mainTeachingFieldViewContainer = container.findViewById(R.id.mainTeachingFieldViewContainer);
        mainTeachingFeidlView = container.findViewById(R.id.mainTeachingFieldView);

        mailView.setOnClickListener(view -> {
            // mailto
            String recipient = ((TextView) view).getText().toString();
            AndroidContext.instance().navigator().navigateToMailToActivityView(getContext(), recipient);
        });

        updateViews();
    }

    private void updateViews() {
        nameView.setText(lecturer.getName());

        String mail = lecturer.getMail();
        if (TextUtils.isEmpty(mail)) {
            mailViewContainer.setVisibility(View.GONE);
        } else {
            mailView.setText(mail);
            mailViewContainer.setVisibility(View.VISIBLE);
        }

        String phone = lecturer.getPhone();
        if (TextUtils.isEmpty(phone)) {
            phoneViewContainer.setVisibility(View.GONE);
        } else {
            phoneView.setText(phone);
            phoneViewContainer.setVisibility(View.VISIBLE);
        }

        String qualification = lecturer.getQualifications();
        if (TextUtils.isEmpty(qualification)) {
            qualificationViewContainer.setVisibility(View.GONE);
        } else {
            qualificationView.setText(qualification);
            qualificationViewContainer.setVisibility(View.VISIBLE);
        }

        String mainTeachingField = lecturer.getMainTeachingField();
        if (TextUtils.isEmpty(mainTeachingField)) {
            mainTeachingFieldViewContainer.setVisibility(View.GONE);
        } else {
            mainTeachingFeidlView.setText(mainTeachingField);
            mainTeachingFieldViewContainer.setVisibility(View.VISIBLE);
        }
    }
}
