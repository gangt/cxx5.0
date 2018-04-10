package com.xi6666.classification.view.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.classification.view.mvp.bean.BrandDetailsBean;
import com.xi6666.classification.view.mvp.bean.ShopSearchHotBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2016/11/30 下午9:47.
 * 个人公众号 ardays
 */
public interface ShopSearchContract {

    interface Model extends BaseModel {
        /**
         * 热门搜索
         */
        Observable<ShopSearchHotBean> getHotSearch();

        /**
         * 获取搜索页面
         */
        Observable<BrandDetailsBean> getSearchList(int page, int sort, String keyword);
    }

    interface View extends BaseView {
        /**
         * 返回热门搜索数据
         *
         * @param bean
         */
        void returnHotSearch(ShopSearchHotBean bean);

        /**
         * 返回搜索结果列表
         */
        void returnSearchList(BrandDetailsBean data);
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 请求热门搜索数据
         */
        public abstract void requestHotSearch();


        /**
         * 请求搜索列表
         *
         * @param page    页数
         * @param keyword 关键字
         */
        public abstract void requestSearchList(int page, int sort, String keyword);
    }
}
