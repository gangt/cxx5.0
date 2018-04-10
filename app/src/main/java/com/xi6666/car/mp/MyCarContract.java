package com.xi6666.car.mp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.car.bean.MyCarBean;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 16/11/26 上午11:38.
 * 个人公众号 ardays
 */
public interface MyCarContract {

    interface Model extends BaseModel {
        /**
         * 拿我的爱车列表
         */
        Observable<MyCarBean> getMyCarList();

        /**
         * 设置默认爱车
         */
        Observable<BaseBean> setDefaultCar(String car_id, String car_brand_id, String car_chexing_id, String car_pailiang_id, String car_nianfen_id);

        /**
         * 删除我的爱车
         */
        Observable<BaseBean> delCar(String car_id);
    }

    interface View extends BaseView {
        /**
         * 返回我的爱车列表
         */
        void returnCarList(MyCarBean bean);

        /**
         * 爱车状态返回
         *
         * @param bean
         */
        void returnCarResult(BaseBean bean, int position);

        /**
         * 返回删除结果
         */
        void returnDelResult(BaseBean bean, int position);

        /**
         * 加载错误
         */
        void returnError(String error);


        /**
         * 列表请求错误
         */
        void returnListNetError();
    }


    abstract class Presenter extends BasePresenter<Model, View> {

        /**
         * 请求我的爱车列表
         */
        public abstract void requestMyCarList();


        /**
         * 设置默认爱车
         *
         * @param car_id         车的ID
         * @param position       下标
         * @param car_chexing_id 车型id
         */
        public abstract void requestSetDefaultCar(String car_id, String car_brand_id, String car_chexing_id, String car_pailiang_id, String car_nianfen_id, int position);


        /**
         * 删除我的爱车
         *
         * @param car_id 我的爱车
         */
        public abstract void requestDelCar(String car_id, int position);
    }
}
