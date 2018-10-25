package com.ais.mobile.jhlee.aisdiary.ui.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Lecturer;

/**
 * Create: 25/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class LecturerViewHolder extends RecyclerView.ViewHolder {

    private TextView nameView;

    public LecturerViewHolder(@NonNull View itemView, OnLecturerItemClickListener onItemClickListener) {
        super(itemView);
        nameView = itemView.findViewById(R.id.nameView);

        itemView.setOnClickListener(view -> {
            Lecturer lecturer = (Lecturer) view.getTag();
            onItemClickListener.onLectureItemClick(this, lecturer);
        });
    }

    public void setName(CharSequence name) {
        nameView.setText(name);
    }
}
