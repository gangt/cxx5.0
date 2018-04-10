package com.xi6666.order.other;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 自定义ViewHolder
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private CustomAdapter adapter;

    //初始化的设置
    protected CustomViewHolder(View itemView) {
        super(itemView);
        //ItemView沾满屏幕宽度，LayoutInflater默认包裹内容
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(lp);
        this.mViews = new SparseArray<>();
        mConvertView = itemView;
    }

    public <T> CustomViewHolder(View itemView, final CustomAdapter<T> adapter) {
        super(itemView);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(lp);
        this.mViews = new SparseArray<>();
        mConvertView = itemView;
        if (adapter.getItemClickListener() != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.getItemClickListener().onItemClickListener(getAdapterPosition() - adapter.getHeaderViewsCount());
                }
            });
        }
        this.adapter = adapter;
    }


    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId 组件id
     * @return 当前组件
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        try {
            return (T) view;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId 组件ID
     * @return 找到的组件
     */
    public <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        try {
            return (T) view;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId 组件ID
     * @param text   显示的文字
     * @return 当前对象
     */
    public CustomViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId  组件ID
     * @param drawableId  图片资源ID
     * @return 当前对象
     */
    public CustomViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId 组件ID
     * @return 当前对象
     */
    public CustomViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }


}
