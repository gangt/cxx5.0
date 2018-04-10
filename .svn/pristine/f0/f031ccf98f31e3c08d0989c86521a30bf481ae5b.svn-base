package com.xi6666.classification.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseFrgm;
import com.xi6666.classification.view.BrandDetailsAct;
import com.xi6666.classification.view.adapter.ServiceClassificationAdapter;
import com.xi6666.classification.view.fragment.mvp.ServiceClassificationContract;
import com.xi6666.classification.view.fragment.mvp.ServiceClassificationModel;
import com.xi6666.classification.view.fragment.mvp.ServiceClassificationPresenter;
import com.xi6666.classification.view.fragment.mvp.bean.ServiceClassificationBean;

import butterknife.BindView;

/**
 * 创建者 sunsun
 * 时间 16/11/12 上午11:01.
 * 个人公众号 ardays
 * <p>
 * 全部分类 - 分类
 */

public class ServiceClassificationFrg extends BaseFrgm<ServiceClassificationPresenter, ServiceClassificationModel> implements ServiceClassificationContract.View {
    private static final String TAG = "ServiceClassificationFr";

    @BindView(R.id.service_classification_rv)
    RecyclerView mRcRv; //分类列表

    ServiceClassificationAdapter mServiceClassificationAdapter;
    int mPosition;


    private static ServiceClassificationFrg mScfFrgm;

    public static ServiceClassificationFrg getInstance(int mPosition) {
        mScfFrgm = new ServiceClassificationFrg();
        mScfFrgm.setPosition(mPosition);
        return mScfFrgm;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_service_classification;
    }

    @Override
    protected void setUp() {
        initItem();
    }

    /**
     * 初始化列表
     */
    private void initItem() {
        mRcRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mServiceClassificationAdapter = new ServiceClassificationAdapter(getContext());
        mRcRv.setAdapter(mServiceClassificationAdapter);
        mServiceClassificationAdapter.setOnServiceClassificationAdapterListenr(data -> {
            BrandDetailsAct.openActivity(getActivity(), "", data.cate_id, data.cate_name);
        });
        mPersenter.requestGoodsList();
    }

    public void setPosition(int position){
        this.mPosition = position;
    }

    @Override
    public void returnGoodsList(ServiceClassificationBean bean) {
        mServiceClassificationAdapter.addAll(bean.data);
        Log.e(TAG, "mPosition: " +mPosition);
        mRcRv.smoothScrollToPosition(mPosition);
        Log.e(TAG, "returnGoodsList: " + bean.toString());
    }
}
