package com.xi6666.illegal.other;

/**
 * Created by Mr_yang on 2016/10/26.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.xi6666.R;

/**
 * @author peng
 * @data 创建时间:2016/10/26
 * @desc toolbar 的封装功能类
 */
public class HYDToolBarUtils {
    private Context mContext;
    private final LayoutInflater mInflater;
    private FrameLayout mContentView;
    private View mUserView;
    private Toolbar mToolBar;

    /*
   * 两个属性
   * 1、toolbar是否悬浮在窗口之上
   * 2、toolbar的高度获取
   * */
    private static int[] ATTRS = {
            R.attr.windowActionBarOverlay,
            R.attr.actionBarSize
    };

    public HYDToolBarUtils(Context context, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        //初始化整个View
        initContentView();
        //初始化用户的View
        initUserView(layoutId);
        //初始化toolbar
        initToolBar();

    }

    /**
     * 初始化contentView
     */
    private void initContentView() {
        //这里创建的FrameLayout实际就是根view
        mContentView = new FrameLayout(mContext);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.setLayoutParams(layoutParams);
     /*
      * 如果设置了改变状态栏式沉浸式状态栏则需要设置fit才能使得toolbar不陷入到状态栏里面去。
     /* mContentView.setFitsSystemWindows(true);*/
    }

    /**
     * 界面界面的view传递进来并且添加到contentview里面
     *
     * @param layoutId
     */
    private void initUserView(int layoutId) {
        mUserView = mInflater.inflate(layoutId, null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
      /*获取主题中定义的悬浮标志*/
        boolean overly = typedArray.getBoolean(0, false);
      /*获取主题中定义的toolbar的高度,这里的高度也是可以自行定义*/
        int i = AssetManager.ACCESS_RANDOM;
        int toolBarSize = (int) typedArray.getDimension(i, (int) mContext.getResources().getDimension(R.dimen.abc_action_bar_default_height_material));
        typedArray.recycle();
      /*判断toolbar是否是悬浮状态的是的话就不要设置toolbar的topmargin,如果不是则需要设置topmargin*/
        //layoutParams.topMargin = overly ? 0 : toolBarSize;
        int i1 = toolBarSize + getStatusBarHeight();
        layoutParams.topMargin = overly ? 0 : i1;
        //如果通过toolbar的topmargin来达到沉浸式状态效果,这里还需要加上状态栏的高度
        //将用户界面的View填充到contentview里面去,并且设置topmargin为toolbar的高度
        mContentView.addView(mUserView, layoutParams);
    }

    /**
     * 获取状态栏的高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        //判断是否是大于4.4版本,如果是4.4以下版本是不支持沉浸式状态栏的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            Resources res = Resources.getSystem();
            int resId = res.getIdentifier("status_bar_height", "dimen", "android");
            if (resId > 0) {
                return res.getDimensionPixelSize(resId);
            }
            return 0;
        } else {
            return 0;
        }
    }

    /**
     * 充气toolbar
     */
    private void initToolBar() {
        //将toolbar 充到contentview里面去
        View toolBarLayout = mInflater.inflate(R.layout.base_toolbar_hyd, mContentView);
        //拿到toolbar 的实例对象
        mToolBar = (Toolbar) toolBarLayout.findViewById(R.id.base_tb);
    }

    /**
     * 获取到contentview
     *
     * @return
     */
    public FrameLayout getContentView() {
        return mContentView;
    }

    /**
     * 获取到toolbar
     *
     * @return
     */
    public Toolbar getToolBar() {
        return mToolBar;
    }
}
