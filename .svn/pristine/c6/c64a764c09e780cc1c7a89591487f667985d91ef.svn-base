package com.xi6666.addoil.contract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;
import com.xi6666.databean.AddOilDataBean;
import com.xi6666.databean.AddOilPopuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr_yang on 2016/11/23.
 */

public interface AddOilContract {

    interface View extends BaseView {

        void setBanner(List<AddOilDataBean.BannerBean> bannerBean);

        void showDefaultOilCard();

        void showAddOilCard();

        void setDefaultOilCard(String type, String cardnum, String cardId);

        void setMaxMoney(int maxMoney);

        void setListData(List<AddOilDataBean.DataBean> data);

        void showToast(String string);

        void showLodingDialog();

        void hideLoadingDialog();

        void setPeopleNum(String num);

        void setSaveMoney(String money);

        void setPeopleState(ArrayList<String> content);

        void setQuanCunUrl(String url);

        void startPay();
    }

    interface Presenter extends BasePersenter<View> {
        void loadData();

        void loadDefaultOilCard(String userId, String userToken);

        void loadPeoleState();

        void testingAddOil(String packageId, String userID, String userToken);
    }
}
