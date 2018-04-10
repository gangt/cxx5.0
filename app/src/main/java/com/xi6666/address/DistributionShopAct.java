package com.xi6666.address;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.xi6666.R;
import com.xi6666.address.fragment.adapter.DistributionShopAdapter;
import com.xi6666.address.fragment.mvp.DistributionShopContract;
import com.xi6666.address.fragment.mvp.DistributionShopModel;
import com.xi6666.address.fragment.mvp.DistributionShopPresenter;
import com.xi6666.address.fragment.mvp.bean.DistributionShopBean;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.base.view.CxxNotView;
import com.xi6666.store.StoreDetailsAct;
import com.xi6666.view.superrecyclerview.XRecyclerView;

/**
 * 创建者 sunsun
 * 时间 2016/11/30 下午9:19.
 * 个人公众号 ardays
 */

public class DistributionShopAct extends BaseToolbarView<DistributionShopPresenter, DistributionShopModel>
        implements DistributionShopContract.View {
    @Override
    public String title() {
        return "附近可服务门店";
    }

    XRecyclerView mDistributionShopXrv;


    DistributionShopAdapter mShopAdapter;
    View mNotView;

    int PAGE = 1;

    @Override
    public int mainResId() {
        return R.layout.activity_distribution_shop;
    }

    @Override
    public void setUp(View view) {
        initView(view);
        mPersenter.requestDistributionShop(PAGE);
        mShopAdapter = new DistributionShopAdapter(this);
        mDistributionShopXrv.setLayoutManager(new LinearLayoutManager(getContext()));
        mDistributionShopXrv.setAdapter(mShopAdapter);

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
        mShopAdapter.setOnDistributionShopAdapterListener(new DistributionShopAdapter.OnDistributionShopAdapterListener() {
            @Override
            public void onItemView(DistributionShopBean.DataBean data) {
                StoreDetailsAct.openActivity(getActivity(), data.id, null);
            }
        });
    }

    private void initView(View view) {
        mDistributionShopXrv = (XRecyclerView) view.findViewById(R.id.distribution_shop_xrv);
        mNotView = view.findViewById(R.id.distribution_not_ll);
    }

    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity) {
        Intent intent = new Intent(activity, DistributionShopAct.class);
        activity.startActivity(intent);
    }

    @Override
    public void returnDistributionShop(DistributionShopBean bean) {
        mDistributionShopXrv.refreshComplete();

        if (bean.success) {
            if (PAGE == 1) {
                if (bean.data.size() > 0) {
                    mShopAdapter.update(bean.data);
                    mDistributionShopXrv.setVisibility(View.VISIBLE);
                    mNotView.setVisibility(View.GONE);
                } else {
                    mDistributionShopXrv.setVisibility(View.GONE);
                    mNotView.setVisibility(View.VISIBLE);
                }

            } else {
                mShopAdapter.addAll(bean.data);
            }
        } else {
            make(bean.info);
        }
    }

    @Override
    public void returnError(String error) {
        make(error);
    }
}
