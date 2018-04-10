package com.xi6666.store.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.store.mvp.bean.StoreServiceBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 16/11/25 下午2:41.
 * 个人公众号 ardays
 */
public interface StoreServiceContract {

    interface Model extends BaseModel {
        /**
         * 获取门店服务列表
         */
//        Observable<StoreServiceBean> getStoreServiceList(String city, String keyword, String lat, String lng, String order_by, int page, int page_size, String status);
        Observable<StoreServiceBean> getStoreServiceList(String city, String lat, String lng, String order_by, int page, int page_size, String status);
    }

    interface View extends BaseView {
        /**
         * 返回门店服务列表
         */
        void returnStoreServiceList(StoreServiceBean bean);

        /**
         * 网络错误
         */
        void netError();
    }


    abstract class Presenter extends BasePresenter<Model, View> {
//        /**
//         * 请求门店服务参数
//         *
//         * @param city      城市ID
//         * @param keyword   关键词
//         * @param lat       纬度
//         * @param lng       经度
//         * @param order_by  排序；从价格升序1;从价格降序2;评分升序3;评分降序4;从近到远5;人气降序6;人气升序7 默认值是5
//         * @param page      页数
//         * @param page_size 每页条数
//         * @param status    服务类型，全部传0，是service_cate_id值
//         */
//        public abstract void requestStoreServiceList(String city, String keyword, String lat, String lng, String order_by, int page, int page_size, String status);

        /**
         * 请求门店服务参数
         *
         * @param city      城市ID
         * @param order_by  排序；从价格升序1;从价格降序2;评分升序3;评分降序4;从近到远5;人气降序6;人气升序7 默认值是5
         * @param page      页数
         * @param status    服务类型，全部传0，是service_cate_id值
         */
        public abstract void requestStoreServiceList(String city,String order_by, int page, String status);
    }
}
