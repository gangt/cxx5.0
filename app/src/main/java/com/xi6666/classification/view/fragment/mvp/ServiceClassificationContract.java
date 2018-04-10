package com.xi6666.classification.view.fragment.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.classification.view.fragment.mvp.bean.ServiceClassificationBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2016/11/28 上午11:59.
 * 个人公众号 ardays
 */
public interface ServiceClassificationContract {

    interface Model extends BaseModel {
        /**
         * 拿商品列表
         */
        Observable<ServiceClassificationBean> getGoosList();
    }

    interface View extends BaseView {
        /**
         * 返回商品列表
         */
        void returnGoodsList(ServiceClassificationBean bean);
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 商品分类
         */
       public abstract void requestGoodsList();
    }
}
