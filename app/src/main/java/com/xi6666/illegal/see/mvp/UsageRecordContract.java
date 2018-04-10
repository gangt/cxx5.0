package com.xi6666.illegal.see.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.illegal.see.bean.UsageRecordBean;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2017/2/8 下午6:22.
 * 个人公众号 ardays
 */
public interface UsageRecordContract {

    interface Model extends BaseModel {
        Observable<UsageRecordBean> getIllegalList(int page, String card_id);
    }

    interface View extends BaseView {
        /**
         * 返回洗车卡记录
         * @param bean
         */
        void returnIllegalList(UsageRecordBean bean);

        /**
         * 获取洗车卡异常
         */
        void illegalListError();
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 获取违章卡的使用记录
         *
         * @param page    页数
         * @param card_id 卡ID
         */
        public abstract void requestIllegalList(int page, String card_id);
    }
}
