package com.xi6666.producthome.presenter;


import android.util.Log;

import com.google.gson.Gson;
import com.xi6666.databean.HomeHotGoodsBean;
import com.xi6666.databean.HomeSpecialBean;
import com.xi6666.databean.ProductHomeCateBean;

import com.xi6666.network.ApiRest;
import com.xi6666.producthome.contract.ProductHomeContract;
import com.xi6666.utils.LogUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/11/28.
 */

public class ProductHomePresenterImpl implements ProductHomeContract.Presenter {
    private static final String TAG = "ProductHome";
    private ProductHomeContract.View mView;

    private ApiRest mApiRest;

    @Override
    public void attachView(ProductHomeContract.View view) {
        this.mView = view;
    }

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;

    }

    @Override
    public void loadCateGories() {
        mApiRest.getCateGories().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            ProductHomeCateBean productHomeCateBean = new Gson().fromJson(string, ProductHomeCateBean.class);
                            mView.addCateData(productHomeCateBean.getData().getList());
                            mView.addBannerData(productHomeCateBean.getData().getBanner());
                            LogUtil.d(TAG, "BannerData--->" + productHomeCateBean.getData().getBanner().get(0));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void loadSpeCiaData() {
        mView.showLoaind();
        mApiRest.getSpecial().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<HomeSpecialBean>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }

                    @Override
                    public void onNext(HomeSpecialBean homeSpecialBean) {
                        mView.hideState();
                        mView.addSpecialData(homeSpecialBean.getData());
                    }
                });
    }

    @Override
    public void loadHotProduct(final int page) {
        mApiRest.getHomeHotGoods(page + "").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            if (page == 1) {
                                mView.finishiRefresh();
                            } else {
                                mView.finishiLoadMore();
                            }
                            HomeHotGoodsBean homeHotGoodsBean = new Gson().fromJson(string, HomeHotGoodsBean.class);
                            mView.addHotProduct(homeHotGoodsBean.getData());
                            if (homeHotGoodsBean.getData().size() < 6) {
                                mView.hasNoData(true);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
