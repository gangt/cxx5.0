package com.xi6666.illegal.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.platform.comapi.map.E;
import com.xi6666.R;
import com.xi6666.addoil.view.AddOilPayAct;
import com.xi6666.addoil.view.AddOilPayResultAct;
import com.xi6666.alipay.PayResult;
import com.xi6666.app.baset.BaseTAct;
import com.xi6666.common.UserData;
import com.xi6666.eventbus.WeChatPayEvent;
import com.xi6666.illegal.other.IllegaSurePayContract;
import com.xi6666.illegal.other.IllegaSurePayModel;
import com.xi6666.illegal.other.IllegaSurePayPresenter;
import com.xi6666.utils.DimenUtils;
import com.xi6666.view.CompatToolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class IllageSurePayAct extends BaseTAct<IllegaSurePayPresenter,
        IllegaSurePayModel> implements IllegaSurePayContract.View {
    @BindView(R.id.txt_basetoolbar_left)
    TextView mTxtBasetoolbarLeft;
    @BindView(R.id.txt_basetoolbar_title)
    TextView mTxtBasetoolbarTitle;
    @BindView(R.id.txt_basetoolbar_right)
    TextView mTxtBasetoolbarRight;
    @BindView(R.id.base_tb)
    CompatToolbar mBaseTb;
    @BindView(R.id.txt_illagesurepay_title_money)
    TextView mTxtIllagesurepayTitleMoney;
    @BindView(R.id.txt_illagesurepay_money)
    TextView mTxtIllagesurepayMoney;
    @BindView(R.id.txt_illagesurepay_message)
    TextView mTxtIllagesurepayMessage;
    @BindView(R.id.txt_illagesurepay_prescription)
    TextView mTxtIllagesurepayPrescription;
    @BindView(R.id.car_wash_pay_discount_moeny)
    TextView mCarWashPayDiscountMoeny;
    @BindView(R.id.txt_illagesurepay_frequency)
    TextView mTxtIllagesurepayFrequency;
    @BindView(R.id.rv)
    RelativeLayout mRv;
    @BindView(R.id.txt_illagesurepay_discount)
    TextView mTxtIllagesurepayDiscount;
    @BindView(R.id.rl_illagesurepay_discount)
    RelativeLayout mRlIllagesurepayDiscount;
    @BindView(R.id.tv_01)
    TextView mTv01;
    @BindView(R.id.pay_wechat_iv)
    ImageView mPayWechatIv;
    @BindView(R.id.pay_wechat_tv)
    TextView mPayWechatTv;
    @BindView(R.id.pay_wechat_rb)
    CheckBox mPayWechatRb;
    @BindView(R.id.pay_wechat_ll)
    RelativeLayout mPayWechatLl;
    @BindView(R.id.pay_ali_iv)
    ImageView mPayAliIv;
    @BindView(R.id.pay_ali_tv)
    TextView mPayAliTv;
    @BindView(R.id.pay_ali_rb)
    CheckBox mPayAliRb;
    @BindView(R.id.pay_ali_ll)
    RelativeLayout mPayAliLl;
    @BindView(R.id.car_wash_pay_btn)
    Button mCarWashPayBtn;
    @BindView(R.id.activity_illage_sure_pay)
    LinearLayout mActivityIllageSurePay;
    private static final int SDK_PAY_FLAG = 1;
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
                        Toast.makeText(IllageSurePayAct.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(IllageSurePayAct.this, AddOilPayResultAct.class);
                        intent.putExtra("result", true);
                        intent.putExtra("paytype", "illega");
                        startActivity(intent);
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(IllageSurePayAct.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            Intent intent2 = new Intent(IllageSurePayAct.this, AddOilPayResultAct.class);
                            intent2.putExtra("result", false);
                            startActivity(intent2);
                            Toast.makeText(IllageSurePayAct.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void payResultData(WeChatPayEvent weChatPayEvent) {
        int msg = weChatPayEvent.getMsg();
        switch (msg) {
            case 0:
                Intent intent = new Intent(this, AddOilPayResultAct.class);
                intent.putExtra("result", true);
                intent.putExtra("paytype", "illega");
                startActivity(intent);
                break;
            case -1:
                Intent intent2 = new Intent(this, AddOilPayResultAct.class);
                intent2.putExtra("result", false);
                startActivity(intent2);
                break;
            case -2:
                Intent intent3 = new Intent(this, AddOilPayResultAct.class);
                intent3.putExtra("result", false);
                startActivity(intent3);
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_illage_sure_pay;
    }

    @Override
    public void init() {
        //toolbar设置
        mTxtBasetoolbarLeft.setVisibility(View.GONE);
        mTxtBasetoolbarTitle.setText("确认支付");
        mTxtBasetoolbarRight.setVisibility(View.GONE);
        mBaseTb.setNavigationIcon(R.drawable.ic_back);
        mBaseTb.setNavigationOnClickListener(v -> {
            finish();
        });
        EventBus.getDefault().register(this);
        String orderNumber = getIntent().getStringExtra("orderNumber");
        mPresenter.creatOrder(orderNumber);
        mPresenter.setHandler(mHandler);
        mPresenter.setContext(this);
    }

    @Override
    public void setOrderInfo(String money, String data, String frequency) {
        mTxtIllagesurepayTitleMoney.setText("¥" + money);
        SpannableString spannableString = new SpannableString(mTxtIllagesurepayTitleMoney.getText());
        spannableString.setSpan(new AbsoluteSizeSpan(DimenUtils.sp2px(this, 15)), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTxtIllagesurepayTitleMoney.setText(spannableString);
        mTxtIllagesurepayMoney.setText("¥" + money);
        mTxtIllagesurepayPrescription.setText(data + "个月");
        mTxtIllagesurepayFrequency.setText(frequency + "次");
        mCarWashPayBtn.setText("确认支付 ¥" + money);
    }

    @OnClick({R.id.pay_wechat_ll, R.id.pay_ali_ll, R.id.car_wash_pay_btn})
    void viewOnclick(View view) {
        switch (view.getId()) {
            case R.id.pay_wechat_ll:
                mPayAliRb.setChecked(false);
                mPayWechatRb.setChecked(true);
                break;
            case R.id.pay_ali_ll:
                mPayAliRb.setChecked(true);
                mPayWechatRb.setChecked(false);
                break;
            case R.id.car_wash_pay_btn:
                if (mPayAliRb.isChecked()) {
                    mPresenter.changePayType(UserData.getUserId(), "4");
                }
                if (mPayWechatRb.isChecked()) {
                    Toast.makeText(this, "请稍等正在加载微信支付.", Toast.LENGTH_SHORT).show();
                    mPresenter.changePayType(UserData.getUserId(), "5");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
