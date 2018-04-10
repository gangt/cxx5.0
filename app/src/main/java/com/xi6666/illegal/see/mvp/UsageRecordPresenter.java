package com.xi6666.illegal.see.mvp;

/**
 * 创建者 sunsun
 * 时间 2017/2/8 下午6:23.
 * 个人公众号 ardays
 */
public class UsageRecordPresenter extends UsageRecordContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestIllegalList(int page, String card_id) {
        mRxManager.add(
                mModel.getIllegalList(page, card_id)
                        .subscribe(
                                bean -> {
                                    mView.returnIllegalList(bean);
                                }, e -> {
                                    e.printStackTrace();
                                    mView.illegalListError();
                                }
                        )
        );
    }
}
