package com.xi6666.carWash.base.network;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * 创建人 孙孙啊i
 * 时间 2016/11/19 0019.
 * 功能
 *     用于管理RxBus的事件和Rxjava相关的生命周期处理
 */
public class RxManager {

    public RxBus mRxBus = RxBus.$(); //使用Rxjava 使用EventBus 简称Rxbus
    private Map<String, Observable<?>> mObservables = new HashMap<>();// 管理观察源
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();// 管理订阅者者


    public void on(String eventName, Action1<Object> action1) {
        Observable<?> mObservable = mRxBus.register(eventName);
        mObservables.put(eventName, mObservable);

        mCompositeSubscription.add(mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1, (e) -> {
                    e.printStackTrace();
                }));
    }

    public void add(Subscription m) {
        mCompositeSubscription.add(m);
    }

    public void clear() {
        mCompositeSubscription.unsubscribe();// 取消订阅
        for (Map.Entry<String, Observable<?>> entry : mObservables.entrySet())
            mRxBus.unregister(entry.getKey(), entry.getValue());// 移除观察
    }

    public void post(Object tag, Object content) {
        mRxBus.post(tag, content);
    }
}
