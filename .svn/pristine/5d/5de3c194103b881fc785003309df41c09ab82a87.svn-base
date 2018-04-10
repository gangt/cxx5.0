package com.xi6666.classification.view.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.classification.view.fragment.mvp.bean.ShoppingNumberBean;
import com.xi6666.classification.view.mvp.bean.BrandDetailsBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午6:41.
 * 个人公众号 ardays
 */
public class BrandDetailsModel implements BrandDetailsContract.Model {

    @Override
    public Observable<BrandDetailsBean> getBrandGoodsList(String brand_id, String goods_id, int page, int sort) {
        return Api.getInstance()
                .mAppUrls
                .getBrandGoods(brand_id,goods_id,page,sort,"")
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ShoppingNumberBean> getShoppingNumber() {
        return Api.getInstance()
                .mAppUrls
                .getGoodsCarNum("u")
                .compose(RxSchedulers.io_main());
    }
}
