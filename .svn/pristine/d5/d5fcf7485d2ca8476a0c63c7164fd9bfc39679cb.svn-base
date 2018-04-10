package com.xi6666.home.contract;

import android.content.Context;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;
import com.xi6666.common.LocationBean;
import com.xi6666.databean.AddOilPopuBean;
import com.xi6666.databean.HomeHeadBean;
import com.xi6666.databean.HomeHotGoodsBean;
import com.xi6666.databean.HomeSpecialBean;

import java.util.List;

/**
 * Created by Mr_yang on 2016/11/7.
 */

public interface HomeContract {

    interface View extends BaseView {

        void setAddress(String address);

        void showSwitchAddressDialog(LocationBean locationBean);

        void setBanner(List<HomeHeadBean.BannerBean> homeBannerBean);

        void setFuncationImage(List<HomeHeadBean.IndexBlockBean> funcationImage);

        void setModuleData(List<HomeHeadBean.IndexIconBean> moduleData);

        void setSpecialData(List<HomeSpecialBean.DataBean> data);

        void addGoodsData(List<HomeHotGoodsBean.DataBean> data);

        void hasNodata();

        void LoadMoreFinish();

        void RefreshFinish();

        void loadCarLogo(String carLogoUrl, boolean hasLogo);

        void showNetError();

        void hideNetError();

        void showLocationSetting(String context);

        void showAddoilPopu(List<AddOilPopuBean.DataBean.CouponListBean> dataBeen);
    }

    interface Persenter extends BasePersenter<HomeContract.View> {

        void loadheadData();

        void refreshData(String page);

        void location();

        void loadMoreData(String page);

        void setContext(Context context);

        void loadCarLogo();

        void loadPopuData(String userId, String userToken);
    }
}
