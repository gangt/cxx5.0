package com.xi6666.carWash.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.carWash.mvp.bean.CarWashListBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/21 下午5:10.
 * 个人公众号 ardays
 */

public class CarWashCardModel implements CarWashCardContract.Model {


    @Override
    public Observable<CarWashListBean> requestWashCardList() {
        return Api.getInstance().
                mAppUrls.washCardList()
                .compose(RxSchedulers.io_main());
    }
}
