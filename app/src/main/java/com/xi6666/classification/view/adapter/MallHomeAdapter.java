package com.xi6666.classification.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.classification.view.mvp.bean.BrandDetailsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/6/29 下午5:33.
 * 个人公众号 ardays
 */
public class MallHomeAdapter extends RecyclerView.Adapter {

    /**
     * 所有数据
     */
    public List<BrandDetailsBean.DataBean.ListBean> mDatas;

    private Context mContext;

    public OnMallHomeClickListener mListener;


    public MallHomeAdapter(Context mContext){
        mDatas = new ArrayList<>();
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mall_details,null);
        return new MallHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MallHomeViewHolder){
            homeViewHolder((MallHomeViewHolder) holder,position);
        }
    }

    private void homeViewHolder(MallHomeViewHolder holder,int position){
        //数据
        BrandDetailsBean.DataBean.ListBean data = mDatas.get(position);
        //设置店铺头像
        Glide.with(mContext)
                .load(data.goods_thumb_img)
                .placeholder(R.drawable.bg_image_default)
                .error(R.drawable.bg_image_default)
                .crossFade(1000)
                .into(holder.mImgIv);
        //购买人数
//        holder.mNumberTv.setText(data.getBuynumber() + "人购买");
        //店名
        holder.mTitletV.setText(data.goods_name);
        //价格
        holder.mMoenyTv.setText("¥" + data.shop_price);


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    /**
     * 添加数据
     */
    public void addData(List<BrandDetailsBean.DataBean.ListBean> data){
        mDatas.addAll(data);
        notifyDataSetChanged();
    }


    /**
     * 清楚数据
     */
    public void clear(){
        mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     *
     * @param listener
     */
    public void setOnMallHomeClickListener(OnMallHomeClickListener listener){
        this.mListener = listener;
    }

    class MallHomeViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.mall_home_item_img)
        ImageView mImgIv;   //店铺头像
        @BindView(R.id.mall_home_item_moeny)
        TextView mMoenyTv;   //价钱
        @BindView(R.id.mall_home_item_number)
        TextView mNumberTv;       //购买人数
        @BindView(R.id.mall_home_item_title)
        TextView mTitletV; //标题

        public MallHomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        mListener.onItemClickHomeMall(mDatas.get(getPosition()));
                    }
                }
            });
        }
    }

    /**
     * 接口
     */
    public interface OnMallHomeClickListener{
        void onItemClickHomeMall(BrandDetailsBean.DataBean.ListBean bean);
    }
}
