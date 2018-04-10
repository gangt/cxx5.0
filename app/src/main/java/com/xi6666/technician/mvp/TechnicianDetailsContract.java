package com.xi6666.technician.mvp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.technician.mvp.bean.TechnicianDetailsBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2016/11/29 上午11:59.
 * 个人公众号 ardays
 */
public interface TechnicianDetailsContract {

    interface Model extends BaseModel {
        /**
         * 获得技师详情
         */
        Observable<TechnicianDetailsBean> getTechnicianDetails(String technician_id);

        /**
         * 点赞
         */
        Observable<BaseBean> likes(String ques_id);

        /**
         * 取消点赞
         */
        Observable<BaseBean> unLikes(String ques_id);
    }

    interface View extends BaseView {
        /**
         * 返回技师详情
         */
        void returnTechnicianDetails(TechnicianDetailsBean bean);

        /**
         * 返回点赞结果
         */
        void returnLikeResult(boolean bol,int position,BaseBean bean);

        /**
         * 异常抛出
         */
        void returnError(String error);
    }


    abstract class Presenter extends BasePresenter<Model, View> {

        /**
         *  请求技师详情
         *  @param technician_id 技师id
         */
        public abstract void requestTechnicianDetails(String technician_id);
        /**
         * 请求点赞
         * @param bol 点赞
         * @param ques_id 问题ID
         * @param position 问题下标
         */
        public abstract void requestLikesClick(boolean bol,String ques_id,int position);
    }
}
