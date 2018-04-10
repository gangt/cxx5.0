package com.xi6666.store.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.store.mvp.bean.StoreServiceTypeBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/25 上午11:32.
 * 个人公众号 ardays
 */
public class StoreModel implements StoreContract.Model {


    @Override
    public Observable<StoreServiceTypeBean> getServiceType() {

        return Api.getInstance()
                .mAppUrls
                .getServiceType("")
                .compose(RxSchedulers.io_main());
    }
}
