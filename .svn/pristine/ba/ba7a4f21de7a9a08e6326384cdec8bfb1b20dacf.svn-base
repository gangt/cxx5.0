package com.xi6666.mine.contract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;
import com.xi6666.databean.UserDataBean;

/**
 * Created by Mr_yang on 2016/11/1.
 */

public interface MineContract {

    interface View extends BaseView {

        void addData(UserDataBean userDataBean);

        void showLoginButton();

        void hideLoginButton();

        void addFucationData(String[] names, int[] images);

        void showToast(String string);

        void showLoadingDialog();

        void hideLoadingDialog();

        void setCardNum(String addOil, String clean, String discount);

        void setPointer(boolean one, boolean two, boolean thr, boolean fore);


    }

    interface Presenter extends BasePersenter<View> {
        void readData();

        void loadUserData();

        void fucationData();

        void loadCardNum();

        void loadPointer();

        void setNoIllegalCard();

    }
}
