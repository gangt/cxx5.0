package com.xi6666.classification.view.fragment.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.classification.view.fragment.mvp.bean.ServiceClassificationBrandBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午2:15.
 * 个人公众号 ardays
 */
public class ServiceClassificationBrandModel implements ServiceClassificationBrandContract.Model {


    @Override
    public Observable<ServiceClassificationBrandBean> getBrandList() {
        return Api.getInstance()
                .mAppUrls
                .getBrandList("")
                .compose(RxSchedulers.io_main());
    }
}
