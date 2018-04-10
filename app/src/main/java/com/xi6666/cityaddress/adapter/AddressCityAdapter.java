package com.xi6666.cityaddress.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.common.OnRecyclerItemClickListener;
import com.xi6666.databean.AddressBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/23.
 */

public class AddressCityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private List<AddressBean.DataBean.CitysBean> mDataBeen;

    private OnRecyclerItemClickListener mOnRecyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        mOnRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public void setDataBeen(List<AddressBean.DataBean.CitysBean> dataBeen) {
        this.mDataBeen = dataBeen;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_address, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder) holder).mTxtItemAddressName.setText(mDataBeen.get(position).getRegion_name());
        ((MyViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnRecyclerItemClickListener.onRecyclerItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataBeen.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_item_address_name)
        TextView mTxtItemAddressName;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}