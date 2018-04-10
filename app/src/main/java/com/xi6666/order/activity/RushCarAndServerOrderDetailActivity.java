package com.xi6666.order.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.carWash.view.CashierAct;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.order.bean.WashAndServerBean;
import com.xi6666.order.other.Utils;
import com.xi6666.owner.view.EvaluateStoreAct;
import com.xi6666.store.StoreDetailsAct;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RushCarAndServerOrderDetailActivity extends ToolBarBaseActivity {

    @BindView(R.id.ll_ras_wait_pay_top)
    LinearLayout mLlRasWaitPayTop;
    @BindView(R.id.ll_ras_pay_success_top)
    LinearLayout mLlRasPaySuccessTop;
    @BindView(R.id.iv_ras_store_img)
    ImageView    mIvRasStoreImg;
    @BindView(R.id.tv_ras_store_name)
    TextView     mTvRasStoreName;
    @BindView(R.id.tv_ras_store_time)
    TextView     mTvRasStoreTime;
    @BindView(R.id.tv_ras_store_score)
    TextView     mTvRasStoreScore;
    @BindView(R.id.tv_ras_store_address)
    TextView     mTvRasStoreAddress;
    @BindView(R.id.ll_ras_store_details)
    LinearLayout mLlRasStoreDetails;
    @BindView(R.id.tv_ras_goods_order_num)
    TextView     mTvRasGoodsOrderNum;
    @BindView(R.id.tv_ras_down_order_time)
    TextView     mTvRasDownOrderTime;
    @BindView(R.id.tv_ras_goods_order_type)
    TextView     mTvRasGoodsOrderType;
    @BindView(R.id.tv_ras_goods_order_total_amount)
    TextView     mTvRasGoodsOrderTotalAmount;
    @BindView(R.id.btn_ras_to_pay)
    Button       mBtnRasToPay;
    @BindView(R.id.btn_ras_to_evaluate)
    Button       mBtnRasToEvaluate;
    @BindView(R.id.ll_reload_data)
    LinearLayout mLlReloadData;
    @BindView(R.id.btn_reload_data)
    Button       mBtnReloadData;

    private String            order_sn;
    private String            order_id;
    private String            store_id;
    private Dialog            mLoading;
    private String            user_id;
    private String            user_token;
    private String            order_type;
    private WashAndServerBean mWashAndServerBean;
    private DecimalFormat mFormat = new DecimalFormat("#####0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rush_car_and_server_order_detail);
        ButterKnife.bind(this);
        init();
        initData();
    }

    private void init() {
        mLoading = ShowDialogUitls.showDio(this);
        user_id = getIntent().getStringExtra("user_id");
        user_token = getIntent().getStringExtra("user_token");
        order_sn = getIntent().getStringExtra("order_sn");
        order_id = getIntent().getStringExtra("order_id");

        mBtnReloadData.setOnClickListener(v -> {
            initData();
        });
    }

    private void initData() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).getWashAndServerDetails(order_sn, user_id, user_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mLoading.isShowing()) {
                                mLoading.dismiss();
                            }
                        }
                    });
                    String result = response.body().string();
                    LogUtil.i("GoodsOrderDetailActivity", result);
                    mWashAndServerBean = GsonUtils.toEntityFromJson(result, WashAndServerBean.class);
                    if (mWashAndServerBean.isSuccess()) {
                        mLlReloadData.setVisibility(View.GONE);
                        WashAndServerBean.DataBean data = mWashAndServerBean.getData();
                        String order_status = data.getOrder_status();
                        int is_comment = data.getIs_comment();

                        if ("0".equals(order_status)) {
                            // 未付款
                            mLlRasWaitPayTop.setVisibility(View.VISIBLE);
                            mLlRasPaySuccessTop.setVisibility(View.GONE);
                            mBtnRasToPay.setVisibility(View.VISIBLE);
                            mBtnRasToEvaluate.setVisibility(View.GONE);
                        } else {
                            // 已付款
                            if (is_comment == 1) {
                                mBtnRasToEvaluate.setVisibility(View.GONE);
                            } else {
                                mBtnRasToEvaluate.setVisibility(View.VISIBLE);
                            }
                            mLlRasWaitPayTop.setVisibility(View.GONE);
                            mLlRasPaySuccessTop.setVisibility(View.VISIBLE);
                            mBtnRasToPay.setVisibility(View.GONE);
                        }
                        store_id = data.getShop_id();
                        mTvRasDownOrderTime.setText(data.getOrder_addtime());
                        mTvRasGoodsOrderNum.setText(data.getOrder_sn());
                        mTvRasGoodsOrderType.setText(data.getOrder_type());
                        order_type = data.getOrder_type();
                        mTvRasGoodsOrderTotalAmount.setText("¥ " + mFormat.format(data.getOrder_amount()));
                        if (!"0".equals(data.getShop_pingfen()))
                            mTvRasStoreScore.setText(data.getShop_pingfen() + "分");
                        mTvRasStoreName.setText(data.getShop_name());
                        mTvRasStoreAddress.setText(data.getShop_address());
                        mTvRasStoreTime.setText(data.getShop_opentime());
                        Glide.with(RushCarAndServerOrderDetailActivity.this).load(data.getShop_img())
                                .placeholder(R.drawable.no_data_empty).centerCrop()
                                .into(mIvRasStoreImg);
                    } else {
                        Utils.make(RushCarAndServerOrderDetailActivity.this, mWashAndServerBean.getInfo());
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Utils.make(RushCarAndServerOrderDetailActivity.this, "服务器异常");
            }
        });
    }

    @Override
    public void setToolbarIconDo() {
        Intent intent = new Intent(this, MyOrderListActivity.class);
        if (order_type.contains("洗车")) {
            intent.putExtra("index", 1);
        } else {
            intent.putExtra("index", 2);
        }
        startActivity(intent);
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "订单详情";
    }

    @OnClick({R.id.ll_ras_store_details, R.id.btn_ras_to_pay, R.id.btn_ras_to_evaluate})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_ras_store_details:
                // 跳转到门店详情页
                StoreDetailsAct.openActivity(RushCarAndServerOrderDetailActivity.this, store_id, order_id);
                break;
            case R.id.btn_ras_to_pay:
                // 收银台
                CashierAct.openActivity(RushCarAndServerOrderDetailActivity.this, order_sn);
                break;
            case R.id.btn_ras_to_evaluate:
                // 门店评价
                EvaluateStoreAct.openActivity(this, order_sn, store_id);
                break;
        }
    }

    public static void openActivity(Activity activity, String user_id, String user_token, String order_sn) {
        Intent intent = new Intent(activity, RushCarAndServerOrderDetailActivity.class);
        intent.putExtra("user_id", user_id);
        intent.putExtra("user_token", user_token);
        intent.putExtra("order_sn", order_sn);
        activity.startActivity(intent);
    }
}
