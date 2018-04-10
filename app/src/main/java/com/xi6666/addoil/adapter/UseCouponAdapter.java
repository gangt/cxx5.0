package com.xi6666.addoil.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.databean.UseCouponBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2017/2/24.
 */

public class UseCouponAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<UseCouponBean.DataBean> itemData;
    private OnItemClickListener mOnItemClickListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_coupon_use, null));
    }

    public void setItemData(List<UseCouponBean.DataBean> itemData) {
        this.itemData = itemData;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //设置金额
        ((MyViewHolder) holder).mTxtCouponMoney.setText(itemData.get(position).getCoupon_money());
        //设置名称
        ((MyViewHolder) holder).mTxtCouponName.setText(itemData.get(position).getCoupon_name());
        //日期
        ((MyViewHolder) holder).mTxtCouponData.setText(itemData.get(position).getSdate() + "--" + itemData.get(position).getEdate());
        if (itemData.get(position).getCoupon_platform() == 0) {
            ((MyViewHolder) holder).mTxtCouponUse.setText("");
        }
        //使用设备
        if (itemData.get(position).getCoupon_platform() == 1) {
            ((MyViewHolder) holder).mTxtCouponUse.setText("仅限Android手机使用");
        }
        if (itemData.get(position).getCoupon_platform() == 2) {
            ((MyViewHolder) holder).mTxtCouponUse.setText("仅限Ios手机使用");
        }
        if (itemData.get(position).getCoupon_platform() == 3) {
            ((MyViewHolder) holder).mTxtCouponUse.setText("仅限微信端使用");
        }
        //设置使用范围
        switch (itemData.get(position).getCoupon_use_type()) {
            case "1":
                ((MyViewHolder) holder).mTxtCouponCanuse.setText("全部适用");
                break;
            case "2":
                ((MyViewHolder) holder).mTxtCouponCanuse.setText("加油套餐充值可用");
                break;
            case "3":
                ((MyViewHolder) holder).mTxtCouponCanuse.setText("门店服务项目可用");
                break;
            case "4":
                ((MyViewHolder) holder).mTxtCouponCanuse.setText("商城购物可用");
                break;
            case "5":
                ((MyViewHolder) holder).mTxtCouponCanuse.setText("违章卡充值可用");
                break;
        }
        ((MyViewHolder) holder).itemView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(itemData.get(position).getId(), itemData.get(position).getCoupon_type());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_coupon_money)
        TextView mTxtCouponMoney;
        @BindView(R.id.txt_coupon_canuse)
        TextView mTxtCouponCanuse;
        @BindView(R.id.ic_coupon_list_dai)
        TextView mIcCouponListDai;
        @BindView(R.id.txt_coupon_name)
        TextView mTxtCouponName;
        @BindView(R.id.txt_coupon_use)
        TextView mTxtCouponUse;
        @BindView(R.id.txt_coupon_data)
        TextView mTxtCouponData;
        @BindView(R.id.iv_coupon_state)
        ImageView mIvCouponState;
        @BindView(R.id.txt_coupon_gouse)
        TextView mTxtCouponGouse;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String couponId, String couPonType);
    }

}
