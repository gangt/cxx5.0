package com.xi6666.order.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.common.UserData;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.network.ApiRest;
import com.xi6666.order.activity.RushCarAndServerOrderDetailActivity;
import com.xi6666.order.bean.WashOrderBean;
import com.xi6666.order.other.CustomAdapter;
import com.xi6666.order.other.CustomViewHolder;
import com.xi6666.order.other.SpaceItemDecoration;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;
import com.xi6666.view.dialog.BaseDialog;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 作者： hydCoder on 2016/11/4
 * 描述：${DESC}
 */

public class RushCarOrderFragment extends Fragment implements XRecyclerView.LoadingListener, CustomAdapter.OnItemClickListener {

    @BindView(R.id.xRecyclerView)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.ll_no_order)
    LinearLayout mLlNoOrder;
    @BindView(R.id.ll_reload_data)
    LinearLayout mLlReloadData;
    @BindView(R.id.btn_reload_data)
    Button mBtnReloadData;
    private View mRootView;

    private MyAdapter mAdapter;
    private List<WashOrderBean.DataBean> mDatas = new ArrayList<>();
    private DecimalFormat mFormat = new DecimalFormat("###0.00");

    private WashOrderBean mWashOrderBean;

    private int page = 1;
    private String user_id;
    private String user_token;
    private Dialog mLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_goods_order, container, false);
        }

        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        ButterKnife.bind(this, mRootView);
        init();
        initData();
        return mRootView;
    }

    private void init() {
        mLoading = ShowDialogUitls.showDio(getActivity());
        user_id = UserData.getUserId();
        user_token = UserData.getUserToken();

        mXRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mXRecyclerView.setLoadingMoreEnabled(true);
        mXRecyclerView.setPullRefreshEnabled(true);
        mAdapter = new MyAdapter(getActivity(), mDatas, R.layout.item_rush_and_server_order);
        mXRecyclerView.addItemDecoration(new SpaceItemDecoration(getActivity(), 11, mAdapter));
        mXRecyclerView.setAdapter(mAdapter);

        mXRecyclerView.setLoadingListener(this);
        mAdapter.setOnItemClickListener(this);

        mLlReloadData.setVisibility(View.GONE);
        mXRecyclerView.setVisibility(View.GONE);
        mLlNoOrder.setVisibility(View.GONE);

        mBtnReloadData.setOnClickListener(v -> {
            initData();
        });
    }

    private void initData() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).getWashOrder(page + "", user_id, user_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    getActivity().runOnUiThread(() -> {
                        if (mLoading.isShowing()) {
                            mLoading.dismiss();
                        }
                    });
                    String result = response.body().string();
                    LogUtil.i("RushCarOrderFragment", result);
                    mWashOrderBean = GsonUtils.toEntityFromJson(result, WashOrderBean.class);
                    if (mWashOrderBean.isSuccess()) {
                        String count = mWashOrderBean.getCount();
                        if (!"0".equals(count)) {
                            List<WashOrderBean.DataBean> data = mWashOrderBean.getData();
                            if (page == 1) {
                                if (data.size() > 0) {
                                    mDatas.clear();
                                    mLlReloadData.setVisibility(View.GONE);
                                    mXRecyclerView.setVisibility(View.VISIBLE);
                                    mLlNoOrder.setVisibility(View.GONE);
                                } else {
                                    mLlReloadData.setVisibility(View.GONE);
                                    mXRecyclerView.setVisibility(View.GONE);
                                    mLlNoOrder.setVisibility(View.VISIBLE);
                                }
                            }
                            if (data.size() > 0) {
                                mDatas.addAll(data);
                                mAdapter.notifyDataSetChanged();
                                if (data.size() < 15) {
                                    mXRecyclerView.loadMoreComplete();
                                    mXRecyclerView.setNoMore(true);
                                }
                            }
                        } else {
                            mLlReloadData.setVisibility(View.GONE);
                            mXRecyclerView.setVisibility(View.GONE);
                            mLlNoOrder.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mLlReloadData.setVisibility(View.GONE);
                        mXRecyclerView.setVisibility(View.GONE);
                        mLlNoOrder.setVisibility(View.VISIBLE);
                        Utils.make(RushCarOrderFragment.this.getActivity(), mWashOrderBean.getInfo());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO 由于后台有数据时返回的data是数组，无数据时是{}，特意作此处理
                    mLlReloadData.setVisibility(View.GONE);
                    mXRecyclerView.setVisibility(View.GONE);
                    mLlNoOrder.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mLoading.isShowing()) {
                    mLoading.dismiss();
                }
                mLlReloadData.setVisibility(View.VISIBLE);
                mXRecyclerView.setVisibility(View.GONE);
                mLlNoOrder.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onRefresh() {
        page = 1;
        initData();
        mXRecyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        page++;
        initData();
        mXRecyclerView.loadMoreComplete();
    }

    @Override
    public void onItemClickListener(int posotion) {
        // 跳转到服务订单详情页
        Intent intent = new Intent(RushCarOrderFragment.this.getActivity(), RushCarAndServerOrderDetailActivity.class);
        intent.putExtra("order_sn", mDatas.get(posotion - 1).getOrder_sn());
        intent.putExtra("user_id", user_id);
        intent.putExtra("user_token", user_token);
        startActivity(intent);
    }

    class MyAdapter extends CustomAdapter<WashOrderBean.DataBean> {

        /**
         * 构造器
         *
         * @param context
         * @param mDatas
         * @param itemLayoutId
         */
        protected MyAdapter(Context context, List<WashOrderBean.DataBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        /**
         * @param holder itemHolder
         * @param item   每一Item显示的数据
         */
        @Override
        public void convert(CustomViewHolder holder, WashOrderBean.DataBean item) {
            holder.setText(R.id.item_ras_order_num, "订单编号：" + item.getOrder_sn());
            holder.setText(R.id.item_ras_order_status, item.getOrder_status());
            holder.setText(R.id.item_ras_order_type, item.getService_name());
            holder.setText(R.id.item_ras_order_goods_name, item.getShop_name());
            String price = item.getOrder_amount();
            String[] split = price.split("\\.");
            holder.setText(R.id.item_ras_tv_order_money, "¥" + split[0]);
            holder.setText(R.id.item_ras_tv_price_point_or_xidou, "." + split[1]);
            holder.findViewById(R.id.iv_delete).setOnClickListener(v -> {
                BaseDialog baseDialog = new BaseDialog(mContext);
                baseDialog.setTitle("确认删除此订单");
                baseDialog.setLeftAndRight("取消", "删除");
                baseDialog.setDialogButtonOnclick(new BaseDialog.DialogButtonOnclick() {
                    @Override
                    public void onLeftOnclick() {
                        baseDialog.cancel();
                    }

                    @Override
                    public void onRightOncklick() {
                        Retrofit.Builder builder = new Retrofit.Builder();
                        Retrofit retrofit = builder.baseUrl(ApiRest.baseUrl).build();
                        retrofit.create(ApiRest.class).deleteServerOrder(UserData.getUserId(), item.getOrder_sn()).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String string = response.body().string();
                                    JSONObject jsonObject = new JSONObject(string);
                                    if (jsonObject.getBoolean("success")) {
                                        page = 1;
                                        initData();
                                    }
                                        Toast.makeText(mContext, jsonObject.getString("info"), Toast.LENGTH_SHORT).show();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                        baseDialog.cancel();
                    }
                });
                baseDialog.show();
            });
            if ("已取消".equals(item.getOrder_status())) {
                holder.findViewById(R.id.item_ras_order_status).setVisibility(View.GONE);
                holder.setImageResource(R.id.iv_ras_order_status_img, R.drawable.has_cancel_icon);
                holder.findViewById(R.id.iv_ras_order_status_img).setVisibility(View.VISIBLE);
            } else if ("已完成".equals(item.getOrder_status())) {
                holder.findViewById(R.id.item_ras_order_status).setVisibility(View.GONE);
                holder.setImageResource(R.id.iv_ras_order_status_img, R.drawable.has_done_icon);
                holder.findViewById(R.id.iv_ras_order_status_img).setVisibility(View.VISIBLE);
            } else {
                holder.findViewById(R.id.item_ras_order_status).setVisibility(View.VISIBLE);
                holder.findViewById(R.id.iv_ras_order_status_img).setVisibility(View.GONE);
            }
            ImageView img = holder.findViewById(R.id.item_ras_goods_img);
            Glide.with(RushCarOrderFragment.this.getActivity())
                    .load(item.getCate_img()).placeholder(R.drawable.no_data_empty).centerCrop()
                    .into(img);
        }
    }
}
