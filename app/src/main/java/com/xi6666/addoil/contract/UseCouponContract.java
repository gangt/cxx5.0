package com.xi6666.addoil.contract;

import com.xi6666.app.baset.BaseTModel;
import com.xi6666.app.baset.BaseTPresenter;
import com.xi6666.app.baset.BaseTView;
import com.xi6666.databean.UseCouponBean;

import java.util.List;

import rx.Observable;

/**
 * Created by Mr_yang on 2017/2/24.
 */

public interface UseCouponContract {
    interface Model extends BaseTModel {
        Observable<UseCouponBean> userCouponList(String userId, String userToken, String page);

    }

    interface View extends BaseTView {
        void setItemData(List<UseCouponBean.DataBean> itemData);

        void showLoading();

        void hideStateLayout();

        void showError();

        void showNoData();

        void refreshFinish();

        void loadMoreFinish();

        void showEmpty();
    }

    abstract class Presenter extends BaseTPresenter<Model, View> {
        public abstract void userCouponList(String userId, String userToken);
    }
}
