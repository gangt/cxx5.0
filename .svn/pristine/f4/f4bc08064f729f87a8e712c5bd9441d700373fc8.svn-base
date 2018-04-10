package com.xi6666.cardbag.view.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.cardbag.view.mvp.bean.OilCardListBean;
import com.xi6666.cardbag.view.mvp.bean.OilCardDeleteBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 16/11/22 上午10:36.
 * 个人公众号 ardays
 */

public interface OilCardContract {


    interface Model extends BaseModel {
        /**
         * 去服务器获取用户油卡列表
         */
        Observable<OilCardListBean> getOilCardList(int PAGE);

        /**
         * 删除油卡
         */
        Observable<OilCardDeleteBean> deleteOilCard(String card_id);

        /**
         * 设置默认油卡
         */
        Observable<OilCardDeleteBean> defaultOilCard(String card_id);
    }


    interface View extends BaseView {
        /**
         * 返回用户油卡列表
         * @param res
         */
        void resultOilCardList(OilCardListBean res);

        /**
         * 删除油卡是否成功
         */
        void deleteOilCardStatus(OilCardDeleteBean bean, int position);

        /**
         * mo设置默认油卡成功
         */
        void resultDefaultOiLCardStatus(OilCardDeleteBean bean, int position);


        /**
         * 抛出异常
         */
        void toastError(String error);
    }


    abstract class Presenter extends BasePresenter<Model, View>
    {
        /**
         * 请求用户油卡列表
         * @param PAGE 分页
         */
        public abstract void requestOilCardList(int PAGE);

        /**
         * 删除油卡
         */
        public abstract void deleteOilCard(OilCardListBean.DataBean bean, int position);

        /**
         * 请求设置默认油卡
         */
        public abstract void requestDefaultOilCard(OilCardListBean.DataBean bean, int position);
    }
}
