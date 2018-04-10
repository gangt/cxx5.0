package com.xi6666.address.fragment.mvp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/25 上午10:03.
 * 个人公众号 ardays
 */
public class EditAddressModel implements EditAddressContract.Model {


    @Override
    public Observable<BaseBean> addAddress(String address, String city, String consignee, String district, String is_default, String mobile, String province) {
        return Api.getInstance()
                .mAppUrls
                .addAddress(address, city, consignee, district, is_default, mobile, province)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BaseBean> updateAddress(String address_id, String address, String city, String consignee, String district, String is_default, String mobile, String province) {
        return Api.getInstance()
                .mAppUrls
                .updateAddress(address_id, address, city, consignee, district, is_default, mobile, province)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BaseBean> deleteAddress(String address_id) {
        JSONArray array = new JSONArray();
        JSONObject ob = new JSONObject();
        try {
            ob.put("address_id", address_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        array.put(ob);
        return Api.getInstance()
                .mAppUrls
                .deleteAddress(array.toString())
                .compose(RxSchedulers.io_main());
    }
}
