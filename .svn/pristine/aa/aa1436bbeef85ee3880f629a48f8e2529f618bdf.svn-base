package com.xi6666.app;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.umeng.analytics.MobclickAgent;

import com.xi6666.view.swipe.SwipeBackActivityBase;
import com.xi6666.view.swipe.SwipeBackActivityHelper;
import com.xi6666.view.swipe.SwipeBackLayout;
import com.xi6666.view.swipe.Utils;



import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;


/**
 * Created by Mr_yang on 2016/10/12.
 */

/**
 * @author peng
 * @data 创建时间:2016/10/12
 * @desc activity的起始类
 */
public class SuperAct extends AppCompatActivity implements SwipeBackActivityBase {

    private InputMethodManager mSystemService;
    private SwipeBackActivityHelper mSwipeBackActivityHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 横屏
        ActManager.getAppManager().addActivity(this);
        mSystemService = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mSwipeBackActivityHelper = new SwipeBackActivityHelper(this);
        mSwipeBackActivityHelper.onActivityCreate();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mSwipeBackActivityHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mSwipeBackActivityHelper != null)
            return mSwipeBackActivityHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mSwipeBackActivityHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        JPushInterface.onResume(this);

        SwipeBackLayout mSwipeBackLayout = mSwipeBackActivityHelper.getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        JPushInterface.onPause(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //关闭软键盘
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                mSystemService.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       ActManager.getAppManager().finishActivity(this);
    }
}
