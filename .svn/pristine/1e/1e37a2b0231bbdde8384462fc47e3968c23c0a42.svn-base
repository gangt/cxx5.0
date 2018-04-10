package com.xi6666.cardbag.view.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.cardbag.view.mvp.bean.AddOilCardBean;
import com.xi6666.cardbag.view.mvp.bean.OilCardGetNameBean;
import com.xi6666.common.UserData;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/22 下午6:35.
 * 个人公众号 ardays
 */

public class AddOilCardModel implements AddOilCardContract.Model {

    @Override
    public Observable<OilCardGetNameBean> getOilCardName(String oilCardNumber) {
        return Api.getInstance()
                .mAppUrls
                .oilCardGetName(oilCardNumber)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<AddOilCardBean> addOilCard(String card_name, String card_number, String user_mobile) {
        return Api.getInstance()
                .mAppUrls
                .addOilCard(card_name, card_number, user_mobile)
                .compose(RxSchedulers.io_main());
    }

}
