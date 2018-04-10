package com.xi6666.carWash.base.network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 创建人 孙孙啊i
 * 时间 2016/11/21 0021.
 * 功能
 */
public class RxSchedulers {

    //从子线程转换到主线程
    public static <T> Observable.Transformer<T, T> io_main() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
