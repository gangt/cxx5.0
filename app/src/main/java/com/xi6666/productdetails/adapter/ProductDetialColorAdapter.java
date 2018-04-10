package com.xi6666.productdetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.xi6666.R;
import com.xi6666.databean.SkuListBean;

import java.util.List;

import static com.xi6666.R.color.loginBtnColor;
/**
 * Created by Mr_yang on 2016/12/8.
 */

public class ProductDetialColorAdapter extends BaseAdapter {
    private Context mContext;


    private List<SkuListBean.ListBean> mColorData;


    public void setColorData(List<SkuListBean.ListBean> colorData) {
        mColorData = colorData;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mColorData.size();
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
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_color, null);
        Button viewById = (Button) inflate.findViewById(R.id.btn_item_product_color);
        if (position == 0) {
            viewById.setBackgroundResource(R.drawable.bg_product_color_green);
            viewById.setTextColor(mContext.getResources().getColor(loginBtnColor));
        }
        viewById.setText(mColorData.get(position).getSku1_name());
        return inflate;
    }
}
