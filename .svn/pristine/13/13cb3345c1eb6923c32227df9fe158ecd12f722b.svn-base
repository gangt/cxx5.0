package com.xi6666.feedback.presenter;

import android.util.Log;

import com.xi6666.common.UserData;
import com.xi6666.feedback.contract.FeedBackContact;
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
 * Created by Mr_yang on 2016/11/29.
 */

public class FeedBackPresenterImpl implements FeedBackContact.Presenter {
    private static final String TAG = "FeedBackPresenterImpl";
    private FeedBackContact.View mView;

    private ApiRest mApiRest;

    @Override
    public void sendData(String context) {
        String userId = UserData.getUserId();
        String userToken = UserData.getUserToken();
        mApiRest.feedBack(context, userId, userToken).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
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
                            LogUtil.d(TAG, "string--->" + string);

                            JSONObject jsonObject = new JSONObject(string);
                            if (jsonObject.getBoolean("success")) {
                                mView.closeAct();
                            }
                            mView.showToast(jsonObject.getString("info"));

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    @Override
    public void attachView(FeedBackContact.View view) {
        this.mView = view;
    }

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;
    }


}
