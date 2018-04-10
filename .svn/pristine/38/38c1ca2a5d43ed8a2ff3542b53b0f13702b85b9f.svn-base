package com.xi6666.carWash.view.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.xi6666.carWash.view.CarWashPayAct;
import com.xi6666.common.AlipayUtils;
import com.xi6666.common.UserData;
import com.xi6666.common.WechatPayUtils;
import com.xi6666.databean.AddOilPayTypeBean;
import com.xi6666.databean.CarWashPayData;
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
 * Created by Mr_yang on 2016/12/1.
 */

public class CarWashPresenter {
    private static final String TAG = "CarWashPresenter";
    private CarWashPayAct mCarWashPayAct;
    private ApiRest mApiRest;
    private Context mContext;
    private Handler mHandler;

    public void setHandler(Handler handler) {
        mHandler = handler;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void setCarWashPayAct(CarWashPayAct carWashPayAct) {
        mCarWashPayAct = carWashPayAct;
    }

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;
    }

    public void creatData(String pack) {
        mApiRest.creatOrder(pack, UserData.getUserId(), UserData.getUserToken()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
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
                            Log.d(TAG, "生成订单--->" + string);
                            JSONObject jsonObject = new JSONObject(string);
                            if (jsonObject.getBoolean("success")) {

                                loadData(jsonObject.getJSONObject("data").getString("order_sn"));
                            } else {
                                mCarWashPayAct.showToast(jsonObject.getString("info"));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void loadData(String order_sn) {
        Log.d(TAG, "order_sn--->" + order_sn);
        mApiRest.getCleanCar(order_sn, UserData.getUserId(), UserData.getUserToken()).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
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
                            CarWashPayData carWashPayData = new Gson().fromJson(string, CarWashPayData.class);

                            mCarWashPayAct.addData(carWashPayData.getData());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    public void wechatPay(String order_sn, String pay_id) {
        mApiRest.changePayType(order_sn, pay_id, UserData.getUserId(),
                UserData.getUserToken()).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        mApiRest.getCleanCarOrder(order_sn, pay_id, UserData.getUserId(),
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
                                            if (TextUtils.equals("5", pay_id)) {
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
                                                                    //      JSONObject jsonObject = new JSONObject(string);
                                                                    new WechatPayUtils(mContext).tuneUpWechatPay(string);
                                                                } catch (IOException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                        });
                                            }
                                            //支付宝支付
                                            if (TextUtils.equals("4", pay_id)) {

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
                                                                LogUtil.d(TAG, "支付宝支付数据---->" + string);
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
                                                                    mCarWashPayAct.showToast(jsonObject.getString("info"));
                                                                }
                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    });
                                                } else {
                                                    mCarWashPayAct.showToast("检测到您尚未安装支付宝,请下载安装!");
                                                }


                                            }
                                        } else {
                                            mCarWashPayAct.showToast(addOilPayTypeBean.getInfo());
                                        }
                                    }
                                });
                    }
                });
    }
}
