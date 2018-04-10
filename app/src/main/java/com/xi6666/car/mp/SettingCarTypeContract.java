package com.xi6666.car.mp;

import com.xi6666.car.bean.BrandCarBean;
import com.xi6666.car.bean.CarBrandTypeBean;
import com.xi6666.car.bean.CarEngineBean;
import com.xi6666.car.bean.CarYearTypeBean;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2017/1/16 上午10:59.
 * 个人公众号 ardays
 */
public interface SettingCarTypeContract {

    interface Model extends BaseModel {
        Observable<BrandCarBean> getCarType();

        Observable<CarBrandTypeBean> getCarBrandType(String brand_number);

        Observable<CarYearTypeBean> getCarYear(String cartype_number);

        Observable<CarEngineBean> getCarEngine(String brand_number, String cartype_number);
    }

    interface View extends BaseView {
        void resultCarType(BrandCarBean bean);

        void resultCarBrandType(CarBrandTypeBean bean);

        void resultCarYear(CarYearTypeBean bean);

        void resultCarEngine(CarEngineBean bean);

        /**
         * 获取品牌网络错误
         */
        void brandNetError();

        /**
         * 网络错误
         */
        void brandTypeNetError();


    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 请求车的品牌信息
         */
        public abstract void requestCarBrand();

        /**
         * 请求车的品牌型号
         * @param brand_number 品牌ID
         */
        public abstract void requestCarBrandType(String brand_number);

        /**
         * 请求车的品牌型号当前年限列表
         * @param cartype_number
         */
        public abstract void reqeustCarYear(String cartype_number);


        /**
         * 请求车的排油型号
         * @param brand_number
         * @param cartype_number
         */
        public abstract void requestCarEngine(String brand_number, String cartype_number);
    }
}
