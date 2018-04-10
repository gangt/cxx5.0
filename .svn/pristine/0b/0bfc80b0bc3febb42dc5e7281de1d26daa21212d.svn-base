package com.xi6666.login.presenter;


import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.xi6666.common.UserData;
import com.xi6666.databean.AddOilPopuBean;
import com.xi6666.databean.UserLoginDataBean;
import com.xi6666.login.contract.LoginContract;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.MD5Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/11/7.
 */

public class LoginActPresenterImpl implements LoginContract.LoginPresenter {

    private static final String TAG = "LoginActPresenterImpl";
    private LoginContract.LoginView view;

    private ApiRest mApiRest;

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;
    }

    @Override
    public void attachView(LoginContract.LoginView view) {
        this.view = view;
    }


    @Override
    public void phoneSignIn(String mobile, String code) {
        view.showLodingDialog();
        mApiRest.phoneLogin(mobile, code).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {
                view.hideLoadingDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, "Throwable--->" + e);
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    LogUtil.d(TAG, "登陆数据---" + string);
                    view.showToast(new JSONObject(string).getString("info"));
                    if (TextUtils.equals("true", new JSONObject(string).getString("success"))) {
                        //登录成功
                        //保存用户数据
                        UserData.setUserData(string);
                        //保存用户Id和token
                        UserLoginDataBean userLoginDataBean = new Gson().fromJson(string, UserLoginDataBean.class);
                        UserData.setUserId(userLoginDataBean.getData().getUser_id());
                        String encode = MD5Utils.encode(MD5Utils.encode(userLoginDataBean.getData().getUser_id()
                                + "CHEXIAOXI:T*^OK*&E%^N") + MD5Utils.encode(userLoginDataBean.getData().getUser_token()
                        ));
                        UserData.setUserToken(encode);
                        loadPopuData(UserData.getUserId(), UserData.getUserToken());
                        view.closeAct();
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
    public void accountSignIn(String userName, String userPwd) {
        view.showLodingDialog();
        mApiRest.accountLogin(userName, userPwd).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {
                view.hideLoadingDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, "error--->" + e);
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    view.showToast(new JSONObject(string).getString("info"));
                    if (TextUtils.equals("true", new JSONObject(string).getString("success"))) {
                        //保存用户数据
                        UserData.setUserData(string);
                        //保存用户Id和token
                        UserLoginDataBean userLoginDataBean = new Gson().fromJson(string, UserLoginDataBean.class);
                        UserData.setUserId(userLoginDataBean.getData().getUser_id());
                        LogUtil.d(TAG, "Id--->" + userLoginDataBean.getData().getUser_id());
                        LogUtil.d(TAG, "token--->" + userLoginDataBean.getData().getUser_token());
                        String encode = MD5Utils.encode(MD5Utils.encode(userLoginDataBean.getData().getUser_id() + "CHEXIAOXI:T*^OK*&E%^N") +
                                MD5Utils.encode(userLoginDataBean.getData().getUser_token()));
                        String encode1 = MD5Utils.encode(userLoginDataBean.getData().getUser_id() + "CHEXIAOXI:T*^OK*&E%^N");

                        String encode2 = MD5Utils.encode(userLoginDataBean.getData().getUser_token());
                        String encode3 = MD5Utils.encode(encode1 + encode2);
                        LogUtil.d(TAG, "1--->" + encode1);
                        LogUtil.d(TAG, "2--->" + encode2);
                        LogUtil.d(TAG, "3--->" + encode3);

                        LogUtil.d(TAG, "tokenencode--->" + encode);
                        UserData.setUserToken(encode);
                        loadPopuData(UserData.getUserId(), UserData.getUserToken());
                        view.closeAct();
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
    public void loadPopuData(String userId, String userToken) {
        mApiRest.addOilPopu(userId, userToken).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<AddOilPopuBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "e->" + e);
                    }

                    @Override
                    public void onNext(AddOilPopuBean addOilPopuBean) {
                        if (addOilPopuBean.isSuccess()) {
                            if (addOilPopuBean.getData().getIs_receive() == 1) {
                                view.showAddoilPopu(addOilPopuBean.getData().getCoupon_list());
                            }
                        } else {
                            view.showToast(addOilPopuBean.getInfo());
                        }
                    }
                });
    }

    @Override
    public void receiveAddOil() {
       /* mApiRest.receiveAddOilCard(UserData.getUserId(), UserData.getUserToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
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

                            if (jsonObject.getBoolean("success")) {
                                view.receiveSuccess(jsonObject.getString("data"));
                            } else {
                                view.showToast(jsonObject.getString("info"));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });*/
    }

    @Override
    public void getTitle() {
        mApiRest.getLoginTitle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
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
                    String data = jsonObject.getString("data");
                    view.setTitle(data);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
