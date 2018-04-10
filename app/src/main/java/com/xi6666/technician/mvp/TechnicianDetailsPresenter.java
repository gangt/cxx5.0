package com.xi6666.technician.mvp;

import android.util.Log;

import com.xi6666.carWash.base.network.BaseBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2016/11/29 下午12:00.
 * 个人公众号 ardays
 */
public class TechnicianDetailsPresenter extends TechnicianDetailsContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestTechnicianDetails(String technician_id) {
        mRxManager.add(
                mModel.getTechnicianDetails(technician_id)
                        .subscribe(
                                res -> {
                                    mView.returnTechnicianDetails(res);
                                }, e -> {
                                    mView.returnError("网络错误");
                                }
                        )
        );
    }

    @Override
    public void requestLikesClick(boolean bol, String ques_id, int position) {
        Log.d("TAG", "requestLikesClick: " + bol);
        mRxManager.add(
                like(bol, ques_id).subscribe(
                        res -> {
                            mView.returnLikeResult(bol, position, res);
                        }, e -> {
                            mView.returnError("网络错误");
                        }
                ));
    }


    public Observable<BaseBean> like(boolean bol, String ques_id) {
        if (bol)
            return mModel.likes(ques_id);
        else
            return mModel.unLikes(ques_id);
    }
}
