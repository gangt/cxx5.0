package com.xi6666.cardbag.view.oilcard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.databean.RechargeListBean;
import com.xi6666.view.MesureListView;

import java.util.List;

/**
 * Created by Mr_yang on 2017/3/12.
 */

public class RechargeRecordAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<RechargeListBean.DataBeanX.DataBean> mDataBeen;
    private final int TYPE_1 = 0;
    private final int TYPE_2 = 1;

    public void setDataBeen(List<RechargeListBean.DataBeanX.DataBean> dataBeen) {
        mDataBeen = dataBeen;
        this.notifyDataSetChanged();
    }

    public RechargeRecordAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mDataBeen.size() + 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View tabView = mLayoutInflater.inflate(R.layout.item_head_recharge_head, null);
        ImageView ivState = (ImageView) tabView.findViewById(R.id.iv_recharge_tab_arrow);
        if (isExpanded) {
            ivState.setBackgroundResource(R.mipmap.ic_recharge_tab_green_down);
        } else {
            ivState.setBackgroundResource(R.mipmap.ic_recharge_tab_green_up);
        }


        return tabView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        int type = getItemViewType(childPosition);

        switch (type) {
            case TYPE_1:
                convertView = mLayoutInflater.inflate(R.layout.item_recharge_package, null);
                TextView tvOne = (TextView) convertView.findViewById(R.id.txt_package_one);
                TextView tvTwo = (TextView) convertView.findViewById(R.id.txt_package_two);
                TextView tvThr = (TextView) convertView.findViewById(R.id.txt_package_thr);
                tvOne.setText(mDataBeen.get(childPosition - 1).getOrder_paytime());
                tvTwo.setText(mDataBeen.get(childPosition - 1).getPackage_name());
                tvThr.setText(mDataBeen.get(childPosition - 1).getPackage_amount());
                break;
            case TYPE_2:
                convertView = mLayoutInflater.inflate(R.layout.item_recharge_fou, null);

                break;
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public int getItemViewType(int groupPosition) {
        int p = groupPosition;
        if (p == 0) {
            return TYPE_2;
        } else if (p == 1) {
            return TYPE_1;
        } else {
            return TYPE_1;
        }
    }
}
