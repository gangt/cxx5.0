package com.xi6666.order.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.common.UserData;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.order.bean.PaySuccessBean;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PaySuccessActivity extends ToolBarBaseActivity {

    @BindView(R.id.iv_cha_red_package)
    ImageView mIvChaRedPackage;
    @BindView(R.id.tv_fen_dan_desc)
    TextView  mTvFenDanDesc;
    @BindView(R.id.btn_pay_done)
    Button    mBtnPayDone;

    private String         order_sn;
    private String         user_id;
    private String         user_token;
    private Dialog         mLoading;
    private PaySuccessBean mPaySuccessBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        ButterKnife.bind(this);
        order_sn = getIntent().getStringExtra("order_sn");
        user_id = UserData.getUserId();
        user_token = UserData.getUserToken();
        mLoading = ShowDialogUitls.showDio(this);
        if (mLoading.isShowing()) {
            mLoading.dismiss();
        }
        initData();
    }

    private void initData() {
        mLoading.show();
        new Retrofit.Builder().baseUrl(NetAddress.baseUrl).build().create(NetAddress.class).paySuccess(order_sn).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (mLoading.isShowing()) {
                    mLoading.dismiss();
                }
                try {
                    String result = response.body().string();
                    LogUtil.i("PaySuccessActivity", result);
                    mPaySuccessBean = GsonUtils.toEntityFromJson(result,PaySuccessBean.class);
                    if (mPaySuccessBean.isSuccess()) {
                        if ("shop_order".equals(mPaySuccessBean.getData().getOrder_type())) {
                            if ("1".equals(mPaySuccessBean.getData().getCan_get_xidou())) {
                                mIvChaRedPackage.setVisibility(View.VISIBLE);
                            } else {
                                mIvChaRedPackage.setVisibility(View.GONE);
                            }
                            if ("1".equals(mPaySuccessBean.getData().getFendan())) {
                                mTvFenDanDesc.setVisibility(View.VISIBLE);
                            } else {
                                mTvFenDanDesc.setVisibility(View.GONE);
                            }
                        }
                    } else {
                        Utils.make(PaySuccessActivity.this,mPaySuccessBean.getInfo());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mLoading.isShowing()) {
                    mLoading.dismiss();
                }
                Toast.makeText(PaySuccessActivity.this, "请求失败,请检查网络连接!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setToolbarIconDo() {
        // 跳转到我的订单列表页商品订单Tab
        startActivity(new Intent(this, MyOrderListActivity.class));
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "支付成功";
    }

    @OnClick({R.id.iv_cha_red_package, R.id.btn_pay_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cha_red_package:
                showRedPackageDialog();
                break;
            case R.id.btn_pay_done:
                // 跳转到我的订单列表页商品订单Tab
                startActivity(new Intent(this, MyOrderListActivity.class));
                finish();
                break;
        }
    }

    private void showRedPackageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PaySuccessActivity.this, R.style.Translucent_Dialog);
        final AlertDialog show = builder.show();
        View view = View.inflate(PaySuccessActivity.this, R.layout.dialog_chai_red_package, null);
        show.setContentView(view);
        view.findViewById(R.id.close_red_package_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
        ImageView ivChai = (ImageView) view.findViewById(R.id.chai_red_package_img);
        ivChai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateAnimation animation = new RotateAnimation(0f,360f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                animation.setDuration(1500);
                animation.setRepeatCount(Integer.MAX_VALUE);
                animation.setFillAfter(true);
                animation.setRepeatMode(Animation.RESTART);
                animation.start();
                new Retrofit.Builder().baseUrl(NetAddress.baseUrl).build().create(NetAddress.class).getXiDou(order_sn, user_id,user_token).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String result = response.body().string();
                            LogUtil.i("redPackage", result);
                            JSONObject jsonObject = new JSONObject(result);
                            animation.cancel();
                            if (jsonObject.optBoolean("success")) {
                                Intent intent = new Intent(PaySuccessActivity.this, RedPackageOpenActivity.class);
                                JSONObject data = jsonObject.optJSONObject("data");
                                intent.putExtra("xidou", data.optInt("xidou"));
                                startActivity(intent);
                                show.dismiss();
                                finish();
                            } else {
                                Toast.makeText(PaySuccessActivity.this, jsonObject.optString("info"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        animation.cancel();
                        Toast.makeText(PaySuccessActivity.this, "请求失败,请检查网络连接!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        WindowManager.LayoutParams lp = show.getWindow().getAttributes();
        lp.dimAmount = 0.0f;
        show.getWindow().setAttributes(lp);
        show.show();
    }

    @Override
    public void onBackPressed() {
        // 跳转到我的订单列表页商品订单Tab
        startActivity(new Intent(this, MyOrderListActivity.class));
        finish();
    }
}
