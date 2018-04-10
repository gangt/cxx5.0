package com.xi6666.password.contract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;

/**
 * Created by Mr_yang on 2016/11/12.
 */

public interface PasswordContract {
    interface View extends BaseView {

        void showToast(String string);

        void closeAct();

        void startLoginAct();

        void setAcountName(String name);

        void setState(String string);

        void canNotChange();
    }

    interface Presenter extends BasePersenter<PasswordContract.View> {

        void setPhoneData();

        void savePassWord(String userId, String password, String userToken, String userName);

        void getUserName();


    }
}
