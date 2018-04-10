package com.xi6666.message.contract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;
import com.xi6666.databean.MessageBean;

import java.util.List;


/**
 * Created by Mr_yang on 2016/11/2.
 */

public interface MessageContract {

    interface View extends BaseView {

        void hideRefresh();

        void hideLoadMore();

        void error();

        void addImages(List<MessageBean.DataBean> list);

        void hasNodata();

        void showEmptyView();


    }

    interface Presenter extends BasePersenter<View> {
        void loadMoreData(String page, String userId, String userToken);

        void refrashData(String page, String userId, String userToken);
    }
}
