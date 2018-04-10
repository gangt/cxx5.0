package com.xi6666.cardbag.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.databean.WashCardDetialBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/14.
 */

public class WashCarDetialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<WashCardDetialBean.DataBean> mDataBeen = new ArrayList<>();

    public void setDataBeen(List<WashCardDetialBean.DataBean> dataBeen) {
        mDataBeen = dataBeen;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_washcarddetial, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).mTxtWashcardetialName.setText(mDataBeen.get(position).getSuper_name());
        ((MyViewHolder) holder).mTxtWashcardetialTime.setText(mDataBeen.get(position).getOrder_paytime());
        ((MyViewHolder) holder).mTxtWashcardetialType.setText(mDataBeen.get(position).getService_name());
        ((MyViewHolder) holder).mTxtWashcardetialMoney.setText(mDataBeen.get(position).getOrder_paid());
    }

    @Override
    public int getItemCount() {
        return mDataBeen.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_washcardetial_name)
        TextView mTxtWashcardetialName;
        @BindView(R.id.txt_washcardetial_time)
        TextView mTxtWashcardetialTime;
        @BindView(R.id.txt_washcardetial_type)
        TextView mTxtWashcardetialType;
        @BindView(R.id.txt_washcardetial_money)
        TextView mTxtWashcardetialMoney;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
