package com.xi6666.store.mvp;

import com.xi6666.common.CityBean;

/**
 * 创建者 sunsun
 * 时间 16/11/25 下午2:42.
 * 个人公众号 ardays
 */
public class StoreServicePresenter extends StoreServiceContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestStoreServiceList(String city, String order_by, int page, String status) {
        mRxManager.add(mModel.getStoreServiceList(city, CityBean.getLat(), CityBean.getLng(), order_by, page, 15, status)
        .subscribe(
                res ->{
                    mView.returnStoreServiceList(res);
                },e ->{
                    mView.netError();
                }
        ));
    }
}
