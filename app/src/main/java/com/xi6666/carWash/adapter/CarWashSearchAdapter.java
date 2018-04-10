package com.xi6666.carWash.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xi6666.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/4 上午9:44.
 * 个人公众号 ardays
 */

public class CarWashSearchAdapter extends BaseAdapter
{

    /**
     * 上下文
     */
    public Context mContext;
    /**
     * 历史记录
     */
    public List<String> mDatas;


    public CarWashSearchAdapter(Context context){
        this.mContext = context;
        mDatas = new ArrayList<>();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_carwash_history, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //获取搜索记录
        final String historyStr = (String) getItem(position);
        //写入历史记录
        holder.mHistoryTv.setText(historyStr);


        //点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mHistoryListenr != null) mHistoryListenr.onHistoryItemClick(historyStr);
            }
        });

        return convertView;
    }


    /**
     * 更新历史搜索记录
     */
    public void update(List<String> data){
        this.mDatas.clear();
        this.mDatas.addAll(data);
        notifyDataSetChanged();
    }


    /*                          @事件回调                        */
    public OnCarWashItemHistoryListener mHistoryListenr;
    public void setOnCarWashItemHistoryListener(OnCarWashItemHistoryListener listener){
        this.mHistoryListenr = listener;
    }
    public interface OnCarWashItemHistoryListener{
        void onHistoryItemClick(String keyWord);
    }



    class ViewHolder{
        TextView mHistoryTv;

        public ViewHolder(View view){
            this.mHistoryTv = (TextView) view;
        }
    }
}
