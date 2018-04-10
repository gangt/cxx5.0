package com.xi6666.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Mr_yang on 2016/12/8.
 */

public class WrapContentLinearLayout extends LinearLayoutManager {

    public WrapContentLinearLayout(Context context) {
        super(context);
    }

    public WrapContentLinearLayout(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public WrapContentLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        //super.onMeasure(recycler, state, widthSpec, heightSpec);

        View view = recycler.getViewForPosition(0);

        if(view != null){

            measureChild(view, widthSpec, heightSpec); //計算View的高寬


            //int measuredWidth = getItemCount() * view.getMeasuredWidth(); //這不用解釋啦?

            int measuredWidth = getItemCount() * view.getMeasuredWidth() > View.MeasureSpec.getSize(widthSpec) ? View.MeasureSpec.getSize(widthSpec) : getItemCount() * view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight(); //這個還要解釋?


            setMeasuredDimension(measuredWidth, measuredHeight);

        }
    }
}
