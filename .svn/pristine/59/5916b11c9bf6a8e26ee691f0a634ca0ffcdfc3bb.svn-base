package com.xi6666.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by Mr_yang on 2016/10/26.
 */

public class CompatToolbar extends Toolbar {
    private static final String TAG = "CompatToolbar";
    private boolean mLayoutReady;

    public CompatToolbar(Context context) {
        this(context, null);
    }

    public CompatToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.toolbarStyle);
    }

    public CompatToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!mLayoutReady) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if ((getWindowSystemUiVisibility() &
                        (SYSTEM_UI_FLAG_LAYOUT_STABLE | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)) ==
                        (SYSTEM_UI_FLAG_LAYOUT_STABLE | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)) {
                    int statusBarHeight = getStatusBarHeight();
                    Log.d(TAG, "statusBarHeight---》" + statusBarHeight);
                    ViewGroup.LayoutParams params = getLayoutParams();
                    params.height = getHeight() + statusBarHeight;
                    setPadding(0, statusBarHeight, 0, 0);
                }
            }
            mLayoutReady = true;
        }
    }

    /**
     * 获取stausbar的高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        Resources res = Resources.getSystem();
        int resId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return res.getDimensionPixelSize(resId);
        }
        return 0;
    }
}
