package com.xi6666.address.fragment.mvp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 16/11/25 上午10:02.
 * 个人公众号 ardays
 */
public interface EditAddressContract {

    interface Model extends BaseModel {
        /**
         * 添加地址
         */
        Observable<BaseBean> addAddress(String address, String city, String consignee, String district, String is_default, String mobile, String province);

        /**
         * 修改地址
         */
        Observable<BaseBean> updateAddress(String address_id, String address, String city, String consignee, String district, String is_default, String mobile, String province);


        /**
         * 删除地址
         */
        Observable<BaseBean> deleteAddress(String address_id);
    }

    interface View extends BaseView {
        /**
         * 返回结果
         */
        void returnResult(BaseBean bean);

    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 添加收货地址
         *
         * @param address    详细地址
         * @param city       城市id
         * @param consignee  收货人
         * @param district   地区id
         * @param is_default 默认设置。0或1
         * @param mobile     联系方式
         * @param province   省id
         */
        public abstract void requestAddAddress(String address, String city, String consignee, String district, String is_default, String mobile, String province);

        /**
         * 修改收货地址
         *
         * @param address_id 地址id
         * @param address    详细地址
         * @param city       城市id
         * @param consignee  收货人
         * @param district   地区id
         * @param is_default 默认设置。0或1
         * @param mobile     联系方式
         * @param province   省id
         */
        public abstract void requestUpdateAddress(String address_id, String address, String city, String consignee, String district, String is_default, String mobile, String province);

        /**
         * 删除地址
         */
        public abstract void requestDeleteAddress(String address_id);
    }
}
