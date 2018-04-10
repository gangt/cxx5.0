package com.xi6666.classification.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xi6666.classification.view.custom.ItemServiceClassificationBrandView;
import com.xi6666.classification.view.custom.ServiceClassificationView;
import com.xi6666.classification.view.fragment.mvp.bean.ServiceClassificationBean;
import com.xi6666.classification.view.fragment.mvp.bean.ServiceClassificationBrandBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/12 下午1:51.
 * 个人公众号 ardays
 */

public class ServiceClassificationBrandAdapter extends RecyclerView.Adapter {

    private Context mContext;

    List<ServiceClassificationBrandBean.DataBean> mDatas;

    public ServiceClassificationBrandAdapter(Context context) {
        this.mContext = context;
        mDatas = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemServiceClassificationBrandView view = new ItemServiceClassificationBrandView(mContext);
        return new ServiceClassificationBrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ServiceClassificationBrandBean.DataBean data = mDatas.get(position);
        //设置数据源
        ((ServiceClassificationBrandViewHolder) holder).mView.setData(data);
        ((ServiceClassificationBrandViewHolder) holder).mView.setOnImageItemClick(
                new ItemServiceClassificationBrandView.BrandAdapter.BrandAdapterListener() {

                    @Override
                    public void onImageItemClick(int index) {
                        if (mServiceClassificationBrandAdapterListener != null)
                            mServiceClassificationBrandAdapterListener.onImageItemClick(data.list.get(index));
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
    public void addAll(List<ServiceClassificationBrandBean.DataBean> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }


    class ServiceClassificationBrandViewHolder extends RecyclerView.ViewHolder {

        ItemServiceClassificationBrandView mView;

        public ServiceClassificationBrandViewHolder(ItemServiceClassificationBrandView itemView) {
            super(itemView);
            this.mView = itemView;
        }
    }

    public ServiceClassificationBrandAdapterListener mServiceClassificationBrandAdapterListener;
    public void setServiceClassificationBrandAdapterListener(
            ServiceClassificationBrandAdapterListener listener){
        this.mServiceClassificationBrandAdapterListener = listener;
    }
    public interface ServiceClassificationBrandAdapterListener{
        void onImageItemClick(ServiceClassificationBrandBean.DataBean.ListBean data);
    }

    public void setContext(Context context){
        this.mContext = context;
        notifyDataSetChanged();
    }
}



