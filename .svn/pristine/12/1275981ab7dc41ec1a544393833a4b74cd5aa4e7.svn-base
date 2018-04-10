package com.xi6666.productdetails.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.databean.PromotionBean;
import com.xi6666.html5show.view.HtmlAct;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2017/1/17.
 */

public class PromotionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PromotionBean.DiscountListBean> mPromotionBeen;

    private Context mContext;

    public void setPromotionBeen(List<PromotionBean.DiscountListBean> promotionBeen) {
        mPromotionBeen = promotionBeen;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_promotion, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).mTxtProductFlag.setText(mPromotionBeen.get(position).getActive_type_name());
        ((MyViewHolder) holder).mTxtProductPromotion.setText(mPromotionBeen.get(position).getActive_name());
        ((MyViewHolder) holder).itemView.setOnClickListener(v -> {
            HtmlAct.unsealActivity((Activity) mContext, mPromotionBeen.get(position).getActive_url() + "?get_device_type=android");
        });
    }

    @Override
    public int getItemCount() {
        return mPromotionBeen.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_product_type)
        TextView mTxtProductType;
        @BindView(R.id.txt_product_flag)
        TextView mTxtProductFlag;
        @BindView(R.id.txt_product_promotion)
        TextView mTxtProductPromotion;
        @BindView(R.id.imageView)
        ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
