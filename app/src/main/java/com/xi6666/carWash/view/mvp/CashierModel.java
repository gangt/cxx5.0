package com.xi6666.carWash.view.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.carWash.view.mvp.bean.CashierBean;
import com.xi6666.carWash.view.mvp.bean.CashierDiscountBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2016/12/2 下午11:22.
 * 个人公众号 ardays
 */
public class CashierModel implements CashierContract.Model {


    @Override
    public Observable<CashierBean> getCashierDetails(String order_id) {
        return Api.getInstance()
                .mAppUrls
                .getCashierDetails(order_id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<CashierDiscountBean> selectServiceDiscount(String service_order_sn, String use_status) {
        return Api.getInstance()
                .mAppUrls
                .selectServiceDiscount(service_order_sn, use_status)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BaseBean> fullDeduction(String service_order_sn) {
        return Api.getInstance()
                .mAppUrls
                .fullDeduction(service_order_sn)
                .compose(RxSchedulers.io_main());
    }
}
