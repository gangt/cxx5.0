package com.xi6666.owner.mvp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.owner.mvp.bean.OwnerEvaluationBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2016/11/30 上午11:41.
 * 个人公众号 ardays
 */
public interface OwnerEvaluationContract {

    interface Model extends BaseModel {
        /**
         * 获取评价列表
         */
        Observable<OwnerEvaluationBean> getEvaluationList(int level, int page, String shop_id);

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
         * 返回评价列表
         */
        void returnOwnerEvaluationList(OwnerEvaluationBean bean);

        /**
         * 返回点赞结果
         */
        void returnLikeResult(boolean bol, int position, BaseBean bean);
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 评价列表
         *
         * @param level   评价 等级/level=1是好评 2是中评 3是差评 0是全部
         * @param page    分页
         * @param shop_id 门店ID
         */
        public abstract void requestEvaluationList(int level, int page, String shop_id);


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
