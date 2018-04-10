package com.xi6666.car.mp;

import com.xi6666.R;
import com.xi6666.car.bean.BrandCarBean;
import com.xi6666.car.bean.CarBrandTypeBean;
import com.xi6666.car.bean.CarEngineBean;
import com.xi6666.car.bean.CarYearTypeBean;
import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.network.ApiRest;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2017/1/16 上午10:59.
 * 个人公众号 ardays
 */
public class SettingCarTypeModel implements SettingCarTypeContract.Model {


    @Override
    public Observable<BrandCarBean> getCarType() {
        return Api.getInstance()
                .mAppUrls
                .getBrandCar("s")
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<CarBrandTypeBean> getCarBrandType(String brand_number) {
        return  Api.getInstance()
                .mAppUrls
                .getBrandTypeCar(brand_number)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<CarYearTypeBean> getCarYear(String cartype_number) {
        return Api.getInstance()
                .mAppUrls
                .getYearCar(cartype_number)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<CarEngineBean> getCarEngine(String brand_number, String cartype_number) {
        return Api.getInstance()
                .mAppUrls
                .getEngineCar(brand_number, cartype_number)
                .compose(RxSchedulers.io_main());
    }
}
