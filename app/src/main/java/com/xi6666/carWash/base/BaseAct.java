package com.xi6666.carWash.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.SuperAct;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.common.UserData;
import com.xi6666.login.view.LoginAct;
import com.xi6666.utils.TUtils;
import com.xi6666.view.dialog.LoadingDialog;

/**
 * 创建者 sunsun
 * 时间 16/10/31 下午3:44.
 * 个人公众号 ardays
 */

public abstract class BaseAct extends SuperAct implements BaseActImpl {


    /**
     * @return 获取布局ID
     */
    public abstract int getLayoutId();

    /**
     * 逻辑处理
     */
    public abstract void init();

    private Dialog mLoading;


    public Toast mToast;

    @Override
    public void make(String text) {
        //如果Toast为空让它生成一个
        if (mToast == null) mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
            //不为空就写入文字
        else mToast.setText(text);
        //显示Toast
        mToast.show();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉侵式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        if (getLayoutId() == 0) {
            make("布局异常,请查看布局Id");
        }
        setContentView(getLayoutId());
        init();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void showLoading() {
        if (mLoading == null) {
            mLoading = new LoadingDialog().LoadingDialog(getContext());
        }
        mLoading.show();
    }

    @Override
    public void hiddenLoading() {
        if (mLoading == null) {
            mLoading = new LoadingDialog().LoadingDialog(getContext());
        }
        mLoading.hide();
    }

    /**
     * 是否登陆 如果没登陆直接返回
     */
    public boolean isLogin() {
        boolean bol = TextUtils.isEmpty(UserData.getUserId()) || TextUtils.isEmpty(UserData.getUserToken());
        if (bol) {
            startActivity(new Intent(this, LoginAct.class));
        }
        return !bol;
    }

    /**
     * 防止异步线程加载图片失败
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
