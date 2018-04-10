package com.xi6666.illegal.other;

import com.xi6666.app.BaseApplication;
import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerApiComponent;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.databean.IllegaPayBean;
import com.xi6666.network.ApiRest;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Mr_yang on 2017/2/8.
 */

public class IllegaSurePayModel implements IllegaSurePayContract.Model {
    @Inject
    ApiRest mApiRest;

    public IllegaSurePayModel() {
        AppComponets build = DaggerAppComponets.builder().
                apiModule(new ApiModule(BaseApplication.getApplication())).
                appModule(new AppModule(BaseApplication.getApplication())).build();
        DaggerApiComponent.builder().appComponets(build).build().Inject(this);
    }

    @Override
    public Observable<ResponseBody> creatOrder(String oderId) {
        return mApiRest.IllegaOrder(oderId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ResponseBody> changePayType(String userId, String payId, String orderSn) {
        return mApiRest.changePayType(userId, payId, orderSn).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<IllegaPayBean> createPayInfo(String userId, String payId, String orderSn) {
        return mApiRest.IllegaPayInfo(userId, payId, orderSn).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ResponseBody> getPayParam(String url, String attach, String body, String out_trade_no, String pay_id, String pay_name, String total_fee) {
        return mApiRest.createOilOrder(url, attach, body, out_trade_no, pay_id, pay_name, total_fee).compose(RxSchedulers.io_main());
    }
}
