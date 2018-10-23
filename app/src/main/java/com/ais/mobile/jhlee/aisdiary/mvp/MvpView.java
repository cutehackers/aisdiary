package com.ais.mobile.jhlee.aisdiary.mvp;

import android.content.Context;

/**
 * Created: 20/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public interface MvpView {

    Context getContext();

    <VV extends MvpView> void onSetPresenter(MvpPresenter<VV> presenter);
}
