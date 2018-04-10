package com.xi6666.order.other;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 作者： qsdsn on 2016/11/5
 * 描述：${DESC}
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace ;
    private RecyclerView.Adapter mAdapter;

    /**
     * @param space 传入的值，其单位视为dp
     */
    public SpaceItemDecoration(Context mContext,int space,RecyclerView.Adapter adapter) {
        this.mSpace = Utils.dp2Px(mContext,space);
        this.mAdapter = adapter;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemCount = mAdapter.getItemCount();
        int pos = parent.getChildAdapterPosition(view);

        outRect.left = 0;
        outRect.top = 0;
        outRect.right = 0;


        if (pos != (itemCount)) {
            outRect.bottom = mSpace;
        } else {
            outRect.bottom = 0;
//            outRect.bottom = mSpace;
        }
    }
}
