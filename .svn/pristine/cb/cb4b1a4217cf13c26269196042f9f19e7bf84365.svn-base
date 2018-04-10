package com.xi6666.cardbag.view.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.cardbag.view.mvp.bean.OilCardListBean;
import com.xi6666.cardbag.view.mvp.bean.OilCardDeleteBean;
import com.xi6666.common.UserData;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/22 上午10:38.
 * 个人公众号 ardays
 */

public class OilCardModel implements OilCardContract.Model {
    @Override
    public Observable<OilCardListBean> getOilCardList(int PAGE) {

        return Api.getInstance().mAppUrls
                .oilCardList(PAGE)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<OilCardDeleteBean> deleteOilCard(String card_id) {
        return Api.getInstance().mAppUrls
                .oilCardDelete(card_id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<OilCardDeleteBean> defaultOilCard(String card_id) {
        return Api.getInstance().mAppUrls
                .oilCardDefualt(card_id)
                .compose(RxSchedulers.io_main());
    }
}
