package com.xi6666.seckill.model;

import com.xi6666.app.BaseApplication;
import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerApiComponent;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.common.CityBean;
import com.xi6666.network.ApiRest;
import com.xi6666.seckill.contract.SecKillContract;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Mr_yang on 2017/1/16.
 */

public class SecKillModel implements SecKillContract.Module {
    @Inject
    ApiRest mApiRest;


    public SecKillModel() {
        AppComponets build = DaggerAppComponets.builder().
                apiModule(new ApiModule(BaseApplication.getApplication())).
                appModule(new AppModule(BaseApplication.getApplication())).build();
        DaggerApiComponent.builder().appComponets(build).build().Inject(this);
    }

    @Override
    public Observable<ResponseBody> getProduct(String goodId, String userId, String userToken) {
        return mApiRest.secKill(goodId, userId, userToken).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ResponseBody> getSku(String goodId, String skuValue) {
        return mApiRest.getSku(goodId, skuValue).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ResponseBody> NearbyStores() {
        String lat = CityBean.getLat();
        String lng = CityBean.getLng();
        String regionId = CityBean.getRegionId();
        return mApiRest.NearbyStores(regionId, lat, lng, "5").compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ResponseBody> secKillSingUp(String userId, String userToken, String deviceType, String secKillId) {
        return mApiRest.seckKillSignUp(userId, userToken, deviceType, secKillId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ResponseBody> secKillSure(String userId, String userToken, String deviceType, String secKillId) {
        return mApiRest.seckKillState(userId, userToken, deviceType, secKillId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ResponseBody> addShopCar(String goods_id, String sku_value_id, String user_id, String user_token) {
        return mApiRest.addGoodsCar(goods_id, sku_value_id, user_id, user_token).compose(RxSchedulers.io_main());
    }
}
