package com.xi6666.illegal.see.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.illegal.see.bean.IllegalCardStatusBean;
import com.xi6666.illegal.see.bean.RedeemCodeBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2017/2/8 下午3:50.
 * 个人公众号 ardays
 */
public class IllegalRedeemCodeModel implements IllegalRedeemCodeContract.Model {


    @Override
    public Observable<IllegalCardStatusBean> getIllegalCardStatus(String card_id) {
        return Api.getInstance()
                .mAppUrls
                .getIllegalCardStauts(card_id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<RedeemCodeBean> getRedeemCode(String card_id) {
        return Api.getInstance()
                .mAppUrls
                .getReddemCode(card_id)
                .compose(RxSchedulers.io_main());
    }
}
