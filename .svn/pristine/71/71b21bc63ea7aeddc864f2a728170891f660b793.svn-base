package com.xi6666.technician.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.order.activity.OrderSeeLagerImgActivity;
import com.xi6666.store.custom.StoreEvaluateView;
import com.xi6666.technician.mvp.bean.TechnicianDetailsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 2016/11/29 下午3:41.
 * 个人公众号 ardays
 */

public class TechnicianAnswerAdapter extends RecyclerView.Adapter {

    public Context mContext;

    List<TechnicianDetailsBean.DataBean.WendaInfoBean> mData;  //评论数量

    public TechnicianAnswerAdapter(Context context) {
        this.mContext = context;
        mData = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_answer, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            onTechnicianAnswerHolder((ViewHolder) holder, position);
        }
    }

    private void onTechnicianAnswerHolder(ViewHolder holder, int position) {
        //添加数据
        TechnicianDetailsBean.DataBean.WendaInfoBean data = mData.get(position);
        //写入电话
        holder.mTelTv.setText(data.user_mobile);
        //写入时间
        holder.mTimeTv.setText(data.add_datetime);
        //写入点赞数
        holder.mDzCk.setChecked(data.zandot.equals("1"));
        //点赞回调事件
        holder.mDzCk.setOnClickListener(v -> {
            holder.mDzCk.setChecked(!holder.mDzCk.isChecked());
            if (mTechnicianAnswerAdapterListener != null)
                mTechnicianAnswerAdapterListener.onLikesClick(!holder.mDzCk.isChecked(), data.ques_id, position);
        });
        //是否点赞中
        //写入文本
        holder.mQuesTv.setText(data.ques_content);
        //写入回答
        if(TextUtils.isEmpty(data.anwser_content)){
            holder.mAnwserTv.setVisibility(View.GONE);
        }else {
            holder.mAnwserTv.setText("技师回复:" + data.anwser_content);
        }
        //写入图片
        holder.mImageRv.setLayoutManager(new GridLayoutManager(mContext, 3));
        holder.mImageRv.setAdapter(new StoreEvaluateAdapter(data.pl_pics));
    }

    /**
     * 写入点赞
     *
     * @param bol      true 点赞 false 取消点赞
     * @param position 下标
     */
    public void setLikes(boolean bol, int position) {
        mData.get(position).zandot = bol ? "1" : "0";   //点赞
        notifyDataSetChanged();
    }


    /**
     * 添加数据
     */
    public void addData(List<TechnicianDetailsBean.DataBean.WendaInfoBean> bean) {
        this.mData.addAll(bean);
        notifyDataSetChanged();
    }


    /**
     * 返回总页数
     * @return
     */
    public int getSize(){
        return mData.size();
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    private TechnicianAnswerAdapterListener mTechnicianAnswerAdapterListener;

    public void setTechnicianAnswerAdapterListener(TechnicianAnswerAdapterListener listener) {
        this.mTechnicianAnswerAdapterListener = listener;
    }

    public interface TechnicianAnswerAdapterListener {
        /**
         * 点赞回调
         */
        void onLikesClick(boolean likes, String ques_id, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_answer_image_rv)
        RecyclerView mImageRv;  //图片数组
        @BindView(R.id.item_answer_tel_tv)
        TextView mTelTv;    //电话文本
        @BindView(R.id.item_answer_time_tv)
        TextView mTimeTv;   //时间文本
        @BindView(R.id.item_answer_ck)
        CheckBox mDzCk; //点赞
        @BindView(R.id.item_answer_ques_tv)
        TextView mQuesTv;   //提问
        @BindView(R.id.item_anwser_tv)
        TextView mAnwserTv; //答案文本

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    //车主评价晒图的适配器
    class StoreEvaluateAdapter extends RecyclerView.Adapter {

        public ArrayList<String> mImages;

        public StoreEvaluateAdapter(List<String> images) {
            mImages = new ArrayList<>();
            mImages.addAll(images);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LinearLayout.inflate(mContext, R.layout.item_store_evaluate_img, null);
            return new StoreEvaluateViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof StoreEvaluateViewHolder) {
                onStoreDetailsData((StoreEvaluateViewHolder) holder, position);
            }
        }

        /**
         * 添加图片适配器
         */
        private void onStoreDetailsData(StoreEvaluateViewHolder holder, int position) {
            //获取Url
            String imageUrl = mImages.get(position);
            //写入图片
            Glide.with(mContext)
                    .load(imageUrl)
                    .placeholder(R.drawable.bg_image_default)
                    .error(R.drawable.bg_image_default)
                    .into(holder.mStoreEvaluateIv);

            holder.mStoreEvaluateIv.setOnClickListener(v ->{
                Intent intent = new Intent(mContext,OrderSeeLagerImgActivity.class);
                intent.putExtra("picS",mImages);
                intent.putExtra("position",position);
                mContext.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return mImages.size();
        }

        class StoreEvaluateViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.item_store_evaluate_iv)
            ImageView mStoreEvaluateIv;

            public StoreEvaluateViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

        }
    }

}
