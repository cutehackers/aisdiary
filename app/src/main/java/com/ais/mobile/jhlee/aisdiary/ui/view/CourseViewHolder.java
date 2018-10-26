package com.ais.mobile.jhlee.aisdiary.ui.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Programme;

/**
 * Create: 26/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class CourseViewHolder extends RecyclerView.ViewHolder {

    private TextView codeView;
    private TextView titleView;

    public CourseViewHolder(@NonNull View itemView) {
        super(itemView);
        codeView = itemView.findViewById(R.id.codeView);
        titleView = itemView.findViewById(R.id.titleView);
    }

    public void bind(Programme programme) {
        titleView.setText(programme.getTitle());
        codeView.setText(programme.getCode());
    }
}
