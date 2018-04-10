package com.xi6666.home.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.databean.HomeSpecialBean;
import com.xi6666.utils.DimenUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/7.
 */

public class HomeHeadSpecialAdapter extends BaseAdapter {
    private List<HomeSpecialBean.DataBean> data;
    private OnClickListener mOnClickListener;
    private boolean isProductHome = false;

    public void setData(List<HomeSpecialBean.DataBean> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public void setProductHome(boolean home) {
        isProductHome = home;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_head_special, null);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        if (isProductHome) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.height = DimenUtils.dip2px(parent.getContext(), 144);
            layoutParams.leftMargin = 0;
            myViewHolder.mIvHomeHeadSpecial.setLayoutParams(layoutParams);
        }
        Glide.with(parent.getContext()).load(data.get(position).getZt_img()).into(myViewHolder.mIvHomeHeadSpecial);
        myViewHolder.mIvHomeHeadSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onBannnerClick(position);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(parent.getContext(), LinearLayoutManager.HORIZONTAL, false);
        myViewHolder.mRlvHomeHeadSpecial.setLayoutManager(linearLayoutManager);
        HomeHeadSpecialGoodsAdapter homeHeadSpecialGoodsAdapter = new HomeHeadSpecialGoodsAdapter();
        homeHeadSpecialGoodsAdapter.setPrentPosition(position);
        if (isProductHome) {
            homeHeadSpecialGoodsAdapter.setProductHome(true);
        }
        homeHeadSpecialGoodsAdapter.setGoodsListBeen(data.get(position).getGoods_list());
        myViewHolder.mRlvHomeHeadSpecial.setAdapter(homeHeadSpecialGoodsAdapter);
        return convertView;
    }

    public class MyViewHolder {
        @BindView(R.id.iv_home_head_special)
        ImageView mIvHomeHeadSpecial;
        @BindView(R.id.rlv_home_head_special)
        RecyclerView mRlvHomeHeadSpecial;

        MyViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnClickListener {
        void onBannnerClick(int position);
    }

    public void setOnBannerClickListener(OnClickListener onBannerClickListener) {
        this.mOnClickListener = onBannerClickListener;
    }
}
