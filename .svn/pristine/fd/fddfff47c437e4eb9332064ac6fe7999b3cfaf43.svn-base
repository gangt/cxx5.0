package com.xi6666.illegal.see.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.illegal.see.bean.UsageRecordBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 2017/2/7 下午3:37.
 * 个人公众号 ardays
 */

public class UsageRecordAdapter extends RecyclerView.Adapter {


    Context mContext;
    private List<UsageRecordBean.DataBean> mData;

    public UsageRecordAdapter(Context context) {
        this.mContext = context;
        this.mData = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_usage_record, parent, false);
        return new UsageRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UsageRecordViewHolder h = (UsageRecordViewHolder) holder;


        UsageRecordBean.DataBean data = mData.get(position);
        //item点击事件
        h.itemView.setOnClickListener(v -> {
            mOnUsageRecordListener.onItemClick(position, data);
        });
        //订单编号
        h.mOrderNumberTv.setText("详情编号: " + data.order_sn);
        //审核状态
        int statusId = Integer.parseInt(data.do_status);
        String statusName;
        int statusColor;
        switch (statusId) {
            case 1://未审核
                statusColor = R.color.text_orange;
                statusName = "待审核";
                h.mStatusTv.setText(statusName);
                h.mStatusTv.setTextColor(mContext.getResources().getColor(statusColor));

                h.mStatusTv.setVisibility(View.VISIBLE);
                h.mStatusIv.setVisibility(View.GONE);
                break;
            case 2://处理中
                statusColor = R.color.text_orange;
                statusName = "处理中";
                h.mStatusTv.setText(statusName);
                h.mStatusTv.setTextColor(mContext.getResources().getColor(statusColor));

                h.mStatusTv.setVisibility(View.VISIBLE);
                h.mStatusIv.setVisibility(View.GONE);
                break;
            case 3://已取消
                h.mStatusIv.setImageResource(R.drawable.has_cancel_icon);
                h.mStatusTv.setVisibility(View.GONE);
                h.mStatusIv.setVisibility(View.VISIBLE);
                break;
            case 4://已完成
                h.mStatusIv.setImageResource(R.drawable.has_done_icon);
                h.mStatusTv.setVisibility(View.GONE);
                h.mStatusIv.setVisibility(View.VISIBLE);
                break;
            default:
                statusName = "";
                break;
        }
        //违章详情
        h.mContentTv.setText(data.act);
        //违章订单生成日期
        h.mTimeTv.setText("使用日期: " + data.add_datetime);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    /**
     * 添加数据
     */
    public void addData(List<UsageRecordBean.DataBean> data) {
        this.mData.addAll(data);
        notifyDataSetChanged(); //刷新当前列表
    }

    /**
     * 修改数据
     */
    public void updateData(List<UsageRecordBean.DataBean> data) {
        this.mData.clear();
        addData(data);
    }


    class UsageRecordViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_usage_record_number_tv)
        TextView mOrderNumberTv;    //订单编号
        @BindView(R.id.item_usage_record_status_tv)
        TextView mStatusTv; //审核状态
        @BindView(R.id.item_usage_record_content_tv)
        TextView mContentTv;    //违章详情
        @BindView(R.id.item_usage_record_time_tv)
        TextView mTimeTv;   //违章订单生成日期
        @BindView(R.id.item_usage_record_num_tv)
        TextView mNumTv;    //处理次数
        @BindView(R.id.item_usage_record_status_iv)
        ImageView mStatusIv;  //审核状态


        public UsageRecordViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    /**
     * 事件代理
     **/
    public OnUsageRecordListener mOnUsageRecordListener;

    public void setOnUsageRecordListener(OnUsageRecordListener listener) {
        this.mOnUsageRecordListener = listener;
    }

    public interface OnUsageRecordListener {
        void onItemClick(int position, UsageRecordBean.DataBean bean);
    }
}
