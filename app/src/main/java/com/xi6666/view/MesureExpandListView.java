package com.xi6666.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by Mr_yang on 2017/3/12.
 */

public class MesureExpandListView extends ExpandableListView {
    public MesureExpandListView(Context context) {
        super(context);
    }

    public MesureExpandListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MesureExpandListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
