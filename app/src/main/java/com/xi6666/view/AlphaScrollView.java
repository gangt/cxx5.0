package com.xi6666.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Mr_yang on 2016/11/16.
 */

public class AlphaScrollView extends ScrollView {
    public static final String TAG = "AlphaTitleScrollView";
    private int mSlop;
    private View mView;

    public AlphaScrollView(Context context) {
        this(context, null);
    }

    public AlphaScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlphaScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSlop = 10;
    }

    public void setTitleAndHead(View view) {
        this.mView = view;

    }
/*
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float headHeight = mView.getMeasuredHeight() / 9;
        int alpha = (int) (((float) t / headHeight) * 255);
        if (alpha >= 255)
            alpha = 255;
        if (alpha <= mSlop)
            alpha = 0;
        mView.getBackground().setAlpha(alpha);
    }*/
}
