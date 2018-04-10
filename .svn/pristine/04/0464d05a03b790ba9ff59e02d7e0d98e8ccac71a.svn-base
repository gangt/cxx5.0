package com.xi6666.store.adapter;

/**
 * 创建者 sunsun
 * 时间 16/11/10 下午2:35.
 * 个人公众号 ardays
 * <p>
 * 评价列表的适配器
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.store.custom.StoreEvaluateView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class EvaluateAdapter extends RecyclerView.Adapter {


    //上下文
    private Context mContext;
    //数据Bean
    private List<StoreDetailsBean.DataBean.DiscussinfoBean> mDatas;

    public EvaluateAdapter(Context context) {
        this.mContext = context;
        this.mDatas = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StoreEvaluateView view = new StoreEvaluateView(mContext);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        parent.addView(view, params);
        return new StoreEvaluateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StoreEvaluateViewHolder) {
            setStoreEvaluateData((StoreEvaluateViewHolder) holder, position);
        }
    }


    private void setStoreEvaluateData(StoreEvaluateViewHolder holder, int position) {
        holder.mView.setCommentData(mDatas.get(position));

        //点赞
        holder.mView.setOnStoreEvaluateViewListner(new StoreEvaluateView.OnStoreEvaluateViewListner() {
            //监听赞的点击事件
            @Override
            public void onLikesClick(boolean bol, String d) {
                if (mOnEvaluateAdapterListener != null)
                    mOnEvaluateAdapterListener.OnLikesClick(!bol, position, d);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mDatas.size();
    }


    /**
     * 添加数据
     */
    public void addData(List<StoreDetailsBean.DataBean.DiscussinfoBean> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 刷新数据
     */
    public void updata(List<StoreDetailsBean.DataBean.DiscussinfoBean> data) {
        mDatas.clear();
        addData(data);
    }

    public void setLikes(boolean bol, int position) {
        mDatas.get(position).zandot = bol ? "1" : "0";
        notifyDataSetChanged();
    }




    class StoreEvaluateViewHolder extends RecyclerView.ViewHolder {

        StoreEvaluateView mView;

        public StoreEvaluateViewHolder(StoreEvaluateView itemView) {
            super(itemView);
            this.mView = itemView;
        }
    }


    public OnEvaluateAdapterListener mOnEvaluateAdapterListener;

    public void setOnEvaluateAdapterListener(OnEvaluateAdapterListener listener) {
        this.mOnEvaluateAdapterListener = listener;
    }

    public interface OnEvaluateAdapterListener {
        /**
         * 点赞
         *
         * @param likes    点赞
         * @param position 下标
         */
        void OnLikesClick(boolean likes, int position, String discuss_id);
    }

}