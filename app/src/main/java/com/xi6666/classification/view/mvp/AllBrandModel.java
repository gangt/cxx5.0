package com.xi6666.classification.view.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.classification.view.mvp.bean.AllBrandBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午4:22.
 * 个人公众号 ardays
 */
public class AllBrandModel implements AllBrandContract.Model {


    @Override
    public Observable<AllBrandBean> getAllBrand() {
        return Api.getInstance()
                .mAppUrls
                .getAllBrand("")
                .compose(RxSchedulers.io_main());
    }
}
