package com.xi6666.cardbag.view.mvp;

import com.xi6666.app.BaseApplication;
import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerApiComponent;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.cardbag.view.mvp.bean.OilCardDeleteBean;
import com.xi6666.databean.RechargeDetialBean;
import com.xi6666.databean.RechargeListBean;
import com.xi6666.network.ApiRest;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 16/11/23 下午4:24.
 * 个人公众号 ardays
 */

public class OilRechargeDetailsModel implements OilRechargeDetailsContract.Model {
    @Inject
    ApiRest mApiRest;

    public OilRechargeDetailsModel() {
        AppComponets build = DaggerAppComponets.builder().
                apiModule(new ApiModule(BaseApplication.getApplication())).
                appModule(new AppModule(BaseApplication.getApplication())).build();
        DaggerApiComponent.builder().appComponets(build).build().Inject(this);
    }

    @Override
    public Observable<RechargeDetialBean> getRechargeDetial(String userId, String cardId, String userToken) {
        return mApiRest.getOilCardChargeDetails(userId, cardId, userToken).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<RechargeListBean> getRechargeList(String userId, String card_id, int page, String userToken) {
        return mApiRest.getOilCardChargeList(userId, card_id, page, userToken).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<OilCardDeleteBean> defaultOilCardDefualt(String card_id, String userId, String userToken) {
        return mApiRest.oilCardDefualt(card_id,userId,userToken).compose(RxSchedulers.io_main());
    }
}
