package com.xi6666.addoil.presenter;
import com.xi6666.addoil.contract.UseCouponContract;
import com.xi6666.databean.UseCouponBean;
import com.xi6666.utils.LogUtil;
import java.util.ArrayList;
import java.util.List;
import rx.Subscriber;
/**
 * Created by Mr_yang on 2017/2/24.
 */

public class UseCouponPresenter extends UseCouponContract.Presenter {
    private boolean mFirst = true;
    private int mPage = 1;
    private List<UseCouponBean.DataBean> mItemData = new ArrayList<>();
    public static final String TAG = "UseCouponPresenter";

    public void setPage(int page) {
        mPage = page;
    }

    @Override
    public void onAttached() {

    }

    @Override
    public void userCouponList(String userId, String userToken) {
        if (mFirst) {
            mView.showLoading();
            mFirst = false;
        }

        mRxManager.add(mModel.userCouponList(userId, userToken, mPage + "").subscribe(new Subscriber<UseCouponBean>() {


            @Override
            public void onCompleted() {
                mView.hideStateLayout();
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d(TAG, "e"+e);
                mView.showError();
            }

            @Override
            public void onNext(UseCouponBean useCouponBean) {
                if (mPage == 1) {
                    mItemData.clear();
                    mView.refreshFinish();
                }
                mView.loadMoreFinish();
                mItemData.addAll(useCouponBean.getData());
                mView.setItemData(mItemData);
                mPage++;
            }
        }));
    }
}
