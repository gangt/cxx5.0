package com.xi6666.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Mr_yang on 2016/7/4.
 * 需要测量的grideview
 */
public class MesureGrideView extends GridView {

    public MesureGrideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MesureGrideView(Context context) {
        super(context);
    }

    public MesureGrideView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
