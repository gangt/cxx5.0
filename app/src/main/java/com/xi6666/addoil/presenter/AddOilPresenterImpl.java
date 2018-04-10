package com.xi6666.addoil.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.xi6666.addoil.contract.AddOilContract;
import com.xi6666.common.UserData;
import com.xi6666.databean.AddOilDataBean;
import com.xi6666.databean.AddOilPopuBean;
import com.xi6666.databean.DefaultOilCardBean;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/11/23.
 */

public class AddOilPresenterImpl implements AddOilContract.Presenter {
    private static final String TAG = "AddOilPresenterImpl";
    private AddOilContract.View mView;
    private ApiRest mApiRest;

    @Override
    public void attachView(AddOilContract.View view) {
        this.mView = view;
    }

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;
    }

    @Override
    public void loadData() {
        mView.showLodingDialog();
        mApiRest.getAddOilBean().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<AddOilDataBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d(TAG, "error1--->" + e);
                        mView.showToast("" + e);
                        mView.hideLoadingDialog();
                    }

                    @Override
                    public void onNext(AddOilDataBean addOilDataBean) {
                        mView.hideLoadingDialog();
                        if (addOilDataBean.isSuccess()) {
                            mView.setBanner(addOilDataBean.getBanner());
                            mView.setMaxMoney(addOilDataBean.getMax_money());
                            mView.setListData(addOilDataBean.getData());
                            mView.setQuanCunUrl(addOilDataBean.getLink_url());
                        } else {
                            mView.showToast("服务器端错误,请稍后重试!");
                        }

                    }
                });
    }

    @Override
    public void loadDefaultOilCard(String userId, String userToken) {
        LogUtil.d(TAG, "userId--->" + userId + "userToken--->" + userToken);
        mApiRest.getDefaultOilCard(userId, userToken).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DefaultOilCardBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d(TAG, "error2--->" + e);
                    }

                    @Override
                    public void onNext(DefaultOilCardBean defaultOilCardBean) {
                        LogUtil.d(TAG, "defaultOilCardBean--->" + defaultOilCardBean.getData());
                        if (defaultOilCardBean.isSuccess()) {
                            if (defaultOilCardBean.getData() == null) {
                                mView.showAddOilCard();
                            } else {
                                mView.showDefaultOilCard();

                                if (TextUtils.equals("1", defaultOilCardBean.getData().getCard_type())) {
                                    mView.setDefaultOilCard("中国石化", defaultOilCardBean.getData().getCard_number().replaceAll("\\d{4}(?!$)", "$0 "),
                                            defaultOilCardBean.getData().getCard_id());
                                }
                                if (TextUtils.equals("2", defaultOilCardBean.getData().getCard_type())) {
                                    mView.setDefaultOilCard("中国石油", defaultOilCardBean.getData().getCard_number().replaceAll("\\d{4}(?!$)", "$0 "),
                                            defaultOilCardBean.getData().getCard_id());
                                }
                            }
                        } else {

                        }

                    }
                });
    }

    @Override
    public void loadPeoleState() {
        mApiRest.getOilPerson().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
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
                        ArrayList<String> objects = new ArrayList<>();
                        JSONArray chargetime = jsonObject.getJSONArray("chargetime");
                        if (chargetime.length() > 0) {
                            for (int x = 0; x < chargetime.length(); x++) {
                                objects.add(chargetime.getString(x));
                            }
                        }
                        mView.setPeopleState(objects);
                        mView.setPeopleNum(jsonObject.getString("chargetatol") + "人");
                        mView.setSaveMoney(jsonObject.getString("chargesupport") + "元");
                    } else {
                        mView.showToast(jsonObject.getString("info"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void testingAddOil(String packageId, String userID, String userToken) {
        mApiRest.testingAddOil(packageId, userID, userToken).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
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
                        mView.startPay();

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
