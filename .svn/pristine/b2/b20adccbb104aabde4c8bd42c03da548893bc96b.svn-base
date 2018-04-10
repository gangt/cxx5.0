package com.xi6666.feedback.contract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;

/**
 * Created by Mr_yang on 2016/11/29.
 */

public interface FeedBackContact {
    interface View extends BaseView {
        void showToast(String info);

        void closeAct();
    }

    interface Presenter extends BasePersenter<View> {
        void sendData(String context);
    }
}
