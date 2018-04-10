package com.xi6666.carWash.view.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.common.CityBean;
import com.xi6666.store.bean.StoreBannerBean;
import com.xi6666.store.mvp.bean.StoreServiceBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2016/11/29 下午5:57.
 * 个人公众号 ardays
 */
public class CarWashActModel implements CarWashActContract.Model {


    @Override
    public Observable<StoreServiceBean> getCarWashList(int type, int sort,int page) {
        return Api.getInstance()
                .mAppUrls
                .getStoreServiceList(CityBean.getRegionId(), "", CityBean.getLat(), CityBean.getLng(), "1", sort + "", page, 15, type + "")
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<StoreBannerBean> getBannerList() {
        return Api.getInstance()
                .mAppUrls
                .getStoreBanner()
                .compose(RxSchedulers.io_main());
    }
}
