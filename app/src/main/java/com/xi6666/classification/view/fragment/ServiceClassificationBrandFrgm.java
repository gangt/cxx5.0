package com.xi6666.classification.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseFrgm;
import com.xi6666.classification.view.AllBrandAct;
import com.xi6666.classification.view.BrandDetailsAct;
import com.xi6666.classification.view.adapter.ServiceClassificationBrandAdapter;
import com.xi6666.classification.view.fragment.mvp.ServiceClassificationBrandContract;
import com.xi6666.classification.view.fragment.mvp.ServiceClassificationBrandModel;
import com.xi6666.classification.view.fragment.mvp.ServiceClassificationBrandPresenter;
import com.xi6666.classification.view.fragment.mvp.bean.ServiceClassificationBrandBean;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建者 sunsun
 * 时间 16/11/12 上午11:04.
 * 个人公众号 ardays
 * <p>
 * 全部分类 - 品牌
 */

public class ServiceClassificationBrandFrgm extends BaseFrgm<ServiceClassificationBrandPresenter,
        ServiceClassificationBrandModel> implements ServiceClassificationBrandContract.View {

    private static final String TAG = "ServiceClassificationBr";

    private static ServiceClassificationBrandFrgm mScbfFrgm;

    public static ServiceClassificationBrandFrgm getInstance() {
        if (mScbfFrgm == null) {
            mScbfFrgm = new ServiceClassificationBrandFrgm();
        }
        return mScbfFrgm;
    }

    @BindView(R.id.service_classification_brand_rv)
    RecyclerView mBrandRv; //品牌列表
    @BindView(R.id.service_classification_error_ll)
    View mErrorView;

    ServiceClassificationBrandAdapter mBrandAdapter;


    List<ServiceClassificationBrandBean.DataBean> mImageData;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_service_classification_brand;
    }

    @Override
    protected void setUp() {
        mBrandRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mBrandAdapter = new ServiceClassificationBrandAdapter(getContext());

        mBrandAdapter.setServiceClassificationBrandAdapterListener(new ServiceClassificationBrandAdapter.ServiceClassificationBrandAdapterListener() {
            @Override
            public void onImageItemClick(ServiceClassificationBrandBean.DataBean.ListBean data) {
                BrandDetailsAct.openActivity(getActivity(), data.brand_id, "", data.brand_name);
            }
        });

        mBrandRv.setAdapter(mBrandAdapter);
        mPersenter.requestBrandList();
    }

    /**
     * 点击事件
     */
    @OnClick(R.id.service_classification_brand_rl)
    void onServiceBrandRlClick() {
        AllBrandAct.openActivity(getContext());
    }

    //              /网络返回
    @Override
    public void returnBrandList(ServiceClassificationBrandBean bean) {
        make(bean.info);
        mErrorView.setVisibility(View.GONE);
        if (bean.success) {
            mBrandAdapter.addAll(bean.data);
            mImageData = bean.data;
        }
    }

    @OnClick(R.id.service_classification_error_btn)
    void onErrorBtn() {
        mPersenter.requestBrandList();
    }

    @Override
    public void error() {
        mErrorView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onResume() {
        super.onResume();
        /*if (mImageData != null) {
            mBrandAdapter.addAll(mImageData);
        }*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBrandAdapter.setContext(getContext());
    }
}
