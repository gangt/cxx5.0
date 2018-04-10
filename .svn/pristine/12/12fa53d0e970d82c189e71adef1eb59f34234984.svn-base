package com.xi6666.store.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.store.mvp.bean.StoreServiceBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/25 下午2:42.
 * 个人公众号 ardays
 */
public class StoreServiceModel implements StoreServiceContract.Model {


    @Override
    public Observable<StoreServiceBean> getStoreServiceList(String city, String lat, String lng, String order_by, int page, int page_size, String status) {
        return Api.getInstance()
                .mAppUrls
                .getStoreServiceList(city, "", lat, lng, "", order_by, page, page_size, status)
                .compose(RxSchedulers.io_main());
    }
}
