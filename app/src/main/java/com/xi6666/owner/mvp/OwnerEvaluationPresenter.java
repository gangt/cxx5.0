package com.xi6666.owner.mvp;

import android.util.Log;

import com.xi6666.carWash.base.network.BaseBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2016/11/30 上午11:42.
 * 个人公众号 ardays
 */
public class OwnerEvaluationPresenter extends OwnerEvaluationContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestEvaluationList(int level, int page, String shop_id) {
        mRxManager.add(mModel.getEvaluationList(level, page, shop_id)
                .subscribe(
                        res -> {
                            mView.returnOwnerEvaluationList(res);
                        }, e -> {
                            e.printStackTrace();
                        }
                ));
    }

    @Override
    public void requestLikesClick(boolean bol, String ques_id, int position, String store_user_id) {
        Log.d("TAG", "requestLikesClick: --> " + bol);
        mRxManager.add(
                like(bol, ques_id, store_user_id).subscribe(
                        res -> {
                            mView.returnLikeResult(bol, position, res);
                        }, e -> {
                            e.printStackTrace();
                        }
                ));
    }


    public Observable<BaseBean> like(boolean bol, String ques_id, String store_user_id) {
        if (bol)
            return mModel.likes(ques_id, store_user_id);
        else
            return mModel.unLikes(ques_id, store_user_id);
    }
}
