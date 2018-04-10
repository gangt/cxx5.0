package com.xi6666.login.contract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;
import com.xi6666.databean.AddOilPopuBean;

import java.util.List;

/**
 * Created by Mr_yang on 2016/11/5.
 */

public interface LoginContract {
    interface View extends BaseView {


        void setToken(String string);

        void sendYzm();

        void showToast(String string);

    }

    interface LoginView extends BaseView {
        void showToast(String string);

        void closeAct();

        void saveUserId(String userId);

        void showLodingDialog();

        void hideLoadingDialog();

        void showAddoilPopu(List<AddOilPopuBean.DataBean.CouponListBean> dataBeen);

        void receiveSuccess(String content);

        void setTitle(String title);

    }

    interface Presenter extends BasePersenter<LoginContract.View> {
        void getToken();

        void getYzm(String mobile, String token);


    }

    interface LoginPresenter extends BasePersenter<LoginContract.LoginView> {
        void phoneSignIn(String mobile, String code);

        void accountSignIn(String userName, String userPwd);

        void loadPopuData(String userId, String userToken);

        void receiveAddOil();

        void getTitle();
    }

}
