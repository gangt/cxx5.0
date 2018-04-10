package com.xi6666.carWash.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xi6666.carWash.view.custom.StoreView;
import com.xi6666.store.mvp.bean.StoreServiceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/1 上午10:19.
 * 个人公众号 ardays
 */

public class CarWashAdapter extends RecyclerView.Adapter {

    public Context mContext;

    List<StoreServiceBean.DataBean> mDatas;

    public CarWashAdapter(Context context) {
        this.mContext = context;
        this.mDatas = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CarWashViewHolder(new StoreView(mContext));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CarWashViewHolder) {
            onCarWashViewHolder((CarWashViewHolder) holder, position);
        }
    }

    private void onCarWashViewHolder(CarWashViewHolder holder, int position) {
        StoreServiceBean.DataBean data = mDatas.get(position);
        holder.mView.setData(data);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public void addAll(List<StoreServiceBean.DataBean> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    public void update(List<StoreServiceBean.DataBean> data) {
        mDatas.clear();
        addAll(data);
    }


    class CarWashViewHolder extends RecyclerView.ViewHolder {
        StoreView mView;

        public CarWashViewHolder(StoreView itemView) {
            super(itemView);
            this.mView = itemView;
        }
    }
}
