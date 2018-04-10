package com.xi6666.classification.view.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.xi6666.R;
import com.xi6666.classification.view.mvp.bean.BrandDetailsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/8/1 上午10:30.
 * 个人公众号 ardays
 */
public class TypeDetailsAdapter extends RecyclerView.Adapter {

    /**
     * glide加载器
     */
    private RequestManager mGlide;

    private Activity mActivity;


    private List<BrandDetailsBean.DataBean.ListBean> mData;


    public TypeDetailsAdapter(Activity mActivity, RequestManager mGlide) {
        this.mActivity = mActivity;
        this.mGlide = mGlide;
        mData = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_brand_details, parent, false);
        return new TypeDetailsViewHodler(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        //数据
        BrandDetailsBean.DataBean.ListBean data = mData.get(position);
        //设置商品图片
        mGlide.load(data.goods_thumb_img)
                .placeholder(R.drawable.bg_image_default)
                .error(R.drawable.bg_image_default)
                .centerCrop()
                .crossFade(1000)
                .into(((TypeDetailsViewHodler) holder).mTypeDetailsIv);
        //价钱
        ((TypeDetailsViewHodler) holder).mTdTitlteTv.setText("¥" + data.shop_price);
        //店名
        ((TypeDetailsViewHodler) holder).mTdMessageTv.setText(data.goods_name);
        //设置网络价钱
        ((TypeDetailsViewHodler) holder).mTdOriginalTv.setText("¥" + data.market_price);

        ((TypeDetailsViewHodler) holder).itemView.setOnClickListener(v -> {
            //创建一个动画数组
            AnimatorSet animtorSet = new AnimatorSet();
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(((TypeDetailsViewHodler) holder).itemView, "scaleX", 1f, 0.95f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(((TypeDetailsViewHodler) holder).itemView, "scaleY", 1f, 0.95f, 1f);
            animtorSet.setDuration(500);
            animtorSet.setInterpolator(new DecelerateInterpolator());
            animtorSet.play(scaleX).with(scaleY);//两个动画同时开始
            animtorSet.start();
            //动画回调事件
            animtorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (mOnTypeDetailsAdapterListener != null)
                        mOnTypeDetailsAdapterListener.onItemClick(data);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(List<BrandDetailsBean.DataBean.ListBean> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }


    /**
     * 清除所有数据
     */
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }


    /**
     * 更改数据
     */
    public void update(List<BrandDetailsBean.DataBean.ListBean> data) {
        mData = data;
        notifyDataSetChanged();
    }


    class TypeDetailsViewHodler extends RecyclerView.ViewHolder {


        @BindView(R.id.type_details_iv)
        ImageView mTypeDetailsIv;       //图片

        @BindView(R.id.type_details_title)
        TextView mTdTitlteTv;          //价格
        @BindView(R.id.type_details_messages)
        TextView mTdMessageTv;          //内容
        @BindView(R.id.type_details_original)
        TextView mTdOriginalTv;


        public TypeDetailsViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //设置删除线
            mTdOriginalTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    public OnTypeDetailsAdapterListener mOnTypeDetailsAdapterListener;

    public void setOnTypeDetailsAdapterListener(OnTypeDetailsAdapterListener listener) {
        this.mOnTypeDetailsAdapterListener = listener;
    }

    public interface OnTypeDetailsAdapterListener {
        /**
         * 列表点击
         */
        void onItemClick(BrandDetailsBean.DataBean.ListBean bean);
    }

}
