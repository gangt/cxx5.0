package com.xi6666.cardbag.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.addoil.adapter.UseCouponAdapter;
import com.xi6666.addoil.view.AddoOilAct;
import com.xi6666.databean.CouponBean;
import com.xi6666.order.other.SwipeMenuLayout;
import com.xi6666.producthome.view.ProductHomeAct;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2017/1/17.
 */

public class CouponAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;

    private List<CouponBean.DataBean> dataBean;

    private onItemDeleteListener mOnItemDeleteListener;


    public void setDataBean(List<CouponBean.DataBean> dataBean) {
        this.dataBean = dataBean;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_coupon_list, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //设置价格
        ((MyViewHolder) holder).mTxtCouponMoney.setText(dataBean.get(position).getPrice() + "");
        //设置名称
        ((MyViewHolder) holder).mTxtCouponName.setText(dataBean.get(position).getCoupon_name());
        //使用客户端
        if (dataBean.get(position).getCoupon_platform() == 0) {
            ((MyViewHolder) holder).mTxtCouponUse.setText("");
        }
        if (dataBean.get(position).getCoupon_platform() == 1) {
            ((MyViewHolder) holder).mTxtCouponUse.setText("仅限Android手机使用");
        }
        if (dataBean.get(position).getCoupon_platform() == 2) {
            ((MyViewHolder) holder).mTxtCouponUse.setText("仅限Ios手机使用");
        }
        if (dataBean.get(position).getCoupon_platform() == 3) {
            ((MyViewHolder) holder).mTxtCouponUse.setText("仅限微信端使用");
        }
        //日期
        ((MyViewHolder) holder).mTxtCouponData.setText(dataBean.get(position).getSdate() + "--" + dataBean.get(position).getEdate());
        //侧滑删除
        ((MyViewHolder) holder).mDelete.setOnClickListener(v -> {
            if (mOnItemDeleteListener != null) {
                mOnItemDeleteListener.onItemDelete(position, dataBean.get(position).getCoupon_id());
            }
        });
        //优惠卡是加油卡
        if (TextUtils.equals(dataBean.get(position).getCoupon_type(), "3")) {

            ((MyViewHolder) holder).mCouponGoUse.setOnClickListener(v -> {
                mContext.startActivity(new Intent(mContext, AddoOilAct.class));
            });
        }
        //优惠卡是商场券
        if (TextUtils.equals(dataBean.get(position).getCoupon_type(), "1")) {

            ((MyViewHolder) holder).mCouponGoUse.setOnClickListener(v -> {
                mContext.startActivity(new Intent(mContext, ProductHomeAct.class));
            });
        }

        //是否过期等等
        switch (dataBean.get(position).getCoupon_status()) {
            //1未使用
            case 1:
                ((MyViewHolder) holder).mCouponGoUse.setVisibility(View.VISIBLE);
                ((MyViewHolder) holder).mState.setVisibility(View.GONE);
                break;
            //2已使用
            case 2:
                ((MyViewHolder) holder).mRelativeLayoutBg.setBackgroundResource(R.drawable.bg_coupon_list_gray);
                ((MyViewHolder) holder).mCouponGoUse.setVisibility(View.GONE);
                ((MyViewHolder) holder).mState.setVisibility(View.VISIBLE);
                ((MyViewHolder) holder).mState.setImageResource(R.drawable.ic_addoil_used);
                //bg_coupon_type_gray
                ((MyViewHolder) holder).mIcCouponListDai.setBackgroundResource(R.drawable.bg_coupon_type_gray);
                break;
            //3已过期
            case 3:
                ((MyViewHolder) holder).mRelativeLayoutBg.setBackgroundResource(R.drawable.bg_coupon_list_gray);
                ((MyViewHolder) holder).mCouponGoUse.setVisibility(View.GONE);
                ((MyViewHolder) holder).mState.setVisibility(View.VISIBLE);
                ((MyViewHolder) holder).mState.setImageResource(R.drawable.ic_addoil_overdue);
                
                ((MyViewHolder) holder).mIcCouponListDai.setBackgroundResource(R.drawable.bg_coupon_type_gray);
                break;
            //4已赠送
            case 4:
                break;
        }

        //设置使用范围
        //设置使用范围
        switch (dataBean.get(position).getCoupon_use_type()) {
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

    }

    @Override
    public int getItemCount() {
        return dataBean.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

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
        @BindView(R.id.sml_coupon)
        SwipeMenuLayout mSwipeMenuLayout;
        @BindView(R.id.iv_coupon_delete)
        ImageView mDelete;
        @BindView(R.id.txt_coupon_gouse)
        TextView mCouponGoUse;
        @BindView(R.id.iv_coupon_state)
        ImageView mState;
        @BindView(R.id.rl_coupon_bg)
        RelativeLayout mRelativeLayoutBg;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void setOnItemDeleteListener(onItemDeleteListener onItemDeleteListener) {
        mOnItemDeleteListener = onItemDeleteListener;
    }

    public interface onItemDeleteListener {
        void onItemDelete(int position, String coupon_id);
    }

}
