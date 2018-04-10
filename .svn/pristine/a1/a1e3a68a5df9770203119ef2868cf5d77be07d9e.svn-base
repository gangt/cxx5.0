package com.xi6666.cardbag.persenter;

import android.text.TextUtils;
import android.util.Log;

import com.xi6666.cardbag.constract.WashCardInfoConstract;
import com.xi6666.databean.WashCardInfoBean;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/11/22.
 */

public class WashCardInfoImpl implements WashCardInfoConstract.persenter {
    private ApiRest mApiRest;
    private WashCardInfoConstract.View mView;
    public static final String TAG = "WashCardInfoImpl";

    @Override
    public void attachView(WashCardInfoConstract.View view) {
        this.mView = view;
    }

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;
    }


    @Override
    public void loadData(String suerId, String userToken) {
        LogUtil.d(TAG, "issucce--->" + suerId + "---------" + userToken);
        mApiRest.getWashCardInfo(suerId, userToken).subscribeOn(Schedulers.io()).
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
                    mView.setCardInfo(washCardInfoBean.getData().get(0).getWashcard_sn(), washCardInfoBean.getData().get(0).getWashcard_cash_amount(),
                            "上次使用时间:" + washCardInfoBean.getData().get(0).getWashcard_used_time() + "  下次可用时间:" + washCardInfoBean.getData().get(0).getNext_user_time()
                    );
                    //洗车卡不可用
                    if (washCardInfoBean.getData().get(0).getIs_used() == 1) {
                        mView.changeBackground();
                        mView.showState(true);
                        //洗车卡金额为0
                        if (TextUtils.equals(washCardInfoBean.getData().get(0).getWashcard_cash_amount(), "0.00")) {
                            mView.showState(false);
                        }
                    } else {
                        //洗车卡可用
                        mView.showState(false);
                    }
                }
            }
        });
    }
}
