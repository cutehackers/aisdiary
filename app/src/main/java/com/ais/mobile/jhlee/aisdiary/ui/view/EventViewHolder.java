package com.ais.mobile.jhlee.aisdiary.ui.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.ais.mobile.jhlee.aisdiary.utils.DateTimeManager.DATE_FORMAT;
import static com.ais.mobile.jhlee.aisdiary.utils.DateTimeManager.DURATION_TIME_FORMAT;
import static com.ais.mobile.jhlee.aisdiary.utils.DateTimeManager.UPDATE_TIME_FORMAT;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class EventViewHolder extends RecyclerView.ViewHolder {

    private TextView dateView;
    private TextView titleView;
    private TextView updateDateView;
    private TextView durationView;
    private TextView locationView;
    private TextView descriptionView;

    private OnEventItemClickListener onEventItemClickListener;

    public EventViewHolder(@NonNull View itemView, OnEventItemClickListener onEventItemClickListener) {
        super(itemView);
        dateView = itemView.findViewById(R.id.dateView);
        titleView = itemView.findViewById(R.id.titleView);
        updateDateView = itemView.findViewById(R.id.updateDateView);
        durationView = itemView.findViewById(R.id.durationView);
        locationView = itemView.findViewById(R.id.locationView);
        descriptionView = itemView.findViewById(R.id.descriptionView);
        this.onEventItemClickListener = onEventItemClickListener;

        itemView.setOnClickListener( view -> {
            Event tag = (Event) view.getTag();
            EventViewHolder.this.onEventItemClickListener.onEventItemClick(EventViewHolder.this, tag);
        });
    }

    public void bind(Event event, boolean showTopDate) {
        dateView.setVisibility(showTopDate ? View.VISIBLE : View.GONE);

        if (showTopDate) {
            try {
                dateView.setText(DATE_FORMAT.format(event.getStartTimeAsDate()));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        titleView.setText(event.getTitle());

        try {
            updateDateView.setText(UPDATE_TIME_FORMAT.format(event.getUpdatedAsDate()));

            durationView.setText(String.format("%s ~ %s",
                    DURATION_TIME_FORMAT.format(event.getStartTimeAsDate()),
                    DURATION_TIME_FORMAT.format(event.getEndTimeAsDate()))
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (TextUtils.isEmpty(event.getLocation())) {
            locationView.setVisibility(View.GONE);
        } else {
            locationView.setText(String.format("@%s", event.getLocation()));
            locationView.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(event.getDescription())) {
            descriptionView.setVisibility(View.GONE);
        } else {
            descriptionView.setText(event.getDescription());
            descriptionView.setVisibility(View.VISIBLE);
        }

        itemView.setTag(event);
    }
}
