package com.xi6666.carWash.view.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.carWash.mvp.bean.DetermineOrderBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2016/12/1 上午10:15.
 * 个人公众号 ardays
 */
public class DetermineOrderModel implements DetermineOrderContract.Model {


    @Override
    public Observable<DetermineOrderBean> generateOrder(String service_id) {
        return Api.getInstance()
                .mAppUrls
                .generateOrder(service_id)
                .compose(RxSchedulers.io_main());
    }
}
