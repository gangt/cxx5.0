package com.xi6666.illegal.see.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.illegal.see.bean.GenerateRedeemCodeBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2017/2/8 下午5:34.
 * 个人公众号 ardays
 */
public interface RedeemCodeContract {

    interface Model extends BaseModel {
        Observable<GenerateRedeemCodeBean> getGenerateRedeemCode(String redeem_code);

    }

    interface View extends BaseView {
        void returnGenerateRedeemCode(GenerateRedeemCodeBean bean);

        void generateError();
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 请求生成兑换码
         * @param redeem_code 兑换码
         */
        public abstract void requestGenerateRedeemCode(String redeem_code);
    }
}
