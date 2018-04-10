package com.xi6666.login.presenter;


import android.util.Log;

import com.google.gson.Gson;
import com.xi6666.databean.TokenBean;
import com.xi6666.login.contract.LoginContract;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.MD5Utils;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Mr_yang on 2016/11/5.
 */

public class LoginPresenterImpl implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenterImpl";
    private ApiRest mApiRest;
    private LoginContract.View mLoginContract;

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;
    }

    @Override
    public void attachView(LoginContract.View view) {
        mLoginContract = view;

    }

    @Override
    public void getToken() {

        mApiRest.getLoginToken().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<TokenBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error--->" + e);
                //mLoginContract.showToast("" + e);
            }

            @Override
            public void onNext(TokenBean tokenBean) {
                if (tokenBean.isSuccess()) {
                    String encode = MD5Utils.encode(MD5Utils.encode("CHEXIAOXI:T*^OK*&E%^N") + MD5Utils.encode(tokenBean.getData().substring(0, 10)));
                    mLoginContract.setToken(encode);
                } else {
                    mLoginContract.showToast(tokenBean.getInfo());
                }
            }
        });
    }

    @Override
    public void getYzm(String mobile, String token) {
        LogUtil.d(TAG, "mobile--->" + mobile);
        LogUtil.d(TAG, "token--->" + token);
        mApiRest.getYzm(mobile, token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    LogUtil.d(TAG, "yanzhengma---" + string);
                    TokenBean tokenBean = new Gson().fromJson(string, TokenBean.class);
                    mLoginContract.showToast(tokenBean.getInfo());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
