package com.xi6666.carWash.view.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.store.bean.StoreBannerBean;
import com.xi6666.store.mvp.bean.StoreServiceBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2016/11/29 下午5:57.
 * 个人公众号 ardays
 */
public interface CarWashActContract {

    interface Model extends BaseModel {
        /**
         * 请求一些洗车列表
         */
        Observable<StoreServiceBean> getCarWashList(int type, int sort, int page);

        /**
         * 请求列表
         */
        Observable<StoreBannerBean> getBannerList();
    }

    interface View extends BaseView {
        /**
         * 返回列表
         *
         * @param bean
         */
        void returnCarWashList(StoreServiceBean bean, int position);

        /**
         * 返回banner列表
         * @param bean
         */
        void returnBannerList(StoreBannerBean bean);

        /**
         * 取消加载
         */
        void loadingError();
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 请求列表
         *
         * @param type 分类 1： 洗车服务
         *             分类 2： 保养服务
         *             分类 3： 美容服务
         *             分类 4： 违章代办
         * @param sort 排序
         */
        public abstract void requestCarWashList(int type, int sort, int page);

        /**
         * 请求banner图
         */
        public abstract void requestBanner();
    }
}
