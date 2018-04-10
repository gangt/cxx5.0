package com.xi6666.cardbag.constract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;

/**
 * Created by Mr_yang on 2016/11/24.
 */

public interface CarWashCardContract {

    interface View extends BaseView {
        void showEmpty();

        void showData();

    }

    interface Presenter extends BasePersenter<View> {
        void loadData();
    }

}
