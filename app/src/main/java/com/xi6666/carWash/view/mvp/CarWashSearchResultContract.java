package com.xi6666.carWash.view.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.store.mvp.bean.StoreServiceBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 16/11/26 上午10:08.
 * 个人公众号 ardays
 */
public interface CarWashSearchResultContract {

    interface Model extends BaseModel {
        /**
         * 搜索门店列表
         */
        Observable<StoreServiceBean> searchStoreList(String keyword, int page, int order_by);
    }

    interface View extends BaseView {
        /**
         * 返回门店列表
         */
        void returnStoreList(StoreServiceBean bean);

        /**
         * 返回异常
         */
        void returnError();
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 请求搜索门店列表
         *
         * @param keyword  关键字
         * @param page     页数
         * @param order_by 排序；从价格升序1;从价格降序2;评分升序3;评分降序4;从近到远5;人气降序6;人气升序7 默认值是5
         */
        public abstract void requestSearchStoreList(String keyword, int page, int order_by);
    }
}
