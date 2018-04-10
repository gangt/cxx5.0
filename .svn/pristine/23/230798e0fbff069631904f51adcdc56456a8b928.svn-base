package com.xi6666.order.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.common.UserData;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.order.bean.CouponBean;
import com.xi6666.order.other.CustomAdapter;
import com.xi6666.order.other.CustomViewHolder;
import com.xi6666.order.other.SpaceItemDecoration;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UseCouponActivity extends ToolBarBaseActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.btn_reload_data)
    Button        mBtnReloadData;
    @BindView(R.id.ll_reload_data)
    LinearLayout  mLlReloadData;
    @BindView(R.id.coupon_XRecyclerView)
    XRecyclerView mCouponXRecyclerView;

    private Dialog mLoading;
    private String user_id;
    private String user_token;
    private int    page = 1;
    private CouponAdapter mAdapter;
    private CouponBean mCouponBean;
    private String money;
    private List<CouponBean.DataBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_coupon);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ToolBarBaseActivity.setMiuiStatusBarDarkMode(this,true);
        init();
        initData();
    }

    private void init() {
        money = getIntent().getStringExtra("money");
        user_id = UserData.getUserId();
        user_token = UserData.getUserToken();
        mLlReloadData.setVisibility(View.VISIBLE);
        mLoading = ShowDialogUitls.showDio(this);
        mLoading.dismiss();
        mCouponXRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCouponXRecyclerView.setLoadingMoreEnabled(true);
        mCouponXRecyclerView.setPullRefreshEnabled(true);
        mCouponXRecyclerView.setLoadingListener(this);
        mAdapter = new CouponAdapter(this,mList,R.layout.item_use_coupon);
        mCouponXRecyclerView.addItemDecoration(new SpaceItemDecoration(this, 11, mAdapter));
        mCouponXRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).getCouponList(page + "",money,user_id,user_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (mLoading.isShowing()) {
                    mLoading.dismiss();
                }
                try {
                    String result = response.body().string();
                    LogUtil.i("result",result);
                    mCouponBean = GsonUtils.toEntityFromJson(result,CouponBean.class);
                    if (mCouponBean.isSuccess()) {
                        List<CouponBean.DataBean> data = mCouponBean.getData();
                        if (page == 1) {
                            if (data.size() > 0) {
                                mList.clear();
                                mLlReloadData.setVisibility(View.GONE);
                                mCouponXRecyclerView.setVisibility(View.VISIBLE);
                            } else {
                                mLlReloadData.setVisibility(View.GONE);
                                mCouponXRecyclerView.setVisibility(View.GONE);
                            }
                        }
                        if (data.size() > 0) {
                            mList.addAll(data);
                            mAdapter.notifyDataSetChanged();
                            if (data.size() < 15) {
                                mCouponXRecyclerView.loadMoreComplete();
//                                mCouponXRecyclerView.setNoMore(true);
                            }
                        }
                    } else {
                        mLlReloadData.setVisibility(View.GONE);
                        mCouponXRecyclerView.setVisibility(View.GONE);
                        Utils.make(UseCouponActivity.this, mCouponBean.getInfo());
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
            }
        });
    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "使用优惠券";
    }

    @OnClick(R.id.btn_reload_data)
    public void onClick() {
        initData();
    }

    @Override
    public void onRefresh() {
        page = 1;
        initData();
        mCouponXRecyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        page++;
        initData();
        mCouponXRecyclerView.loadMoreComplete();
    }

    class CouponAdapter extends CustomAdapter<CouponBean.DataBean> {

        /**
         * 构造器
         *
         * @param context
         * @param mDatas
         * @param itemLayoutId
         */
        protected CouponAdapter(Context context, List<CouponBean.DataBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(CustomViewHolder holder, CouponBean.DataBean item) {
            LinearLayout mLlCoupon = holder.findViewById(R.id.ll_item_bg);
            TextView tvTime = holder.findViewById(R.id.tv_item_validity_time);
            TextView tvName = holder.findViewById(R.id.tv_item_coupon_name);
            TextView tvMoney = holder.findViewById(R.id.item_tv_coupon_money);
            TextView tvCondition = holder.findViewById(R.id.item_tv_use_condition);
            TextView tvPlatform = holder.findViewById(R.id.tv_item_platform);
            ImageView ivQuan = holder.findViewById(R.id.iv_item_quan);
            String is_available = item.getIs_available();
            if ("1".equals(is_available)) {
                Drawable drawable = getResources().getDrawable(R.drawable.bg_able);
                mLlCoupon.setBackgroundDrawable(drawable);
                ivQuan.setImageResource(R.drawable.quan_able);
                mLlCoupon.setClickable(true);
            } else {
                Drawable drawable = getResources().getDrawable(R.drawable.bg_enable);
                mLlCoupon.setBackgroundDrawable(drawable);
                mLlCoupon.setClickable(false);
                mLlCoupon.setEnabled(false);
                ivQuan.setImageResource(R.drawable.quan_enable);
            }
            tvName.setText(item.getCoupon_name());
            tvMoney.setText(item.getPrice());
            tvCondition.setText("满" + item.getLimit_money() + "元可用");
            tvTime.setText(item.getSdate() + "--" + item.getEdate());
            int platform = item.getCoupon_platform();
            if (platform == 0) {
                tvPlatform.setText("");
            } else if (platform == 1) {
                tvPlatform.setText("仅限安卓手机使用");
            } else if (platform == 2) {
                tvPlatform.setText("仅限苹果手机使用");
            } else if (platform == 3) {
                tvPlatform.setText("仅限微信使用");
            }

            String coupon_id = item.getCoupon_id();
            String coupon_money = item.getPrice();

            mLlCoupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UseCouponActivity.this,GoodsOrderAffirmActivity.class);
                    intent.putExtra("coupon_id",coupon_id);
                    intent.putExtra("coupon_money",coupon_money);
                    setResult(RESULT_OK,intent);
                    finish();
//                    Utils.make(UseCouponActivity.this,"优惠券" + (holder.getAdapterPosition() - 1));
                }
            });
        }
    }
}
