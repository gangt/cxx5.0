package com.xi6666.classification.view.fragment.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.classification.view.fragment.mvp.bean.ServiceClassificationBrandBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午2:14.
 * 个人公众号 ardays
 */
public interface ServiceClassificationBrandContract {

    interface Model extends BaseModel {
        /**
         * 请求品牌数组
         */
        Observable<ServiceClassificationBrandBean> getBrandList();
    }

    interface View extends BaseView {
        /**
         * 返回品牌数组
         */
        void returnBrandList(ServiceClassificationBrandBean bean);

        /**
         * 异常
         */
        void error();
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 请求品牌
         */
        public abstract void requestBrandList();
    }
}
