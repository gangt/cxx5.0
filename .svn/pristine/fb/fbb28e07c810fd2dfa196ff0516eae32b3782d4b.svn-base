package com.xi6666.carWash.view.mvp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.carWash.view.mvp.bean.CashierBean;
import com.xi6666.carWash.view.mvp.bean.CashierDiscountBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2016/12/2 下午11:22.
 * 个人公众号 ardays
 */
public interface CashierContract {

    interface Model extends BaseModel {

        /**
         * 获取收银台详情
         */
        Observable<CashierBean> getCashierDetails(String order_id);

        /**
         * 选择折扣方式
         */
        Observable<CashierDiscountBean> selectServiceDiscount(String service_order_sn, String use_status);

        /**
         * 完全抵扣
         */
        Observable<BaseBean> fullDeduction(String service_order_sn);
    }

    interface View extends BaseView {
        /**
         * 返回收银台详情
         *
         * @param bean
         */
        void returnCashierDetails(CashierBean bean);

        /**
         * 返回折扣结果
         */
        void returnDiscountResult(CashierDiscountBean bean, int position);

        /**
         * 返回抵扣结果
         */
        void returnFullDeductionResult(BaseBean bean);

        /**
         * 抵扣网络异常
         */
        void returnFullDeductionError();

        /**
         * 返回网络异常
         */
        void returnNetError();

    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 请求收银台详情
         *
         * @param order_id 订单ID
         */
        public abstract void requestCashierDetails(String order_id);

        /**
         * 选择折扣方式
         *
         * @param service_order_sn 服务订单号
         * @param use_status       状态
         * @param position         下标
         */
        public abstract void requestSelectDiscount(String service_order_sn, String use_status, int position);

        /**
         * 请求数据完全抵扣
         * @param service_order_sn  订单号
         */
        public abstract void requestFullDeduction(String service_order_sn);
    }
}
