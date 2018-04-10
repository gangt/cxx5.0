package com.xi6666.productdetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xi6666.R;
import com.xi6666.databean.ProductDetialBean;
import com.xi6666.databean.SkuListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/25.
 */

public class ProductSizeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private onRecycLerViewItemOlick mOnRecycLerViewItemOlick;
    private Context mContext;
    private List<SkuListBean.ListBean> mColorData;

    public void setSizeData(List<SkuListBean.ListBean> colorData) {
        mColorData = colorData;
        this.notifyDataSetChanged();
    }

    public void setOnRecycLerViewItemOlick(onRecycLerViewItemOlick onRecycLerViewItemOlick) {
        mOnRecycLerViewItemOlick = onRecycLerViewItemOlick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new MyViewHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_size, null));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {




        ((MyViewHoler) holder).mBtnItemProductColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notifyDataSetChanged();
                mOnRecycLerViewItemOlick.onRecyclerClick(v, position);
            }
        });
        if (mColorData.get(position).getIs_selected() == 1) {
            holder.itemView.setSelected(true);
        }else{
            holder.itemView.setSelected(false);
        }

        ((MyViewHoler) holder).mBtnItemProductColor.setText(mColorData.get(position).getSku1_name());

    }

    @Override
    public int getItemCount() {
        return mColorData.size();
    }

    public class MyViewHoler extends RecyclerView.ViewHolder {
        @BindView(R.id.btn_item_product_color)
        Button mBtnItemProductColor;

        public MyViewHoler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface onRecycLerViewItemOlick {
        void onRecyclerClick(View view, int position);


    }
}