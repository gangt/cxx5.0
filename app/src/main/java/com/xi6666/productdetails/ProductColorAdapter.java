package com.xi6666.productdetails;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.databean.ProductDetialBean;
import com.xi6666.databean.SkuListBean;
import com.xi6666.productdetails.view.ProductDetailsAct;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/15.
 */

public class ProductColorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private onRecycLerViewItemOlick mOnRecycLerViewItemOlick;
    private Context mContext;

    private int mSelectPosition = 0;

    private List<SkuListBean.ListBean> mColorData;

    public void setColorData(List<SkuListBean.ListBean> colorData) {
        mColorData = colorData;
        this.notifyDataSetChanged();
    }

    public void setOnRecycLerViewItemOlick(onRecycLerViewItemOlick onRecycLerViewItemOlick) {
        mOnRecycLerViewItemOlick = onRecycLerViewItemOlick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new MyViewHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_color, null));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setSelected(mSelectPosition == position);
        ((MyViewHoler) holder).mBtnItemProductColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectPosition = holder.getLayoutPosition();
                notifyDataSetChanged();
                mOnRecycLerViewItemOlick.onRecyclerClick(v, position);
            }
        });

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
   /* //展示样式图片popu弹窗
    public void showTypePopu(String imageUrl) {
        PopupWindow popupWindow = new PopupWindow(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        View popu = LayoutInflater.from(mContext).inflate(R.layout.popuwindow_product_color, null);
        ImageView imageView = (ImageView) popu.findViewById(R.id.iv_popu_product_color);
        Glide.with(mContext).load(imageUrl).into(imageView);
        popu.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = popu.getMeasuredWidth();
        int popupHeight = popu.getMeasuredHeight();
        popupWindow.setContentView(popu);
        popupWindow.setOutsideTouchable(true);
        int[] location = new int[2];
        // 获得位置
        view.getLocationOnScreen(location);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, (location[0] + view.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
    }*/
}
