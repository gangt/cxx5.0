package com.xi6666.illegal.see.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.illegal.see.bean.UsageRecordBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2017/2/8 下午6:23.
 * 个人公众号 ardays
 */
public class UsageRecordModel implements UsageRecordContract.Model {


    @Override
    public Observable<UsageRecordBean> getIllegalList(int page, String card_id) {
        return Api.getInstance()
                .mAppUrls
                .getUsageRecord(card_id, page)
                .compose(RxSchedulers.io_main());
    }
}
