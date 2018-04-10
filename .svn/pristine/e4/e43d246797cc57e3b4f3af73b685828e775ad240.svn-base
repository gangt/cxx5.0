package com.xi6666.happybeans.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.databean.HappyBeansBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/22.
 */

public class HappyBeansAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;

    private List<HappyBeansBean.DataBean.XidouListBean> mDataBeen;

    public void setDataBeen(List<HappyBeansBean.DataBean.XidouListBean> dataBeen) {
        mDataBeen = dataBeen;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new MyViewHoler(LayoutInflater.from(mContext).inflate(R.layout.item_happybeans, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataBeen.get(position).getAdd_datetime().length() > 10) {
            ((MyViewHoler) holder).mTxtHappybeansTime.setText(mDataBeen.get(position).getAdd_datetime().substring(0, 10));
        } else {
            ((MyViewHoler) holder).mTxtHappybeansTime.setText(mDataBeen.get(position).getAdd_datetime());
        }

        String happyBeanNum;

        String substring = mDataBeen.get(position).getXidou_num().substring(0, 1);
        if (TextUtils.equals(substring, "-")) {
            happyBeanNum = mDataBeen.get(position).getXidou_num();
        } else {
            happyBeanNum = "+" + mDataBeen.get(position).getXidou_num();
        }
        ((MyViewHoler) holder).mTxtHappybeansNum.setText(happyBeanNum + "个");
        ((MyViewHoler) holder).mTxtHappybeansDescribe.setText(mDataBeen.get(position).getXidou_info());
    }

    @Override
    public int getItemCount() {
        return mDataBeen.size();
    }

    public class MyViewHoler extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_happybeans_time)
        TextView mTxtHappybeansTime;
        @BindView(R.id.txt_happybeans_num)
        TextView mTxtHappybeansNum;
        @BindView(R.id.txt_happybeans_describe)
        TextView mTxtHappybeansDescribe;

        public MyViewHoler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
