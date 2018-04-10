package com.xi6666.cardbag.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.common.UserData;
import com.xi6666.databean.IllegaBagListBean;
import com.xi6666.illegal.Activity.IllegalBindCardActivity;
import com.xi6666.illegal.Activity.IllegalFindResultAct;
import com.xi6666.illegal.see.view.IllegalRedeemCodeAct;
import com.xi6666.illegal.see.view.RedeemCodeAct;
import com.xi6666.illegal.see.view.UsageRecordAct;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2017/2/7.
 */

public class IllageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<IllegaBagListBean.DataBean> mDataBeen;

    public void setDataBeen(List<IllegaBagListBean.DataBean> dataBeen) {
        mDataBeen = dataBeen;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_illagecardbag, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).mTxtItemIllagecardbagCar.setText(mDataBeen.get(position).getBinding_carno());
        ((ViewHolder) holder).mTxtItemIllagecardbagNumber.setText(mDataBeen.get(position).getCard_number());
        ((ViewHolder) holder).mTxtItemIllagecardbagType.setText("类        型: " + mDataBeen.get(position).getSurplus_number() + "个月套餐--" +
                mDataBeen.get(position).getSurplus_number() + "次--" + "¥" + mDataBeen.get(position).getCard_amount().substring(0, mDataBeen.get(position).getCard_amount().length() - 3));
        ((ViewHolder) holder).mTxtItemIllagecardbagCount.setText("剩余次数: " + (Integer.parseInt(mDataBeen.get(position).getSurplus_number())
                - Integer.parseInt(mDataBeen.get(position).getUsed_number()) + "次"));
        if (mDataBeen.get(position).getUser_datetime() != null && mDataBeen.get(position).getUser_datetime().length() > 0) {
            ((ViewHolder) holder).mTxtItemIllagecardbagData.setText("有效日期: " + mDataBeen.get(position).getUser_datetime().substring(0, 10).replace("-", ".")
                    + "--" + mDataBeen.get(position).getEnd_datetime().substring(0, 10).replace("-", "."));
        }
        //已到期
        if (mDataBeen.get(position).getCard_status() == 1) {
            ((ViewHolder) holder).mIvItemIllagecardbagState.setImageResource(R.drawable.ic_illage_end);
            ((ViewHolder) holder).mBtnItemIllagecardbagLeft.setVisibility(View.GONE);
            ((ViewHolder) holder).mBtnItemIllagecardbagRight.setVisibility(View.VISIBLE);
            //使用记录
            ((ViewHolder) holder).mBtnItemIllagecardbagRight.setText("去抽奖");
            ((ViewHolder) holder).mBtnItemIllagecardbagRight.setOnClickListener(v -> {
                Toast.makeText(mContext, "该功能正在开发中!", Toast.LENGTH_SHORT).show();
            });
        }
        //已赠送
        if (mDataBeen.get(position).getCard_status() == 2) {
            ((ViewHolder) holder).mTxtItemIllagecardbagCar.setVisibility(View.GONE);
            ((ViewHolder) holder).mIvItemIllagecardbagState.setImageResource(R.drawable.ic_illage_song);

            ((ViewHolder) holder).mBtnItemIllagecardbagLeft.setVisibility(View.GONE);
            ((ViewHolder) holder).mBtnItemIllagecardbagRight.setVisibility(View.VISIBLE);
            //使用记录
            ((ViewHolder) holder).mBtnItemIllagecardbagRight.setText("查看");
            ((ViewHolder) holder).mBtnItemIllagecardbagRight.setOnClickListener(v -> {
                IllegalRedeemCodeAct.openActivity((Activity) mContext, mDataBeen.get(position).getCard_id());
            });

        }
        //使用中
        if (mDataBeen.get(position).getCard_status() == 3) {
            ((ViewHolder) holder).mTxtItemIllagecardbagCar.setVisibility(View.VISIBLE);
            ((ViewHolder) holder).mBtnItemIllagecardbagLeft.setVisibility(View.VISIBLE);
            ((ViewHolder) holder).mBtnItemIllagecardbagRight.setVisibility(View.VISIBLE);
            ((ViewHolder) holder).mIvItemIllagecardbagState.setImageResource(R.drawable.ic_illage_useing);
            //查询违章
            ((ViewHolder) holder).mBtnItemIllagecardbagLeft.setText("查询违章");
            ((ViewHolder) holder).mBtnItemIllagecardbagLeft.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, IllegalFindResultAct.class);
                intent.putExtra("userid", UserData.getUserId());
                intent.putExtra("car_no", mDataBeen.get(position).getCar_no());
                intent.putExtra("city_code", mDataBeen.get(position).getCity_code());
                intent.putExtra("engineno", mDataBeen.get(position).getEngineno());
                intent.putExtra("classno", mDataBeen.get(position).getClassno());
                intent.putExtra("province_code", mDataBeen.get(position).getProvince_code());
                intent.putExtra("query_id", mDataBeen.get(position).getQuery_id());
                mContext.startActivity(intent);
            });
            //使用记录
            ((ViewHolder) holder).mBtnItemIllagecardbagRight.setText("使用记录");
            ((ViewHolder) holder).mBtnItemIllagecardbagRight.setOnClickListener(v -> {
                UsageRecordAct.openActivity((Activity) mContext, mDataBeen.get(position).getCard_id());
            });

        }
        //未启用
        if (mDataBeen.get(position).getCard_status() == 4) {
            ((ViewHolder) holder).mBtnItemIllagecardbagLeft.setVisibility(View.VISIBLE);
            ((ViewHolder) holder).mBtnItemIllagecardbagRight.setVisibility(View.VISIBLE);
            ((ViewHolder) holder).mTxtItemIllagecardbagCar.setVisibility(View.GONE);
            ((ViewHolder) holder).mIvItemIllagecardbagState.setImageResource(R.drawable.ic_illage_notuse);
            //去赠送
            ((ViewHolder) holder).mBtnItemIllagecardbagLeft.setText("去赠送");
            ((ViewHolder) holder).mBtnItemIllagecardbagLeft.setOnClickListener(v -> {
                IllegalRedeemCodeAct.openActivity((Activity) mContext, mDataBeen.get(position).getCard_id());
            });
            //去启用
            ((ViewHolder) holder).mBtnItemIllagecardbagRight.setText("去启用");
            ((ViewHolder) holder).mBtnItemIllagecardbagRight.setOnClickListener(v -> {
                IllegalBindCardActivity.openActivity((Activity) mContext, mDataBeen.get(position).getCard_id());
            });
            ((ViewHolder) holder).mTxtItemIllagecardbagData.setText("有效日期: ");
        }
    }

    @Override
    public int getItemCount() {
        return mDataBeen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_item_illagecardbag_car)
        TextView mTxtItemIllagecardbagCar;
        @BindView(R.id.txt_item_illagecardbag_number)
        TextView mTxtItemIllagecardbagNumber;
        @BindView(R.id.txt_item_illagecardbag_type)
        TextView mTxtItemIllagecardbagType;
        @BindView(R.id.txt_item_illagecardbag_count)
        TextView mTxtItemIllagecardbagCount;
        @BindView(R.id.txt_item_illagecardbag_data)
        TextView mTxtItemIllagecardbagData;
        @BindView(R.id.iv_item_illagecardbag_state)
        ImageView mIvItemIllagecardbagState;
        @BindView(R.id.btn_item_illagecardbag_right)
        Button mBtnItemIllagecardbagRight;
        @BindView(R.id.btn_item_illagecardbag_left)
        Button mBtnItemIllagecardbagLeft;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
