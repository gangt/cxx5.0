package com.xi6666.technician.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.technician.mvp.bean.ItsAnswerBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2016/11/30 下午3:51.
 * 个人公众号 ardays
 */
public class ItsAnswerModel implements ItsAnswerContract.Model {


    @Override
    public Observable<ItsAnswerBean> getAnswerList(String js_user_id, int page) {
        return Api.getInstance()
                .mAppUrls
                .getJSAnswerList(js_user_id, page)
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
