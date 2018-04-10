package com.xi6666.happybeans.contract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;
import com.xi6666.databean.HappyBeansBean;
import com.xi6666.network.ApiRest;

import java.util.List;

/**
 * Created by Mr_yang on 2016/11/22.
 */

public interface HappyBeansConstract {
    interface View extends BaseView {

        void setHappyBeanNum(String num);

        void setPersionNum(String num);

        void showLoading();

        void showError();

        void hideEmptyLayout();

        void loadData(List<HappyBeansBean.DataBean.XidouListBean> mDataBeen);

        void refreshFinish();

        void hasNodata();

        void showEmpty();

    }

    interface Persenter extends BasePersenter<View> {
        void setApiRest(ApiRest apiRest);

        void loadData(String option, String page);


    }
}
