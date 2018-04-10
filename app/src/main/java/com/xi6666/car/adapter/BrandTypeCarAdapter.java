package com.xi6666.car.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.car.bean.CarBrandTypeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 2017/1/17 下午4:30.
 * 个人公众号 ardays
 */

public class BrandTypeCarAdapter extends BaseAdapter {


    Context mContext;


    private List<CarBrandTypeBean.TypeBean> mBean;

    public BrandTypeCarAdapter(Context context, CarBrandTypeBean bean) {
        mContext = context;
        if (bean.data != null) {
            this.mBean = bean.data;
        } else {
            mBean = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return mBean.size();
    }

    @Override
    public Object getItem(int position) {
        return mBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_car_type, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final CarBrandTypeBean.TypeBean data = (CarBrandTypeBean.TypeBean) getItem(position);
        viewHolder.mListTv.setText(data.car_type);
        viewHolder.mListTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnBrandTypeCarListener != null){
                    mOnBrandTypeCarListener.onBrandTypeClick(data);
                }
            }
        });
        return convertView;
    }

    /**
     * 存储化减少findViewById
     */
    class ViewHolder {
        @BindView(R.id.car_type_list_tv)
        TextView mListTv;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public OnBrandTypeCarListener mOnBrandTypeCarListener;
    public void setOnBrandTypeCarListener(OnBrandTypeCarListener listener){
        this.mOnBrandTypeCarListener = listener;
    }
    public interface OnBrandTypeCarListener{
        void onBrandTypeClick(CarBrandTypeBean.TypeBean data);
    }
}
