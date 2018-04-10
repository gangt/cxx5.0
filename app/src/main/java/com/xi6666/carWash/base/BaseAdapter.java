package com.xi6666.carWash.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/10 下午2:40.
 * 个人公众号 ardays
 *
 * 适配器父类
 *
 * 1.增加 增加 修改 删除
 *
 */

public abstract class BaseAdapter<T, D> extends RecyclerView.Adapter implements BaseAdapterImpl<D> {

    /**
     * 数据Bean
     */
    private List<T> mDatas;





    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
