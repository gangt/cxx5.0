package com.xi6666.cardbag.constract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;

/**
 * Created by Mr_yang on 2016/11/22.
 */

public interface WashCardInfoConstract {
    interface View extends BaseView {
        void showLoading();

        void hideStateLyout();

        void showError();

        void showEmpty();

        void hasNodata();

        void loadMoreFinish();

        void refreshFinish();

        void setCardInfo(String cradNum, String balance, String date);

        void changeBackground();

        void showState(boolean flag);

    }

    interface persenter extends BasePersenter<View> {
        void loadData(String suerId, String userToken);
    }
}
