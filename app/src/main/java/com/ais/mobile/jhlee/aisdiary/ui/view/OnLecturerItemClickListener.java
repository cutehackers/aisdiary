package com.ais.mobile.jhlee.aisdiary.ui.view;

import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Lecturer;

/**
 * Create: 25/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public interface OnLecturerItemClickListener {

    void onLectureItemClick(LecturerViewHolder holder, Lecturer lecturer);
}
