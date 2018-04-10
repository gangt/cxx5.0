package com.xi6666.carWash.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xi6666.R;
import com.xi6666.carWash.adapter.CarWashAdapter;
import com.xi6666.libray.widget.HeaderViewPagerFragment;
import com.xi6666.store.mvp.bean.StoreServiceBean;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/1 下午5:21.
 * 个人公众号 ardays
 */

public class CarWashNearFarFragment extends HeaderViewPagerFragment {

    XRecyclerView mStoreXrv;//门店列表


    CarWashAdapter mCarWashAdapter; //特惠洗车列表
    private XRecyclerView.LoadingListener mListener;


    public static CarWashNearFarFragment newInstance() {
        return new CarWashNearFarFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carwash_near_far, container, false);
        mStoreXrv = (XRecyclerView) view.findViewById(R.id.store_xrv);
        mStoreXrv.setLayoutManager(new LinearLayoutManager(getContext()));
        mCarWashAdapter = new CarWashAdapter(getContext());
        mStoreXrv.setAdapter(mCarWashAdapter);
        mStoreXrv.setLoadingListener(mListener);

        return view;
    }


    /**
     * 添加上拉加载下拉刷新
     */
    public void setLoadingListener(XRecyclerView.LoadingListener listener){
        this.mListener = listener;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View getScrollableView() {
        return mStoreXrv;
    }

    //添加列表
    public void addList(List<StoreServiceBean.DataBean> data){
        mStoreXrv.refreshComplete();
        mCarWashAdapter.addAll(data);
    }
    //更新
    public void update(List<StoreServiceBean.DataBean> data){
        mStoreXrv.refreshComplete();
        mCarWashAdapter.update(data);
    }

    public void refreshComplete(){
        mStoreXrv.refreshComplete();
    }



}
