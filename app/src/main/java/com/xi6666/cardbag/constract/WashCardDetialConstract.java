package com.xi6666.cardbag.constract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;
import com.xi6666.databean.WashCardDetialBean;

import java.util.List;

/**
 * Created by Mr_yang on 2016/11/22.
 */

public interface WashCardDetialConstract {
    interface View extends BaseView {
        void showLoading();

        void hideStateLyout();

        void showError();

        void showEmpty();

        void hasNodata();

        void loadMoreFinish();

        void refreshFinish();

        void setData(List<WashCardDetialBean.DataBean> mDataBeen);

        void showToast(String string);
    }

    interface Persenter extends BasePersenter<View> {
        void loadMoreData(String option, String page, String userId, String userToken);

        void refreshData(String option, String page, String userId, String userToken);
    }
}
