package com.xi6666.carWash.view.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.common.CityBean;
import com.xi6666.store.mvp.bean.StoreServiceBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/26 上午10:09.
 * 个人公众号 ardays
 */
public class CarWashSearchResultModel implements CarWashSearchResultContract.Model {


    @Override
    public Observable<StoreServiceBean> searchStoreList(String keyword, int page, int order_by) {
        return Api.getInstance()
                .mAppUrls
                .getStoreServiceList(CityBean.getRegionId(), keyword, CityBean.getLat(), CityBean.getLng(), "", order_by + "", page, 15, "0")
                .compose(RxSchedulers.io_main());
    }
}
