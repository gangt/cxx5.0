package com.xi6666.app.baset;

import android.content.Context;

import com.xi6666.carWash.base.network.RxManager;

/**
 * Created by Mr_yang on 2017/1/16.
 */

public abstract class BaseTPresenter<M, V> {
    public M mModel;
    public V mView;
    public Context mContext;
    public RxManager mRxManager = new RxManager();

    public void setModelAndView(M m, V v) {
        this.mModel = m;
        this.mView = v;
    }

    public abstract void onAttached();

    public void onDetached() {
        mRxManager.clear();
    }
}
