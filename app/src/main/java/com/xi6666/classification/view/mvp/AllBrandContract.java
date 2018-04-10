package com.xi6666.classification.view.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.classification.view.mvp.bean.AllBrandBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午4:22.
 * 个人公众号 ardays
 */
public interface AllBrandContract {

    interface Model extends BaseModel {
        /**
         * 获取所有品牌
         */
        Observable<AllBrandBean> getAllBrand();
    }

    interface View extends BaseView {
        /**
         * 返回所有品牌
         */
        void returnAllBrand(AllBrandBean bean);
    }


    abstract class Presenter extends BasePresenter<Model, View> {

        /**
         * 获取所有品牌
         */
        public abstract void requestAllBrand();
    }
}
