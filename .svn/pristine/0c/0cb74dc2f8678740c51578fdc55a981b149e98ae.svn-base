package com.xi6666.splash.presenter;

import android.util.Log;

import com.xi6666.splash.view.SplashView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;

import static android.R.attr.versionName;

/**
 * Created by Mr_yang on 2016/10/28.
 */

public class SplashPersenter {

    private static final String TAG = "SplashPersenter";
    private SplashView mSplashView;

    public void attachView(SplashView view) {
        mSplashView = view;
    }

    public void countdownTime() {

        final int countTime = 2;//需要的倒计时的时间
        Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).map(new Func1<Long, Integer>() {
            @Override
            public Integer call(Long aLong) {
                Log.d(TAG, aLong + "");
                return countTime - aLong.intValue();
            }
        }).take(countTime + 1).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "开始计时");
            }
        })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                       /* Log.d("TAG", "计时完成");*/
                        mSplashView.startNextAct();
                        mSplashView.finishAct();
                    }

                    @Override
                    public void onError(Throwable e) {
                        /* Log.d("TAG", "错误");*/
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "时间" + integer);
                        mSplashView.showCountDownTime(integer + "");
                    }
                });
    }



}
