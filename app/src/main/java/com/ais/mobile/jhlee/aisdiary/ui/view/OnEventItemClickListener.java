package com.ais.mobile.jhlee.aisdiary.ui.view;

import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Event;

/**
 * Create: 24/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public interface OnEventItemClickListener {

    void onEventItemClick(EventViewHolder holder, Event event);
}
