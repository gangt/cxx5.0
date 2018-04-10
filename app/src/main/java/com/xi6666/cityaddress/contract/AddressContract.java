package com.xi6666.cityaddress.contract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;
import com.xi6666.databean.AddressBean;

import java.util.List;

/**
 * Created by Mr_yang on 2016/11/11.
 */

public class AddressContract {
    public interface View extends BaseView {


        void showError();

        void hideError();

        void showLoding();

        void showLocation();

        void showLocationPower();

        void showLocationFial();

        void addData(List<AddressBean.DataBean> mDataBeen);



        void showToast(String toast);

    }

    public interface Presenter extends BasePersenter<View> {
        void loadData();

        void location();


    }

    public interface CityView extends BaseView {

        void showLoading();

        void hideStateView();

        void showError();

        void addData(List<AddressBean.DataBean> dataBeen);

    }


    public interface CityPresenter extends BasePersenter<CityView> {

        void loadData(String cityId);
    }
}
