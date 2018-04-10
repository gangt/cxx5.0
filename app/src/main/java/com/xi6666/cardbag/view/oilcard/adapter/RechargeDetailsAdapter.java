package com.xi6666.cardbag.view.oilcard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.cardbag.view.mvp.bean.OilCardChargeBean;
import com.xi6666.cardbag.view.mvp.bean.OilCardChargeDetailsBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/23 下午6:42.
 * 个人公众号 ardays
 */

public class RechargeDetailsAdapter extends RecyclerView.Adapter {


    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 所有数据
     */
    private List<OilCardChargeDetailsBean.DataBean.ChargeDetailsData> mDatas;

    public RechargeDetailsAdapter(Context context){
        this.mContext = context;
        mDatas = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_recharge_details,null);
        return new RechargeDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RechargeDetailsViewHolder){
            onBindViewHolder((RechargeDetailsViewHolder)holder, position);
        }
    }

    public void onBindViewHolder(RechargeDetailsViewHolder holder, int position) {
        OilCardChargeDetailsBean.DataBean.ChargeDetailsData data = mDatas.get(position);



        //写充值时间
        holder.mTimeTv.setText(data.order_paytime);

        //套餐类型
        holder.mTypeTv.setText(data.package_name);

        //金额
        holder.mMoenyTv.setText("+ " + data.package_amount);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public void addDatas(List<OilCardChargeDetailsBean.DataBean.ChargeDetailsData> datas){
        this.mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void clear() {
        this.mDatas.clear();
        notifyDataSetChanged();
    }


    class RechargeDetailsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_oil_recharge_details_time)
        TextView mTimeTv;           //充值时间
        @BindView(R.id.item_oil_recharge_details_type)
        TextView mTypeTv;           //充值类型
        @BindView(R.id.item_oil_recharge_details_moeny)
        TextView mMoenyTv;           //充值金额



        public RechargeDetailsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }



}