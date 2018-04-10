package com.xi6666.carWash.base.network;

import android.content.Context;

import com.xi6666.carWash.base.network.RxManager;

/**
 * 创建人 孙孙啊i
 * 时间 2016/11/19 0019.
 * 功能
 *
 * P层  处理与用户交互的负责逻辑。
 */
public abstract class BasePresenter<M, V> {
    public Context context;
    public M mModel;
    public V mView;
    public RxManager mRxManager = new RxManager();


    public void setVm(V view,M model){
        this.mView = view;
        this.mModel = model;
        this.onStart();
    }

    protected abstract void onStart();


    public void onDestory(){
        mRxManager.clear();
    }


}
