package com.xi6666.carWash.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.xi6666.R;
import com.xi6666.car.http.OkHttp;
import com.xi6666.car.http.RequestParams;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.mvp.bean.ALiPayBean;
import com.xi6666.carWash.view.custom.CashierPayView;
import com.xi6666.carWash.view.custom.CashierPayViewTwo;
import com.xi6666.carWash.view.impl.CashierActImpl;
import com.xi6666.carWash.view.mvp.CashierContract;
import com.xi6666.carWash.view.mvp.CashierModel;
import com.xi6666.carWash.view.mvp.CashierPresenter;
import com.xi6666.carWash.view.mvp.bean.CashierBean;
import com.xi6666.carWash.view.mvp.bean.CashierDiscountBean;
import com.xi6666.common.UserData;
import com.xi6666.common.WechatPayUtils;
import com.xi6666.eventbus.WeChatPayEvent;
import com.xi6666.order.activity.RushCarAndServerOrderDetailActivity;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.AppUitls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 * 创建者 sunsun
 * 时间 16/11/3 上午11:43.
 * 个人公众号 ardays
 * 当前是收银台界面
 */

public class CashierAct extends BaseToolbarView<CashierPresenter, CashierModel>
        implements CashierActImpl, CashierPayView.OnCashierPayListener
        , View.OnClickListener, CashierContract.View {

    CashierPayView mSelPayView; //选择支付方式
    CashierPayViewTwo mSelCardView;    //选择洗车卡
    Button mCommitPayBtn;      //确定支付
    CashierBean.DataBean mPayData;  //支付参数

    String mOrderSn;


    RequestParams mWeChatParams;      //微信支付地址
    RequestParams mAliParams;      //微信支付地址
    int mPay = 0;   //0代表微信 1代表支付宝
    boolean isDK = false;   //是否抵扣

    @Override
    public String title() {
        return "收银台";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_cashier;
    }

    @Override
    public void setUp(View view) {
        EventBus.getDefault().register(this);
        initView(view);
        initValue();
        initClick();
        showLoading();
        mPersenter.requestCashierDetails(mOrderSn);
    }


    /**
     * 初始化View
     */
    private void initView(View view) {
        mSelPayView = (CashierPayView) view.findViewById(R.id.cashier_pay_cpv);
        mCommitPayBtn = (Button) view.findViewById(R.id.cashier_pay_commit_pay_btn);
        mSelCardView = (CashierPayViewTwo) view.findViewById(R.id.cashier_card_cpv);
    }

    /**
     * 初始化值
     */
    private void initValue() {
        mOrderSn = getIntent().getStringExtra("order_id");
    }

    /**
     * 点击事件
     */
    private void initClick() {
        mSelPayView.setOnCashierPayListener(this);
        mCommitPayBtn.setOnClickListener(this);
        mSelCardView.setUnSelect(true);

        mSelCardView.setOnCashierItemListener(new CashierPayViewTwo.OnCashierItemListener() {
            @Override
            public void onIteClick(int position, boolean select) {
                String use_status = select ? "0" : "1"; //判断是否选中洗车卡
                showLoading();
                mPersenter.requestSelectDiscount(mOrderSn, use_status, position);
            }
        });


    }

/*                          系统事件                          */

    @Override
    public void setMoeny(String moeny) {
        moeny = Utils.getDoubleMoeny(moeny);

        if (TextUtils.equals(moeny, "0.00")) {
            mCommitPayBtn.setText("完全抵扣");
            isDK = true;
            mSelPayView.setPayClick(false);
        } else {
            mCommitPayBtn.setText("确定支付 ¥" + moeny);
            mSelPayView.setPayClick(true);
            isDK = false;
        }
    }


/*                          点击事件回调                          */

    /**
     * 选择支付方式
     */
    @Override
    public void onPayListener(String type, int id) {
        this.mPay = id;

    }


    @Override
    public void onClick(View v) {
        if (isDK) {
            //如果是完全抵扣走这里
            showLoading();
            mPersenter.requestFullDeduction(mOrderSn);
            return;
        }

        switch (v.getId()) {
            case R.id.cashier_pay_commit_pay_btn:
                switch (this.mPay) {
                    case 0:
                        make("正在打开微信...");
                        if (new AppUitls().isAvilible(getContext(), "com.tencent.mm")) {
                            wechatPay();
                        } else {
                            make("您尚未安装微信应用,请到应用商店进行下载安装!");
                        }
                        break;
                    case 1:
                        make("正在打开支付宝...");
                        if (new AppUitls().isAvilible(getContext(), "com.eg.android.AlipayGphone")) {
                            aliPay();
                        } else {
                            make("您尚未安装支付宝应用,请到应用商店进行下载安装!");
                        }
                        break;
                }
                break;
        }

    }

    @Override
    public void returnCashierDetails(CashierBean bean) {
        hiddenLoading();
        if (bean.success) {

            mPayData = bean.data;
            setMoeny(mPayData.order_amount);

            //微信支付参数
            mWeChatParams = new RequestParams();
            mWeChatParams.setUrl(mPayData.interface_url);
            mWeChatParams.put("out_trade_no", mPayData.out_trade_no);
            mWeChatParams.put("body", mPayData.body);
            mWeChatParams.put("attach", mPayData.attach);
            mWeChatParams.put("total_fee", mPayData.total_fee);

            mAliParams = new RequestParams();
            mAliParams.setUrl(mPayData.zhifubao_url);
            mAliParams.put("out_trade_no", mPayData.out_trade_no);
            mAliParams.put("body", mPayData.body);
            mAliParams.put("attach", mPayData.attach);
            mAliParams.put("total_fee", mPayData.total_fee / 100.00);

            if (!(mPayData.is_used == 1)) {
                //洗车卡
                if (!Utils.getDoubleMoeny(mPayData.can_use_money).equals("0.00")) {//debug
                    //可以抵扣
                    List<com.xi6666.carWash.view.custom.CashierBean> cardBeans = new ArrayList<>();
                    com.xi6666.carWash.view.custom.CashierBean cardBean1 = new com.xi6666.carWash.view.custom.CashierBean();
                    cardBean1.icon = mPayData.washcar_zhekou;   //图标样式
                    cardBean1.card_id = mPayData.washcard_id;        //卡的标号
                    cardBean1.moeny = mPayData.can_use_money;   //金额
                    cardBean1.title = "洗车卡抵扣 ¥" + Utils.getDoubleMoeny(mPayData.can_use_money);
                    cardBean1.isCanUse = true;
                    cardBean1.canUseMoney = "洗车卡可用余额¥" + mPayData.washcard_cash_amount;
                    cardBeans.add(cardBean1);
                    mSelCardView.setItemDatas(cardBeans);
                } else {
                    //无法抵扣
                    List<com.xi6666.carWash.view.custom.CashierBean> cardBeans = new ArrayList<>();
                    com.xi6666.carWash.view.custom.CashierBean cardBean1 = new com.xi6666.carWash.view.custom.CashierBean();
                    cardBean1.icon = mPayData.washcar_zhekou;   //图标样式
                    cardBean1.card_id = mPayData.washcard_id;   //卡的标号
                    cardBean1.moeny = mPayData.can_use_money;   //金额
                    cardBean1.title = "距离下次使用还有" + mPayData.washcard_next_used_time + "天";
                    cardBean1.isCanUse = false;
                    cardBean1.canUseMoney = "洗车卡可用余额¥" + mPayData.washcard_cash_amount;
                    cardBeans.add(cardBean1);
                    mSelCardView.setItemDatas(cardBeans);
                }

            }


        } else {
            make(bean.info);
        }
    }

    @Override
    public void returnDiscountResult(CashierDiscountBean bean, int position) {
        hiddenLoading();
        if (bean.success) {
            if (bean.data.is_selected == 1) {//勾选
                mSelCardView.setSelectPosition(position);
            } else {
                mSelCardView.unSelect();
            }
            mWeChatParams.put("total_fee", bean.data.total_fee);
            mPayData.total_fee = bean.data.total_fee;
            setMoeny(bean.data.order_money);
        } else {
            make(bean.info);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void wechatPayEvent(WeChatPayEvent weChantPayEvent) {
        RushCarAndServerOrderDetailActivity.openActivity(getActivity(), UserData.getUserId(), UserData.getUserToken(), mOrderSn);
        finish();
    }

    @Override
    public void returnFullDeductionResult(BaseBean bean) {
        hiddenLoading();
        hiddenErrorView();
        make(bean.info);
        if (bean.success) {
            RushCarAndServerOrderDetailActivity.openActivity(getActivity(), UserData.getUserId(), UserData.getUserToken(), mOrderSn);
            finish();
        }
    }


    /*              微信支付             */
    private void wechatPay() {
        OkHttp.getStr(mWeChatParams, this, new OkHttp.OkHttpCallBackStr() {
            @Override
            public void onFailure(Exception e) {
                make("网络错误");
            }

            @Override
            public void onResponse(String bean) {
                new WechatPayUtils(getContext()).tuneUpWechatPay(bean);
            }
        });
    }

    /*                  支付宝                    */
    private void aliPay() {
        OkHttp.get(mAliParams, ALiPayBean.class, this, new OkHttp.OkHttpCallBack<ALiPayBean>() {
            @Override
            public void onFailure(Exception e) {
                make("网络异常");
            }

            @Override
            public void onResponse(ALiPayBean bean) {
                if (bean.success) {
                    //开启线程去支付
                    Runnable payRunnable = new Runnable() {

                        @Override
                        public void run() {
                            // 构造PayTask 对象
                            PayTask alipay = new PayTask(getActivity());
                            // 调用支付接口，获取支付结果
                            String result = alipay.pay(bean.zhifubao, true);

                            Message msg = new Message();
                            mAliPayHandler.sendMessage(msg);
                        }
                    };

                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                } else {
                    make(bean.info);
                }
            }
        });
//        new AlipayUtils(this).pay(mPayData.body, mPayData.out_trade_no, mPayData.total_fee / 100.00 + "", mPayData.zhifubao_url, mAliPayHandler);
    }

    //阿里handler
    Handler mAliPayHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RushCarAndServerOrderDetailActivity.openActivity(getActivity(), UserData.getUserId(), UserData.getUserToken(), mOrderSn);
            finish();
        }
    };


    @Override
    public void returnFullDeductionError() {
        hiddenLoading();
        make("网络异常，抵扣失败");
    }

    @Override
    public void returnNetError() {
        showErrorView();
        errorClick(v -> {
            showLoading();
            mPersenter.requestCashierDetails(mOrderSn);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    //    @Deprecated


    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity, String order_id) {
        Intent intent = new Intent(activity, CashierAct.class);
        intent.putExtra("order_id", order_id);
        activity.startActivity(intent);
    }

}
