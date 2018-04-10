package com.xi6666.cardbag.view.oilcard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.cardbag.view.mvp.bean.OilCardNotAlreadyBean;
import com.xi6666.utils.DimenUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/24 上午10:52.
 * 个人公众号 ardays
 */

public class OilCardNotAlreadyAdapter extends RecyclerView.Adapter {

    public List<OilCardNotAlreadyBean.DataBean> mDatas;  //数据集合
    public Context mContext;        //上下文
    private SimpleDateFormat mFormat;

    public OilCardNotAlreadyAdapter(Context context){
        this.mContext = context;
        this.mDatas = new ArrayList<>();
        mFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        TextView textView = new TextView(mContext);
//        textView.setTextSize(DimenUtils.dip2px(mContext, 14));      //设置字体大小
//        textView.setGravity(Gravity.CENTER);      //设置字体位置
//        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT); //设置宽度
//        textView.setHeight(DimenUtils.dip2px(mContext, 40));     //设置高度
//        textView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_center_tv, parent, false);
        return new NotAlreadyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OilCardNotAlreadyBean.DataBean notAlreaday = mDatas.get(position);
        String s = mContext.getString(R.string.recharge_details_not, mFormat.format(notAlreaday.add_datetime * 1000), notAlreaday.package_return_number, notAlreaday.package_cash, notAlreaday.package_left_number, notAlreaday.total_money);
        ((NotAlreadyViewHolder)holder).mTv.setText(s);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    /**
     * 添加数据
     * @param data 数据源
     */
    public void addDatas(List<OilCardNotAlreadyBean.DataBean> data){
        this.mDatas.addAll(data);
        notifyDataSetChanged();
    }

    class NotAlreadyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv)
        TextView mTv;   //文本

        public NotAlreadyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
