package com.xi6666.store.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.xi6666.R;
import com.xi6666.carWash.adapter.CarWashAdapter;
import com.xi6666.carWash.base.BaseFrgm;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import butterknife.BindView;

/**
 * 创建者 sunsun
 * 时间 16/11/4 下午3:15.
 * 个人公众号 ardays
 *
 *              门店 - 所有服务
 */

public class StoreAllServiceFrgm extends BaseFrgm {


    public static StoreAllServiceFrgm getInstance() {
        return new StoreAllServiceFrgm();
    }



    @BindView(R.id.store_xrv)
    XRecyclerView mStoreAllServiceXrv;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_carwash_near_far;
    }

    @Override
    protected void setUp() {
        mStoreAllServiceXrv.setLayoutManager(new LinearLayoutManager(getContext()));
        mStoreAllServiceXrv.setAdapter(new CarWashAdapter(getContext()));
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
