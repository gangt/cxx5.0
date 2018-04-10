package com.xi6666.app;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


import com.xi6666.R;
import com.xi6666.common.ToolBarUtils;

/**
 * @author peng
 * @data 创建时间:2016/10/26
 * @desc 带有toolbar 沉浸的弗雷
 */
public abstract class ToolBarBaseAct extends ImmerseBaseAct {
    private ToolBarUtils mToolBarUtils;
    protected Toolbar mToolBar;
    protected TextView mTvTitle;
    protected TextView mTxtTiltle;
    protected TextView mTxtRight;
    protected TextView mTxtLeft;

    public TextView getTxtTiltle() {
        return mTxtTiltle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mToolBarUtils = new ToolBarUtils(this, layoutResID); //创建工具类的实例对象,并且将界面的布局传递过去
        mToolBar = mToolBarUtils.getToolBar();//获取到toolbar的实例对象
        setContentView(mToolBarUtils.getContentView());//设置根布局,工具类里面封装好的contentview
        setSupportActionBar(mToolBar); /*把 toolbar 设置到Activity 中*/
        onCreateCustomToolBar(mToolBar); /*对toolbar自定义的一些操作*/
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置箭头可见
        mToolBar.setNavigationIcon(R.drawable.ic_back);

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToolbarIconDo();
            }
        });
        mTxtTiltle = (TextView) mToolBar.findViewById(R.id.txt_basetoolbar_title);
        mTxtTiltle.setText(setToolbarTitle());
        mTxtRight = (TextView) mToolBar.findViewById(R.id.txt_basetoolbar_right);
        mTxtLeft = (TextView) mToolBar.findViewById(R.id.txt_basetoolbar_left);
        mTxtLeft.setVisibility(View.GONE);
        mTxtRight.setVisibility(View.GONE);
    }

    public abstract void setToolbarIconDo();

    public abstract String setToolbarTitle();


    public void onCreateCustomToolBar(Toolbar toolbar) {
        toolbar.setContentInsetsRelative(0, 0);
    }

    protected void setRightTxt(String string) {
        mTxtRight.setVisibility(View.VISIBLE);
        mTxtRight.setText(string);
    }
}
