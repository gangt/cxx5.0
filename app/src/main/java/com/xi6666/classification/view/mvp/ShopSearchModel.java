package com.xi6666.classification.view.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.classification.view.mvp.bean.BrandDetailsBean;
import com.xi6666.classification.view.mvp.bean.ShopSearchHotBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2016/11/30 下午9:48.
 * 个人公众号 ardays
 */
public class ShopSearchModel implements ShopSearchContract.Model {


    @Override
    public Observable<ShopSearchHotBean> getHotSearch() {
        return Api.getInstance()
                .mAppUrls
                .getHotSearch("")
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BrandDetailsBean> getSearchList(int page, int sort, String keyword) {
        return Api.getInstance()
                .mAppUrls
                .getBrandGoods("", "", page, sort, keyword)
                .compose(RxSchedulers.io_main());
    }
}
