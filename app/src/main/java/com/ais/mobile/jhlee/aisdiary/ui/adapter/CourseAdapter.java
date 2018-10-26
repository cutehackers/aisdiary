package com.ais.mobile.jhlee.aisdiary.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Programme;
import com.ais.mobile.jhlee.aisdiary.ui.view.CourseViewHolder;

import java.util.List;

/**
 * Create: 26/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {

    private List<Programme> data;

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item_layout, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Programme item = data.get(position);
        holder.bind(item);
    }

    public void update(List<Programme> programmes) {
        data = programmes;
        notifyDataSetChanged();
    }
}
