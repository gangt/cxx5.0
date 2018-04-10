package com.xi6666.order.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.xi6666.R;
import com.xi6666.alipay.PayResult;
import com.xi6666.common.UserData;
import com.xi6666.common.WechatPayUtils;
import com.xi6666.eventbus.WeChatPayEvent;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.main.view.MainAct;
import com.xi6666.order.bean.CashBean;
import com.xi6666.order.other.ButtonUtils;
import com.xi6666.order.other.DialogOnClickListener;
import com.xi6666.order.other.NormalAlertDialog;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.AppUitls;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CashDeskActivity extends AppCompatActivity {

    @BindView(R.id.cd_toolbar_left_iv)
    ImageView      mCdToolbarLeftIv;
    @BindView(R.id.cd_toolbar)
    Toolbar        mCdToolbar;
    @BindView(R.id.cd_pay_ali_cb)
    CheckBox       mCdPayAliCb;
    @BindView(R.id.pay_ali_ll)
    RelativeLayout mPayAliLl;
    @BindView(R.id.cd_pay_wechat_cb)
    CheckBox       mCdPayWechatCb;
    @BindView(R.id.pay_wechat_ll)
    RelativeLayout mPayWechatLl;
    @BindView(R.id.cd_select_pay_type)
    RelativeLayout mCdSelectPayType;
    @BindView(R.id.cd_confirm_pay_btn)
    Button         mCdConfirmPayBtn;

    private double money;
    private String order_money;
    private String log_id;
    private String pay_id = "5";
    private String        order_sn;
    private String        order_id;
    private NumberFormat  nFormat;
    private DecimalFormat df;
    private CashBean      mCashBean;
    private Dialog        mLoading;
    private String        user_id;
    private String        user_token;

    private boolean isPaying;

    private static final int SDK_PAY_FLAG = 1;

    private static NormalAlertDialog dialog;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(CashDeskActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CashDeskActivity.this, PaySuccessActivity.class);
                        intent.putExtra("order_sn", order_sn);
                        startActivity(intent);
                        finish();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(CashDeskActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(CashDeskActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CashDeskActivity.this, PayFailedActivity.class);
                            startActivity(intent);
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_desk);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        setSupportActionBar(mCdToolbar);
        order_sn = getIntent().getStringExtra("order_sn");
        order_id = getIntent().getStringExtra("order_id");
        user_id = UserData.getUserId();
        user_token = UserData.getUserToken();
        mLoading = ShowDialogUitls.showDio(this);
        if ("4".equals(pay_id)) {
            mCdPayAliCb.setChecked(true);
            mCdPayWechatCb.setChecked(false);
        } else if ("5".equals(pay_id)) {
            mCdPayAliCb.setChecked(false);
            mCdPayWechatCb.setChecked(true);
        } else {
            mCdPayAliCb.setChecked(false);
            mCdPayWechatCb.setChecked(true);
        }
        initData();
        nFormat = NumberFormat.getNumberInstance();
        nFormat.setMaximumFractionDigits(2);
        df = new DecimalFormat("#####0.00");
    }

    private void initData() {
        mLoading.show();
        new Retrofit.Builder().baseUrl(NetAddress.baseUrl).build().create(NetAddress.class).cash(order_sn).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (mLoading.isShowing())
                    mLoading.dismiss();
                try {
                    String result = response.body().string();
                    LogUtil.i("CashDeskActivity", result);
                    mCashBean = GsonUtils.toEntityFromJson(result, CashBean.class);
                    if (mCashBean.isSuccess()) {
                        order_money = mCashBean.getData().getOrder_money();
                        mCdConfirmPayBtn.setText("确认支付 ¥ " + order_money);
                    } else {
                        Utils.make(CashDeskActivity.this, mCashBean.getInfo());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mLoading.isShowing())
                    mLoading.dismiss();
                Utils.make(CashDeskActivity.this, "请求失败");
            }
        });
    }

    @OnClick({R.id.cd_toolbar_left_iv, R.id.pay_ali_ll, R.id.pay_wechat_ll, R.id.cd_confirm_pay_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cd_toolbar_left_iv:
                startActivity(new Intent(CashDeskActivity.this, PayFailedActivity.class));
                break;
            case R.id.pay_ali_ll:
                pay_id = "4";
                if (!mCdPayAliCb.isChecked()) {
                    mCdPayAliCb.setChecked(true);
                    mCdPayWechatCb.setChecked(false);
                }
                break;
            case R.id.pay_wechat_ll:
                pay_id = "5";
                if (!mCdPayWechatCb.isChecked()) {
                    mCdPayWechatCb.setChecked(true);
                    mCdPayAliCb.setChecked(false);
                }
                break;
            case R.id.cd_confirm_pay_btn:
                //  给button设置需要支付的金额
                mCdConfirmPayBtn.setText("确认支付 ¥ " + order_money);
                if (ButtonUtils.isFastClick(5000)) {
                    return;
                }
                if (mCdPayAliCb.isChecked()) {
                    pay_id = "4";
                }
                if (mCdPayWechatCb.isChecked()) {
                    pay_id = "5";
                }
                LogUtil.i("pay_id", pay_id);
                String pay_url;
                if ("5".equals(pay_id)) {
                    pay_url = mCashBean.getData().getInterface_url() +
                            "?attach=" + mCashBean.getData().getAttach() +
                            "&body=" + mCashBean.getData().getBody() +
                            "&out_trade_no=" + mCashBean.getData().getOut_trade_no() +
                            "&total_fee=" + mCashBean.getData().getTotal_fee() +
                            "&user_id=" + user_id +
                            "&user_token=" + user_token;
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request.Builder builder = new Request.Builder();
                    Request.Builder url = builder.url(pay_url);
                    Request build = url.build();
                    LogUtil.i("pay_url", "pay_url = " + pay_url);
                    okHttpClient.newCall(build).enqueue(new okhttp3.Callback() {
                        @Override
                        public void onFailure(okhttp3.Call call, IOException e) {
                        }

                        @Override
                        public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                            final String json = response.body().string();
                            LogUtil.i("pay", "支付的参数是==" + json);
                            runOnUiThread(() -> {
                                if ("5".equals(pay_id)) {
                                    boolean avilible_wx = AppUitls.isAvilible(CashDeskActivity.this, "com.tencent.mm");
                                    if (avilible_wx) {
                                        //微信支付
                                        Utils.make(CashDeskActivity.this, "正在为您打开微信支付，请稍等...");
                                        new WechatPayUtils(CashDeskActivity.this).tuneUpWechatPay(json);
                                    } else {
                                        Toast.makeText(CashDeskActivity.this, "您尚未安装微信应用,请到应用商店进行下载安装!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
                } else {
                    Utils.make(CashDeskActivity.this,"请稍等...");
                    pay_url = mCashBean.getData().getZhifubao_url() +
                            "?attach=" + mCashBean.getData().getAttach() +
                            "&body=" + mCashBean.getData().getBody() +
                            "&out_trade_no=" + mCashBean.getData().getOut_trade_no() +
                            "&total_fee=" + order_money +
                            "&user_id=" + user_id +
                            "&user_token=" + user_token;
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request.Builder builder = new Request.Builder();
                    Request.Builder url = builder.url(pay_url);
                    Request build = url.build();
                    LogUtil.i("pay_url", "pay_url = " + pay_url);
                    okHttpClient.newCall(build).enqueue(new okhttp3.Callback() {
                        @Override
                        public void onFailure(okhttp3.Call call, IOException e) {
                        }

                        @Override
                        public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                            final String json = response.body().string();
                            LogUtil.i("pay", "支付宝支付的参数是==" + json);
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                if (jsonObject.optBoolean("success")) {
                                    String zhifubao = jsonObject.optString("zhifubao");
                                    //支付宝支付
                                    //判断是否安装支付宝
                                    boolean avilible_zfb = AppUitls.isAvilible(CashDeskActivity.this, "com.eg.android.AlipayGphone");
                                    if (avilible_zfb) {
                                        //开启线程去支付
                                        Runnable payRunnable = new Runnable() {

                                            @Override
                                            public void run() {
                                                // 构造PayTask 对象
                                                PayTask alipay = new PayTask(CashDeskActivity.this);
                                                // 调用支付接口，获取支付结果
                                                String result = alipay.pay(zhifubao, true);

                                                Message msg = new Message();
                                                msg.what = SDK_PAY_FLAG;
                                                msg.obj = result;
                                                mHandler.sendMessage(msg);
                                            }
                                        };

                                        // 必须异步调用
                                        Thread payThread = new Thread(payRunnable);
                                        payThread.start();
                                    } else {
                                        Toast.makeText(CashDeskActivity.this, "您尚未安装支付宝应用,请到应用商店进行下载安装!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
                break;
        }
    }

    //微信支付返回的event事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void WeChatPayRes(WeChatPayEvent weChatPayEvent) {
        int msg = weChatPayEvent.getMsg();
        if (msg != 0) {
            startActivity(new Intent(CashDeskActivity.this,PayFailedActivity.class));
        } else {
            Intent intent = new Intent(CashDeskActivity.this, PaySuccessActivity.class);
            intent.putExtra("order_sn", order_sn);
            startActivity(intent);
            finish();
        }
    }

    /**
     * 商品库存不足时显示的dialog
     *
     * @param context 上下文
     */
    public void showCenterDialog(Context context) {
        dialog = new NormalAlertDialog.Builder(context)
                .setHeight(0.23f)  //屏幕高度*0.23
                .setWidth(0.65f)  //屏幕宽度*0.65
                .setTitleVisible(false)
                .setTitleText("")
                .setTitleTextColor(R.color.black_text)
                .setContentText("商品库存不足")
                .setContentTextColor(R.color.black_text)
                .setSingleMode(false)
                .setCanceledOnTouchOutside(true)
                .setLeftButtonText("留在此页")
                .setLeftButtonTextColor(R.color.gray_text)
                .setRightButtonText("返回首页")
                .setRightButtonTextColor(R.color.red_text)
                .setOnclickListener(new DialogOnClickListener() {
                    @Override
                    public void clickLeftButton(View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(View view) {
                        // 跳转到首页
                        CashDeskActivity.this.startActivity(new Intent(CashDeskActivity.this, MainAct.class));
                    }
                })
                .build();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CashDeskActivity.this, PayFailedActivity.class));
    }
}
