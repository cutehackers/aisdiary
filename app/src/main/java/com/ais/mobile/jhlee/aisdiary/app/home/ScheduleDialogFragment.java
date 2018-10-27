package com.ais.mobile.jhlee.aisdiary.app.home;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Event;

import java.util.Objects;

/**
 * Create: 27/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class ScheduleDialogFragment extends DialogFragment {

    private static final String ARGS_EVENT = "event";

    private Event event;

    public interface  ScheduleDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, Event event);
        void onDialogNegativeClick(DialogFragment dialog, Event event);
    }


    private ScheduleDialogListener listener;


    public ScheduleDialogFragment() { }

    public static ScheduleDialogFragment create(Event event) {
        ScheduleDialogFragment fragment = new ScheduleDialogFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARGS_EVENT, event);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ScheduleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ScheduleDialogListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args == null) {
            Toast.makeText(Objects.requireNonNull(getContext()).getApplicationContext(),
                    R.string.msg_invalid_arguments, Toast.LENGTH_SHORT).show();
        }
        event = args.getParcelable(ARGS_EVENT);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setMessage(R.string.home_schedule_to_event_msg)
                .setPositiveButton(R.string.app_ok, (dialog, id) -> listener.onDialogPositiveClick(ScheduleDialogFragment.this, event))
                .setNegativeButton(R.string.app_cancel, (dialog, id) -> listener.onDialogNegativeClick(ScheduleDialogFragment.this, event))
                .create();
    }
}
