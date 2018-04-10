package com.xi6666.seckill.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.xi6666.databean.ProductDetialBean;
import com.xi6666.databean.SkuListBean;

import com.xi6666.seckill.contract.SecKillContract;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.TimeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Subscriber;


/**
 * Created by Mr_yang on 2017/1/16.
 */

public class SecKillPresenter extends SecKillContract.Persenter {

    private static final String TAG = "SecKillPresenter";
    private List<String> strings = new ArrayList<>();

    @Override
    public void onAttached() {

    }

    @Override
    public void loadData(String goodId, String userId, String goodsToken) {
        mRxManager.add(mModel.getProduct(goodId, userId, goodsToken).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
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

                    //设置商品id
                    mView.setProductId(goodsInfoBean.getGoods_id());

                    //设置该商品是否下架
                    if (goodsInfoBean.getIs_on_sale() == 2) {
                        mView.showOffShelf();
                    }
                    //设置商品名称价格
                    mView.setNamePrice(goodsInfoBean.getGoods_name(),
                            "秒杀价:  ¥" + goodsInfoBean.getGoods_seckill_price(),
                            "惊喜价:  ¥" + goodsInfoBean.getGoods_price()
                            , "原价:  ¥" + goodsInfoBean.getMarket_price());
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

                    //设置图文详情
                    mView.loadProductDetial(jsonObject.getJSONObject("data").getString("pic_url"),
                            jsonObject.getJSONObject("data").getString("good_para_url"));

                    //设置倒计时
                    String nowTime = jsonObject.getJSONObject("data").getString("now_time");
                    String seckill_datetime = jsonObject.getJSONObject("data").getString("seckill_datetime");
                    //判断
                    if (Long.parseLong(nowTime) * 1000 < Long.parseLong(seckill_datetime) * 1000) {
                        Long[] distanceTime = TimeUtils.getDistanceTime(Long.parseLong(nowTime) * 1000,
                                Long.parseLong(seckill_datetime) * 1000);
                        mView.setTime(0, distanceTime[1], distanceTime[2], distanceTime[3], "距离开始");
                    } else {
                        mView.setTime(0, 0, 0, 0, "正在秒杀");
                    }
                   /* //  mView.setTime(0, 0, 0, 0, "正在秒杀");
                    Long[] distanceTime = TimeUtils.getDistanceTime(1484787620 * 1000,
                            1484787625 * 1000);
                    mView.setTime(0, distanceTime[1], distanceTime[2], distanceTime[3], "距离开始");*/


                } catch (IOException e) {
                    e.printStackTrace();
                    LogUtil.d(TAG, "error3---->" + e);
                    mView.showToast(e.toString());
                    mView.showError();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }));
    }


    @Override
    public void loadSku(String goodId, String skuValue) {
        mRxManager.add(mModel.getSku(goodId, skuValue).subscribe(responseBody -> {
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
                        , "");
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
        }));


    }

    //添加到购物车
    @Override
    public void addGoodsCard(String goods_id, String sku_value_id, final String user_id, final String user_token) {
        mRxManager.add(mModel.addShopCar(goods_id, sku_value_id, user_id,
                user_token).subscribe(new Subscriber<ResponseBody>() {
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
                        mView.orderPay();
                    } else {
                        mView.showToast(jsonObject.getString("info"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }));


    }

    @Override
    public void loadSkuSize(String goodId, String skuValue) {
        mRxManager.add(mModel.getSku(goodId, skuValue).subscribe(responseBody -> {
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
                        , "");
                mView.showSkuPopu(goodsInfoBean.getGoods_thumb_img());
                //判断颜色是否有数据
                if (TextUtils.equals("{}", skuInfoObject.getJSONObject("sku2_list").toString())) {
                    //设置商品名称价格
                    mView.setNamePrice(goodsInfoBean.getGoods_name(),
                            "小喜价:  ¥" + goodsInfoBean.getShop_price(),
                            "网购价" + goodsInfoBean.getMarket_price()
                            , "");
                    mView.setColorType(new Gson().fromJson(skuInfoObject.getJSONObject("sku1_list").toString(), SkuListBean.class).
                            getList(), new Gson().fromJson(jsonObjectGoodTata.toString(),
                            ProductDetialBean.DataBean.GoodsInfoBean.class));

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
        }));
    }

    @Override
    public void signUp(String user_id, String user_token, String get_device_type, String seckill_id) {
        mRxManager.add(mModel.secKillSingUp(user_id, user_token, get_device_type, seckill_id).subscribe(responseBody -> {
            try {
                String string = responseBody.string();
                JSONObject jsonObject = new JSONObject(string);
                mView.showToast(jsonObject.getString("info"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }));
    }

    @Override
    public void secKillSure(String user_id, String user_token, String get_device_type, String seckill_id) {
        mRxManager.add(mModel.secKillSure(user_id, user_token, get_device_type, seckill_id).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showToast("服务端错误");
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    JSONObject jsonObject = new JSONObject(string);
                    boolean success = jsonObject.getBoolean("success");
                    mView.goToSecKill(success);
                    mView.showToast(jsonObject.getString("info"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    @Override
    public void loadNearNearBy() {
        mRxManager.add(mModel.NearbyStores().subscribe(new Subscriber<ResponseBody>() {
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
                    LogUtil.d(TAG, "附近的门店------>" + string);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }));


    }
}
