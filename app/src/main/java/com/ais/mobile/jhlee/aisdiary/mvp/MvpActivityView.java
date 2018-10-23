package com.ais.mobile.jhlee.aisdiary.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ais.mobile.jhlee.aisdiary.base.BaseActivity;

/**
 * Created: 20/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public abstract class MvpActivityView<V extends MvpView, P extends MvpPresenter<V>> extends BaseActivity implements MvpView {

    protected P presenter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = onCreatePresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (presenter != null) {
            presenter.detachView();
        }
        onDestroyPresenter();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <VV extends MvpView> void onSetPresenter(MvpPresenter<VV> presenter) {

        this.presenter = (P) presenter;
    }

    protected abstract P onCreatePresenter();

    protected void onDestroyPresenter() {

    }
}
