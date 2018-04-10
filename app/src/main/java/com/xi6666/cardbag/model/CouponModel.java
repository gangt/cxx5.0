package com.xi6666.cardbag.model;

import com.xi6666.app.BaseApplication;
import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerApiComponent;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.cardbag.constract.CouPonContract;
import com.xi6666.cardbag.view.CouponFrgm;
import com.xi6666.databean.CouponBean;
import com.xi6666.network.ApiRest;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Mr_yang on 2017/1/17.
 */

public class CouponModel implements CouPonContract.Model {
    @Inject
    ApiRest mApiRest;

    public CouponModel() {
        AppComponets build = DaggerAppComponets.builder().
                apiModule(new ApiModule(BaseApplication.getApplication())).
                appModule(new AppModule(BaseApplication.getApplication())).build();
        DaggerApiComponent.builder().appComponets(build).build().Inject(this);
    }

    @Override
    public Observable<CouponBean> getCouponList(String page, String money, String userid, String user_token) {
        return mApiRest.getCouponListData(page, money, userid, user_token).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ResponseBody> deleteCouponCard(String coupnCard, String userId, String userToken) {
        return mApiRest.deleteCouponCard(coupnCard,userId,userToken).compose(RxSchedulers.io_main());
    }
}
