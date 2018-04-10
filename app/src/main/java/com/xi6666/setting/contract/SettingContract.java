package com.xi6666.setting.contract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;

/**
 * Created by Mr_yang on 2016/11/19.
 */

public interface SettingContract {
    interface View extends BaseView {

        void showToast(String string);

        void setVersionName(String versionName);

        void startHtmlAct();

        void showSigOut();

        void hideSigOut();

        void showLoadingDialog();

        void hideLoadingDialog();

        void showAlertDialog();

        void closeAndJumpToLogin();

    }

    interface Presenter extends BasePersenter<View> {

        void sigState();

        void signout(String userId, String userToken);
    }
}
