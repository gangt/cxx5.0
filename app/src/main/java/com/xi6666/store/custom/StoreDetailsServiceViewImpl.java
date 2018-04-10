package com.xi6666.store.custom;

import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.store.mvp.bean.StoreDetailsServiceBean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/7 下午4:47.
 * 个人公众号 ardays
 */
public interface StoreDetailsServiceViewImpl {

    /**
     *  写入服务项目条数
     */
    void setServiceData(List<StoreDetailsBean.DataBean.ServiceListBean> serviceData);


    /**
     * 写入门店ID
     */
    void setStoreId(String store_id);
}
