package com.xi6666.technician.mvp;

import com.xi6666.carWash.base.network.BaseBean;

import rx.Observable;
import rx.Subscription;

/**
 * 创建者 sunsun
 * 时间 2016/11/30 下午3:51.
 * 个人公众号 ardays
 */
public class ItsAnswerPresenter extends ItsAnswerContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void reuqestAnswerList(String js_user_id, int page) {
        mRxManager.add(mModel.getAnswerList(js_user_id, page)
                .subscribe(
                        res -> {
                            mView.returnAnswerList(res);
                        }, e -> {
                            e.printStackTrace();
                        }
                ));
    }

    @Override
    public void requestLikesClick(boolean bol, String ques_id, int position) {
        mRxManager.add(
                like(bol, ques_id).subscribe(
                        res -> {
                            mView.returnLikeResult(bol, position, res);
                        }, e -> {
                            e.printStackTrace();
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
