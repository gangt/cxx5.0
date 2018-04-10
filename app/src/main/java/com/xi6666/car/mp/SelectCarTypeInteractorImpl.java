package com.xi6666.car.mp;

import com.xi6666.car.bean.CarEngineBean;
import com.xi6666.car.bean.CarBrandTypeBean;
import com.xi6666.car.bean.CarYearTypeBean;
import com.xi6666.car.bean.SelectCarBean;
import com.xi6666.car.http.OkHttp;
import com.xi6666.car.http.RequestParams;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午2:55.
 * 个人公众号 ardays
 */

public class SelectCarTypeInteractorImpl implements SelectCarTypeInteractor {

    private SelectCarListener mListener;

    public SelectCarTypeInteractorImpl(SelectCarListener mListener){
        this.mListener = mListener;
    }

    @Override
    public void getCarType() {
        RequestParams requestParams = new RequestParams();
        requestParams.addUrl("/brand/get_car_brand");
        OkHttp.get(requestParams, SelectCarBean.class, new OkHttp.OkHttpCallBack<SelectCarBean>() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onResponse(SelectCarBean myCarBean) {
                mListener.myCarDataList(myCarBean);
            }
        });
    }

    /**
     * 获取汽车品牌
     */
    @Override
    public void getCarSpType(String brand_number) {
        RequestParams params = new RequestParams();
        params.addUrl("/brand/get_car_number");
        params.put("brand_number", brand_number);
        OkHttp.get(params, CarBrandTypeBean.class, new OkHttp.OkHttpCallBack<CarBrandTypeBean>() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onResponse(CarBrandTypeBean carBrandTypeBean) {
                mListener.myCarDataSpList(carBrandTypeBean);
            }
        });

    }

    @Override
    public void getCarYearType(String cartype_number) {
        RequestParams params = new RequestParams();
        params.addUrl("/brand/get_tyep_year");
        params.put("cartype_number", cartype_number);
        OkHttp.get(params, CarYearTypeBean.class, new OkHttp.OkHttpCallBack<CarYearTypeBean>() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onResponse(CarYearTypeBean carTypeBean) {
                mListener.myCarYearDataList(carTypeBean);
            }
        });
    }

    @Override
    public void getCarEngineType(String brand_number, String cartype_number) {
        RequestParams params = new RequestParams();
        params.addUrl("/brand/get_displacement");
        params.put("brand_number",brand_number);
        params.put("cartype_number",cartype_number);
        OkHttp.get(params, CarEngineBean.class, new OkHttp.OkHttpCallBack<CarEngineBean>() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onResponse(CarEngineBean carTypeBean) {
                mListener.myCarEngineList(carTypeBean);
            }
        });
    }
}
