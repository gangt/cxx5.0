package com.xi6666.store.custom.utils;

import android.content.Context;
import android.view.animation.AccelerateInterpolator;
import android.widget.Scroller;

/**
 * 创建者 sunsun
 * 时间 16/11/25 下午2:24.
 * 个人公众号 ardays
 * 控制ViewPager滚动速度
 */

public class FixedSpeedScroller extends Scroller {

    /**
     * 控制速度  默认2秒
     */
    private int  mDuration = 2 * 1000;

    public FixedSpeedScroller(Context context, AccelerateInterpolator accelerateInterpolator) {
        super(context);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public void setmDuration(int time) {
        mDuration = time;
    }

    public int getmDuration() {
        return mDuration;
    }

}
