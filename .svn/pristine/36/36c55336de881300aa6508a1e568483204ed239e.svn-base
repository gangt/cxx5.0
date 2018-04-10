package com.xi6666.address.fragment.mvp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.address.fragment.mvp.bean.PersonalAddressBean;
import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/24 下午2:34.
 * 个人公众号 ardays
 */
public class PersonalAddressModel implements PersonalAddressContract.Model {


    @Override
    public Observable<PersonalAddressBean> getAddressList() {
        return Api.getInstance().mAppUrls
                .getAddressList("yaya")
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BaseBean> setDefaultAddress(String address_id) {
        return Api.getInstance().mAppUrls
                .setDeufaltAddress(address_id)
                .compose(RxSchedulers.io_main());
    }
}
