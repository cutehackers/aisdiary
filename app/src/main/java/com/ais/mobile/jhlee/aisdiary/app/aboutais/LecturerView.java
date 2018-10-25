package com.ais.mobile.jhlee.aisdiary.app.aboutais;

import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Lecturer;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpView;

import java.util.List;

/**
 * Create: 25/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public interface LecturerView extends MvpView {

    void showEmptyView();

    void showLecturerView();

    void update(List<Lecturer> lecturers);

}
