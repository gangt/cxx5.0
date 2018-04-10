package com.xi6666.userdata;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.xi6666.common.UserData;
import com.xi6666.evaluate.bean.UploadBean;
import com.xi6666.eventbus.ChangeUserDataEvent;
import com.xi6666.eventbus.LoginEvent;
import com.xi6666.databean.UserDataBean;
import com.xi6666.network.ApiRest;
import com.xi6666.userdata.contract.UserDataContract;
import com.xi6666.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/11/21.
 */

public class UserDataPresenterImpl implements UserDataContract.Presenter {
    private static final String TAG = "UserDataPresenterImpl";
    private ApiRest mApiRest;

    private UserDataContract.View mView;
    private File mPathDir;


    public UserDataPresenterImpl(ApiRest apiRest) {
        mApiRest = apiRest;
    }


    @Override
    public void attachView(UserDataContract.View view) {
        this.mView = view;
    }


    @Override
    public void loadUserData() {
        mView.showLoadingDialog();
        mApiRest.getUserInfo(UserData.getUserId(), UserData.getUserToken()).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {
                mView.hidLoadingDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, e + "");
                mView.showToast("服务端数据错误,请稍后重试");
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    JSONObject jsonObject = new JSONObject(string);
                    if (jsonObject.getBoolean("success")) {
                        UserDataBean userDataBean = new Gson().fromJson(string, UserDataBean.class);
                        if (userDataBean != null) {
                            mView.setUserDataBean(userDataBean);
                        }
                        String user_mobile = userDataBean.getData().getUser_mobile();
                        String user_nickname = userDataBean.getData().getUser_nickname();
                        String user_sex = userDataBean.getData().getUser_sex();
                        String user_birthday = userDataBean.getData().getUser_birthday();
                        if (user_mobile.length() == 11) {
                            user_mobile = (user_mobile.substring(0, 3)
                                    + "****" + user_mobile.substring(7, 11));
                        }

                        mView.setUserData(user_mobile, user_nickname, user_sex, user_birthday);
                        mView.setHead(userDataBean.getData().getUser_face());
                        mView.setQRCode(userDataBean.getData().getUser_qrcode());

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

    @Override
    public void setUserData(String user_birthday, String user_face, String user_nickname,
                            String user_sex, String userId, String userToken) {
        mView.showLoadingDialog();
        mApiRest.setUserData(user_birthday, user_face, user_nickname, user_sex, userId, userToken
        ).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {
                mView.hidLoadingDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, "error---->" + e);
                mView.showToast("服务端数据错误,请稍后重试");
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    mView.showToast(new JSONObject(string).getString("info"));
                    if (new JSONObject(string).getBoolean("success")) {
                        EventBus.getDefault().post(new ChangeUserDataEvent(""));
                        mView.closeAct();
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
    public void showChoiceHead() {
        //判断内存卡是否够用
        String externalStorageState = Environment.getExternalStorageState();
        if (!externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            mView.showToast("内存卡不够使用");
            return;
        }
        //创建文件夹
        mPathDir = new File(Environment.getExternalStorageDirectory().toString() + "/Photo_CXX/");
        //判断如果没有就创建
        if (!mPathDir.exists()) {
            mPathDir.mkdir();
        }
        mView.choicePic(mPathDir);
    }

    @Override
    public void changeHeadFace(File file) {
        Map<String, RequestBody> files = new HashMap<>();
        files.put("file_" + "\"; filename=" + "image.png",
                RequestBody.create(MediaType.parse("multipart/form-data"),
                        file));
        mApiRest.setUserFace(files).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, "---error" + e);
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    UploadBean uploadBean = new Gson().fromJson(string, UploadBean.class);
                    if (uploadBean.isSuccess()) {
                        if (uploadBean.getData().size() > 0) {
                            mView.setUserFaceUrl(uploadBean.getData().get(0));
                        }
                    } else {
                        mView.showToast(uploadBean.getInfo());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
