package com.xi6666.illegal.see.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.illegal.see.bean.IllegalCardStatusBean;
import com.xi6666.illegal.see.bean.RedeemCodeBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2017/2/8 下午3:49.
 * 个人公众号 ardays
 */
public interface IllegalRedeemCodeContract {

    interface Model extends BaseModel {
        Observable<IllegalCardStatusBean> getIllegalCardStatus(String card_id);

        Observable<RedeemCodeBean> getRedeemCode(String card_id);
    }

    interface View extends BaseView {
        /**
         * 返回违章卡状态信息
         * @param bean 违章卡状态信息
         */
        void returnIllegalCardStatus(IllegalCardStatusBean bean);

        /**
         * 返回兑换码
         * @param bean
         */
        void returnRedeemCode(RedeemCodeBean bean);


        void returnError();

        /**
         * 生成兑换码错误
         */
        void returnRedeemCodeError();
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 请求违章卡状态
         * @param card_id 油卡ID
         */
        public abstract void requestIllegalCardStatus(String card_id);

        /**
         * 生成兑换码
         * @param card_id 油卡ID
         */
        public abstract void requestRedeemCode(String card_id);
    }
}
