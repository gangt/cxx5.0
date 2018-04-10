package com.xi6666.main.contract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;
import com.xi6666.databean.MainVersionData;

/**
 * Created by Mr_yang on 2016/12/1.
 */

public interface MainContract {

    interface View extends BaseView {
        void showUpVersion(MainVersionData mainVersionData);

        void gotoLogin();

        void showToast(String toast);
    }

    interface Presenter extends BasePersenter<View> {
        void loadVersion();

        void LoginTime();

    }
}
