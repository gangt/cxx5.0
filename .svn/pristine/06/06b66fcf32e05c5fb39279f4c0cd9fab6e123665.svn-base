package com.xi6666.car.mp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.car.bean.MyCarBean;
import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/26 上午11:38.
 * 个人公众号 ardays
 */
public class MyCarModel implements MyCarContract.Model {


    @Override
    public Observable<MyCarBean> getMyCarList() {
        return Api.getInstance()
                .mAppUrls
                .getCarType("s")
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BaseBean> setDefaultCar(String car_id, String car_brand_id, String car_chexing_id, String car_pailiang_id, String car_nianfen_id) {
        return Api.getInstance()
                .mAppUrls
                .add_mycar(car_brand_id, car_chexing_id, "", car_id, "", car_nianfen_id, car_pailiang_id, "", "1")
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BaseBean> delCar(String car_id) {
        return Api.getInstance()
                .mAppUrls
                .delMyCar(car_id + ",")
                .compose(RxSchedulers.io_main());
    }
}
