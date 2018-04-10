package com.xi6666.address;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.xi6666.R;
import com.xi6666.address.fragment.DistributionShopFrgm;
import com.xi6666.address.fragment.PersonalAddressFrgm;
import com.xi6666.address.fragment.adapter.DistributionShopAdapter;
import com.xi6666.address.fragment.adapter.PersonalAddressListAdapter;
import com.xi6666.address.fragment.mvp.bean.DistributionShopBean;
import com.xi6666.address.fragment.mvp.bean.PersonalAddressBean;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.eventbus.ChangeAddressEvent;
import com.xi6666.eventbus.ChoiceAddressEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * 创建者 sunsun
 * 时间 16/11/12 下午3:09.
 * 个人公众号 ardays
 */

public class ReceiptAddressAct extends BaseToolbarView {
    final String[] mTabName = {"个人地址", "配送门店"};

    TabLayout mTab; //切换栏
    View mTabLl;    //切换栏布局
    ViewPager mVp;  //轮播器
    Fragment[] mFgs; //轮播
    /**
     * 0 代表个人地址；
     * 1 代表选择地址;
     */
    int mType = 1;
    int position = 1;
    PersonalAddressFrgm mAddressFrgm;
    DistributionShopFrgm mShopFrgm;

    @Override
    public String title() {
        return "选择收货地址";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_receipt_address;
    }

    @Override
    public void setUp(View view) {
        initView(view);
        initValue();
        initTab();
        initVp();
    }

    private void initValue() {
        mType = getIntent().getIntExtra(TYPE, 0);

        if (mType == 0) {
            mTabLl.setVisibility(View.GONE);
            setToolbarTitle("我的收货地址");
        }

        position = getIntent().getIntExtra(POSITION, 0);
    }


    /**
     * 初始化绑定控件
     */
    private void initView(View view) {
        mTab = (TabLayout) view.findViewById(R.id.receipt_address_tab);
        mTabLl = view.findViewById(R.id.receipt_address_tab_ll);
        mVp = (ViewPager) view.findViewById(R.id.receipt_address_vp);
    }


    /**
     * 初始化Tab
     */
    private void initTab() {
        for (String s : mTabName) {
            TabLayout.Tab tab = mTab.newTab().setText(s);
            mTab.addTab(tab);
        }

        //监听Tab滚动
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    /**
     * 初始化fragment
     */
    private void initVp() {
        mAddressFrgm = new PersonalAddressFrgm();

        mAddressFrgm.setPersonalAddressFrgm(new PersonalAddressListAdapter.PersonalAddressListListener() {
            @Override
            public void onDefaultClick(View view, String address_id, int position) {
                mAddressFrgm.requestSetDefaultAddress(address_id, position);
            }

            @Override
            public void onEditClick(View view, PersonalAddressBean.DataBean data) {
                Log.e("TAG", "adata--->" + data.toString());
                mAddressFrgm.onEditClick(data);
            }

            @Override
            public void onItemClick(View view, PersonalAddressBean.DataBean bean) {
                if (mType == 1) {
                    selectAddress(bean);
                    EventBus.getDefault().post(new ChangeAddressEvent("wuow"));
                }
            }
        });
        mShopFrgm = new DistributionShopFrgm();

        mShopFrgm.setOnDistributionShopAdapterListener(new DistributionShopAdapter.OnDistributionShopAdapterListener() {
            @Override
            public void onItemView(DistributionShopBean.DataBean data) {
                selectStore(data);
            }
        });

        //地址选择

        mFgs = new Fragment[]{mAddressFrgm, mShopFrgm};

        //写入数据
        mVp.setAdapter(new ReceiptAddressFragmentPagerAdapter(getSupportFragmentManager()));
        //监听滑动
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTab.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mVp.setCurrentItem(position);
    }

    class ReceiptAddressFragmentPagerAdapter extends FragmentPagerAdapter {
//        Fragment[] frg = {PersonalAddressFrgm.getInstance(getActivity()), DistributionShopFrgm.getInstance()};

        public ReceiptAddressFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFgs[position];
        }

        @Override
        public int getCount() {
            if (mType == 0) return 1;
            return mFgs.length;
        }
    }

    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity) {
        Intent intent = new Intent(activity, ReceiptAddressAct.class);
        intent.putExtra(TYPE, 0);
        activity.startActivity(intent);
    }

    public static final void SelectAddress(Activity activity, int position) {
        Intent intent = new Intent(activity, ReceiptAddressAct.class);
        intent.putExtra(TYPE, 1);
        intent.putExtra(POSITION, position);
        activity.startActivityForResult(intent, SELECT_CODE);
    }

    public static final int SELECT_CODE = 142;
    //类型
    private static final String TYPE = "address_type";
    //下标位置
    private static final String POSITION = "address_tab_position";
    //                      @code
    //地址
    public static final String SELECT_DATA_ADDRESS = "com.xi6666.address";
    //配送门店
    public static final String SELECT_DATA_SERVICE_STORE = "com.xi6666.service_store";


    //选择地址
    public void selectAddress(PersonalAddressBean.DataBean data) {
        EventBus.getDefault().post(new ChoiceAddressEvent(1, data.address_id));
        Intent intent = new Intent();
        intent.putExtra(SELECT_DATA_ADDRESS, data);
        setResult(RESULT_OK, intent);
        finish();
    }

    //选择配送门店
    public void selectStore(DistributionShopBean.DataBean data) {
        EventBus.getDefault().post(new ChoiceAddressEvent(2, data.id));
        Intent intent = new Intent();
        intent.putExtra(SELECT_DATA_SERVICE_STORE, data);
        setResult(RESULT_OK, intent);
        finish();
    }

}
