package com.xi6666.carWash.view.mvp;

import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;


/**
 * 创建者 sunsun
 * 时间 16/11/26 上午10:35.
 * 个人公众号 ardays
 */
public interface CarWashSearchContract {

    interface Model extends BaseModel {

    }

    interface View extends BaseView {

    }


    abstract class Presenter extends BasePresenter<Model, View> {

    }
}
