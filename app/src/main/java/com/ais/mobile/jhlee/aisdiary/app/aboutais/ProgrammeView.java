package com.ais.mobile.jhlee.aisdiary.app.aboutais;

import com.ais.mobile.jhlee.aisdiary.mvp.MvpView;

import java.util.List;

/**
 * Create: 26/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public interface ProgrammeView extends MvpView {

    void updateProgrammeView(List<String> departments);
}
