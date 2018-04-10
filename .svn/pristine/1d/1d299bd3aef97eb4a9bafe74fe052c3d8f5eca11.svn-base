package com.xi6666.illegal.other;

import com.xi6666.app.baset.BaseTModel;
import com.xi6666.app.baset.BaseTPresenter;
import com.xi6666.app.baset.BaseTView;
import com.xi6666.databean.IllegaPayBean;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Mr_yang on 2017/2/8.
 */

public interface IllegaSurePayContract {
    interface Model extends BaseTModel {
        Observable<ResponseBody> creatOrder(String oderId);

        Observable<ResponseBody> changePayType(String userId,
                                               String payId, String orderSn);

        Observable<IllegaPayBean> createPayInfo(String userId,
                                                String payId, String orderSn);

        Observable<ResponseBody> getPayParam(String url,
                                        String attach,
                                        String body,
                                        String out_trade_no,
                                        String pay_id,
                                        String pay_name,
                                        String total_fee);

    }

    interface View extends BaseTView {
        void setOrderInfo(String money, String data, String frequency);


    }

    abstract class Presenter extends BaseTPresenter<Model, View> {

        public abstract void creatOrder(String oderId);

        public abstract void changePayType(String userId,
                                           String payId);

        public abstract void createPayInfo(String userId,
                                           String payId);
    }
}
