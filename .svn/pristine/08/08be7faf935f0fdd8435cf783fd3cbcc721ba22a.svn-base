package com.xi6666.illegal.other;

import com.xi6666.app.baset.BaseTModel;
import com.xi6666.app.baset.BaseTPresenter;
import com.xi6666.app.baset.BaseTView;
import com.xi6666.databean.IllegaHomeListBean;

import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Mr_yang on 2017/2/7.
 */

public interface IllegaHomeContract {
    interface Model extends BaseTModel {
        Observable<IllegaHomeListBean> getIllageHomeList(String userId,
                                                         String userToken,
                                                         String page);

        Observable<ResponseBody> deleteItem(String car_no,
                                            String city_code,
                                            String province_code,
                                            String user_id);
    }

    interface View extends BaseTView {
        void showLoading();

        void showError();

        void hideEmptyLayout();

        void showOrCloseEmpty(boolean flag);

        void setListData(List<IllegaHomeListBean.DataBean> data);

        void refreshFinish();

        void loadMoreFinish();

        void hasMoreData(boolean flag);

        void setBanner(List<IllegaHomeListBean.BannerBean> bannerData);

        void refreshList();

    }

    abstract class Persenter extends BaseTPresenter<Model, View> {
        public abstract void getIllageHomeList(String userId,
                                               String userToken,
                                               String page);

        public abstract void deleteList(String car_no,
                                        String city_code,
                                        String province_code,
                                        String user_id);
    }
}
