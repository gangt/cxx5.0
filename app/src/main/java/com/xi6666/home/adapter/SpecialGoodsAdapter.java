package com.xi6666.home.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xi6666.R;
import com.xi6666.databean.HomeSpecialBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/17.
 */

public class SpecialGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<HomeSpecialBean.DataBean> data;
    private Context mContext;

    public void setData(List<HomeSpecialBean.DataBean> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();


        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_head_special, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        ((MyViewHolder)holder).mRlvHomeHeadSpecial.setLayoutManager(linearLayoutManager);
        HomeHeadSpecialGoodsAdapter homeHeadSpecialGoodsAdapter = new HomeHeadSpecialGoodsAdapter();
        homeHeadSpecialGoodsAdapter.setGoodsListBeen(data.get(position).getGoods_list());
        ((MyViewHolder)holder).mRlvHomeHeadSpecial.setAdapter(homeHeadSpecialGoodsAdapter);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_home_head_special)
        ImageView mIvHomeHeadSpecial;
        @BindView(R.id.rlv_home_head_special)
        RecyclerView mRlvHomeHeadSpecial;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
