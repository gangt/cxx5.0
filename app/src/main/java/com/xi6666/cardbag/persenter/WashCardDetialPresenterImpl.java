package com.xi6666.cardbag.persenter;


import android.text.TextUtils;
import android.util.Log;

import com.xi6666.cardbag.constract.WashCardDetialConstract;
import com.xi6666.databean.WashCardDetialBean;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/11/22.
 */

public class WashCardDetialPresenterImpl implements WashCardDetialConstract.Persenter {
    private static final String TAG = "PresenterImpl";
    private ApiRest mApiRest;

    private WashCardDetialConstract.View mView;

    @Override
    public void attachView(WashCardDetialConstract.View view) {
        this.mView = view;
    }

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;
    }

    @Override
    public void loadMoreData(String option, final String page, String userId, String userToken) {
        if (TextUtils.equals(page, "1")) {
            mView.showLoading();
        }
        mApiRest.getWashCardDetial(option, page, userId, userToken).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<WashCardDetialBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, "error---->" + e);
                mView.showToast("服务端数据错误,稍后重试");
                mView.showError();
            }

            @Override
            public void onNext(WashCardDetialBean washCardDetialBean) {
                LogUtil.d(TAG, "washCardDetialBean---->" + washCardDetialBean.getInfo());
                if (TextUtils.equals(page, "1")) {
                    mView.hideStateLyout();
                }
                mView.setData(washCardDetialBean.getData());
                mView.loadMoreFinish();
                if (washCardDetialBean.getData().size() < 15) {
                    mView.hasNodata();
                }
            }
        });
    }

    @Override
    public void refreshData(String option, final String page, String userId, String userToken) {
        mApiRest.getWashCardDetial(option, page, userId, userToken).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<WashCardDetialBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, "error---->" + e);
                mView.showToast("服务端数据错误,稍后重试");
                mView.showError();
            }

            @Override
            public void onNext(WashCardDetialBean washCardDetialBean) {
                LogUtil.d(TAG, "washCardDetialBean---->" + washCardDetialBean.getInfo());
                mView.setData(washCardDetialBean.getData());
                mView.refreshFinish();
                if (washCardDetialBean.getData().size() < 15) {
                    mView.hasNodata();
                }
            }
        });
    }
}
