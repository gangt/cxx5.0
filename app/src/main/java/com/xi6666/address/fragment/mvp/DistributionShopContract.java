package com.xi6666.address.fragment.mvp;

import com.xi6666.address.fragment.mvp.bean.DistributionShopBean;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 16/11/25 下午5:40.
 * 个人公众号 ardays
 */
public interface DistributionShopContract {

    interface Model extends BaseModel {
        /**
         * 获取服务门店列表
         */
        Observable<DistributionShopBean> getDistributionShop(int page);
    }

    interface View extends BaseView {

        /**
         * 返回服务门店列表
         */
        void returnDistributionShop(DistributionShopBean bean);

        /**
         * 返回异常
         */
        void returnError(String error);
    }


    abstract class Presenter extends BasePresenter<Model, View> {

        /**
         * 请求服务门店列表
         */
        public abstract void requestDistributionShop(int page);
    }
}
