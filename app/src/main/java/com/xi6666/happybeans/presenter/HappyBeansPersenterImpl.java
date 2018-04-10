package com.xi6666.happybeans.presenter;

import android.text.TextUtils;

import com.xi6666.common.UserData;
import com.xi6666.databean.HappyBeansBean;
import com.xi6666.happybeans.contract.HappyBeansConstract;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/11/22.
 */

public class HappyBeansPersenterImpl implements HappyBeansConstract.Persenter {
    private static final String TAG = "HappyBeansPersenterImpl";
    private HappyBeansConstract.View mView;
    private ApiRest mApiRest;

    @Override
    public void attachView(HappyBeansConstract.View view) {
        this.mView = view;
    }

    @Override
    public void setApiRest(ApiRest apiRest) {
        this.mApiRest = apiRest;
    }

    @Override
    public void loadData(String option, final String page) {
        String userId = UserData.getUserId();
        String userToken = UserData.getUserToken();
        LogUtil.d(TAG, "option--->" + option);
        LogUtil.d(TAG, "page--->" + page);
        LogUtil.d(TAG, "userId--->" + userId);
        LogUtil.d(TAG, "userToken--->" + userToken);
        if (TextUtils.equals(page, "1")) {
            mView.showLoading();
        }
        mApiRest.getHappyBeans(option, page, userId, userToken).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HappyBeansBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showError();
                mView.refreshFinish();
            }

            @Override
            public void onNext(HappyBeansBean happyBeansBean) {
                if (TextUtils.equals(page, "1")) {
                    if (happyBeansBean.getData().getXidou_list().size() == 0) {
                        mView.showEmpty();
                    } else {
                        mView.hideEmptyLayout();
                    }

                }
                mView.refreshFinish();
                mView.loadData(happyBeansBean.getData().getXidou_list());
                if (happyBeansBean.getData().getXidou_list().size() < 15) {
                    mView.hasNodata();
                }
                mView.setHappyBeanNum(happyBeansBean.getData().getXidou_num());
                mView.setPersionNum(happyBeansBean.getData().getTuijian_person());
            }
        });
    }
}
