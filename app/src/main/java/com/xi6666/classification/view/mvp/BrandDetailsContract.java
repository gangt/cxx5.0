package com.xi6666.classification.view.mvp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.classification.view.fragment.mvp.bean.ShoppingNumberBean;
import com.xi6666.classification.view.mvp.bean.BrandDetailsBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午6:40.
 * 个人公众号 ardays
 */
public interface BrandDetailsContract {

    interface Model extends BaseModel {

        /**
         * 拿品牌  或 商品列表
         */
        Observable<BrandDetailsBean> getBrandGoodsList(String brand_id, String goods_id, int page, int sort);

        /**
         *获取 购物车数量
         */
        Observable<ShoppingNumberBean> getShoppingNumber();
    }

    interface View extends BaseView {
        /**
         * 返回列表
         * @param bean
         */
        void returnBrandGoodsData(BrandDetailsBean bean);

        /**
         * 返回购物车购物数量
         */
        void returnShoppingNumber(ShoppingNumberBean bean);


        /**
         * 购物异常
         */
        void returnBrandError();
    }

    enum Sort {
        //默认排序
        Default,
        //销量高
        SalesMany,
        //销量低
        SalesLess,
        //价格高
        MoneyMany,
        //价格低
        MeneyLess
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 请求品牌或者商品列表
         *
         * @param brand_id 品牌ID
         * @param goods_id 商品ID
         * @param page     页数
         */
        public abstract void requestBrandGoodsList(String brand_id, String goods_id, int page, Sort sort);


        /**
         * 获取购物车数量
         */
        public abstract void requestShoppingNumber();
    }
}
