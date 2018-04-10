package com.xi6666.technician.mvp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.technician.mvp.bean.ItsAnswerBean;
import com.xi6666.technician.view.ItsAnswerAct;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2016/11/30 下午3:50.
 * 个人公众号 ardays
 */
public interface ItsAnswerContract {

    interface Model extends BaseModel {
        /**
         * 获取问答列表
         */
        Observable<ItsAnswerBean> getAnswerList(String js_user_id, int page);

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
         * 返回问答列表
         */
        void returnAnswerList(ItsAnswerBean bean);

        /**
         * 返回点赞结果
         */
        void returnLikeResult(boolean bol,int position,BaseBean bean);
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 获取回答列表
         *
         * @param js_user_id 技师ID
         * @param page       页码
         */
        public abstract void reuqestAnswerList(String js_user_id, int page);


        /**
         * 请求点赞
         * @param bol 点赞
         * @param ques_id 问题ID
         * @param position 问题下标
         */
        public abstract void requestLikesClick(boolean bol,String ques_id,int position);
    }
}
