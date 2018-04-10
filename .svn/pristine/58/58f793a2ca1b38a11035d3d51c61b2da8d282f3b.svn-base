package com.xi6666.cityaddress.presenter;

import com.xi6666.cityaddress.contract.AddressContract;
import com.xi6666.databean.AddressBean;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_yang on 2016/11/11.
 */

public class AddressPresenterCityImpl implements AddressContract.CityPresenter {
    public static final String TAG = "AddressCityImpl";


    private AddressContract.CityView mCityView;

    private ApiRest mApiRest;

    @Override
    public void attachView(AddressContract.CityView view) {
        this.mCityView = view;
    }

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;
    }

    @Override
    public void loadData(String cityId) {
        mCityView.showLoading();
        mApiRest.getCityAddress().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<AddressBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, "error-->" + e);
                mCityView.showError();
            }

            @Override
            public void onNext(AddressBean addressBean) {
                LogUtil.d(TAG, "addressBean-->" + addressBean.getInfo());
                mCityView.hideStateView();
                if (addressBean.isSuccess()) {
                   // mCityView.addData();
                }
            }
        });
    }
}
