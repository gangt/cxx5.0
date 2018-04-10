package com.xi6666.mine.presenter;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xi6666.R;
import com.xi6666.cardbag.view.CardBagAct;
import com.xi6666.common.UserData;
import com.xi6666.databean.UserDataBean;
import com.xi6666.mine.contract.MineContract;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/11/1.
 */

public class MinePresenterImpl implements MineContract.Presenter {
    private static final String TAG = "MinePresenterImpl";
    private MineContract.View mView;
    private ApiRest mApiRest;
    private String[] mNames;
    private int[] mImages;

    @Inject
    public MinePresenterImpl(ApiRest apiRest) {
        mApiRest = apiRest;
    }

    @Override
    public void attachView(MineContract.View view) {
        mView = view;
    }


    @Override
    public void readData() {
        String userData = UserData.getUserData();
        if (!TextUtils.isEmpty(userData)) {
            UserDataBean userDataBean = new Gson().fromJson(userData, UserDataBean.class);
            mView.hideLoginButton();
            mView.addData(userDataBean);
            loadUserData();
        }

    }

    @Override
    public void loadUserData() {
        mView.showLoadingDialog();
        mApiRest.getUserInfo(UserData.getUserId(), UserData.getUserToken()).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoadingDialog();
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
                                UserData.setUserData(string);
                                UserDataBean userDataBean = new Gson().fromJson(string, UserDataBean.class);
                                mView.hideLoginButton();
                                mView.addData(userDataBean);
                                mView.hideLoginButton();
                            } else {
                                mView.showToast(jsonObject.getString("info"));
                                mView.showLoginButton();
                                UserData.cleanUserData();
                                mView.setCardNum("加油卡", "洗车卡", "优惠券");
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
    public void fucationData() {
        //加载功能区数据
        mNames = new String[]{"违章卡", "收藏", "评价与回复", "喜豆", "邀请好友", "联盟店加盟", "意见反馈", "客服中心"};
        mImages = new int[]{R.drawable.ic_mine_fucation_xck,
                R.drawable.ic_mine_fucation_sc,
                R.drawable.ic_mine_fucation_wd,
                R.drawable.ic_mine_fucation_xd,
                R.drawable.ic_mine_fucation_yq,
                R.drawable.ic_mine_fucation_lm,
                R.drawable.ic_mine_fucation_fk,
                R.drawable.ic_mine_fucation_kf};
        mView.addFucationData(mNames, mImages);
    }

    @Override
    public void loadCardNum() {
        mApiRest.getCardNum(UserData.getUserId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ResponseBody>() {
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
                            int anInt = jsonObject.getJSONObject("data").getInt("coupon_count");
                            int anInt1 = jsonObject.getJSONObject("data").getInt("oil_count");
                            int anInt2 = jsonObject.getJSONObject("data").getInt("wash_count");
                            String anInt3 = jsonObject.getJSONObject("data").getString("illegalcard_count");
                            mView.setCardNum("加油卡(" + anInt1 + ")", "洗车卡(" + anInt2 + ")", "优惠券(" + anInt + ")");
                            mNames[0] = "违章卡(" + anInt3 + ")";
                            mView.addFucationData(mNames, mImages);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void loadPointer() {
        mApiRest.minePointer(UserData.getUserId(), UserData.getUserToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ResponseBody>() {
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
                                mView.setPointer(jsonObject.getJSONObject("data").getInt("oil_back") == 1, jsonObject.getJSONObject("data").getInt("washcard_is_used") == 1,
                                        jsonObject.getJSONObject("data").getInt("coupon_is_used") == 1, jsonObject.getJSONObject("data").getInt("illegal_card") == 1);
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
    public void setNoIllegalCard() {
        mNames[0] = "违章卡";
        mView.addFucationData(mNames, mImages);
    }
}
