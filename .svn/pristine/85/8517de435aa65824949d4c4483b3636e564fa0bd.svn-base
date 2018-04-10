package com.xi6666.store.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xi6666.store.mvp.bean.TechnicianTeamBean;
import com.xi6666.store.custom.StoreTechnicianTeamItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/7 下午6:33.
 * 个人公众号 ardays
 *
 *
 * 技师头像 + 文字
 */

public class TechnicianTeamAdapter extends RecyclerView.Adapter {

    /**
     * 上下文
     */
    public Context mContext;


    private List<TechnicianTeamBean> mData;

    public TechnicianTeamAdapter(Context context){
        this.mContext = context;
        mData = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StoreTechnicianTeamItemView view  = new StoreTechnicianTeamItemView(mContext);
        return new TechnicianTeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TechnicianTeamViewHolder){
            setTechnicianData((TechnicianTeamViewHolder) holder, position);
        }
    }

    // 写用户数据以及详情等
    private void setTechnicianData(TechnicianTeamViewHolder holder,int position){
        final TechnicianTeamBean data = mData.get(position);
        holder.view.setName(data.user_truename);
        holder.view.setHeadImage(data.user_face);

        //              监听技师点击事件，并回调到上一个界面
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnTechnicianTeamListener != null) mOnTechnicianTeamListener.onItemClick(data);
            }
        });
    }


    /**
     * 添加数据
     */
    public void addData(List<TechnicianTeamBean> data){
        this.mData.addAll(data);
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }


    class TechnicianTeamViewHolder extends RecyclerView.ViewHolder{

        private StoreTechnicianTeamItemView view;

        public TechnicianTeamViewHolder(StoreTechnicianTeamItemView itemView) {
            super(itemView);
            this.view = itemView;
        }
    }


    //                          @代理方法

    public OnTechnicianTeamListener mOnTechnicianTeamListener;

    public void setOnTechnicianTeamListener(OnTechnicianTeamListener listener){
        this.mOnTechnicianTeamListener = listener;
    }

    public interface OnTechnicianTeamListener{
        /**
         * 点击技师头像回调方法
         * @param userData
         */
        void onItemClick(TechnicianTeamBean userData);
    }
}
