package com.xi6666.store.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 创建者 sunsun
 * 时间 16/11/8 上午10:11.
 * 个人公众号 ardays
 *
 * 此类是监听scrollView滚动
 */

public class MonitorScrollView extends ScrollView {
    public MonitorScrollView(Context context) {
        super(context);
    }

    public MonitorScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MonitorScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mListener != null) mListener.onScrollChanged(l, t, oldl, oldt);
    }



    private OnScrollListener mListener;
    public void setOnScrollListener(OnScrollListener listener){
        this.mListener = listener;
    }

    public interface OnScrollListener{
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}
