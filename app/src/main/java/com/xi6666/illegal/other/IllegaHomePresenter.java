package com.xi6666.illegal.other;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import com.xi6666.databean.IllegaHomeListBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * Created by Mr_yang on 2017/2/7.
 */

public class IllegaHomePresenter extends IllegaHomeContract.Persenter {
    private boolean isFirst = true;


    @Override
    public void onAttached() {
    }

    @Override
    public void getIllageHomeList(String userId, String userToken, String page) {

        if (TextUtils.equals("1", page) && isFirst) {
            mView.showLoading();
            isFirst = false;
        }
        if (TextUtils.equals("1", page)) {
            mView.hasMoreData(true);
        }
        mRxManager.add(mModel.getIllageHomeList(userId, userToken, page).subscribe(new Subscriber<IllegaHomeListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showError();
                if (TextUtils.equals("1", page)) {
                    mView.refreshFinish();
                }
                mView.loadMoreFinish();
            }

            @Override
            public void onNext(IllegaHomeListBean illegaHomeListBean) {

                if (illegaHomeListBean.isSuccess()) {
                    if (TextUtils.equals("1", page)) {
                        mView.showOrCloseEmpty(false);
                        mView.hideEmptyLayout();
                        mView.refreshFinish();
                        //设置轮播图
                        mView.setBanner(illegaHomeListBean.getBanner());
                    }
                    mView.loadMoreFinish();
                    mView.setListData(illegaHomeListBean.getData());
                    if (illegaHomeListBean.getData().size() < 15 && !TextUtils.equals("1", page)) {
                        mView.hasMoreData(false);
                    }
                } else {
                    mView.refreshFinish();
                    //设置轮播图
                    mView.setBanner(illegaHomeListBean.getBanner());
                    mView.showToast(illegaHomeListBean.getInfo());
                    mView.showOrCloseEmpty(true);
                }
                //用户数据为空时的显示
                if (illegaHomeListBean.getData().size() == 0 && TextUtils.equals("1", page)) {
                    mView.showOrCloseEmpty(true);
                    mView.hideEmptyLayout();
                }
            }
        }));
    }

    @Override
    public void deleteList(String car_no, String city_code, String province_code, String user_id) {
        Log.d("delete", "--" + car_no);
        mRxManager.add(mModel.deleteItem(car_no, city_code, province_code, user_id).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

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
                        mView.refreshList();
                    } else {
                        mView.showToast(jsonObject.getString("info"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }));
    }
}
