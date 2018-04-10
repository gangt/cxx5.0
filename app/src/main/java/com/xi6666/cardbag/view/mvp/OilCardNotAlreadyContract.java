package com.xi6666.cardbag.view.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.cardbag.view.mvp.bean.OilCardNotAlreadyBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/22 下午6:28.
 * 个人公众号 ardays
 */

public interface OilCardNotAlreadyContract {


    interface Model extends BaseModel {

        /**
         * 获取未到账的记录
         */
        Observable<OilCardNotAlreadyBean> getNotAlready(String card_id);
    }

    interface View extends BaseView {
        /**
         * 返回未到账的记录
         */
        void returnNotAlready(OilCardNotAlreadyBean bean);
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 请求未到账
         * @param card_id 油卡Id
         */
        public abstract void requestNotAlready(String card_id);
    }
}
