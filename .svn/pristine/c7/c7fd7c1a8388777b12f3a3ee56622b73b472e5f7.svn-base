package com.xi6666.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xi6666.R;
import com.xi6666.utils.DimenUtils;

/**
 * Created by Mr_yang on 2016/12/7.
 */

public class SettingDivDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    private Context mContext;

    public SettingDivDecoration(Context context) {
        this.mContext = context;
        mDivider = context.getResources().getDrawable(R.drawable.setting_div);

    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        drawVertical(c, parent);
    }

    //绘制分割线
    public void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft() + DimenUtils.dip2px(mContext, 13);
        int right = parent.getWidth() - parent.getPaddingRight() - DimenUtils.dip2px(mContext, 13);

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == childCount - 1) {
                return;
            }
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    //进行偏移
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }
}
