package com.xi6666.cardbag.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.databean.WashCardInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/14.
 */

public class WashCardInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<WashCardInfoBean.DataBean> mData = new ArrayList<>();

    public void setData(List<WashCardInfoBean.DataBean> data) {
        mData.addAll(data);
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_washcardinfo, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).mTxtWashcardinfoCardnum.setText(mData.get(position).getWashcard_id());
        ((MyViewHolder) holder).mTxtWashcardinfoBalance.setText(mData.get(position).getWashcard_cash_amount());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_washcardinfo_cardnum)
        TextView mTxtWashcardinfoCardnum;
        @BindView(R.id.txt_washcardinfo_balance)
        TextView mTxtWashcardinfoBalance;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
