package com.xi6666.cardbag.view.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.cardbag.view.mvp.bean.OilCardNotAlreadyBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/24 上午10:35.
 * 个人公众号 ardays
 */
public class OilCardNotAlreadyModel implements OilCardNotAlreadyContract.Model {


    @Override
    public Observable<OilCardNotAlreadyBean> getNotAlready(String card_id) {
        return Api.getInstance()
                .mAppUrls
                .getOilCardNotAlreday(card_id)
                .compose(RxSchedulers.io_main());
    }
}
