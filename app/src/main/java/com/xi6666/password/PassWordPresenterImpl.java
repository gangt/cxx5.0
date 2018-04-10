package com.xi6666.password;

import android.text.TextUtils;

import com.xi6666.common.UserData;
import com.xi6666.eventbus.ChangeUserDataEvent;
import com.xi6666.network.ApiRest;
import com.xi6666.password.contract.PasswordContract;
import com.xi6666.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/11/12.
 */

public class PassWordPresenterImpl implements PasswordContract.Presenter {
    private static final String TAG = "PassWordPresenterImpl";
    private ApiRest mApiRest;
    private PasswordContract.View mView;

    @Inject
    public PassWordPresenterImpl(ApiRest apiRest) {
        this.mApiRest = apiRest;
    }

    @Override
    public void attachView(PasswordContract.View view) {
        this.mView = view;
    }

    @Override
    public void setPhoneData() {

    }

    @Override
    public void savePassWord(String userId, String password, String userToken, String userName) {

        LogUtil.d(TAG, "userId--->" + userId);
        LogUtil.d(TAG, "password--->" + password);
        LogUtil.d(TAG, "userToken--->" + userToken);

        mApiRest.modifyPassWord(userId, password, userToken, userName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, "error--->" + e);
                mView.showToast("服务端数据错误请稍后重试!");
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    LogUtil.d(TAG, "string--->" + string);
                    mView.showToast(new JSONObject(string).getString("info"));
                    if (new JSONObject(string).getBoolean("success")) {
                        //修改成功
                        mView.closeAct();
                        EventBus.getDefault().post(new ChangeUserDataEvent("change"));
                        mView.startLoginAct();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getUserName() {
        String userId = UserData.getUserId();
        String userToken = UserData.getUserToken();

        mApiRest.getUserName(userId, userToken).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
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
                    LogUtil.d(TAG, "username--->" + string);
                    JSONObject jsonObject = new JSONObject(string);
                    mView.setAcountName(jsonObject.getJSONObject("data").getString("user_name"));
                    if (jsonObject.getBoolean("success")) {
                        if (TextUtils.equals("1", jsonObject.getJSONObject("data").getString("is_set_user_name"))) {
                            mView.canNotChange();
                            mView.setState("登录账号名无法再次修改");
                        }
                    } else {
                        mView.showToast(jsonObject.getString("info"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

