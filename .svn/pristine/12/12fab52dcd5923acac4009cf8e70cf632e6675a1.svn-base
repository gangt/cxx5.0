package com.xi6666.address.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.xi6666.R;
import com.xi6666.address.ReceiptAddressAct;
import com.xi6666.address.fragment.adapter.DistributionShopAdapter;
import com.xi6666.address.fragment.mvp.DistributionShopContract;
import com.xi6666.address.fragment.mvp.DistributionShopModel;
import com.xi6666.address.fragment.mvp.DistributionShopPresenter;
import com.xi6666.address.fragment.mvp.bean.DistributionShopBean;
import com.xi6666.carWash.base.BaseFrgm;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import butterknife.BindView;

/**
 * 创建者 sunsun
 * 时间 16/11/12 下午4:30.
 * 个人公众号 ardays
 * <p>
 * 收货地址-配送到门店
 */

public class DistributionShopFrgm extends BaseFrgm<DistributionShopPresenter, DistributionShopModel> implements DistributionShopContract.View {


    /*public DistributionShopFrgm(ReceiptAddressAct activity) {
        this.mSuperActivity = activity;
    }*/

    public ReceiptAddressAct mSuperActivity;
    @BindView(R.id.distribution_shop_xrv)
    XRecyclerView mDistributionShopXrv;
    @BindView(R.id.distribution_shop_not_ll)
    View mNotLL;


    DistributionShopAdapter mShopAdapter;
    private DistributionShopAdapter.OnDistributionShopAdapterListener mListener;

    int PAGE = 1;//分页

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_distribution_shop;
    }

    @Override
    protected void setUp() {
        make("请求列表中");
        mPersenter.requestDistributionShop(PAGE);
        mShopAdapter = new DistributionShopAdapter(getActivity());
        mShopAdapter.setOnDistributionShopAdapterListener(mListener);
        mDistributionShopXrv.setLayoutManager(new LinearLayoutManager(getContext()));
        mDistributionShopXrv.setAdapter(mShopAdapter);

        //监听上拉下拉
        mDistributionShopXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE = 1;
                mPersenter.requestDistributionShop(PAGE);
            }

            @Override
            public void onLoadMore() {
                PAGE++;
                mPersenter.requestDistributionShop(PAGE);
            }
        });
    }


    /**
     * 点击服务门店返回
     */
    public void setOnDistributionShopAdapterListener(DistributionShopAdapter.OnDistributionShopAdapterListener listener) {
        this.mListener = listener;
    }


    @Override
    public void returnDistributionShop(DistributionShopBean bean) {
        mDistributionShopXrv.refreshComplete();
        if (bean.success) {
            mShopAdapter.addAll(bean.data);
            if(PAGE == 1){
                if(bean.data.size() == 0){
                    mNotLL.setVisibility(View.VISIBLE);
                    mDistributionShopXrv.setVisibility(View.GONE);
                }else{
                    mNotLL.setVisibility(View.GONE);
                    mDistributionShopXrv.setVisibility(View.VISIBLE);
                }
            }
        } else {
            make(bean.info);
        }
    }

    @Override
    public void returnError(String error) {
        mDistributionShopXrv.refreshComplete();
        make(error);
    }
}
