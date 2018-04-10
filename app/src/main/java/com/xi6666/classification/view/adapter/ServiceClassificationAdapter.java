package com.xi6666.classification.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xi6666.classification.view.custom.ServiceClassificationView;
import com.xi6666.classification.view.fragment.mvp.bean.ServiceClassificationBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/12 上午11:19.
 * 个人公众号 ardays
 */

public class ServiceClassificationAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ServiceClassificationBean.DataBean> mDatas;

    public ServiceClassificationAdapter(Context context) {
        this.mContext = context;
        mDatas = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ServiceClassificationViewHolder(new ServiceClassificationView(mContext));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ServiceClassificationBean.DataBean data = mDatas.get(position);
        //设置标签
        ((ServiceClassificationViewHolder) holder).mView.setTags(data.child);
        //设置标题
        ((ServiceClassificationViewHolder) holder).mView.setTitle(data.cate_name);
        //设置tag点击事件
        ((ServiceClassificationViewHolder) holder).mView.setOnTagClick(index -> {
            if (mOnServiceClassificationAdapterListenr != null) {
                ServiceClassificationBean.DataBean.ChildBean child = data.child.get(index);
                mOnServiceClassificationAdapterListenr.onTagClick(child);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 添加数据
     */
    public void addAll(List<ServiceClassificationBean.DataBean> data) {

        mDatas.addAll(data);
        notifyDataSetChanged();
    }


    class ServiceClassificationViewHolder extends RecyclerView.ViewHolder {

        public ServiceClassificationView mView;

        public ServiceClassificationViewHolder(ServiceClassificationView itemView) {
            super(itemView);
            this.mView = itemView;
        }
    }

    private OnServiceClassificationAdapterListenr mOnServiceClassificationAdapterListenr;

    public void setOnServiceClassificationAdapterListenr(OnServiceClassificationAdapterListenr listenr) {
        this.mOnServiceClassificationAdapterListenr = listenr;
    }

    public interface OnServiceClassificationAdapterListenr {
        /**
         * 标签点击事件
         */
        void onTagClick(ServiceClassificationBean.DataBean.ChildBean bean);
    }
}


