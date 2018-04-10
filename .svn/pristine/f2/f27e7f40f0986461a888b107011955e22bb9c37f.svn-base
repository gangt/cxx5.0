package com.xi6666.cardbag.persenter;

import android.util.Log;

import com.xi6666.cardbag.constract.CarWashCardContract;
import com.xi6666.common.UserData;
import com.xi6666.databean.WashCardInfoBean;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/11/24.
 */

public class CarWashCardPresenterImpl implements CarWashCardContract.Presenter {
    private static final String TAG = "CarWashCardImpl";
    private CarWashCardContract.View mView;
    private ApiRest mApiRest;

    @Override
    public void attachView(CarWashCardContract.View view) {
        this.mView = view;
    }

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;
    }

    @Override
    public void loadData() {
        String userId = UserData.getUserId();
        String userToken = UserData.getUserToken();
        mApiRest.getWashCardInfo(userId, userToken).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<WashCardInfoBean>() {


            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, "error--->" + e);
            }

            @Override
            public void onNext(WashCardInfoBean washCardInfoBean) {
                LogUtil.d(TAG, "issucce--->" + washCardInfoBean.isSuccess());
                if (washCardInfoBean.isSuccess()) {
                    if (washCardInfoBean.getData().size() > 0) {
                        mView.showData();
                    } else {
                        mView.showEmpty();
                    }
                }
            }
        });
    }
}
