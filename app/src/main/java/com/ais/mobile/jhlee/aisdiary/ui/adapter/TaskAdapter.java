package com.ais.mobile.jhlee.aisdiary.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Task;
import com.ais.mobile.jhlee.aisdiary.ui.view.OnTaskItemCheckedChangedListener;
import com.ais.mobile.jhlee.aisdiary.ui.view.OnTaskItemClickListener;
import com.ais.mobile.jhlee.aisdiary.ui.view.TaskViewHolder;

import java.util.List;

/**
 * Create: 24/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private List<Task> data = null;
    private OnTaskItemClickListener onTaskItemClickListener;
    private OnTaskItemCheckedChangedListener onTaskItemCheckedChangedListener;

    public TaskAdapter(OnTaskItemClickListener onTaskItemClickListener, OnTaskItemCheckedChangedListener onTaskItemCheckedChangedListener) {
        this.onTaskItemClickListener = onTaskItemClickListener;
        this.onTaskItemCheckedChangedListener = onTaskItemCheckedChangedListener;
    }

    @Override
    public int getItemCount() {
        return (data == null) ? 0 : data.size();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.task_item_layout, viewGroup, false);
        return new TaskViewHolder(view, onTaskItemClickListener, onTaskItemCheckedChangedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task item = data.get(position);

        holder.bind(item);
    }

    public void update(List<Task> tasks) {
        this.data = tasks;
        notifyDataSetChanged();
    }
}
