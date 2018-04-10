package com.xi6666.cardbag.constract;

import com.xi6666.app.baset.BaseTModel;
import com.xi6666.app.baset.BaseTPresenter;
import com.xi6666.app.baset.BaseTView;
import com.xi6666.databean.IllegaBagListBean;

import java.util.List;

import rx.Observable;

/**
 * Created by Mr_yang on 2017/2/8.
 */

public interface IllegaBagContract {
    interface Model extends BaseTModel {
        Observable<IllegaBagListBean> loadData(String userId, String userToken, String page);
    }

    interface View extends BaseTView {
        void showLoading();

        void hideEmptyLayout();

        void showError();

        void setListData(List<IllegaBagListBean.DataBean> data);

        void refreshFinish();

        void loadMoreFinish();

        void hasNoData(boolean has);

        void showEmpty();

    }

    abstract class Presenter extends BaseTPresenter<Model, View> {

        public abstract void loadData(String userId, String userToken, String page);

    }
}
