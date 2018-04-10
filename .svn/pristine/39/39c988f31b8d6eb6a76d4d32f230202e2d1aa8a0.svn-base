package com.xi6666.store.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;

import com.xi6666.store.mvp.bean.StoreServiceTypeBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/25 上午11:32.
 * 个人公众号 ardays
 */
public interface StoreContract {

    interface Model extends BaseModel {
        /**
         * 拿门店洗车服务类型
         */
        Observable<StoreServiceTypeBean> getServiceType();
    }

    interface View extends BaseView {
        /**
         * 返回门店洗车服务类型
         */
        void returnServiceType(StoreServiceTypeBean res);

        /**
         * 网络异常
         */
        void netError();
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 请求门店洗车服务类型
         */
        public abstract void requestServiceType();
    }
}
