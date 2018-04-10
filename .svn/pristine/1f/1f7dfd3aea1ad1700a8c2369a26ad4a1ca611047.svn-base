package com.xi6666.producthome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.databean.ProductHomeCateBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/14.
 */

public class ProductHeadcClassifyAdapter extends BaseAdapter {
    private List<ProductHomeCateBean.DataBean.ListBean> mDataBeen;
    private Context mContext;

    public void setDataBeen(List<ProductHomeCateBean.DataBean.ListBean> dataBeen) {
        mDataBeen = dataBeen;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataBeen.size();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producthead_classify, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext).load(mDataBeen.get(position).getCate_pic()).into(viewHolder.mIvProducthomeItemCate);
        return convertView;
    }

    public class ViewHolder {
        @BindView(R.id.iv_producthome_item_cate)
        ImageView mIvProducthomeItemCate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
