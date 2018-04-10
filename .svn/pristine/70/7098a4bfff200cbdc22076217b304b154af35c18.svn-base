package com.xi6666.carWash.view.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.carWash.mvp.bean.DetermineOrderBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2016/12/1 上午10:14.
 * 个人公众号 ardays
 */
public interface DetermineOrderContract {

    interface Model extends BaseModel {
        /**
         * 生成订单
         */
        Observable<DetermineOrderBean> generateOrder(String service_id);
    }

    interface View extends BaseView {
        /**
         * 返回生成订单参数
         */
        void returnGenerateOrder(DetermineOrderBean bean);

        /**
         * 网络异常
         */
        void returnNetError();
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 请求生成订单
         * @param service_id 服务id
         */
        public abstract void requestGenerateOrder(String service_id);
    }
}
