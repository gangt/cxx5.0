package com.xi6666.illegal.other;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.app.ImmerseBaseAct;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class ToolBarBaseActivity extends ImmerseBaseAct {
    private   HYDToolBarUtils mToolBarUtils;
    protected Toolbar      mToolBar;
    private   TextView     mTvTitle;
    protected   TextView     mTxtTiltle;
    protected   TextView     mTxtRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setMiuiStatusBarDarkMode(this,true);
    }

    @Override
    public void setContentView(int layoutResID) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mToolBarUtils = new HYDToolBarUtils(this, layoutResID); //创建工具类的实例对象,并且将界面的布局传递过去
        mToolBar = mToolBarUtils.getToolBar();//获取到toolbar的实例对象
        setContentView(mToolBarUtils.getContentView());//设置根布局,工具类里面封装好的contentview
        setSupportActionBar(mToolBar); /*把 toolbar 设置到Activity 中*/
        onCreateCustomToolBar(mToolBar); /*对toolbar自定义的一些操作*/
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置箭头可见
        mToolBar.setNavigationIcon(R.drawable.back_black);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToolbarIconDo();
            }
        });
        mTxtTiltle = (TextView) mToolBar.findViewById(R.id.txt_toolbar_title);
        mTxtTiltle.setText(setToolbarTitle());
        mTxtRight = (TextView) mToolBar.findViewById(R.id.txt_toolbar_right);
        mTxtRight.setVisibility(View.GONE);
    }

    public abstract void setToolbarIconDo();

    public abstract String setToolbarTitle();

    public void onCreateCustomToolBar(Toolbar toolbar) {
        toolbar.setContentInsetsRelative(0, 0);
    }

    /**
     * 适配miui系统标题栏颜色
     */
    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
