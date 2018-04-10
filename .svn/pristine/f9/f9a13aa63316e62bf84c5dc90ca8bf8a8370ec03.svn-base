package com.xi6666.carWash.base;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.xi6666.app.SuperFrgm;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.common.UserData;
import com.xi6666.login.view.LoginAct;
import com.xi6666.utils.TUtils;
import com.xi6666.view.dialog.LoadingDialog;

/**
 * 创建者 sunsun
 * 时间 16/11/4 下午5:15.
 * 个人公众号 ardays
 */

public abstract class BaseFrgm<P extends BasePresenter, M extends BaseModel> extends SuperFrgm {


    public P mPersenter; //业务逻辑层
    public M mModel;     //网络层

    private Dialog mLoading;

    @Override
    protected void init() {
        mPersenter = TUtils.getT(this, 0);
        mModel = TUtils.getT(this, 1);
        if (this instanceof BaseView) {
            mPersenter.setVm(this, mModel); //添加MVP的View回调事件
        }
        this.setUp();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPersenter != null) mPersenter.onDestory();
    }


    public Fragment getFragment() {
        return this;
    }

    protected abstract void setUp();

    /*       Toast 输出        */
    private Toast mToast;

    public void make(String toast) {
        if (mToast == null) mToast = Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT);
        else mToast.setText(toast);
        mToast.show();
    }


    public void showLoading() {
        if (mLoading == null) {
            mLoading = new LoadingDialog().LoadingDialog(getContext());
        }
        mLoading.show();
    }

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
            startActivity(new Intent(getActivity(), LoginAct.class));
        }
        return !bol;
    }

}
