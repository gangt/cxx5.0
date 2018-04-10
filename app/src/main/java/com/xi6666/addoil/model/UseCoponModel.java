package com.xi6666.addoil.model;

import com.xi6666.addoil.contract.UseCouponContract;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerApiComponent;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.databean.UseCouponBean;
import com.xi6666.network.ApiRest;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Mr_yang on 2017/2/24.
 */

public class UseCoponModel implements UseCouponContract.Model {

    @Inject
    ApiRest mApiRest;

    public UseCoponModel() {
        AppComponets build = DaggerAppComponets.builder().
                apiModule(new ApiModule(BaseApplication.getApplication())).
                appModule(new AppModule(BaseApplication.getApplication())).build();
        DaggerApiComponent.builder().appComponets(build).build().Inject(this);
    }

    @Override
    public Observable<UseCouponBean> userCouponList(String userId, String userToken, String page) {
        return mApiRest.userCouponItem(userId, userToken, page).compose(RxSchedulers.io_main());
    }
}
