package com.xi6666.illegal.other;

import com.xi6666.app.BaseApplication;
import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerApiComponent;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.databean.IllegaHomeListBean;
import com.xi6666.network.ApiRest;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Mr_yang on 2017/2/7.
 */

public class IllegaHomeModel implements IllegaHomeContract.Model {
    @Inject
    ApiRest mApiRest;

    public IllegaHomeModel() {
        AppComponets build = DaggerAppComponets.builder().
                apiModule(new ApiModule(BaseApplication.getApplication())).
                appModule(new AppModule(BaseApplication.getApplication())).build();
        DaggerApiComponent.builder().appComponets(build).build().Inject(this);
    }

    @Override
    public Observable<IllegaHomeListBean> getIllageHomeList(String userId, String userToken, String page) {
        return mApiRest.illegaHomeList(userId, userToken, page).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ResponseBody> deleteItem(String car_no, String city_code, String province_code, String user_id) {
        return mApiRest.deleteIllega(car_no, city_code, province_code, user_id).compose(RxSchedulers.io_main());
    }
}
