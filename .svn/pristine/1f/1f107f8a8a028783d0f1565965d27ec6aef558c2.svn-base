package com.xi6666.technician.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.technician.mvp.bean.TechnicianDetailsBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2016/11/29 下午12:00.
 * 个人公众号 ardays
 */
public class TechnicianDetailsModel implements TechnicianDetailsContract.Model {


    @Override
    public Observable<TechnicianDetailsBean> getTechnicianDetails(String technician_id) {
        return Api.getInstance()
                .mAppUrls
                .getTechnicianDetails(technician_id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BaseBean> likes(String ques_id) {
        return Api.getInstance()
                .mAppUrls
                .questionLikes(ques_id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BaseBean> unLikes(String ques_id) {
        return Api.getInstance()
                .mAppUrls
                .questionUnLikes(ques_id)
                .compose(RxSchedulers.io_main());
    }
}
