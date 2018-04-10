package com.xi6666.address.fragment.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.address.fragment.mvp.bean.DistributionShopBean;
import com.xi6666.app.BaseApplication;
import com.xi6666.order.other.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/25 下午7:16.
 * 个人公众号 ardays
 */

public class DistributionShopAdapter extends RecyclerView.Adapter {

    public Activity mContext;
    List<DistributionShopBean.DataBean> mDatas;

    public DistributionShopAdapter(Activity context) {
        this.mContext = context;
        mDatas = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_distribution_shop, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            onAddressViewHolder((ViewHolder) holder, position);
        }
    }

    private void onAddressViewHolder(ViewHolder holder, int position) {
        DistributionShopBean.DataBean data = mDatas.get(position);
        //写入头像
        Glide.with(mContext)
                .load(data.shop_banner)
                .placeholder(R.drawable.bg_image_default)
                .error(R.drawable.bg_image_default)
                .centerCrop()
                .into(holder.mHeadIv);

        //写入标题
        holder.mTitleTv.setText(data.shop_name);

        //写入地址
        holder.mDetailsTv.setText(data.shop_address);

        //写入距离
        holder.mDistanceTv.setText(data.distance);

        holder.itemView.setOnClickListener(v -> {
            if (mOnDistributionShopAdapterListener != null)
                mOnDistributionShopAdapterListener.onItemView(data);
        });
        //拨打电话
        holder.mTelIv.setOnClickListener(v -> {
            Utils.callTel(data.shop_tel, mContext);
        });

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addAll(List<DistributionShopBean.DataBean> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    public void update(List<DistributionShopBean.DataBean> data) {
        mDatas.clear();
        addAll(data);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.service_store_head_iv)
        ImageView mHeadIv;  //头像
        @BindView(R.id.service_store_title_tv)
        TextView mTitleTv;  //标题
        @BindView(R.id.service_store_details_tv)
        TextView mDetailsTv;//详细
        @BindView(R.id.service_store_distance_tv)
        TextView mDistanceTv;//距离
        @BindView(R.id.service_store_cell_iv)
        ImageView mTelIv;   //拨打电话

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnDistributionShopAdapterListener mOnDistributionShopAdapterListener;

    public void setOnDistributionShopAdapterListener(OnDistributionShopAdapterListener listener) {
        this.mOnDistributionShopAdapterListener = listener;
    }

    public interface OnDistributionShopAdapterListener {
        /**
         * 单条点击
         */
        void onItemView(DistributionShopBean.DataBean data);
    }
}
