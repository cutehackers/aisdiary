package com.ais.mobile.jhlee.aisdiary.ui.view;

import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Task;

/**
 * Create: 24/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public interface OnTaskItemCheckedChangedListener {

    void onTaskItemCheckChanged(TaskViewHolder holder, Task task, boolean isChecked);
}
