package com.xi6666.cardbag.view.mvp;

import com.xi6666.app.baset.BaseTModel;
import com.xi6666.app.baset.BaseTPresenter;
import com.xi6666.app.baset.BaseTView;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.cardbag.view.mvp.bean.OilCardDeleteBean;
import com.xi6666.databean.RechargeDetialBean;
import com.xi6666.databean.RechargeListBean;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/23 下午4:19.
 * 个人公众号 ardays
 */

public interface OilRechargeDetailsContract {

    interface Model extends BaseTModel {
        Observable<RechargeDetialBean> getRechargeDetial(String userId, String cardId, String userToken);

        Observable<RechargeListBean> getRechargeList(String userId,
                                                     String card_id,
                                                     int page,
                                                     String userToken);

        Observable<OilCardDeleteBean> defaultOilCardDefualt(String card_id, String userId, String userToken);

    }

    interface View extends BaseTView {
        void setData(RechargeDetialBean rechargeDetialBean);

        void setMoney(String arrival, String total);

        void setRechargeList(List<RechargeListBean.DataBeanX.DataBean> rechargeList);

        void resultDefaultOiLCardStatus(OilCardDeleteBean bean);
    }

    abstract class Presenter extends BaseTPresenter<Model, View> {
        public abstract void getRechargeDetial(String userId, String cardId, String userToken);

        public abstract void getRechargeList(String userId,
                                             String card_id,
                                             int page,
                                             String userToken);

        public abstract void requestDefaultOilCard(String cardId);
    }
}
