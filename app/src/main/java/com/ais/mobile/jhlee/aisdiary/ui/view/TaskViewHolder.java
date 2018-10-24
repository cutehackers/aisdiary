package com.ais.mobile.jhlee.aisdiary.ui.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Task;

/**
 * Create: 24/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class TaskViewHolder extends RecyclerView.ViewHolder {

    private CheckBox checkView;
    private StrikeThruTextView contentView;

    private OnTaskItemClickListener onTaskItemClickListener;
    private OnTaskItemCheckedChangedListener onTaskItemCheckedChangedListener;

    public TaskViewHolder(@NonNull View itemView,
                          OnTaskItemClickListener onTaskItemClickListener,
                          OnTaskItemCheckedChangedListener onTaskItemCheckedChangedListener) {
        super(itemView);

        checkView = itemView.findViewById(R.id.checkView);
        contentView = itemView.findViewById(R.id.contentView);
        this.onTaskItemClickListener = onTaskItemClickListener;
        this.onTaskItemCheckedChangedListener = onTaskItemCheckedChangedListener;

        itemView.setOnClickListener(view -> {
            Task tag = (Task) view.getTag();
            if (TaskViewHolder.this.onTaskItemClickListener != null) {
                TaskViewHolder.this.onTaskItemClickListener.onTaskItemClick(TaskViewHolder.this, tag);
            }
        });

        checkView.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Task tag = (Task) itemView.getTag();
            applyCheckedStatus(isChecked);

            if (this.onTaskItemCheckedChangedListener != null) {
                this.onTaskItemCheckedChangedListener.onTaskItemCheckChanged(TaskViewHolder.this, tag, isChecked);
            }
        });
    }

    public void bind(Task task) {
        itemView.setTag(task);

        checkView.setChecked(task.getCompleted());
        contentView.setText(task.getContent());
        applyCheckedStatus(task.getCompleted());
    }

    private void applyCheckedStatus(boolean isChecked) {
        if (isChecked) {
            contentView.showAsStrikeThruText();
        } else {
            contentView.clearStrikeThruText();
        }
    }

}
