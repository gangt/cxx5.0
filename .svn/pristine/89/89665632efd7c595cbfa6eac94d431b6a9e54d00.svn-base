package com.xi6666.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Mr_yang on 2016/6/28.
 */
public class MesureListView extends ListView {

    public MesureListView(Context context) {
        super(context);
    }

    public MesureListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MesureListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
