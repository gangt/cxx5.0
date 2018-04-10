package com.xi6666.home.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.common.BuriedPoint;
import com.xi6666.databean.HomeSpecialBean;
import com.xi6666.productdetails.view.ProductDetailsAct;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.scaleX;

/**
 * Created by Mr_yang on 2016/11/7.
 */

public class HomeHeadSpecialGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeSpecialBean.DataBean.GoodsListBean> mGoodsListBeen;
    private int mPrentPosition;
    private Context mContext;
    private boolean isProductHome = false;

    public void setProductHome(boolean home) {
        isProductHome = home;
    }

    public void setPrentPosition(int prentPosition) {
        mPrentPosition = prentPosition;
    }

    public void setGoodsListBeen(List<HomeSpecialBean.DataBean.GoodsListBean> goodsListBeen) {
        mGoodsListBeen = goodsListBeen;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHodler(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_head_special_goods, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Glide.with(mContext).load(mGoodsListBeen.get(position).getGoods_thumb_img()).into(((ViewHodler) holder).mIvHomeSpecial);
        ((ViewHodler) holder).mTxtHomeSpecialName.setText(mGoodsListBeen.get(position).getGoods_name());
        ((ViewHodler) holder).mTxtHomeSpecialPrice.setText("¥" + mGoodsListBeen.get(position).getShop_price());

        if (isProductHome) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ((ViewHodler) holder).mLinearLayoutRoot.setLayoutParams(layoutParams);
        }
        ((ViewHodler) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BuriedPoint().sendToServer("sy", "zt", "zt" + mPrentPosition + "." + position);
                PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.8f, 1.0f);
                PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.8f, 1.0f);
                ValueAnimator valueAnimator = ObjectAnimator.ofPropertyValuesHolder(((ViewHodler) holder).itemView, scaleX, scaleY);
                valueAnimator.start();
                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent intent = new Intent(mContext, ProductDetailsAct.class);
                        intent.putExtra("goodId", mGoodsListBeen.get(position).getGoods_id());
                        mContext.startActivity(intent);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return mGoodsListBeen.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_home_special)
        ImageView mIvHomeSpecial;
        @BindView(R.id.txt_home_special_name)
        TextView mTxtHomeSpecialName;
        @BindView(R.id.txt_home_special_price)
        TextView mTxtHomeSpecialPrice;
        @BindView(R.id.ll_home_special_root)
        LinearLayout mLinearLayoutRoot;

        public ViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
