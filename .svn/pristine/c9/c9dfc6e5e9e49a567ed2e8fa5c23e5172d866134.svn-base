package com.xi6666.illegal.see.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.illegal.see.bean.GenerateRedeemCodeBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2017/2/8 下午5:34.
 * 个人公众号 ardays
 */
public class RedeemCodeModel implements RedeemCodeContract.Model {


    @Override
    public Observable<GenerateRedeemCodeBean> getGenerateRedeemCode(String redeem_code) {
        return Api.getInstance()
                .mAppUrls
                .getRedeemIllegal(redeem_code)
                .compose(RxSchedulers.io_main());
    }
}
