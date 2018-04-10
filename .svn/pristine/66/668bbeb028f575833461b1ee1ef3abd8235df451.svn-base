package com.xi6666.addoil.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.databean.AddOilDataBean;
import com.xi6666.utils.LogUtil;


import java.util.List;

import static com.xi6666.R.drawable.bg_addoil_packge;

/**
 * Created by Mr_yang on 2016/8/25.
 */
public class ChoiceAddOilAdp extends BaseAdapter {
    private static final String TAG = "ChoiceAddOilAdp";
    private Context mContext;
    private List<AddOilDataBean.DataBean> mData;
    private int index;

    public ChoiceAddOilAdp(List<AddOilDataBean.DataBean> data, Context context) {
        mData = data;
        LogUtil.d(TAG, "data.size==" + data.size());
        mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
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
        View rootView = View.inflate(mContext, R.layout.item_oil_type, null);
        TextView zhe = (TextView) rootView.findViewById(R.id.item_addoillist_zhe);
        TextView tiem = (TextView) rootView.findViewById(R.id.item_addoillist_time);
        TextView packageType = (TextView) rootView.findViewById(R.id.ltv_package_type);
        RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.item_addoillist_bg);
        RelativeLayout relativeLayoutPackage = (RelativeLayout) rootView.findViewById(R.id.item_addoillist_package);


        zhe.setText(mData.get(position).getPackage_title());
        tiem.setText(mData.get(position).getPackage_name());
        if (position == index) {
            if (TextUtils.equals("2", mData.get(position).getPackage_show())) {
                relativeLayout.setBackgroundResource(R.drawable.bg_addoil_package);
            } else {
                relativeLayout.setBackgroundResource(R.drawable.home_discount_btn_selected);
            }
        } else {
            if (TextUtils.equals("2", mData.get(position).getPackage_show())) {
            } else {
                relativeLayout.setBackgroundResource(R.drawable.home_count_btn_selected);
            }

        }

        if (TextUtils.equals("2", mData.get(position).getPackage_show())) {
            packageType.setVisibility(View.VISIBLE);
            relativeLayoutPackage.setBackgroundResource(R.drawable.bg_addoil_packge);
            zhe.setTextColor(Color.WHITE);
            tiem.setTextColor(Color.WHITE);
            packageType.setText(mData.get(position).getActivity_name());
        }
        return rootView;
    }

    public void setMySelection(int index) {
        this.index = index;
        notifyDataSetChanged();
    }
}
