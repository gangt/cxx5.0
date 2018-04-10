package com.xi6666.illegal.see.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.illegal.see.bean.CancelOrderBean;
import com.xi6666.illegal.see.bean.UsageDetailsBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2017/2/9 上午10:36.
 * 个人公众号 ardays
 */
public class UsageDetailsModel implements UsageDetailsContract.Model {


    @Override
    public Observable<UsageDetailsBean> getUsageDetails(String log_id) {
        return Api.getInstance()
                .mAppUrls
                .getUsageDetails(log_id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<CancelOrderBean> getCancelOrder(String log_id) {
        return Api.getInstance()
                .mAppUrls
                .cancelIillegalOrder(log_id)
                .compose(RxSchedulers.io_main());
    }
}
