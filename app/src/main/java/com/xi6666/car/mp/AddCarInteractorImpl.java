package com.xi6666.car.mp;

import com.xi6666.app.BaseApplication;
import com.xi6666.car.bean.AddCarBean;
import com.xi6666.car.bean.AddCarParams;
import com.xi6666.car.http.OkHttp;
import com.xi6666.car.http.RequestParams;
import com.xi6666.utils.SpUtils;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午3:12.
 * 个人公众号 ardays
 */

public class AddCarInteractorImpl implements AddCarInteractor {

    private AddCarListener mListener;

    public AddCarInteractorImpl(AddCarListener mListener){
        this.mListener = mListener;
    }

    @Override
    public void upCarData(AddCarParams params) {
        RequestParams requestParams = new RequestParams();
        requestParams.addUrl("/Brand/add_mycar");
        requestParams.put("car_brand",params.car_brand);
        requestParams.put("car_chexing",params.car_chexing);
//        requestParams.put("placment",params.getPlacment());
        requestParams.put("car_displacemen",params.car_displacemen);
        requestParams.put("car_kilometre",params.car_kilometre);
        requestParams.put("car_nianfen",params.car_nianfen);
        requestParams.put("car_pailiang",params.car_pailiang);
        requestParams.put("car_plate",params.car_plate);
        requestParams.put("is_default",params.is_default);
        requestParams.put("car_id",params.car_id);
//        requestParams.put("city",params.getCity());
        OkHttp.get(requestParams, AddCarBean.class, new OkHttp.OkHttpCallBack<AddCarBean>() {
            @Override
            public void onFailure(Exception e) {
                //网络请求成功
            }

            @Override
            public void onResponse(AddCarBean addCarBean) {
                mListener.addDataResult(addCarBean);
            }
        });
    }
}
