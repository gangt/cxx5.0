package com.xi6666.illegal.other;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.xi6666.common.WechatPayUtils;
import com.xi6666.databean.IllegaPayBean;
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
 * Created by Mr_yang on 2017/2/8.
 */

public class IllegaSurePayPresenter extends IllegaSurePayContract.Presenter {

    private static final String TAG = "IllegaSurePayPresenter";
    private String mOrder_sn;
    private Context mContext;

    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public void onAttached() {

    }

    private Handler mHandler;

    public void setHandler(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void creatOrder(String oderId) {
        mRxManager.add(mModel.creatOrder(oderId).subscribe(new Subscriber<ResponseBody>() {
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
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        String order_total = jsonObject.getJSONObject("data").getString("order_total");
                        mOrder_sn = jsonObject.getJSONObject("data").getString("order_sn");
                        String order_type = jsonObject.getJSONObject("data").getString("order_type");
                        mView.setOrderInfo(order_total, order_type, order_type);
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
    public void changePayType(String userId, String payId) {
        mRxManager.add(mModel.changePayType(userId, payId, mOrder_sn).subscribe(new Subscriber<ResponseBody>() {
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
                    if (jsonObject.getBoolean("success")) {
                        createPayInfo(userId, payId);
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
    public void createPayInfo(String userId, String payId) {
        mRxManager.add(mModel.createPayInfo(userId, payId, mOrder_sn).subscribe(new Subscriber<IllegaPayBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(IllegaPayBean illegaPayBean) {
                if (illegaPayBean.isSuccess()) {
                    //微信支付
                    if (TextUtils.equals("5", payId)) {
                        mRxManager.add(mModel.getPayParam(illegaPayBean.getInterface_url(),
                                illegaPayBean.getData().getAttach(),
                                illegaPayBean.getData().getBody(),
                                illegaPayBean.getData().getOut_trade_no(),
                                illegaPayBean.getData().getPay_id() + "",
                                illegaPayBean.getData().getPay_name(),
                                illegaPayBean.getData().getTotal_fee() + "").subscribe(new Subscriber<ResponseBody>() {
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
                                    new WechatPayUtils(mContext).tuneUpWechatPay(string);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }));
                    }
                    //支付宝支付
                    if (TextUtils.equals("4", payId)) {
                        if (new AppUitls().isAvilible(mContext, "com.eg.android.AlipayGphone")) {
                            mRxManager.add(mModel.getPayParam(illegaPayBean.getZhifubao_url(),
                                    illegaPayBean.getData().getAttach(),
                                    illegaPayBean.getData().getBody(),
                                    illegaPayBean.getData().getOut_trade_no(),
                                    illegaPayBean.getData().getPay_id() + "",
                                    illegaPayBean.getData().getPay_name(),
                                    illegaPayBean.getData().getTotal_fee() + "").subscribe(new Subscriber<ResponseBody>() {
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
                                        LogUtil.d(TAG, "支付宝支付参数-------->" + string);
                                        JSONObject jsonObject = new JSONObject(string);

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


                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }));
                        } else {
                            mView.showToast("检测到您尚未安装支付宝,请下载安装!");
                        }
                    }
                } else {
                    mView.showToast(illegaPayBean.getInfo());
                }
            }
        }));
    }
}
