package com.xi6666.message.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.databean.MessageBean;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/2.
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MessageBean.DataBean> mMessageBean;

    public void setDatas(List<MessageBean.DataBean> messageBean) {
        this.mMessageBean = messageBean;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHoler) holder).mTxtItemMessageTitle.setText(mMessageBean.get(position).getMsg_title());
        ((MyViewHoler) holder).mTxtItemMessageTime.setText(mMessageBean.get(position).getCreate_time());
        ((MyViewHoler) holder).mTxtItemMessageContent.setText(mMessageBean.get(position).getMsg_text());
    }

    @Override
    public int getItemCount() {
        return mMessageBean.size();
    }

    public class MyViewHoler extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_item_message_title)
        TextView mTxtItemMessageTitle;
        @BindView(R.id.txt_item_message_time)
        TextView mTxtItemMessageTime;
        @BindView(R.id.txt_item_message_content)
        TextView mTxtItemMessageContent;

        public MyViewHoler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
