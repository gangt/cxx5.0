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
import com.xi6666.view.superrecyclerview.XRecyclerView;

/**
 * 创建者 sunsun
 * 时间 16/11/1 下午5:21.
 * 个人公众号 ardays
 * 洗车价格
 */

public class CarWashMoenyFragment extends HeaderViewPagerFragment {

    XRecyclerView mStoreXrv;//门店列表

    public static CarWashMoenyFragment newInstance() {
        return new CarWashMoenyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carwash_near_far, container, false);
        mStoreXrv = (XRecyclerView) view.findViewById(R.id.store_xrv);
        mStoreXrv.setLayoutManager(new LinearLayoutManager(getContext()));
        mStoreXrv.setAdapter(new CarWashAdapter(getContext()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View getScrollableView() {
        return mStoreXrv;
    }
}
