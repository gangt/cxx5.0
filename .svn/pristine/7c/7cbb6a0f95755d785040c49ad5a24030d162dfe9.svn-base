package com.xi6666.addoil.view;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ToolBarBaseAct;
import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerApiComponent;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;
import com.xi6666.cardbag.view.CardBagAct;
import com.xi6666.cardbag.view.oilcard.OilRechargeDetailsAct;
import com.xi6666.common.UserData;
import com.xi6666.databean.AddOilPopuBean;
import com.xi6666.main.view.MainAct;
import com.xi6666.network.ApiRest;
import com.xi6666.view.dialog.CallDialog;
import com.xi6666.view.dialog.PromotionDialogAct;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddOilPayResultAct extends ToolBarBaseAct {
    @BindView(R.id.iv_payreuslt_state)
    ImageView mIvPayreusltState;
    @BindView(R.id.txt_payreuslt_state)
    TextView mTxtPayreusltState;
    @BindView(R.id.txt_payresult_look)
    TextView mTxtPayresultLook;
    @BindView(R.id.txt_payresult_order)
    TextView mTxtPayresultOrder;
    @BindView(R.id.txt_payresult_phone)
    TextView mTxtPayresultPhone;
    @BindView(R.id.btn_payresult_pay)
    Button mBtnPayresultPay;
    @BindView(R.id.rl_payresult_how)
    RelativeLayout mRlPayresultHow;
    @BindView(R.id.activity_add_oil_pay_result)
    LinearLayout mActivityAddOilPayResult;
    @BindView(R.id.txt_payresult_explain)
    TextView mTxtPayresultExplain;
    private boolean mResult;
    private String mCard;
    private String mPaytype;

    @Inject
    ApiRest mApiRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_oil_pay_result);

        AppComponets build = DaggerAppComponets.builder().
                apiModule(new ApiModule(BaseApplication.getApplication())).
                appModule(new AppModule(BaseApplication.getApplication())).build();
        DaggerApiComponent.builder().appComponets(build).build().Inject(this);

        ButterKnife.bind(this);
        SpannableStringBuilder builder = new SpannableStringBuilder(mTxtPayresultPhone.getText().toString());
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.GREEN);
        builder.setSpan(redSpan, mTxtPayresultPhone.getText().toString().length() - 12, mTxtPayresultPhone.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTxtPayresultPhone.setText(builder);
        mResult = getIntent().getBooleanExtra("result", false);
        mCard = getIntent().getStringExtra("card");
        mPaytype = getIntent().getStringExtra("paytype");
        if (TextUtils.equals("carWash", mPaytype)) {
            mTxtPayresultExplain.setVisibility(View.VISIBLE);
        }
        if (TextUtils.equals("illega", mPaytype)) {
            mTxtPayresultExplain.setVisibility(View.VISIBLE);
            mTxtPayresultOrder.setText("查看卡券包");
            mTxtPayresultExplain.setText("违章处理卡金额已放入你的账户，你可通过“我的-卡券包”查看，添加车辆信息后才可启用哦!");
        }
        if (mResult) {
            showPaySuccess();
            mTxtTiltle.setText("支付成功");
            mApiRest.addOilPopu(UserData.getUserId(), UserData.getUserToken()).subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe(new Subscriber<AddOilPopuBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            // Log.d(TAG, "e->" + e);
                        }

                        @Override
                        public void onNext(AddOilPopuBean addOilPopuBean) {
                            if (addOilPopuBean.isSuccess()) {
                                if (addOilPopuBean.getData().getIs_receive() == 1) {
                                    //   mView.showAddoilPopu(addOilPopuBean.getData().getCoupon_list());
                                    Intent intent = new Intent(AddOilPayResultAct.this, PromotionDialogAct.class);
                                    intent.putExtra("list", (Serializable) addOilPopuBean.getData().getCoupon_list());
                                    startActivity(intent);
                                }
                            } else {
                                // view.showToast(addOilPopuBean.getInfo());
                            }
                        }
                    });
        } else {
            showPayFail();
            mTxtTiltle.setText("支付失败");
        }



    }

    @Override
    public void setToolbarIconDo() {
        if (mResult) {
            startActivity(new Intent(this, MainAct.class));
        } else {
            finish();
        }
    }

    @Override
    public String setToolbarTitle() {
        return "支付结果";
    }

    @OnClick({R.id.txt_payresult_look, R.id.txt_payresult_order, R.id.txt_payresult_phone, R.id.btn_payresult_pay})
    public void viewOnclick(View view) {
        switch (view.getId()) {
            //继续逛逛
            case R.id.txt_payresult_look:
                startActivity(new Intent(this, MainAct.class));
                break;
            //充值明细
            case R.id.txt_payresult_order:
                if (TextUtils.equals("carWash", mPaytype)) {
                    Intent intent = new Intent(this, CardBagAct.class);
                    intent.putExtra("type", "1");
                    startActivity(intent);
                } else if (TextUtils.equals("illega", mPaytype)) {
                    //到我的卡券包违章卡界面
                    Intent intent = new Intent(this, CardBagAct.class);
                    intent.putExtra("type", "3");
                    startActivity(intent);
                } else {
                    OilRechargeDetailsAct.openActivity(this, mCard);
                }
                break;
            //拨打电话
            case R.id.txt_payresult_phone:
                RxPermissions.getInstance(this).request(Manifest.permission.CALL_PHONE).subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(AddOilPayResultAct.this, "为了能够正常使用,请给予相关的权限", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            new CallDialog(AddOilPayResultAct.this).MakePhoneCall("400-9999-353");
                        }
                    }
                });
                break;
            //去支付
            case R.id.btn_payresult_pay:
                finish();
                break;
        }
    }

    private void showPaySuccess() {
        mIvPayreusltState.setImageResource(R.drawable.ic_oil_pay_success);
        mRlPayresultHow.setVisibility(View.VISIBLE);
        mTxtPayreusltState.setText("支付成功");
        mTxtPayresultPhone.setVisibility(View.GONE);
        mBtnPayresultPay.setVisibility(View.GONE);
    }

    private void showPayFail() {
        mIvPayreusltState.setImageResource(R.drawable.ic_oil_pay_fail);
        mTxtPayreusltState.setText("支付失败");
        mRlPayresultHow.setVisibility(View.GONE);
        mTxtPayresultPhone.setVisibility(View.VISIBLE);
        mBtnPayresultPay.setVisibility(View.VISIBLE);
    }
}
