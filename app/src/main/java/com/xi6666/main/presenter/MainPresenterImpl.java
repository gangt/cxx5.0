package com.xi6666.main.presenter;

import com.google.gson.Gson;
import com.xi6666.common.UserData;
import com.xi6666.databean.MainVersionData;
import com.xi6666.main.contract.MainContract;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/12/1.
 */

public class MainPresenterImpl implements MainContract.Presenter {
    public static final String TAG = "MainPresenterImpl";
    private MainContract.View mView;
    private ApiRest mApiRest;

    @Override
    public void attachView(MainContract.View view) {
        this.mView = view;
    }

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;
    }


    @Override
    public void loadVersion() {
        mApiRest.loadVersion().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {


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
                    LogUtil.d(TAG, "Str--->" + string);
                    MainVersionData mainVersionData = new Gson().fromJson(string, MainVersionData.class);
                    mView.showUpVersion(mainVersionData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void LoginTime() {
        mApiRest.getUserLoginTime(UserData.getUserId(), UserData.getUserToken()).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
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
                    JSONObject jsonObject = new JSONObject(string);
                    if (!jsonObject.getBoolean("success")) {
                        mView.showToast(jsonObject.getString("info"));
                        UserData.cleanUserData();
                        mView.gotoLogin();
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
