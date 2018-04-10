package com.xi6666.order.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
import com.xi6666.order.activity.GoodsOrderDetailActivity;
import com.xi6666.order.bean.GoodsOrderBean;
import com.xi6666.order.other.CustomAdapter;
import com.xi6666.order.other.CustomViewHolder;
import com.xi6666.order.other.HorizontalListView;
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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * 作者： hydCoder on 2016/11/4
 * 描述：${DESC}
 */

public class GoodsOrderFragment extends Fragment implements XRecyclerView.LoadingListener {

    private XRecyclerView mXRecyclerView;
    private LinearLayout mLlNoOrder;
    private LinearLayout mLlReloadData;
    private Button mBtnReloadData;

    private MyAdapter mAdapter;
    private List<GoodsOrderBean.DataBean> mGoodsList = new ArrayList<>();
    private DecimalFormat mFormat = new DecimalFormat("###0.00");
    private GoodsOrderBean mGoodsOrderBean;

    private int page = 1;
    private String user_id;
    private String user_token;
    private Dialog mLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_goods_order, container, false);
        mXRecyclerView = (XRecyclerView) mRootView.findViewById(R.id.xRecyclerView);
        mLlNoOrder = (LinearLayout) mRootView.findViewById(R.id.ll_no_order);
        mLlReloadData = (LinearLayout) mRootView.findViewById(R.id.ll_reload_data);
        mBtnReloadData = (Button) mRootView.findViewById(R.id.btn_reload_data);
        init();
        initData();
        initEvent();
        return mRootView;
    }

    private void initEvent() {
        mAdapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int posotion) {
                Intent intent = new Intent(GoodsOrderFragment.this.getActivity(), GoodsOrderDetailActivity.class);
                intent.putExtra("order_sn", mGoodsList.get(posotion - 1).getOrder_sn());
                startActivity(intent);
            }
        });
    }

    private void initData() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).getGoodsOrder(page + "", user_id, user_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    getActivity().runOnUiThread(() -> {
                        if (mLoading.isShowing()) {
                            mLoading.dismiss();
                        }
                    });
                    String result = response.body().string();
                    LogUtil.i("GoodsOrderFragment", result);
                    mGoodsOrderBean = GsonUtils.toEntityFromJson(result, GoodsOrderBean.class);
                    if (mGoodsOrderBean.isSuccess()) {
                        String count = mGoodsOrderBean.getCount();
                        if (!"0".equals(count)) {
                            List<GoodsOrderBean.DataBean> data = mGoodsOrderBean.getData();
                            if (page == 1) {
                                if (data.size() > 0) {
                                    mGoodsList.clear();
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
                                mGoodsList.addAll(data);
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
                        Utils.make(GoodsOrderFragment.this.getActivity(), mGoodsOrderBean.getInfo());
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

    private void init() {
        mLoading = ShowDialogUitls.showDio(getActivity());
        user_id = UserData.getUserId();
        user_token = UserData.getUserToken();
        mXRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mXRecyclerView.setLoadingMoreEnabled(true);
        mXRecyclerView.setPullRefreshEnabled(true);
        mAdapter = new MyAdapter(getActivity(), mGoodsList, R.layout.item_goods_order);
        mXRecyclerView.addItemDecoration(new SpaceItemDecoration(getActivity(), 11, mAdapter));
        mXRecyclerView.setAdapter(mAdapter);

        mXRecyclerView.setLoadingListener(this);

        mLlReloadData.setVisibility(View.GONE);
        mXRecyclerView.setVisibility(View.GONE);
        mLlNoOrder.setVisibility(View.GONE);

        mBtnReloadData.setOnClickListener(v -> {
            initData();
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

    class MyAdapter extends CustomAdapter<GoodsOrderBean.DataBean> {

        /**
         * 构造器
         *
         * @param context
         * @param mDatas
         * @param itemLayoutId
         */
        protected MyAdapter(Context context, List<GoodsOrderBean.DataBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        /**
         * @param holder itemHolder
         * @param item   每一Item显示的数据
         */
        @Override
        public void convert(CustomViewHolder holder, GoodsOrderBean.DataBean item) {
            holder.setText(R.id.item_order_num, "订单编号：" + item.getOrder_sn());
            holder.setText(R.id.item_order_status, item.getOrder_state());
            int goods_count = item.getGoods_count();
            HorizontalListView listView = holder.findViewById(R.id.more_goods_view);
            ImageView ivDelete = holder.findViewById(R.id.iv_delete);
            ivDelete.setOnClickListener(v -> {
                BaseDialog baseDialog = new BaseDialog(mContext);
                baseDialog.setTitle("确认删除此订单");
                baseDialog.setLeftAndRight("取消", "删除");
                baseDialog.setDialogButtonOnclick(
                        new BaseDialog.DialogButtonOnclick() {
                            @Override
                            public void onLeftOnclick() {
                                baseDialog.cancel();
                            }

                            @Override
                            public void onRightOncklick() {
                                Retrofit.Builder builder = new Retrofit.Builder();
                                Retrofit retrofit = builder.baseUrl(ApiRest.baseUrl).build();
                                retrofit.create(ApiRest.class).deleteGoodsOrder(UserData.getUserId(), item.getOrder_sn()).enqueue(new Callback<ResponseBody>() {
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

                        }

                );
                baseDialog.show();
            });

            List<GoodsOrderBean.DataBean.GoodsInfoBean> goods_info = item.getGoods_info();
            if (goods_count > 1) {
                holder.findViewById(R.id.ll_one_good).setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                ArrayList<String> goodsImgs = new ArrayList<>();
                for (int i = 0; i < goods_count; i++) {
                    goodsImgs.add(goods_info.get(i).getGoods_soure_img());
                }
                listView.setAdapter(new ImageAdapter(goodsImgs));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(GoodsOrderFragment.this.getActivity(), GoodsOrderDetailActivity.class);
                        intent.putExtra("order_sn", mGoodsList.get(holder.getAdapterPosition() - 1).getOrder_sn());
                        startActivity(intent);
                    }
                });
            } else {
                holder.findViewById(R.id.ll_one_good).setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                if (goods_info.size() != 0) {
                    holder.setText(R.id.item_order_goods_name, goods_info.get(0).getGoods_name());
                    ImageView img = holder.findViewById(R.id.item_goods_img);
                    Glide.with(GoodsOrderFragment.this.getActivity()).load(goods_info.get(0).getGoods_soure_img())
                            .placeholder(R.drawable.no_data_empty).centerCrop()
                            .into(img);
                }
            }
            holder.setText(R.id.tv_order_goods_num, "共" + goods_count + "件");
//            String price = mFormat.format(item.getMoney_paid());
            String price = item.getOrder_total();
            if (price != null) {
                String[] split = price.split("\\.");
                holder.setText(R.id.item_tv_order_money, "¥" + split[0]);
                holder.setText(R.id.item_tv_price_point_or_xidou, "." + split[1]);
            }
            if ("已取消".equals(item.getOrder_state())) {
                holder.findViewById(R.id.item_order_status).setVisibility(View.GONE);
                holder.setImageResource(R.id.iv_order_status_img, R.drawable.has_cancel_icon);
                holder.findViewById(R.id.iv_order_status_img).setVisibility(View.VISIBLE);
            } else if ("已完成".equals(item.getOrder_state())) {
                holder.findViewById(R.id.item_order_status).setVisibility(View.GONE);
                holder.setImageResource(R.id.iv_order_status_img, R.drawable.has_done_icon);
                holder.findViewById(R.id.iv_order_status_img).setVisibility(View.VISIBLE);
            } else {
                holder.findViewById(R.id.item_order_status).setVisibility(View.VISIBLE);
                holder.findViewById(R.id.iv_order_status_img).setVisibility(View.GONE);
            }
        }
    }

    class ImageAdapter extends BaseAdapter {
        private List<String> mDatas;

        public ImageAdapter(List<String> datas) {
            this.mDatas = datas;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(GoodsOrderFragment.this.getActivity(), R.layout.item_images, null);
                holder = new ViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.item_more_img);
                ViewGroup.LayoutParams layoutParams = holder.img.getLayoutParams();
                DisplayMetrics dpMetrics = new DisplayMetrics();
                getActivity().getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
                int screenWidth = dpMetrics.widthPixels;
                layoutParams.width = (int) (screenWidth / 4.5);
                holder.img.setLayoutParams(layoutParams);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.img.setPadding(Utils.dp2Px(GoodsOrderFragment.this.getActivity(), 18), 0, 0, 0);

            Glide.with(GoodsOrderFragment.this.getActivity())
                    .load(mDatas.get(position)).placeholder(R.drawable.no_data_empty).centerCrop()
                    .into(holder.img);
            return convertView;
        }
    }

    class ViewHolder {
        ImageView img;
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }
}
