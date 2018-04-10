package com.xi6666.common;

import android.util.Log;

import com.xi6666.app.BaseApplication;
import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerApiComponent;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2017/1/18.
 */

public class BuriedPoint {
    private static final String TAG = "BuriedPoint";
    @Inject
    ApiRest mApiRest;

    public BuriedPoint() {
        AppComponets build = DaggerAppComponets.builder().
                apiModule(new ApiModule(BaseApplication.getApplication())).
                appModule(new AppModule(BaseApplication.getApplication())).build();
        DaggerApiComponent.builder().appComponets(build).build().Inject(this);
    }

    public void sendToServer(String data_sn, String data_name, String data_remark) {
        LogUtil.d(TAG, "data_sn--->" + data_sn + "\ndata_name--->" + data_name + "\ndata_remark--->" + data_remark);
        mApiRest.getYuriedPoint(data_sn, data_name, data_remark, "android", UserData.getUserId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error--->" + e.toString());
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    Log.d(TAG, "string--->" + string);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
