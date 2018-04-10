package com.xi6666.store.mvp;

import com.xi6666.carWash.base.network.BaseBean;

import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/25 下午6:47.
 * 个人公众号 ardays
 */
public class StoreDetailsPresenter extends StoreDetailsContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestStoreDetails(String store_id, String service_cate_id) {
        mRxManager.add(mModel.getStoreDetails(store_id, service_cate_id)
                .subscribe(
                        res -> {
                            mView.returnStoreDetails(res);
                        }, e -> {
                            e.printStackTrace();
                            mView.returnError();
                        }
                ));
    }


    @Override
    public void requestLikesClick(boolean bol, String ques_id, int position, String store_user_id) {
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
