package com.xi6666.cityaddress.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.xi6666.cityaddress.contract.AddressContract;
import com.xi6666.common.LocationService;
import com.xi6666.databean.AddressBean;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Mr_yang on 2016/11/11.
 */

public class AddressPresenterImpl implements AddressContract.Presenter {
    private static final String TAG = "AddressPresenterImpl";


    private AddressContract.View view;


    private Context mContext;


    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public void attachView(AddressContract.View view) {
        this.view = view;
    }


    @Override
    public void loadData() {
        view.showLoding();
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                try {
                    InputStreamReader isr = new InputStreamReader(mContext.getAssets().open("addressCity.json"));
                    BufferedReader br = new BufferedReader(isr);
                    String line;
                    StringBuilder builder = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        builder.append(line);
                    }
                    br.close();
                    isr.close();
                    String s = builder.toString();
                    subscriber.onNext(s);
                } catch (Exception e) {

                    e.printStackTrace();
                }


            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String string) {
                AddressBean addressBean = new Gson().fromJson(string, AddressBean.class);
                view.addData(addressBean.getData());
                view.hideError();
            }
        });
    }

    @Override
    public void location() {
        final LocationService locationService = new LocationService(mContext);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int what = msg.what;
                switch (what) {
                    case LocationService.DATA:
                        view.showLocation();
                        break;
                    case LocationService.NODATA:
                        Log.d(TAG, "nodata");
                        view.showLocationFial();
                        view.showToast("定位失败!");
                        break;
                }
                super.handleMessage(msg);
            }
        };
        //开启定位
        locationService.start(handler);
    }
}
