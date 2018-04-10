package com.xi6666.cardbag.constract;

import com.xi6666.app.baset.BaseTModel;
import com.xi6666.app.baset.BaseTPresenter;
import com.xi6666.app.baset.BaseTView;
import com.xi6666.databean.CouponBean;

import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Mr_yang on 2017/1/17.
 */

public interface CouPonContract {
    interface Model extends BaseTModel {
        Observable<CouponBean> getCouponList(String page,
                                             String money,
                                             String userid,
                                             String user_token);

        Observable<ResponseBody> deleteCouponCard(String coupnCard,
                                                  String userId,
                                                  String userToken);


    }

    interface View extends BaseTView {
        void showLoading(boolean show);

        void showError(boolean show);

        void setListData(List<CouponBean.DataBean> dataBean);

        void hasNoData(boolean has);

        void refreshData();

    }

    abstract class Presenter extends BaseTPresenter<Model, View> {
        public abstract void getCouponList(String page,
                                           String money,
                                           String userid,
                                           String user_token);

        public abstract void deleteCoupon(String coupnCard,
                                          String userId,
                                          String userToken);
    }
}
