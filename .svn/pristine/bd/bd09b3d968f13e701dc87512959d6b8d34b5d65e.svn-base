package com.xi6666.addoil.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;

import com.xi6666.addoil.contract.AddOilPayContract;
import com.xi6666.addoil.di.DaggerAddOilPayComponent;
import com.xi6666.addoil.presenter.AddOilPayPresenterImpl;
import com.xi6666.alipay.PayResult;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ToolBarBaseAct;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.common.BuriedPoint;
import com.xi6666.common.UserData;
import com.xi6666.eventbus.WeChatPayEvent;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.network.ApiRest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.xi6666.app.BaseApplication.getApplication;


public class AddOilPayAct extends ToolBarBaseAct implements
        AddOilPayContract.View {
    private static final String TAG = "AddOilPayAct";
    private static final int SDK_PAY_FLAG = 1;
    private static final int COUPON = 2;
    @BindView(R.id.txt_every_month)
    TextView mMonth;

    @BindView(R.id.txt_discount)
    TextView mDiscount;

    @BindView(R.id.txt_save_money)
    TextView mSaveMoney;

    @BindView(R.id.txt_should_pay)
    TextView mShouldPay;
    @BindView(R.id.surepay_btn_pay)
    Button mSurepayBtnPay;
    @BindView(R.id.surepay_cb_checked)
    CheckBox mSurepayCbChecked;
    @BindView(R.id.surepay_iv_read)
    TextView mSurepayIvRead;
    @BindView(R.id.surepay_tv_user)
    TextView mSurepayTvUser;
    @BindView(R.id.layout_pay_detial)
    RelativeLayout mLayoutPayDetial;
    @BindView(R.id.txt_pay_type)
    TextView mTxtPayType;
    @BindView(R.id.surepay_iv_wechatpay)
    ImageView mSurepayIvWechatpay;
    @BindView(R.id.surepay_tv_wechatpay)
    TextView mSurepayTvWechatpay;
    @BindView(R.id.surepay_rb_wechatpay)
    RadioButton mSurepayRbWechatpay;
    @BindView(R.id.surepay_pr_wechatpay)
    RelativeLayout mSurepayPrWechatpay;
    @BindView(R.id.surepay_iv_alipay)
    ImageView mSurepayIvAlipay;
    @BindView(R.id.surepay_tv_alipay)
    TextView mSurepayTvAlipay;
    @BindView(R.id.surepay_rb_alipay)
    RadioButton mSurepayRbAlipay;
    @BindView(R.id.txt_discount_save_money)
    TextView mTxtDisCountSave;
    @BindView(R.id.txt_discount_content)
    TextView mTextViewDisContent;

    @BindView(R.id.surepay_pr_alipay)
    RelativeLayout mSurepayPrAlipay;
    @Inject
    ApiRest mApiRest;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
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
                        // Toast.makeText(AddOilPayAct.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddOilPayAct.this, AddOilPayResultAct.class);
                        intent.putExtra("result", true);
                        intent.putExtra("card", mCardId);
                        startActivity(intent);

                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(AddOilPayAct.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Intent intent2 = new Intent(AddOilPayAct.this, AddOilPayResultAct.class);
                            intent2.putExtra("result", false);
                            startActivity(intent2);
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
    private String mChargeType;
    private int mMoney;
    private String mCardId;
    private AddOilPayPresenterImpl mAddOilPayPresenter;
    private String mCouponId = "";

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void payResultData(WeChatPayEvent weChatPayEvent) {
        int msg = weChatPayEvent.getMsg();
        switch (msg) {
            case 0:
                Intent intent = new Intent(AddOilPayAct.this, AddOilPayResultAct.class);
                intent.putExtra("result", true);
                intent.putExtra("card", mCardId);
                startActivity(intent);
                break;
            case -1:
                Intent intent2 = new Intent(AddOilPayAct.this, AddOilPayResultAct.class);
                intent2.putExtra("result", false);
                startActivity(intent2);
                break;
            case -2:
                Intent intent3 = new Intent(AddOilPayAct.this, AddOilPayResultAct.class);
                intent3.putExtra("result", false);
                startActivity(intent3);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_oil_pay);
        EventBus.getDefault().register(this);
        init();
        //注入
        DaggerAddOilPayComponent.builder().apiModule(new ApiModule((BaseApplication)
                getApplication())).build().Inject(this);
        //设置presenter
        mAddOilPayPresenter = new AddOilPayPresenterImpl();
        mAddOilPayPresenter.attachView(this);
        mAddOilPayPresenter.setHandler(mHandler);
        mAddOilPayPresenter.setApiRest(mApiRest);
        mAddOilPayPresenter.setContext(this);
        //获取订单信息
        mAddOilPayPresenter.createOrderInfo(mChargeType, mMoney + "",
                UserData.getUserId(), UserData.getUserToken());
        mSurepayRbWechatpay.setClickable(false);
        mSurepayRbAlipay.setClickable(false);

    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "支付";
    }

    /**
     * @data 创建时间:2016/8/27
     * @author peng
     * @desc
     * @version
     */
    private void init() {
        //获取上个界面传来的金额等相关信息
        Intent intent = getIntent();
        //获取支付的相关参数
        mMoney = intent.getIntExtra("money", 0);
        mChargeType = intent.getStringExtra("chargeType");
        mCardId = intent.getStringExtra("cardId");
        //设置默认选择的单选框
        mSurepayRbWechatpay.setChecked(true);
        mSurepayCbChecked.setChecked(true);
        //数据埋点
        mSurepayCbChecked.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sendToServer("yhxy", "yhxy" + 1);
            } else {
                sendToServer("yhxy", "yhxy" + 2);
            }
        });
    }

    @OnClick({R.id.surepay_pr_alipay, R.id.surepay_pr_wechatpay,
           /* R.id.surepay_rb_alipay, R.id.surepay_rb_wechatpay,*/
            R.id.surepay_btn_pay, R.id.surepay_tv_user})
    void viewOnclik(View view) {
        switch (view.getId()) {
            case R.id.surepay_pr_alipay:
                mAddOilPayPresenter.setOrder_sn("");
                sendToServer("zffs", "zffs" + 4);
                mSurepayRbWechatpay.setChecked(false);
                mSurepayRbAlipay.setChecked(true);

                break;
            case R.id.surepay_pr_wechatpay:
                mAddOilPayPresenter.setOrder_sn("");
                sendToServer("zffs", "zffs" + 5);
                mSurepayRbWechatpay.setChecked(true);
                mSurepayRbAlipay.setChecked(false);

                break;
           /* case R.id.surepay_rb_alipay:
                mSurepayRbWechatpay.setChecked(false);
                mSurepayRbAlipay.setChecked(true);
                break;
            case R.id.surepay_rb_wechatpay:
                mSurepayRbWechatpay.setChecked(true);
                mSurepayRbAlipay.setChecked(false);
                break;*/
            case R.id.surepay_tv_user:
                HtmlAct.unsealActivity(this, ApiRest.baseUrl + ApiRest.AGRRMENT);
                break;
            case R.id.surepay_btn_pay:
                sendToServer("qrzf", "qrzf");
                if (!mSurepayCbChecked.isChecked()) {
                    Toast.makeText(this, "请确认是否阅读并勾选用户使用协议.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mSurepayRbAlipay.isChecked()) {
                    // 支付宝支付
                    pay("4");
                    showToast("请稍等,正在加载支付宝支付!");
                }
                if (mSurepayRbWechatpay.isChecked()) {
                    // 微信支付
                    pay("5");
                    showToast("请稍等,正在加载微信支付!");

                }
                break;

        }
    }

    public void pay(String mPayId) {

        mAddOilPayPresenter.createOrder(mCardId, mMoney + "", mChargeType, mPayId, mCouponId);
    }


    @Override
    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hasCoupon(boolean state, String content) {
        mTxtDisCountSave.setText(content);
        if (state) {
            //TODO 跳转到使用优惠券界面
            mTxtDisCountSave.setOnClickListener(v -> {
                startActivityForResult(new Intent(this, UseCouponAct.class), COUPON);
            });
        } else {
            //更改字体
            mTxtDisCountSave.setTextColor(getResources().getColor(R.color.light_gray_text));
            mTextViewDisContent.setTextColor(getResources().getColor(R.color.light_gray_text));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == COUPON) {
            if (resultCode == 3) {
                if (!TextUtils.isEmpty(data.getStringExtra("couponId"))) {
                    mCouponId = data.getStringExtra("couponId");
                }

                if (mSurepayRbAlipay.isChecked()) {
                    // 支付宝支付
                    mAddOilPayPresenter.loadCouponData(mCardId, mMoney + "", mChargeType, "4", mCouponId);
                }
                if (mSurepayRbWechatpay.isChecked()) {
                    // 微信支付
                    mAddOilPayPresenter.loadCouponData(mCardId, mMoney + "", mChargeType, "5", mCouponId);
                }
            }
        }
    }

    //设置充值详情 界面数据
    @Override
    public void setData(String everyMonth, String discount, String save, String shouldPay) {
        //每月
        mMonth.setText(everyMonth);
        //折扣
        mDiscount.setText(discount);
        //节省
        mSaveMoney.setText(save);
        //应付
        mShouldPay.setText(shouldPay);

        //字体大小及颜色设置
        SpannableStringBuilder builder = new SpannableStringBuilder(mMonth.getText().toString());
        ForegroundColorSpan redSpan = new ForegroundColorSpan(getResources().getColor(R.color.gray_text));
        builder.setSpan(redSpan, 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mMonth.setText(builder);

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(mDiscount.getText().toString());
        spannableStringBuilder.setSpan(redSpan, 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mDiscount.setText(spannableStringBuilder);

        SpannableStringBuilder spannableStringBuilder1 = new SpannableStringBuilder(mSaveMoney.getText().toString());
        spannableStringBuilder1.setSpan(redSpan, 0, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mSaveMoney.setText(spannableStringBuilder1);

        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(mShouldPay.getText().toString());
        spannableStringBuilder2.setSpan(redSpan, 0, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mShouldPay.setText(spannableStringBuilder2);

    }

    @Override
    public void setCouPonData(String reduce, String save, String pay) {
        if (!TextUtils.isEmpty(mCouponId)) {
            mTxtDisCountSave.setText(reduce);
            mSaveMoney.setText(save);
            mShouldPay.setText(pay);
            SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(mShouldPay.getText().toString());
            ForegroundColorSpan redSpan = new ForegroundColorSpan(getResources().getColor(R.color.gray_text));
            spannableStringBuilder2.setSpan(redSpan, 0, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mShouldPay.setText(spannableStringBuilder2);
        }

    }

    @Override
    public void clearCouPonId() {
        mCouponId = "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * @data 创建时间:2017/1/18
     * @author peng
     * @desc 数据埋点统计
     * @version
     */
    public void sendToServer(String name, String remark) {
        new BuriedPoint().sendToServer("ykzf", name, remark);
    }
}
