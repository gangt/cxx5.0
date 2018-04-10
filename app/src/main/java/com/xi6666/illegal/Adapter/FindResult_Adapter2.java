package com.xi6666.illegal.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.illegal.bean.FindResultBean2;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Huang Yingde on 2016/6/7 0007.
 */
public class FindResult_Adapter2 extends RecyclerView.Adapter<FindResult_Adapter2.FindResultViewHolder>{

    private Context                                 mContext;
    private List<FindResultBean2.DataBean.ListBean> mDatas;
    private HashMap<Integer,Boolean>                mItemSelectMap;

    public FindResult_Adapter2(Context context, List<FindResultBean2.DataBean.ListBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mItemSelectMap = new HashMap<>();
        for (int i = 0; i < datas.size(); i++) {
            mItemSelectMap.put(i,false);
        }
    }

    @Override
    public FindResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FindResultViewHolder findResultViewHolder = new FindResultViewHolder(LayoutInflater.from(mContext).inflate(R.layout.findresult_item, parent, false));
        return findResultViewHolder;
    }

    @Override
    public void onBindViewHolder(FindResultViewHolder holder, int position) {
        FindResultBean2.DataBean.ListBean ListsBean = mDatas.get(position);
        holder.tvBreakName.setText(ListsBean.getArea());
        String date = ListsBean.getDate_time();
        holder.tvDate.setText(date.substring(0, date.length() - 3));
        holder.tvComment.setText(ListsBean.getAct());
        holder.tvBreakMoney.setText(ListsBean.getMoney() + " 元");
        holder.tvBreakFen.setText(ListsBean.getFen() + " 分");
        Boolean isChecked = mItemSelectMap.get(position);
        holder.checkBox.setChecked(isChecked);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mItemSelectMap.put(position,true);
                else
                    mItemSelectMap.put(position,false);
//                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDatas != null)
            return mDatas.size();
        return  0;
    }

    class FindResultViewHolder extends RecyclerView.ViewHolder {
        TextView tvBreakName;
        TextView tvDate;
        TextView tvComment;
        TextView tvBreakMoney;
        TextView tvBreakFen;
        CheckBox checkBox;

        public FindResultViewHolder(View itemView) {
            super(itemView);
            tvBreakName = (TextView) itemView.findViewById(R.id.break_name);
            tvDate = (TextView) itemView.findViewById(R.id.date);
            tvComment = (TextView) itemView.findViewById(R.id.comment);
            tvBreakMoney = (TextView) itemView.findViewById(R.id.break_money);
            tvBreakFen = (TextView) itemView.findViewById(R.id.break_fen);
            checkBox = (CheckBox) itemView.findViewById(R.id.item_illegal_cb);
        }
    }

    public HashMap<Integer,Boolean> getItemSelectMap() {
        return mItemSelectMap;
    }
}
