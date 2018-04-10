package com.xi6666.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;

import com.xi6666.R;

/**
 * 创建者 sunsun
 * 时间 2016/12/5 下午3:42.
 * 个人公众号 ardays
 */

public class RecyclearViewUtils {

    /**
     * 添加分割线
     */
    public static class RecyclearViewDivider extends RecyclerView.ItemDecoration {

        //添加分割线参数方法
        private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
        //
        private Drawable mDivider;

        //宽度
        private int width;
        //这是默认颜色
        private int mColor = R.color.gray;

        public RecyclearViewDivider(Context mContext, int width) {
            final TypedArray a = mContext.obtainStyledAttributes(ATTRS);
            this.width = width;
            mDivider = a.getDrawable(0);
            mDivider.setAlpha(0);
            //刷新控件重新绘制
            a.recycle();
        }

        public RecyclearViewDivider(Context mContext, int width, int color) {
            final TypedArray a = mContext.obtainStyledAttributes(ATTRS);
            this.width = width;
            mDivider = a.getDrawable(0);
            mColor = color;
            mDivider.setAlpha(0);
            //刷新控件重新绘制
            a.recycle();
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            //绘制竖线
            drawHorizontal(c, parent);
            //绘制横线
            drawVertical(c, parent);

        }

        private int getSpanCount(RecyclerView parent) {
            // 列数
            int spanCount = -1;
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {

                spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                spanCount = ((StaggeredGridLayoutManager) layoutManager)
                        .getSpanCount();
            }
            return spanCount;
        }

        public void drawHorizontal(Canvas c, RecyclerView parent) {
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getLeft() - params.leftMargin;
                final int right = child.getRight() + params.rightMargin
                        + mDivider.getIntrinsicWidth();
                final int top = child.getBottom() + params.bottomMargin + 15;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        public void drawVertical(Canvas c, RecyclerView parent) {
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);

                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getTop() - params.topMargin + 15;
                final int bottom = child.getBottom() + params.bottomMargin;
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                    int childCount) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int orientation = ((StaggeredGridLayoutManager) layoutManager)
                        .getOrientation();
                if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                    if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                    {
                        return true;
                    }
                } else {
                    childCount = childCount - childCount % spanCount;
                    if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                        return true;
                }
            }
            return false;
        }

        private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                                  int childCount) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                    return true;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int orientation = ((StaggeredGridLayoutManager) layoutManager)
                        .getOrientation();
                // StaggeredGridLayoutManager 且纵向滚动
                if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                    childCount = childCount - childCount % spanCount;
                    // 如果是最后一行，则不需要绘制底部
                    if (pos >= childCount)
                        return true;
                } else
                // StaggeredGridLayoutManager 且横向滚动
                {
                    // 如果是最后一行，则不需要绘制底部
                    if ((pos + 1) % spanCount == 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public void getItemOffsets(Rect outRect, int itemPosition,
                                   RecyclerView parent) {

            int spanCount = getSpanCount(parent);
            int childCount = parent.getAdapter().getItemCount();
            if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
            {
                outRect.set(width, width, width, 0);
            } else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
            {
                outRect.set(width, width, width, 0);
            } else {
                outRect.set(width, width, width, 0);
            }
        }


        public void setColor(int color) {
            this.mColor = color;
        }
    }


}
