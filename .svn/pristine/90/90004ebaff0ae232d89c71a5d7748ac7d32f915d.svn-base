package com.xi6666.store.mvp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 16/11/25 下午6:47.
 * 个人公众号 ardays
 */
public interface StoreDetailsContract {

    interface Model extends BaseModel {

        /**
         * 拿门店详情
         */
        Observable<StoreDetailsBean> getStoreDetails(String store_id, String service_cate_id);

        /**
         * 点赞
         */
        Observable<BaseBean> likes(String ques_id, String store_user_id);

        /**
         * 取消点赞
         */
        Observable<BaseBean> unLikes(String ques_id, String store_user_id);
    }

    interface View extends BaseView {
        /**
         * 返回门店详情
         */
        void returnStoreDetails(StoreDetailsBean bean);

        /**
         * 返回点赞结果
         */
        void returnLikeResult(boolean bol, int position, BaseBean bean);


        /**
         * 返回异常
         */
        void returnError();
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 请求门店详情
         *
         * @param store_id 门店ID
         */
        public abstract void requestStoreDetails(String store_id, String service_cate_id);

        /**
         * 请求点赞
         *
         * @param bol      点赞
         * @param ques_id  问题ID
         * @param position 问题下标
         */
        public abstract void requestLikesClick(boolean bol, String ques_id, int position, String store_user_id);


    }
}
