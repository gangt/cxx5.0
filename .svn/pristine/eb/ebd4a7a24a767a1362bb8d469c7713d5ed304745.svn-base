package com.xi6666.setting;

import android.text.TextUtils;

import com.xi6666.common.UserData;
import com.xi6666.eventbus.LoginEvent;
import com.xi6666.network.ApiRest;
import com.xi6666.setting.contract.SettingContract;
import com.xi6666.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/11/19.
 */

public class SettingPresenterImpl implements SettingContract.Presenter {
    private static final String TAG = "SettingPresenterImpl";
    private ApiRest mApiRest;

    private SettingContract.View mView;

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;
    }

    @Override
    public void attachView(SettingContract.View view) {
        this.mView = view;
    }


    @Override
    public void sigState() {
        if (TextUtils.isEmpty(UserData.getUserId())) {
            mView.hideSigOut();
        } else {
            mView.showSigOut();
        }
    }

    @Override
    public void signout(String userId, String userToken) {
        mView.showLoadingDialog();
        mApiRest.signOut(userId, userToken).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {
                mView.hideLoadingDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, "e------>" + e);
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    LogUtil.d(TAG, "string------>" + string);

                    if (TextUtils.equals("true", new JSONObject(string).getString("success"))) {

                        UserData.cleanUserData();
                        sigState();
                        EventBus.getDefault().post(new LoginEvent("success"));
                        mView.closeAndJumpToLogin();
                    }
                    mView.showToast(new JSONObject(string).getString("info"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
