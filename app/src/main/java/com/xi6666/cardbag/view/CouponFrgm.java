package com.xi6666.cardbag.view;


import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.app.baset.BaseTFrgm;
import com.xi6666.cardbag.adapter.CouponAdapter;
import com.xi6666.cardbag.constract.CouPonContract;
import com.xi6666.cardbag.model.CouponModel;
import com.xi6666.cardbag.persenter.CouponPresenter;
import com.xi6666.common.UserData;
import com.xi6666.databean.CouponBean;
import com.xi6666.view.EmptyLayout;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author peng
 * @data 创建时间:2016/11/24
 * @desc 优惠券
 */
public class CouponFrgm extends BaseTFrgm<CouponPresenter, CouponModel> implements
        CouPonContract.View, XRecyclerView.LoadingListener, CouponAdapter.onItemDeleteListener {
    @BindView(R.id.xrv_coupon)
    XRecyclerView mXrvCoupon;
    @BindView(R.id.el_coupon)
    EmptyLayout mElCoupon;
    private int page = 1;
    private CouponAdapter mCouponAdapter;
    private List<CouponBean.DataBean> mDataBean = new ArrayList<>();
    private boolean isFrist = true;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        mXrvCoupon.setLayoutManager(linearLayoutManager);
        mCouponAdapter = new CouponAdapter();
        mCouponAdapter.setDataBean(mDataBean);
        mXrvCoupon.setAdapter(mCouponAdapter);
        mCouponAdapter.setOnItemDeleteListener(this);
        mXrvCoupon.setLoadingListener(this);
        mElCoupon.setErrorButtonClickListener(v -> {
            mPresent.getCouponList(page + "", "0", UserData.getUserId(), UserData.getUserToken());
        });
        mPresent.getCouponList(page + "", "0", UserData.getUserId(), UserData.getUserToken());
    }


    @Override
    public void showLoading(boolean show) {
        if (show) {
            if (isFrist) {
                mElCoupon.showLoading();
                isFrist = false;
            }
        } else {
            mElCoupon.hide();
        }
    }

    @Override
    public void showError(boolean show) {
        if (show) {
            mElCoupon.showError();
        } else {
            mElCoupon.hide();
        }
    }

    @Override
    public void setListData(List<CouponBean.DataBean> Bean) {
        if (page == 1) {
            mDataBean.clear();
            mXrvCoupon.refreshComplete();
        }
        if (page > 1) {
            mXrvCoupon.loadMoreComplete();
        }
        mDataBean.addAll(Bean);
        mCouponAdapter.setDataBean(mDataBean);
    }

    @Override
    public void hasNoData(boolean has) {
        mXrvCoupon.setNoMore(has);
    }

    @Override
    public void refreshData() {
        page = 1;
        mPresent.getCouponList(page + "", "0", UserData.getUserId(), UserData.getUserToken());
    }

    @Override
    public void onRefresh() {
        page = 1;
        mPresent.getCouponList(page + "", "0", UserData.getUserId(), UserData.getUserToken());
    }

    @Override
    public void onLoadMore() {
        page++;
        mPresent.getCouponList(page + "", "0", UserData.getUserId(), UserData.getUserToken());
    }

    @Override
    public void onItemDelete(int position, String coupon_id) {
        mPresent.deleteCoupon(coupon_id, UserData.getUserId(), UserData.getUserToken());
    }
}
