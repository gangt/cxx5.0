package com.xi6666.store.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.carWash.adapter.CarWashSearchResultAdapter;
import com.xi6666.carWash.base.BaseFrgm;
import com.xi6666.carWash.base.view.CxxErrorView;
import com.xi6666.common.CityBean;
import com.xi6666.eventbus.AddressEvent;
import com.xi6666.eventbus.LocationEvent;
import com.xi6666.store.event.StoreServiceSortEvent;
import com.xi6666.store.mvp.StoreServiceContract;
import com.xi6666.store.mvp.StoreServiceModel;
import com.xi6666.store.mvp.StoreServicePresenter;
import com.xi6666.store.mvp.bean.StoreServiceBean;
import com.xi6666.store.mvp.bean.StoreServiceTypeBean;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 创建者 sunsun
 * 时间 16/11/4 下午3:15.
 * 个人公众号 ardays
 * <p>
 * 门店 - 所有服务
 */

@SuppressLint("ValidFragment")
public class StoreServiceFrgm extends BaseFrgm<StoreServicePresenter, StoreServiceModel>
        implements StoreServiceContract.View {


    public StoreServiceFrgm(StoreServiceTypeBean.DataBean data) {
        this.mDatas = data;
    }


    @BindView(R.id.store_xrv)
    XRecyclerView mStoreAllServiceXrv;
    @BindView(R.id.store_not_tv)
    TextView mStoreNotTv;   //没有数据
    @BindView(R.id.store_error_view)
    CxxErrorView mErrorView;    //处理错误view

    /**
     * 数据源
     */
    public StoreServiceTypeBean.DataBean mDatas;
    public String mType = "5";

    //刷新地址
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshAddress(AddressEvent addressEvent) {
        PAGE = 1;
        mPersenter.requestStoreServiceList(CityBean.getRegionId(), mType, PAGE, mDatas.service_cate_id);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loacationAddress(LocationEvent locationEvent) {
        mPersenter.requestStoreServiceList(CityBean.getRegionId(), mType, PAGE, mDatas.service_cate_id);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshType(StoreServiceSortEvent event) {
        PAGE = 1;
        if (TextUtils.equals(event.type, "距离优先")) {
            mType = "5";
        } else if (TextUtils.equals(event.type, "评分优先")) {
            mType = "4";
        } else if (TextUtils.equals(event.type, "人气优先")) {
            mType = "6";
        }
        mPersenter.requestStoreServiceList(CityBean.getRegionId(), mType, PAGE, mDatas.service_cate_id);
    }


    /**
     * 页数
     */
    private int PAGE = 1;

    public CarWashSearchResultAdapter mStoreSearchResultAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_carwash_near_far;
    }

    @Override
    protected void setUp() {
        EventBus.getDefault().register(this);
        mStoreAllServiceXrv.setLayoutManager(new LinearLayoutManager(getContext()));
        mStoreSearchResultAdapter = new CarWashSearchResultAdapter(getContext());
        mStoreAllServiceXrv.setAdapter(mStoreSearchResultAdapter);
        mPersenter.requestStoreServiceList(CityBean.getRegionId(), mType, PAGE, mDatas.service_cate_id);

        mStoreAllServiceXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE = 1;
                mPersenter.requestStoreServiceList(CityBean.getRegionId(), mType, PAGE, mDatas.service_cate_id);
            }

            @Override
            public void onLoadMore() {
                PAGE++;
                mPersenter.requestStoreServiceList(CityBean.getRegionId(), mType, PAGE, mDatas.service_cate_id);
            }
        });

        //网络错误，点击重新加载
        mErrorView.setOnErrorClickListener(v -> {
            showLoading();
            mPersenter.requestStoreServiceList(CityBean.getRegionId(), mType, PAGE, mDatas.service_cate_id);
        });
    }

    //                              @网络请求
    @Override
    public void returnStoreServiceList(StoreServiceBean bean) {
        mErrorView.setVisibility(View.GONE);
        mStoreAllServiceXrv.refreshComplete();      //刷新
        if (bean.success) {
            if (PAGE == 1) {
                if (bean.data.size() == 0) {
                    mStoreNotTv.setVisibility(View.VISIBLE);
                    mStoreAllServiceXrv.setVisibility(View.GONE);
                } else {
                    mStoreNotTv.setVisibility(View.GONE);
                    mStoreAllServiceXrv.setVisibility(View.VISIBLE);
                }
                mStoreSearchResultAdapter.update(bean.data);
            } else {
                mStoreSearchResultAdapter.addAll(bean.data);
            }
        }
    }


    @Override
    public void netError() {
        hiddenLoading();
        mStoreAllServiceXrv.refreshComplete();      //刷新
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //    @Override
//    protected int getLayoutResId() {
//        return 0;
//    }
//
//    @Override
//    protected void init() {
//
//    }
}
