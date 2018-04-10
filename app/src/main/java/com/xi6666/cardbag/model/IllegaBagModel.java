package com.xi6666.cardbag.model;

import com.xi6666.app.BaseApplication;
import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerApiComponent;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;
import com.xi6666.carWash.base.network.RxManager;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.cardbag.constract.IllegaBagContract;
import com.xi6666.databean.IllegaBagListBean;
import com.xi6666.network.ApiRest;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Mr_yang on 2017/2/8.
 */

public class IllegaBagModel implements IllegaBagContract.Model {
    @Inject
    ApiRest mApiRest;

    public IllegaBagModel() {
        AppComponets build = DaggerAppComponets.builder().
                apiModule(new ApiModule(BaseApplication.getApplication())).
                appModule(new AppModule(BaseApplication.getApplication())).build();
        DaggerApiComponent.builder().appComponets(build).build().Inject(this);
    }


    @Override
    public Observable<IllegaBagListBean> loadData(String userId, String userToken, String page) {
        return mApiRest.illageCardBag(userId, userToken, page).compose(RxSchedulers.io_main());
    }
}
