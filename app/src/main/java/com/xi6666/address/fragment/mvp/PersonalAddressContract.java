package com.xi6666.address.fragment.mvp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.address.fragment.mvp.bean.PersonalAddressBean;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 16/11/24 下午2:34.
 * 个人公众号 ardays
 */
public interface PersonalAddressContract {

    interface Model extends BaseModel {
        /**
         * 获取地址列表
         */
        Observable<PersonalAddressBean> getAddressList();

        /**
         * 设置默认地址
         */
        Observable<BaseBean> setDefaultAddress(String address_id);
    }

    interface View extends BaseView {
        /**
         * 返回地址列表
         */
        void returnAddressList(PersonalAddressBean bean);

        /**
         * 返回操作结果
         */
        void returnSetDefaultResult(BaseBean bean, int position);
    }


    abstract class Presenter extends BasePresenter<Model, View> {

        /**
         * 请求 地址--查询
         */
        public abstract void requestAddressList();

        /**
         * 设置默认地址
         * @param address_id
         */
        public abstract void requestSetDefaultAddress(String address_id, int position);
    }
}
