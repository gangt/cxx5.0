package com.xi6666.carWash.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.carWash.mvp.bean.CarWashListBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/21 下午5:07.
 * 个人公众号 ardays
 *
 * 洗车卡Mvp模式
 */

public interface CarWashCardContract {

    interface Model extends BaseModel{
        /**
         * 请求洗车卡列表
         */
        Observable<CarWashListBean> requestWashCardList();
    }


    interface View extends BaseView{
        /**
         * 洗车列表返回
         */
        void resultWashCardList(CarWashListBean carWash);
    }


    abstract class Presenter extends BasePresenter<Model, View>{
        /**
         * 获取洗车卡列表
         */
        public abstract void getWashCardList();
    }

}
