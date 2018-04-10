package com.xi6666.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.databean.HomeHeadBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/7.
 */

public class HomeFucationAdapter extends BaseAdapter {


    private Context mContext;
    private List<HomeHeadBean.IndexIconBean> mIndexIconBeen;

    public void setIndexIconBeen(List<HomeHeadBean.IndexIconBean> indexIconBeen) {
        mIndexIconBeen = indexIconBeen;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mIndexIconBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mContext = parent.getContext();
        ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homehead_fucation, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext).load(mIndexIconBeen.get(position).getIcon_img()).into(mViewHolder.mIvHomeHeadItem);
        mViewHolder.mTvHomeHeadItem.setText(mIndexIconBeen.get(position).getIcon_name());
        return convertView;
    }

    public class ViewHolder {
        @BindView(R.id.iv_home_head_item)
        ImageView mIvHomeHeadItem;
        @BindView(R.id.tv_home_head_item)
        TextView mTvHomeHeadItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
