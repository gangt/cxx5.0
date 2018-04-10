package com.xi6666.addoil.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.xi6666.addoil.contract.AddOilPayContract;
import com.xi6666.common.UserData;
import com.xi6666.common.WechatPayUtils;
import com.xi6666.databean.AddOilPayTypeBean;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.AppUitls;
import com.xi6666.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/12/3.
 */

public class AddOilPayPresenterImpl implements AddOilPayContract.Presenter {

    private AddOilPayContract.View mView;
    private ApiRest mApiRest;
    public static final String TAG = "AddOilPayPresenter";
    private Context mContext;

    private Handler mHandler;
    private String mOrder_sn;

    public void setOrder_sn(String order_sn) {
        mOrder_sn = order_sn;
    }

    public void setHandler(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void attachView(AddOilPayContract.View view) {
        this.mView = view;
    }

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    //生成订单参数
    @Override
    public void createOrderInfo(String package_id, String package_cash, String user_id, String user_token) {
        mApiRest.addOilPayInfo(package_id, package_cash, user_id, user_token).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
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
                    JSONObject jsonObject = new JSONObject(string);
                    mView.setData("每月加油:         " + jsonObject.getString("package_cash") + "元",
                            "折扣选择:         " + jsonObject.getString("package_info")
                            , "节        省:         " + jsonObject.getInt("package_support") + "元",
                            "应        付:         " + jsonObject.getInt("package_amount") + "元");

                    if (jsonObject.getInt("counpon_num") == 0) {
                        mView.hasCoupon(false, "暂无券可用");
                    } else {
                        mView.hasCoupon(true, "有券可用");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //生成支付订单
    @Override
    public void createOrder(String card_id, String package_cash,
                            String package_id, String pay_id, String couponId) {

        if (TextUtils.isEmpty(mOrder_sn)) {
            mApiRest.createAddOilOrder(card_id, package_cash, package_id, pay_id, UserData.getUserId(),
                    UserData.getUserToken()).subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {


                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    LogUtil.d(TAG, "error" + e);
                }

                @Override
                public void onNext(ResponseBody responseBody) {
                    try {
                        String string = responseBody.string();
                        LogUtil.d(TAG, "生成订单--->" + string);
                        JSONObject jsonObject = new JSONObject(string);
                        if (jsonObject.getBoolean("success")) {
                            mOrder_sn = jsonObject.getString("order_sn");
                            changePaytype(mOrder_sn, pay_id, UserData.getUserId(), UserData.getUserToken(), couponId);
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
        } else {

            toPay(pay_id);
        }


    }

    //修改支付方式
    @Override
    public void changePaytype(String order, String payType, String userId, String userToken, String CouPonId) {

        mApiRest.modifyOilPayType(mOrder_sn, payType, userId, userToken, CouPonId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
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
                    JSONObject json = new JSONObject(string);
                    if (json.getBoolean("success")) {
                        JSONObject jsonObject = json.getJSONObject("data");
                        mView.setCouPonData("-" + jsonObject.getInt("coipon_money") + "元", "节        省:         " + jsonObject.getInt("save_money") + "元",
                                "应        付:         " + jsonObject.getInt("order_money") + "元");
                        mOrder_sn = jsonObject.getString("order_sn");
                    } else {
                        mView.showToast(json.getString("info"));
                        mOrder_sn = json.getString("data");
                        mView.clearCouPonId();
                    }
                    toPay(payType);


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //去支付
    @Override
    public void toPay(String payType) {
        mApiRest.getOilPayOrder(mOrder_sn, payType, UserData.getUserId(),
                UserData.getUserToken()).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<AddOilPayTypeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d(TAG, "error2--->" + e);
                    }

                    @Override
                    public void onNext(AddOilPayTypeBean addOilPayTypeBean) {
                        if (TextUtils.equals("true", addOilPayTypeBean.getSuccess())) {
                            //判断是微信支付
                            if (TextUtils.equals("5", payType)) {
                                mApiRest.createOilOrder(addOilPayTypeBean.getInterface_url(),
                                        addOilPayTypeBean.getData().getAttach(),
                                        addOilPayTypeBean.getData().getBody(),
                                        addOilPayTypeBean.getData().getOut_trade_no(),
                                        addOilPayTypeBean.getData().getPay_id() + "",
                                        addOilPayTypeBean.getData().getPay_name(),
                                        addOilPayTypeBean.getData().getTotal_fee() + ""
                                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
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
                                                    LogUtil.d(TAG, "支付数据---->" + string);
                                                    JSONObject jsonObject = new JSONObject(string);
                                                    new WechatPayUtils(mContext).tuneUpWechatPay(string);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                            }
                            //支付宝支付
                            if (TextUtils.equals("4", payType)) {

                                if (new AppUitls().isAvilible(mContext, "com.eg.android.AlipayGphone")) {
                                    mApiRest.createOilOrder(addOilPayTypeBean.getZhifubao_url(),
                                            addOilPayTypeBean.getData().getAttach(),
                                            addOilPayTypeBean.getData().getBody(),
                                            addOilPayTypeBean.getData().getOut_trade_no(),
                                            addOilPayTypeBean.getData().getPay_id() + "",
                                            addOilPayTypeBean.getData().getPay_name(),
                                            addOilPayTypeBean.getData().getTotal_fee() + ""
                                    ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            LogUtil.d(TAG, "error3--->" + e);
                                        }

                                        @Override
                                        public void onNext(ResponseBody responseBody) {
                                            try {
                                                String string = responseBody.string();
                                                LogUtil.d(TAG, "支付宝支付参数-------->" + string);
                                                JSONObject jsonObject = new JSONObject(string);
                                                if (jsonObject.getBoolean("success")) {
                                                    Runnable payRunnable = new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            // 构造PayTask 对象
                                                            PayTask alipay = new PayTask((Activity) mContext);
                                                            // 调用支付接口，获取支付结果
                                                            String result = null;
                                                            try {
                                                                result = alipay.pay(jsonObject.getString("zhifubao"), true);
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }

                                                            Message msg = new Message();
                                                            msg.what = 1;
                                                            msg.obj = result;
                                                            mHandler.sendMessage(msg);
                                                        }
                                                    };
                                                    // 必须异步调用
                                                    Thread payThread = new Thread(payRunnable);
                                                    payThread.start();
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


                                } else {
                                    mView.showToast("检测到您尚未安装支付宝,请下载安装!");
                                }

                            }
                        } else {
                            mView.showToast(addOilPayTypeBean.getInfo());
                        }
                    }
                });
    }

    @Override
    public void loadCouponData(String card_id, String package_cash, String package_id, String pay_id, String couponId) {
        mApiRest.createAddOilOrder(card_id, package_cash, package_id, pay_id, UserData.getUserId(),
                UserData.getUserToken()).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, "error" + e);
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    LogUtil.d(TAG, "生成订单--->" + string);
                    JSONObject jsonObject = new JSONObject(string);
                    if (jsonObject.getBoolean("success")) {
                        mOrder_sn = jsonObject.getString("order_sn");
                        mApiRest.modifyOilPayType(mOrder_sn, pay_id, UserData.getUserId(), UserData.getUserToken(), couponId).
                                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
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
                                            JSONObject json = new JSONObject(string);
                                            if (json.getBoolean("success")) {
                                                JSONObject jsonObject = json.getJSONObject("data");
                                                mView.setCouPonData("-" + jsonObject.getInt("coipon_money") + "元",
                                                        "节        省:         " + jsonObject.getInt("save_money") + "元",
                                                        "应        付:         " + jsonObject.getInt("order_money") + "元");
                                                mOrder_sn = jsonObject.getString("order_sn");
                                            } else {
                                                mView.showToast(json.getString("info"));
                                                mOrder_sn = json.getString("data");
                                                mView.clearCouPonId();
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });


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
}
