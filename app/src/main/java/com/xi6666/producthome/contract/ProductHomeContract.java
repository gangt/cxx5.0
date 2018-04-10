package com.xi6666.producthome.contract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;
import com.xi6666.databean.HomeHotGoodsBean;
import com.xi6666.databean.HomeSpecialBean;
import com.xi6666.databean.ProductHomeCateBean;


import java.util.List;

/**
 * Created by Mr_yang on 2016/11/28.
 */

public interface ProductHomeContract {
    interface View extends BaseView {
        void addCateData(List<ProductHomeCateBean.DataBean.ListBean> productHomeCateBean);

        void addHotProduct(List<HomeHotGoodsBean.DataBean> dataBeen);

        void addSpecialData(List<HomeSpecialBean.DataBean> listBeen);

        void addBannerData(List<String> bannerBeen);

        void finishiRefresh();

        void finishiLoadMore();

        void hasNoData(boolean has);

        void showLoaind();

        void showError();

        void hideState();
    }

    interface Presenter extends BasePersenter<View> {
        void loadCateGories();

        void loadHotProduct(int page);

        void loadSpeCiaData();

    }
}
