package com.xi6666.addoil.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.addoil.adapter.UseCouponAdapter;
import com.xi6666.addoil.contract.UseCouponContract;
import com.xi6666.addoil.model.UseCoponModel;
import com.xi6666.addoil.presenter.UseCouponPresenter;
import com.xi6666.app.baset.BaseTAct;
import com.xi6666.common.UserData;
import com.xi6666.databean.UseCouponBean;
import com.xi6666.view.CompatToolbar;
import com.xi6666.view.EmptyLayout;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author peng
 * @data 创建时间:2017/2/24
 * @desc 使用优惠券
 */
public class UseCouponAct extends BaseTAct<UseCouponPresenter, UseCoponModel>
        implements UseCouponContract.View, XRecyclerView.LoadingListener,
        UseCouponAdapter.OnItemClickListener {
    @BindView(R.id.txt_basetoolbar_left)
    TextView mTxtBasetoolbarLeft;
    @BindView(R.id.txt_basetoolbar_title)
    TextView mTxtBasetoolbarTitle;
    @BindView(R.id.txt_basetoolbar_right)
    TextView mTxtBasetoolbarRight;
    @BindView(R.id.base_tb)
    CompatToolbar mBaseTb;
    @BindView(R.id.xr_coupon_use)
    XRecyclerView mXrCouponUse;
    @BindView(R.id.el_coupon_use)
    EmptyLayout mElCouponUse;
    private UseCouponAdapter mUseCouponAdapter;
    private List<UseCouponBean.DataBean> mItemData = new ArrayList<>();
    private String mUSeId = UserData.getUserId();
    private String mUsurToken = UserData.getUserToken();

    @Override
    public int getLayoutId() {
        return R.layout.activity_coupon_use;
    }

    @Override
    public void init() {
        //toolbar設置
        mTxtBasetoolbarTitle.setText("使用优惠券");
        mTxtBasetoolbarLeft.setVisibility(View.GONE);
        mTxtBasetoolbarRight.setVisibility(View.GONE);
        mBaseTb.setNavigationIcon(R.drawable.ic_back);
        mBaseTb.setNavigationOnClickListener(v -> {
            finish();
        });
        //设置recycler
        mXrCouponUse.setLayoutManager(new LinearLayoutManager(this));
        mUseCouponAdapter = new UseCouponAdapter();
        mUseCouponAdapter.setItemData(mItemData);
        mXrCouponUse.setAdapter(mUseCouponAdapter);
        mXrCouponUse.setLoadingListener(this);
        mUseCouponAdapter.setOnItemClickListener(this);

        //设置错误页面
        mElCouponUse.setErrorButtonClickListener(v -> {
            mPresenter.setPage(1);
            mPresenter.userCouponList(mUSeId, mUsurToken);
        });
        //load数据
        mPresenter.userCouponList(mUSeId, mUsurToken);
    }


    @Override
    public void setItemData(List<UseCouponBean.DataBean> itemData) {

        mUseCouponAdapter.setItemData(itemData);
    }

    @Override
    public void showLoading() {
        mElCouponUse.showLoading();
    }

    @Override
    public void hideStateLayout() {
        mElCouponUse.hide();
    }

    @Override
    public void showError() {
        mElCouponUse.showError();
    }

    @Override
    public void showNoData() {
        mXrCouponUse.setNoMore(true);
    }

    @Override
    public void refreshFinish() {
        mXrCouponUse.refreshComplete();
    }

    @Override
    public void loadMoreFinish() {
        mXrCouponUse.loadMoreComplete();
    }

    @Override
    public void showEmpty() {
        showEmpty();
    }

    @Override
    public void onRefresh() {
        mPresenter.setPage(1);
        mPresenter.userCouponList(mUSeId, mUsurToken);
    }

    @Override
    public void onLoadMore() {
        mPresenter.userCouponList(mUSeId, mUsurToken);
    }

    @Override
    public void onItemClick(String couponId, String couPonType) {
        Intent intent = new Intent();
        intent.putExtra("couponId", couponId);
        setResult(3, intent);
        finish();
    }
}
