package com.xi6666.home.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.databean.HomeHotGoodsBean;
import com.xi6666.utils.DimenUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/7.
 */

public class HomeGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeHotGoodsBean.DataBean> mDataBeen;
    private Context mContext;
    private OnRecyclerViewItemClickListener mOnItemClickListener;


    public void setDataBeen(List<HomeHotGoodsBean.DataBean> dataBeen) {
        mDataBeen = dataBeen;
        this.notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_goods, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).mTxtHomeGoodsName.setText(mDataBeen.get(position).getGoods_name());
        ((ViewHolder) holder).mTxtHomeGoodsPrice.setText("¥" + mDataBeen.get(position).getShop_price());
        SpannableString spannableString = new SpannableString(((ViewHolder) holder).mTxtHomeGoodsPrice.getText());
        spannableString.setSpan(new AbsoluteSizeSpan(DimenUtils.sp2px(mContext, 12)), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(DimenUtils.sp2px(mContext, 12)), ((ViewHolder) holder).mTxtHomeGoodsPrice.getText().
                toString().trim().length() - 2, ((ViewHolder) holder).mTxtHomeGoodsPrice.getText().toString().trim().length()
                , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ((ViewHolder) holder).mTxtHomeGoodsPrice.setText(spannableString);


        TextPaint paint = ((ViewHolder) holder).mTxtHomeGoodsOldPrice.getPaint();
        paint.setAntiAlias(true);
        paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        ((ViewHolder) holder).mTxtHomeGoodsOldPrice.setText("¥" + mDataBeen.get(position).getMarket_price());
        Glide.with(mContext).load(mDataBeen.get(position).getGoods_thumb_img()).into(((ViewHolder) holder).mIvHomeGood);
        ((ViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.9f, 1.0f);
                PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.9f, 1.0f);
                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(((ViewHolder) holder).itemView, scaleX, scaleY);
                objectAnimator.start();
                objectAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mOnItemClickListener.onRecyclerItemClick(position);
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
        return mDataBeen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_home_goods)
        ImageView mIvHomeGood;
        @BindView(R.id.txt_home_goods_price)
        TextView mTxtHomeGoodsPrice;
        @BindView(R.id.txt_home_goods_old_price)
        TextView mTxtHomeGoodsOldPrice;
        @BindView(R.id.txt_home_goods_name)
        TextView mTxtHomeGoodsName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onRecyclerItemClick(int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
