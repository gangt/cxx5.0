package com.xi6666.address.fragment.mvp;

import com.xi6666.address.fragment.mvp.bean.DistributionShopBean;
import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.common.CityBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/25 下午5:42.
 * 个人公众号 ardays
 */
public class DistributionShopModel implements DistributionShopContract.Model {


    @Override
    public Observable<DistributionShopBean> getDistributionShop(int page) {
        return Api.getInstance()
                .mAppUrls
                .getDistributionShop(CityBean.getRegionId(), CityBean.getLat(), CityBean.getLng(), page)
                .compose(RxSchedulers.io_main());
    }
}
