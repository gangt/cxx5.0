package com.xi6666.owner.mvp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.evaluate.bean.EvaluateServiceBean;
import com.xi6666.store.bean.FileBean;

import junit.framework.Test;

import java.util.List;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2016/11/30 上午11:33.
 * 个人公众号 ardays
 */
public interface EvaluateStoreContract {

    interface Model extends BaseModel {
        /**
         * 提交评论
         */
        Observable<BaseBean> commitEvaluate(String imageFile, String context, String store_id, float star, int type, String order_id);

        /**
         * 上传图片
         */
        Observable<FileBean> commitImages(List<String> imageFile);


        /**
         * 请求门店ID
         */
        Observable<EvaluateServiceBean> getServiceList(String store_id);
    }

    interface View extends BaseView {
        /**
         * 返回提交结果
         */
        void returnCommitResult(BaseBean bean);

        /**
         * 异常跑出
         *
         * @param bean 异常
         */
        void returnError(String bean);

        /**
         * 返回门店列表
         *
         * @param bean 门店列表
         */
        void returnServiceList(EvaluateServiceBean bean);
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 上传评论
         *
         * @param imageFile 图片数组
         * @param context   内容
         */
        public abstract void requestUpEvaluate(List<String> imageFile, String context, String store_id, float star, int type, String order_id);

        /**
         * 上传评论
         *
         * @param fileBean 图片路径
         * @param context  评论
         * @param star     评分
         * @param store_id 门店id
         */
        public abstract void requestEvaluate(FileBean fileBean, String context, String store_id, float star, int type, String order_id);

        /**
         * 请求服务列表
         *
         * @param store_id 门店ID
         */
        public abstract void requestServiceList(String store_id);
    }
}
