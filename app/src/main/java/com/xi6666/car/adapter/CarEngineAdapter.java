package com.xi6666.car.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.car.bean.CarEngineBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午2:44.
 * 个人公众号 ardays
 */

public class CarEngineAdapter extends BaseAdapter {

    private Context mContext;
    List<CarEngineBean.TypeBean> mDatas;
    //接口
    private OnItemClickListener mListener;

    public CarEngineAdapter(Context mContext, CarEngineBean bean, OnItemClickListener listener){
        this.mContext = mContext;
        mDatas = new ArrayList<>();
        mDatas.addAll(bean.data);
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_car_type,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final CarEngineBean.TypeBean data = (CarEngineBean.TypeBean) getItem(position);

        //写入名字
        viewHolder.mListTv.setText(data.pailiang);
        viewHolder.mListTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.setOnItemClickListener(data,position);
                    mDatas.clear();
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }

    public interface OnItemClickListener{
        void setOnItemClickListener(CarEngineBean.TypeBean bean, int position);
    }


    class ViewHolder{
        @BindView(R.id.car_type_list_tv)
        TextView mListTv;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
