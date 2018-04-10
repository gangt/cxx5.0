package com.xi6666.store.custom;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xi6666.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/18 下午4:29.
 * 个人公众号 ardays
 */
public class SelectImageAdapter extends RecyclerView.Adapter {

    //上下文
    Context mContext;
    List<String> mImageUrlData;

    public SelectImageAdapter(Context context,List<String> imageUrls){
        this.mContext = context;
        mImageUrlData = imageUrls;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_select_image_iv, parent, false);
        return new SelectImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof SelectImageViewHolder){
            onBind((SelectImageViewHolder) holder, position);
        }
    }

    private void onBind(SelectImageViewHolder holder, final int position){
        final String filePath = mImageUrlData.get(position);
        Glide.with(mContext)
                .load(new File(filePath))
                .placeholder(R.drawable.bg_image_default)
                .error(R.drawable.bg_image_default)
                .into(holder.mImageIv);

        //监听图片移除事件
        holder.mCloseImageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将地址移除
                mImageUrlData.remove(filePath);
                if(mOnSelectImageAdapterListener != null) mOnSelectImageAdapterListener.removeImage();

                //立即刷新
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrlData.size();
    }



    class SelectImageViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_select_image)
        ImageView mImageIv; //图片
        @BindView(R.id.item_close_image)
        ImageView mCloseImageIv;    //删除

        public SelectImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    OnSelectImageAdapterListener mOnSelectImageAdapterListener;
    public void setOnSelectImageAdapterListener(OnSelectImageAdapterListener listener){
        this.mOnSelectImageAdapterListener = listener;
    }
    public interface OnSelectImageAdapterListener{
        /**
         * 删除图片
         */
        void removeImage();
    }
}
