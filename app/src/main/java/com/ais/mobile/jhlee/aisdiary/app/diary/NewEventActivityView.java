package com.ais.mobile.jhlee.aisdiary.app.diary;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;
import com.ais.mobile.jhlee.aisdiary.base.Database;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpActivityView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class NewEventActivityView extends MvpActivityView<NewEventView, NewEventPresenter<NewEventView>> implements
        NewEventView {

    private EditText titleView;
    private TextView startDateView;
    private TextView startTimeView;
    private TextView endDateView;
    private TextView endTimeView;
    private EditText locationView;
    private EditText descriptionView;

    private Calendar startTime = Calendar.getInstance();
    private Calendar endTime = Calendar.getInstance();


    //----------------------------------------------------------------------------------------------
    // overrides

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_new_event);

        setUpViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_event_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_done: {
                if (ensureValidData()) {
                    presenter.newEvent();
                } else {
                    Toast.makeText(AndroidContext.instance().getApplication(),
                            R.string.msg_invalid_event_data,Toast.LENGTH_LONG).show();
                }
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    //----------------------------------------------------------------------------------------------
    // implements: MvpFragmentView

    @Override
    protected NewEventPresenter<NewEventView> onCreatePresenter() {
        return new NewEventPresenter<>(this);
    }


    //----------------------------------------------------------------------------------------------
    // implements: NewEventView

    @Override
    public String getTitleText() {
        return titleView.getText().toString();
    }

    @Override
    public Date getStartTime() {
        return startTime.getTime();
    }

    @Override
    public Date getEndTime() {
        return endTime.getTime();
    }

    @Override
    public String getLocationText() {
        return locationView.getText().toString();
    }

    @Override
    public String getDescriptionText() {
        return descriptionView.getText().toString();
    }

    @Override
    public void finishView() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    //----------------------------------------------------------------------------------------------
    // methods

    private void setUpViews() {
        titleView = findViewById(R.id.titleView);
        startDateView = findViewById(R.id.startDateView);
        startTimeView = findViewById(R.id.startTimeView);
        endDateView = findViewById(R.id.endDateView);
        endTimeView = findViewById(R.id.endTimeView);
        locationView = findViewById(R.id.locationView);
        descriptionView = findViewById(R.id.descriptionView);

        final Calendar calendar = Calendar.getInstance();

        startDateView.setOnClickListener(view -> {
            DatePickerDialog picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    startTime.set(Calendar.YEAR, year);
                    startTime.set(Calendar.MONTH, month);
                    startTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    startDateView.setText(Database.ISO8601DATE.format(startTime.getTime()));
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

            picker.show();
        });

        startTimeView.setOnClickListener(view -> {
            TimePickerDialog picker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    startTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    startTime.set(Calendar.MINUTE, minute);

                    startDateView.setText(Database.ISO8601TIME.format(startTime.getTime()));
                }
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);

            picker.show();
        });

        endDateView.setOnClickListener(view -> {
            DatePickerDialog picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    endTime.set(Calendar.YEAR, year);
                    endTime.set(Calendar.MONTH, month);
                    endTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    endDateView.setText(Database.ISO8601DATE.format(startTime.getTime()));
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

            picker.show();
        });

        endTimeView.setOnClickListener(view -> {
            TimePickerDialog picker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    endTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    endTime.set(Calendar.MINUTE, minute);

                    endTimeView.setText(Database.ISO8601TIME.format(startTime.getTime()));
                }
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);

            picker.show();
        });
    }

    public boolean ensureValidData() {
        return isNotEmpty(titleView.getText().toString()) &&
                isNotEmpty(startDateView.getText()) &&
                isNotEmpty(startTimeView.getText()) &&
                isNotEmpty(endDateView.getText())  &&
                isNotEmpty(endTimeView.getText());
    }

    private boolean isNotEmpty(CharSequence str) {
        return str != null && str.length() > 0;
    }
}
