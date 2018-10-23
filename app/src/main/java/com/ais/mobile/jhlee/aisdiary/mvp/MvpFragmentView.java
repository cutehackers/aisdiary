package com.ais.mobile.jhlee.aisdiary.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ais.mobile.jhlee.aisdiary.base.BaseFragment;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public abstract class MvpFragmentView<V extends MvpView, P extends MvpPresenter<V>> extends BaseFragment implements MvpView {

    protected P presenter = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = onCreatePresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }

    @Override
    public Context getContext() {
        return this.getActivity();
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
