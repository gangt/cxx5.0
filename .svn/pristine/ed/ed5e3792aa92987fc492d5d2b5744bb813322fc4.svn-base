package com.xi6666.carWash.view;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.addoil.view.AddOilPayResultAct;
import com.xi6666.alipay.PayResult;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ToolBarBaseAct;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.carWash.view.di.CarWashPayModule;
import com.xi6666.carWash.view.di.DaggerCarWashPayComponent;
import com.xi6666.carWash.view.mvp.CarWashPresenter;
import com.xi6666.databean.CarWashPayData;
import com.xi6666.eventbus.WeChatPayEvent;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.DimenUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author peng
 * @data 创建时间:2016/12/01
 * @desc
 */

public class CarWashPayAct extends ToolBarBaseAct {
    @BindView(R.id.car_wash_pay_title)
    TextView mCarWashPayTitle;
    @BindView(R.id.car_wash_pay_moeny)
    TextView mCarWashPayMoeny;

    @BindView(R.id.car_wash_pay_total_moeny)
    TextView mCarWashPayTotalMoeny;
    @BindView(R.id.car_wash_pay_discount_moeny)
    TextView mCarWashPayDiscountMoeny;
    @BindView(R.id.car_wash_pay_arrival_time)
    TextView mCarWashPayArrivalTime;
    @BindView(R.id.rv)
    RelativeLayout mRv;
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


    private String mPackageId;
    private static final int SDK_PAY_FLAG = 1;
    @Inject
    CarWashPresenter mCarWashPresenter;

    @Inject
    ApiRest mApiRest;
    private CarWashPayData.DataBean mDataBean;

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
                        // Toast.makeText(AddOilPayAct.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CarWashPayAct.this, AddOilPayResultAct.class);
                        intent.putExtra("result", true);
                        intent.putExtra("paytype", "carWash");
                        startActivity(intent);

                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(CarWashPayAct.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Intent intent2 = new Intent(CarWashPayAct.this, AddOilPayResultAct.class);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void payResultData(WeChatPayEvent weChatPayEvent) {
        int msg = weChatPayEvent.getMsg();
        switch (msg) {
            case 0:
                Intent intent = new Intent(this, AddOilPayResultAct.class);
                intent.putExtra("result", true);
                intent.putExtra("paytype", "carWash");
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_car_wash_pay);
        ButterKnife.bind(this);
        mPackageId = getIntent().getStringExtra("package_id");
        DaggerCarWashPayComponent.builder().apiModule(new ApiModule((BaseApplication) getApplication())).
                carWashPayModule(new CarWashPayModule()).build().Inject(this);

        mCarWashPresenter.setCarWashPayAct(this);
        mCarWashPresenter.setApiRest(mApiRest);
        mCarWashPresenter.setHandler(mHandler);
        mCarWashPresenter.setContext(this);
        mCarWashPresenter.creatData(mPackageId);

    }

    @OnClick({R.id.pay_wechat_ll, R.id.pay_ali_ll, R.id.car_wash_pay_btn})
    void viewOnclick(View view) {
        switch (view.getId()) {
            case R.id.pay_wechat_ll:
                mPayWechatRb.setChecked(true);
                mPayAliRb.setChecked(false);
                break;
            case R.id.pay_ali_ll:
                mPayWechatRb.setChecked(false);
                mPayAliRb.setChecked(true);
                break;
            case R.id.car_wash_pay_btn:

                if (mPayWechatRb.isChecked()) {
                    mCarWashPresenter.wechatPay(mDataBean.getOrder_sn(), "5");
                    showToast("请稍等,正在加载微信支付.");
                } else {
                    mCarWashPresenter.wechatPay(mDataBean.getOrder_sn(), "4");
                    Toast.makeText(this, "支付宝支付", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "确认支付";
    }

    public void addData(CarWashPayData.DataBean dataBean) {
        mDataBean = dataBean;
        //充值金额
        mCarWashPayMoeny.setText(mDataBean.getPackage_cash_amount() + "");
        SpannableString spannableString = new SpannableString("¥" + mCarWashPayMoeny.getText());
        spannableString.setSpan(new AbsoluteSizeSpan(DimenUtils.sp2px(this, 13)), 0, 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mCarWashPayMoeny.setText(spannableString);
        //洗车卡金额
        mCarWashPayTotalMoeny.setText("¥" + mDataBean.getTotal_amount());
        //设置确认支付
        mCarWashPayBtn.setText("确认支付￥" + mDataBean.getOrder_total());
        //设置购物代金券
        mCarWashPayArrivalTime.setText("¥" + mDataBean.getShop_coupon());
    }

    public void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
