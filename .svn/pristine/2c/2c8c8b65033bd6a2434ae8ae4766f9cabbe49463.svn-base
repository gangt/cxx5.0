package com.xi6666.store.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.common.CityBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/25 下午6:47.
 * 个人公众号 ardays
 */
public class StoreDetailsModel implements StoreDetailsContract.Model {


    @Override
    public Observable<StoreDetailsBean> getStoreDetails(String store_id, String service_cate_id) {
        return Api.getInstance()
                .mAppUrls
                .getStoreDetails(store_id, service_cate_id, CityBean.getLng(), CityBean.getLat())
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BaseBean> likes(String ques_id, String store_user_id) {
        return Api.getInstance()
                .mAppUrls
                .commentLikes(ques_id, store_user_id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BaseBean> unLikes(String ques_id, String store_user_id) {
        return Api.getInstance()
                .mAppUrls
                .cannelLikes(ques_id, store_user_id)
                .compose(RxSchedulers.io_main());
    }
}
