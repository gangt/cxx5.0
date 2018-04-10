package com.xi6666.owner.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.owner.mvp.bean.OwnerEvaluationBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2016/11/30 上午11:42.
 * 个人公众号 ardays
 */
public class OwnerEvaluationModel implements OwnerEvaluationContract.Model {


    @Override
    public Observable<OwnerEvaluationBean> getEvaluationList(int level, int page, String shop_id) {
        return Api.getInstance()
                .mAppUrls
                .getEvaluationList(level, page, shop_id)
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
