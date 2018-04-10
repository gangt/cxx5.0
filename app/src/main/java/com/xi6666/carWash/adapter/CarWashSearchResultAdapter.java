package com.xi6666.carWash.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.xi6666.carWash.view.custom.CarWashSearchView;
import com.xi6666.classification.view.adapter.TypeDetailsAdapter;
import com.xi6666.store.StoreDetailsAct;
import com.xi6666.store.mvp.bean.StoreServiceBean;
import com.xi6666.store.mvp.bean.StoreServiceTypeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/2 下午2:32.
 * 个人公众号 ardays
 */

public class CarWashSearchResultAdapter extends RecyclerView.Adapter {

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 数据源
     */
    List<StoreServiceBean.DataBean> mDatas;

    public CarWashSearchResultAdapter(Context context) {
        this.mContext = context;
        mDatas = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CarWashViewHolder(new CarWashSearchView(mContext));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((CarWashViewHolder) holder).view.setData(mDatas.get(position));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个动画数组
                AnimatorSet animtorSet = new AnimatorSet();
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(holder.itemView, "scaleX", 1f, 0.95f, 1f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(holder.itemView, "scaleY", 1f, 0.95f, 1f);
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
                        StoreDetailsAct.openActivity(mContext, mDatas.get(position).store_id, mDatas.get(position).service_store_id);
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
        return mDatas.size();
    }


    /**
     * 添加数据
     *
     * @param data
     */
    public void addAll(List<StoreServiceBean.DataBean> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 充值列表
     */
    public void update(List<StoreServiceBean.DataBean> data) {
        mDatas.clear();
        addAll(data);
    }

    class CarWashViewHolder extends RecyclerView.ViewHolder {
        CarWashSearchView view;

        public CarWashViewHolder(CarWashSearchView itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
