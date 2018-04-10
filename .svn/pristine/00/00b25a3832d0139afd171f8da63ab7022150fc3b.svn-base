package com.xi6666.cardbag.view.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.cardbag.view.mvp.bean.AddOilCardBean;
import com.xi6666.cardbag.view.mvp.bean.OilCardGetNameBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/22 下午6:28.
 * 个人公众号 ardays
 */

public interface AddOilCardContract {

    interface Model extends BaseModel{
        /**
         * 获取油卡持卡人名字
         */
        Observable<OilCardGetNameBean> getOilCardName(String oilCardNumber);

        /**
         * 添加油卡
         */
        Observable<AddOilCardBean> addOilCard(String card_name, String card_number, String user_mobile);
    }

    interface View extends BaseView{
        /**
         * 返回的油卡持卡人名字
         */
        void returnOilCardName(OilCardGetNameBean bean);

        /**
         * 返回添加油卡结果
         */
        void returnAddOilCardResult(AddOilCardBean bean);
    }


    abstract class Presenter extends BasePresenter<Model, View>
    {
        /**
         * 获取油卡持卡人名字
         * @param oilCardNumber 油卡号码
         */
        public abstract void requestOilCardName(String oilCardNumber);


        /**
         * 添加用户油卡
         * @param card_name   油卡名字
         * @param card_number 油卡电话号码
         * @param user_mobile 手机号码
         */
        public abstract void requestAddOilCard(String card_name, String card_number, String user_mobile);
    }
}
