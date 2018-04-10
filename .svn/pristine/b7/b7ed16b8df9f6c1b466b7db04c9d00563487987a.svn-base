package com.xi6666.addoil.contract;

import com.xi6666.app.BaseView;
import com.xi6666.app.BasePersenter;

import retrofit2.http.Field;

/**
 * Created by Mr_yang on 2016/12/3.
 */

public interface AddOilPayContract {
    interface View extends BaseView {
        void showToast(String s);

        void hasCoupon(boolean state, String content);

        void setData(String everyMonth, String discount, String save, String shouldPay);

        void setCouPonData(String reduce, String save, String pay);

        void clearCouPonId();

    }

    interface Presenter extends BasePersenter<View> {

        void createOrderInfo(String package_id, String package_cash, String user_id
                , String user_token);

        void createOrder(String card_id, String package_cash,
                         String package_id, String pay_id, String couponId);

        void changePaytype(String order, String payType, String userId, String userToken, String couPonId);

        void toPay(String payType);

        void loadCouponData(String card_id, String package_cash,
                            String package_id, String pay_id, String couponId);

    }

}
