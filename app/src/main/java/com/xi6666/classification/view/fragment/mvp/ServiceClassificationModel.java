package com.xi6666.classification.view.fragment.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.classification.view.fragment.mvp.bean.ServiceClassificationBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午12:03.
 * 个人公众号 ardays
 */
public class ServiceClassificationModel implements ServiceClassificationContract.Model {


    @Override
    public Observable<ServiceClassificationBean> getGoosList() {
        return Api.getInstance()
                .mAppUrls
                .getGoodsTypeList("")
                .compose(RxSchedulers.io_main());
    }
}
