package com.xi6666.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Mr_yang on 2016/11/7.
 */

public class GridItemDivDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;
    private int mountNum;
    private boolean mIsXrecycler;

    /**
     * @param space       分割线的宽度
     * @param countNum    一行有多少个
     * @param isXrecycler 是否是x系列加了头布局的
     */
    public GridItemDivDecoration(int space, int countNum, boolean isXrecycler) {
        this.mSpace = space;
        this.mountNum = countNum;
        this.mIsXrecycler = isXrecycler;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        int childPosition = parent.getChildPosition(view);
        if (mIsXrecycler) {
            //是x系列加了头布局的
            if (childPosition > 1) {
                //给给除了头布局以外的部分进行偏移
                if ((childPosition + 2) % mountNum == 0) {
                    outRect.left = 2 * mSpace;
                    outRect.right = mSpace;
                    outRect.bottom = 2 * mSpace;
                } else if ((childPosition + 3) % mountNum == 3) {
                 /*   outRect.left = mSpace;
                    outRect.right = 2 * mSpace;
                    outRect.bottom = 2 * mSpace;*/
                } else {
                    outRect.left = mSpace;
                    outRect.right = 2 *mSpace;
                    outRect.bottom = 2 * mSpace;
                }
            }
        } else {
            if (childPosition % mountNum == 0) {
                outRect.left = 2 * mSpace;
                outRect.right = mSpace;
                outRect.bottom = 2 * mSpace;
            } else if (childPosition % mountNum == 4) {
                outRect.left = mSpace;
                outRect.right = 2 * mSpace;
                outRect.bottom = 2 * mSpace;
            } else {
                outRect.left = mSpace;
                outRect.right = mSpace;
                outRect.bottom = 2 * mSpace;
            }
        }
    }
}
