package com.ais.mobile.jhlee.aisdiary.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.ui.view.EventViewHolder;

import java.util.List;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<Integer> data = null;

    @Override
    public int getItemCount() {
        return (data == null) ? 0 : data.size();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_item_layout, viewGroup, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int position) {

    }

}
