package com.xi6666.productdetails.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.xi6666.common.CityBean;
import com.xi6666.databean.ProductDetialBean;
import com.xi6666.databean.PromotionBean;
import com.xi6666.databean.SkuListBean;
import com.xi6666.network.ApiRest;
import com.xi6666.productdetails.contract.ProductDetialContract;
import com.xi6666.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/11/15.
 */

public class ProductPresenterImpl implements ProductDetialContract.Presenter {
    private static final String TAG = "ProductPresenterImpl";
    private ProductDetialContract.View mView;
    private ApiRest mApiRest;
    private List<String> strings = new ArrayList<>();

    private boolean mCollect = false;

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;
    }

    @Override
    public void attachView(ProductDetialContract.View view) {
        this.mView = view;
    }

    @Override
    public void loadData(String goodId, String usId, String goodsToken) {
        mView.showLoading();
        mApiRest.getProduct(goodId, usId, goodsToken).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, "error3---->" + e);
                //mView.showToast(e.toString());
                mView.showError();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    LogUtil.d(TAG, "商品数据--->" + string);
                    mView.hideState();
                    JSONObject jsonObject = new JSONObject(string);
                    //sku数据
                    JSONObject jsonColorList = jsonObject.getJSONObject("data").getJSONObject("sku_info").getJSONObject("sku1_list");
                    JSONObject jsonSizeList = jsonObject.getJSONObject("data").getJSONObject("sku_info").getJSONObject("sku2_list");
                    //商品数据
                    ProductDetialBean.DataBean.GoodsInfoBean goodsInfoBean = new Gson().fromJson(jsonObject.getJSONObject("data")
                                    .getJSONObject("goods_info").toString(),
                            ProductDetialBean.DataBean.GoodsInfoBean.class);

                    //设置该商品是否下架
                  /*  if (goodsInfoBean.getIs_on_sale() == 0) {
                        mView.showOffShelf();
                    }*/
                    //设置商品名称价格
                    mView.setNamePrice(goodsInfoBean.getGoods_name(),
                            "小喜价:  ¥" + goodsInfoBean.getShop_price(),
                            "网购价:  ¥" + goodsInfoBean.getMarket_price()
                    );
                    //设置分享参数
                    mView.loadShare(jsonObject.getJSONObject("data").getString("wx_share_link"),
                            jsonObject.getJSONObject("data").getString("wx_share_title"),
                            jsonObject.getJSONObject("data").getString("wx_share_desc"),
                            jsonObject.getJSONObject("data").getString("wx_share_img_url")
                    );

                    /*sku数据判断-------<护眼>*/
                    //有颜色的sku
                    if (!TextUtils.equals("{}", jsonColorList.toString())) {
                        SkuListBean skuColorBean = new Gson().
                                fromJson(jsonColorList.toString(),
                                        SkuListBean.class);
                        if (skuColorBean.getList().size() > 0) {
                            //设置color的名字
                            mView.showColorText(skuColorBean.getSku_name());
                            //设置color的Sku_value_id
                            mView.setSkuValue(skuColorBean.getList().get(0).getSku_value_id());
                            //设置color的数据
                            mView.setColorType(skuColorBean.getList(), goodsInfoBean);
                        }
                    }
                    //有尺寸的sku
                    if (!TextUtils.equals("{}", jsonSizeList.toString())) {
                        //解析
                        SkuListBean skuSizeBean = new Gson().
                                fromJson(jsonSizeList.toString(), SkuListBean.class);
                        if (skuSizeBean.getList().size() > 0) {
                            //设置size的名字
                            mView.showSizeText(skuSizeBean.getSku_name());
                            //设置size的Sku_value_id
                            mView.setSkuValue(skuSizeBean.getList().get(0).getSku_value_id());
                            //设置size的数据
                            mView.setSizeType(skuSizeBean.getList(), goodsInfoBean);
                        }

                    }

                    //没有颜色也没有尺寸
                    if ((TextUtils.equals("{}", jsonColorList.toString()) && (TextUtils.equals("{}", jsonSizeList.toString())))) {
                        //隐藏颜色和尺寸
                        mView.hideColorAndSize();
                    }

                    //设置banner
                    JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("goods_pics");
                    for (int x = 0; x < jsonArray.length(); x++) {
                        strings.add(jsonArray.getString(x));
                    }
                    mView.setBanner(strings);
                    //设置收藏
                    mCollect = jsonObject.getJSONObject("data").getBoolean("is_collect");
                    mView.setCollection(mCollect);
                    //设置图文详情
                    mView.loadProductDetial(jsonObject.getJSONObject("data").getString("pic_url"),
                            jsonObject.getJSONObject("data").getString("good_para_url"));

                    //设置是否有活动
                    PromotionBean data = new Gson().fromJson(jsonObject.getJSONObject("data").toString(), PromotionBean.class);
                    List<PromotionBean.DiscountListBean> discount_list = data.getDiscount_list();
                    mView.setPromotion(discount_list);
                } catch (IOException e) {
                    e.printStackTrace();
                    LogUtil.d(TAG, "error3---->" + e);
                    mView.showToast(e.toString());
                    mView.showError();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        //刷新购物车的数量
        loadGoodsCarNum(usId, goodsToken);
    }

    //加入收藏
    @Override
    public void loadCollect(String goodId, String userId, String goodsToken) {
        if (mCollect) {
            JSONArray array = new JSONArray();
            array.put(goodId);
            mApiRest.collectCancle(array.toString(), userId, goodsToken).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
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
                                LogUtil.d(TAG, "取消收藏----->" + string);
                                if (new JSONObject(string).getBoolean("success")) {
                                    mCollect = false;
                                    mView.setCollection(false);
                                    mView.showToast(new JSONObject(string).getString("info"));
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            mApiRest.sendCollect(goodId, userId, goodsToken).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                    subscribe(new Subscriber<ResponseBody>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            LogUtil.d(TAG, "error3---->" + e);
                            // mView.showToast(e.toString());
                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {
                            try {
                                String string = responseBody.string();
                                LogUtil.d(TAG, "收藏------>" + string);
                                if (new JSONObject(string).getBoolean("success")) {
                                    mCollect = true;
                                    mView.setCollection(true);
                                }
                                mView.showToast(new JSONObject(string).getString("info"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }

    @Override
    public void loadSku(String goodId, String skuValue) {
        mApiRest.getSku(goodId, skuValue).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
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
                            Log.d(TAG, "sku颜色点击--------" + string);
                            //获取sku信息
                            JSONObject skuInfoObject = new JSONObject(string).getJSONObject("data").getJSONObject("sku_info");
                            //获取商品信息
                            JSONObject jsonObjectGoodTata = new JSONObject(string).getJSONObject("data").getJSONObject("goods_info");
                            ProductDetialBean.DataBean.GoodsInfoBean goodsInfoBean =
                                    new Gson().fromJson(jsonObjectGoodTata.toString(), ProductDetialBean.DataBean.GoodsInfoBean.class);
                            //设置商品名称价格
                            mView.setNamePrice(goodsInfoBean.getGoods_name(),
                                    "小喜价:  ¥" + goodsInfoBean.getShop_price(),
                                    "网购价  ¥" + goodsInfoBean.getMarket_price()
                            );
                            mView.showSkuPopu(goodsInfoBean.getGoods_thumb_img());
                            //判断颜色是否有数据
                            if (TextUtils.equals("{}", skuInfoObject.getJSONObject("sku2_list").toString())) {
                               /* //设置商品名称价格
                                mView.setNamePrice(goodsInfoBean.getGoods_name(),
                                        "小喜价:  ¥" + goodsInfoBean.getShop_price(),
                                        "网购价" + goodsInfoBean.getMarket_price()
                                );
                                mView.setColorType(new Gson().fromJson(skuInfoObject.getJSONObject("sku1_list").toString(), SkuListBean.class).
                                        getList(), new Gson().fromJson(jsonObjectGoodTata.toString(),
                                        ProductDetialBean.DataBean.GoodsInfoBean.class));*/

                            } else {

                                //设置颜色和尺寸
                                mView.setSizeType(new Gson().fromJson(skuInfoObject.getJSONObject("sku2_list").toString(),
                                        SkuListBean.class).getList()
                                        , new Gson().fromJson(jsonObjectGoodTata.toString(), ProductDetialBean.DataBean.GoodsInfoBean.class));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d(TAG, "error--->" + e);
                        } catch (JSONException e) {
                            Log.d(TAG, "error--->" + e);
                            e.printStackTrace();
                        }
                    }
                });
    }

    //添加到购物车
    @Override
    public void addGoodsCard(String goods_id, String sku_value_id, final String user_id, final String user_token) {
        LogUtil.d(TAG, "goods_id--->" + goods_id);
        LogUtil.d(TAG, "sku_value_id--->" + sku_value_id);
        LogUtil.d(TAG, "user_id--->" + user_id);
        LogUtil.d(TAG, "user_token--->" + user_token);
        mApiRest.addGoodsCar(
                goods_id,
                sku_value_id,
                user_id,
                user_token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
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
                            Log.d(TAG, "string---->" + string);
                            JSONObject jsonObject = new JSONObject(string);
                            if (jsonObject.getBoolean("success")) {
                                loadGoodsCarNum(user_id, user_token);
                                addCarSuccessState();
                            } else {
                                mView.showToast(jsonObject.getString("info"));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    private void addCarSuccessState() {
        mView.showAddCarSuccess();
        mView.showAddShopCarAnim(true);
        final int countTime = 6;//需要的倒计时的时间
        Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).map(new Func1<Long, Integer>() {
            @Override
            public Integer call(Long aLong) {
                LogUtil.d(TAG, aLong + "");
                return countTime - aLong.intValue();
            }
        }).take(countTime + 1).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                LogUtil.d(TAG, "开始计时");
            }
        })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        mView.hideAddCarSuccessPopu();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Integer integer) {
                    }
                });
    }

    @Override
    public void loadSkuSize(String goodId, String skuValue) {
        mApiRest.getSku(goodId, skuValue).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
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
                            Log.d(TAG, "sku尺寸点击--------" + string);
                            //获取商品相关信息
                            JSONObject jsonObjectGoodTata = new JSONObject(string).getJSONObject("data").getJSONObject("goods_info");
                            ProductDetialBean.DataBean.GoodsInfoBean goodsInfoBean =
                                    new Gson().fromJson(jsonObjectGoodTata.toString(), ProductDetialBean.DataBean.GoodsInfoBean.class);
                            //设置商品名称价格
                            mView.setNamePrice(goodsInfoBean.getGoods_name(),
                                    "小喜价:  ¥" + goodsInfoBean.getShop_price(),
                                    "网购价  ¥" + goodsInfoBean.getMarket_price()
                            );
                            //设置展示图片
                            mView.showSkuPopu(goodsInfoBean.getGoods_thumb_img());
                            //获取sku相关信息
                            JSONObject skuInfoObject = new JSONObject(string).getJSONObject("data").getJSONObject("sku_info").getJSONObject("sku2_list");
                            SkuListBean skuListBean = new Gson().fromJson(skuInfoObject.toString(), SkuListBean.class);
                            mView.setSizeType(skuListBean.getList(), goodsInfoBean);

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //获取购物车数量
    @Override
    public void loadGoodsCarNum(String userId, String goodsToken) {
        mApiRest.getGoodsCarNum(userId, goodsToken).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d(TAG, "error2---->" + e);
                        mView.showError();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            LogUtil.d(TAG, "购物车数量--->" + string);
                            JSONObject jsonObject = new JSONObject(string);
                            if (jsonObject.getBoolean("success")) {
                                if (TextUtils.equals("0", jsonObject.getString("data"))) {
                                    mView.setGoodsCarPointShow(false);
                                } else {
                                    mView.setGoodsCarPointShow(true);
                                    mView.setGoodsCarNum(jsonObject.getString("data"));
                                }

                            } else {

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //获取附近的门店信息
    @Override
    public void loadNearNearBy() {
        String lat = CityBean.getLat();
        String lng = CityBean.getLng();
        String regionId = CityBean.getRegionId();
        LogUtil.d(TAG, "lat------>" + lat);
        LogUtil.d(TAG, "lng------>" + lng);
        LogUtil.d(TAG, "regionId------>" + regionId);

        mApiRest.NearbyStores(regionId, lat, lng, "5").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d(TAG, "error4---->" + e);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            LogUtil.d(TAG, "附近门店数据---->" + string);
                            JSONObject jsonObject = new JSONObject(string);
                            if (jsonObject.getBoolean("success")) {
                                JSONArray data = jsonObject.getJSONArray("data");
                                if (data.length() < 1) {
                                    mView.hideStore();
                                } else {
                                    mView.showStore();
                                }
                            }

                           /* LogUtil.d(TAG, "附近的门店------>" + string);*/
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
