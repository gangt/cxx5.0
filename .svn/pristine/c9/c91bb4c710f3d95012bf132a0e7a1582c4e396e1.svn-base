package com.xi6666.store;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseFrgm;
import com.xi6666.carWash.base.view.CxxErrorView;
import com.xi6666.carWash.view.CarWashSearchAct;
import com.xi6666.carWash.view.NewCarWashSearchAct;
import com.xi6666.cityaddress.view.AddressAct;
import com.xi6666.common.CityBean;
import com.xi6666.eventbus.AddressEvent;
import com.xi6666.eventbus.LocationEvent;
import com.xi6666.store.adapter.StoreServiceAdapter;
import com.xi6666.store.custom.StoreScreenPopow;
import com.xi6666.store.custom.utils.FixedSpeedScroller;
import com.xi6666.store.event.StoreServiceSortEvent;
import com.xi6666.store.fragment.StoreServiceFrgm;
import com.xi6666.store.mvp.StoreContract;
import com.xi6666.store.mvp.StoreModel;
import com.xi6666.store.mvp.StorePresenter;
import com.xi6666.store.mvp.bean.StoreServiceTypeBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.support.design.widget.TabLayout.MODE_FIXED;
import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

/**
 * Created by Mr_yang on 2016/10/18.
 */

public class StoreFrgm extends BaseFrgm<StorePresenter, StoreModel>
        implements StoreContract.View {

    @BindView(R.id.store_tab)
    TabLayout mStoreTab; //tab切换
    @BindView(R.id.toolbar_view)
    View mToolbarV; //标题栏布局
    @BindView(R.id.store_vp)
    ViewPager mStoreVp; //切换的fragment
    @BindView(R.id.store_query_iv)
    View mStoreQueryIv; //搜索
    @BindView(R.id.store_screen_iv)
    View mStoreScreenIv;//筛选
    @BindView(R.id.store_address_tv)
    TextView mStoreAddress;
    @BindView(R.id.store_error_view)
    CxxErrorView mErrorView; //没有网络显示页面


    //筛选popow
    StoreScreenPopow mStoreScreenPopow;
    List<StoreServiceFrgm> mFragments;


    //刷新地址
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshAddress(AddressEvent addressEvent) {
        mStoreAddress.setText(CityBean.getCity());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loacationAddress(LocationEvent locationEvent) {
        if (!locationEvent.getMsg().equals("error")) {
            mStoreAddress.setText(CityBean.getCity());
        } else {
            mStoreAddress.setText("未知");
        }
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_store;
    }

    @Override
    protected void setUp() {
        EventBus.getDefault().register(this);
        initToolbar();
        initTab();
        //初始化筛选弹窗
        mStoreScreenPopow = new StoreScreenPopow(getContext());
        initClick();

    }


    /**
     * 初始化Tab标题栏
     */
    private void initToolbar() {
        //沉侵式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mToolbarV.setPadding(0, 60, 0, 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                setMiuiStatusBarDarkMode(getActivity(), true);
            else
                mToolbarV.setBackgroundColor(Color.rgb(240, 240, 240));
        }

        //写入位置显示
        mStoreAddress.setText(TextUtils.isEmpty(CityBean.getCity()) ? "未知" : CityBean.getCity());
        mStoreAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddressAct.class);
                startActivity(intent);
            }
        });

        //如果错误就让它重新加载
        mErrorView.setOnErrorClickListener(v -> {
            initTab();
        });
    }


    /**
     * 初始化tab
     */
    private void initTab() {
        showLoading();
        mPersenter.requestServiceType();
    }


    /**
     * 初始化事件
     */
    private void initClick() {
        //监听搜索的点击事件
        mStoreQueryIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewCarWashSearchAct.openActivity(getActivity());
            }
        });


        //监听筛选的点击事件
        mStoreScreenIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStoreScreenPopow.showAsDropDown(getView(), 0, -getView().getHeight());
            }
        });

        //监听筛选弹窗的筛选
        mStoreScreenPopow.setOnStoreScreenItemListener(new StoreScreenPopow.OnStoreScreenItemListener() {
            @Override
            public void onItemClick(String type) {
                EventBus.getDefault().post(new StoreServiceSortEvent(type));
            }
        });
    }

    /**
     * 适配miui系统标题栏颜色
     */
    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //                      @网络数据返回
    @Override
    public void returnServiceType(StoreServiceTypeBean res) {
        hiddenLoading();
        mErrorView.setVisibility(View.GONE);
        if (!res.success) {    //如果后台返回false
            make(res.info);
            return;
        }


        mFragments = new ArrayList<>();
        //遍历数据创建对应的tab和fragment
        for (StoreServiceTypeBean.DataBean data : res.data) {
            StoreServiceFrgm storeFrgm = new StoreServiceFrgm(data);
            mFragments.add(storeFrgm);
        }
        StoreServiceAdapter storeServire = new StoreServiceAdapter(getActivity().getSupportFragmentManager(), mFragments);
        mStoreVp.setAdapter(storeServire);
        try {
            Field filed = ViewPager.class.getDeclaredField("mScroller");    //减速带
            filed.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mStoreVp.getContext(), new AccelerateInterpolator());
            filed.set(mStoreVp, scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mStoreTab.setupWithViewPager(mStoreVp);
        if (res.data.size() >= 5) {
            mStoreTab.setTabMode(MODE_SCROLLABLE);
        } else {
            mStoreTab.setTabMode(MODE_FIXED);
        }
    }

    @Override
    public void netError() {
        hiddenLoading();
        make("网络加载错误");
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
