package com.xi6666.carWash.base.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * 创建人 孙孙啊i
 * 时间 2016/6/8 0008.
 * 功能 重写RecyclerView，让它拥有下啦刷新功能事件
 */
public class LoadMoreRecyclerView extends RecyclerView {
    private static final String TAG = "LoadMoreRecyclerView";


    /* 是否加载*/
    private boolean isLoading;
    private OnRefreshEndListener mEndListener;
    private OnScrllowXyListenerY mXyListener;

    public LoadMoreRecyclerView(Context context) {
        super(context);
        init();
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    /**
     * 初始化
     */
    private void init(){
        isLoading = true;
        setOverScrollMode(View.OVER_SCROLL_NEVER);
    }


    /**
     * 监听RecyclerView滚动
     * @param dx 往X轴滚动
     * @param dy 往Y轴滚动
     */
    @Override
    public void onScrolled(int dx, int dy) {
        if(mXyListener != null){
            mXyListener.onScrollXy(dx,dy);
        }
        //判断当前是否正在加载
        if (isLoading) {
            //判断是什么布局
            if (getLayoutManager() instanceof LinearLayoutManager) {
                linearLayoutScrolled(dy);
            } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutScrolled(dy);
            }
        }
    }


    private void linearLayoutScrolled(int dy) {
        Log.e(TAG, "linearLayoutScrolled: "  + dy);
        LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
        if (layoutManager != null) {
            //获取当前可见页面最后一个Item
            int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            int top = layoutManager.findFirstVisibleItemPosition();
            //获取所有数量
            int totalItemCount = layoutManager.getItemCount();
            Log.e("TAG","lastVisibleItem" + lastVisibleItem + "\ntop" + top + "\ntotalItemCount" + totalItemCount);
            //当前页面最后一个Item + 1 大于 所有数量,并且滑动距离不少于1  就可以触发
            if (lastVisibleItem + 1 >= totalItemCount && dy > 0) {
                //防止用户多次下拉多次触发
                isLoading = false;
                if (mEndListener != null) {
                    mEndListener.onEnd();
                }
            }else if(top -1 > 0 && dy < 0){
                isLoading = false;
            }
        }
    }

    private void StaggeredGridLayoutScrolled(int dy) {
        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) getLayoutManager();
        if (layoutManager != null) {

            //获取当前页面最后一行完全可见的Item
            int[] lastItemPositions = layoutManager.findLastCompletelyVisibleItemPositions(null);
            //获取当前页面最后一个完全可见的Item
            int lastVisibleItem = lastItemPositions[lastItemPositions.length - 1];
            //获取当前总共有多少个item
            int totalItemCount = layoutManager.getItemCount();
            //当前页面最后一个Item + 1 大于 所有数量,并且滑动距离不少于1  就可以触发
            if (lastVisibleItem + 1 >= totalItemCount && dy > 0) {
                isLoading = false;
                if (mEndListener != null) {
                    mEndListener.onEnd();
                }
            }
        }
    }


    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean boo) {
        this.isLoading = boo;
    }

    public void setOnRefreshEndListener(OnRefreshEndListener endListener) {
        mEndListener = endListener;
    }


    public void setOnScrllowXyListenerY(OnScrllowXyListenerY mXY){
        this.mXyListener = mXY;
    }




    public interface OnRefreshEndListener {
        public void onEnd();
    }

    public interface OnScrllowXyListenerY{
        void onScrollXy(int x, int y);
    }
}
