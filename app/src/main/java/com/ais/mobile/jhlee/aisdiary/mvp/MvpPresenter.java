package com.ais.mobile.jhlee.aisdiary.mvp;

import java.lang.ref.WeakReference;

/**
 * Created: 20/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class MvpPresenter<V extends MvpView> {

    private WeakReference<V> weakRefsView = null;

    public MvpPresenter(V view) {
        attachView(view);
    }

    public V getView() {
        return weakRefsView != null ? weakRefsView.get() : null;
    }

    public final Boolean isViewAttached() {
        return weakRefsView != null && weakRefsView.get() != null;
    }

    public void attachView(V view) {
        if (isViewAttached()) {
            detachView();
        }

        weakRefsView = new WeakReference<>(view);
        view.onSetPresenter(this);
    }

    public void detachView() {
        weakRefsView.clear();
        weakRefsView = null;
    }
}
