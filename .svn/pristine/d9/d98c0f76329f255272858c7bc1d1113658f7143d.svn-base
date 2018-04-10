package com.xi6666.illegal.see.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.illegal.see.bean.CancelOrderBean;
import com.xi6666.illegal.see.bean.UsageDetailsBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2017/2/9 上午10:35.
 * 个人公众号 ardays
 */
public interface UsageDetailsContract {

    interface Model extends BaseModel {
        Observable<UsageDetailsBean> getUsageDetails(String log_id);

        Observable<CancelOrderBean> getCancelOrder(String log_id);
    }

    interface View extends BaseView {
        /**
         * 返回违章卡"使用记录详情"
         * @param bean
         */
        void returnUsageDetails(UsageDetailsBean bean);

        /**
         * 请求失败
         */
        void returnUsageDetailsError();

        /**
         * 返回违章卡记录取消
         */
        void returnCancelOrder(CancelOrderBean bean);
        /**
         * 返回违章卡记录取消失败
         */
        void returnCancelOrderError();
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 请求违章卡"使用记录详情"
         * @param log_id 违章卡订单id
         */
        public abstract void requestUsageDetails(String log_id);

        /**
         * 取消违章卡订单
         * @param log_id 违章卡订单id
         */
        public abstract void requestCancelOrder(String log_id);
    }
}
