package com.xi6666.cardbag.view.mvp;

import com.xi6666.cardbag.view.mvp.bean.OilCardListBean;

/**
 * 创建者 sunsun
 * 时间 16/11/22 上午10:39.
 * 个人公众号 ardays
 */

public class OilCardPresenter extends OilCardContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestOilCardList(int PAGE) {
        mRxManager.add(mModel.getOilCardList(PAGE).subscribe(
                res -> {
                    if (res.success) mView.resultOilCardList(res);
                    else mView.toastError(res.info);
                }, e -> {
                    e.printStackTrace();
                    mView.toastError("网络异常");
                }
        ));
    }


    @Override
    public void deleteOilCard(OilCardListBean.DataBean bean, int position) {
        mRxManager.add(
            mModel.deleteOilCard(bean.card_id).subscribe(
                res -> {
                    mView.deleteOilCardStatus(res, position);
                },e ->{
                        e.printStackTrace();
                        mView.toastError("网络异常");
                }
            )
        );
    }

    @Override
    public void requestDefaultOilCard(OilCardListBean.DataBean bean, int position) {
        mRxManager.add(
                mModel.defaultOilCard(bean.card_id).subscribe(
                        res -> {
                            mView.resultDefaultOiLCardStatus(res, position);
                        },e -> {
                            mView.toastError("网络异常");
                        }
                )
        );
    }
}
