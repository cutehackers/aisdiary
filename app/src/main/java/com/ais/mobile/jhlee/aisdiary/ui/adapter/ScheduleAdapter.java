package com.ais.mobile.jhlee.aisdiary.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Event;
import com.ais.mobile.jhlee.aisdiary.ui.view.EventViewHolder;
import com.ais.mobile.jhlee.aisdiary.ui.view.OnEventItemClickListener;
import com.ais.mobile.jhlee.aisdiary.ui.view.OnScheduleItemClickListener;
import com.ais.mobile.jhlee.aisdiary.ui.view.ScheduleViewHolder;
import com.ais.mobile.jhlee.aisdiary.utils.DateTimeManager;

import java.text.ParseException;
import java.util.List;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    private List<Event> data = null;
    private OnScheduleItemClickListener onItemClickListener;

    public ScheduleAdapter(OnScheduleItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return (data == null) ? 0 : data.size();
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_item_layout, viewGroup, false);
        return new ScheduleViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Event item = data.get(position);

        boolean showTopDate;
        if (position == 0) {
            showTopDate = true;
        } else {
            try {
                showTopDate = !DateTimeManager.isSameDay(item.getStartTimeAsDate(), data.get(position - 1).getStartTimeAsDate());
            } catch (ParseException e) {
                e.printStackTrace();
                showTopDate = false;
            }
        }

        holder.bind(item, showTopDate);
    }

    public void update(List<Event> data) {
        this.data = data;
        notifyDataSetChanged();
    }

}
