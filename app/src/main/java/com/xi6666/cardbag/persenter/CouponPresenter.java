package com.xi6666.cardbag.persenter;

import com.xi6666.cardbag.constract.CouPonContract;
import com.xi6666.databean.CouponBean;
import com.xi6666.eventbus.CardNumEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * Created by Mr_yang on 2017/1/17.
 * 数据埋点
 */
public class CouponPresenter extends CouPonContract.Presenter {
    @Override
    public void onAttached() {

    }

    @Override
    public void getCouponList(String page, String money, String userid, String user_token) {
        mView.showLoading(true);
        mRxManager.add(mModel.getCouponList(page, money, userid, user_token).subscribe(new Subscriber<CouponBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showError(true);
            }

            @Override
            public void onNext(CouponBean couponBean) {
                mView.showLoading(false);
                mView.setListData(couponBean.getData());
            }
        }));
    }

    @Override
    public void deleteCoupon(String coupnCard, String userId, String userToken) {
        mView.showLoading(true);
        mRxManager.add(mModel.deleteCouponCard(coupnCard, userId, userToken).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {
                mView.showLoading(false);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    JSONObject jsonObject = new JSONObject(string);
                    if (jsonObject.getBoolean("success")) {
                        mView.refreshData();
                        EventBus.getDefault().post(new CardNumEvent("success"));
                    }
                    mView.showToast(jsonObject.getString("info"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }));
    }
}
