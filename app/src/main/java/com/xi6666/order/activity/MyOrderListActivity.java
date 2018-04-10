package com.xi6666.order.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.eventbus.HomeItemChangeEvent;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.main.view.MainAct;
import com.xi6666.order.fragment.GoodsOrderFragment;
import com.xi6666.order.fragment.RushCarOrderFragment;
import com.xi6666.order.fragment.ServerOrderFragment;
import com.xi6666.order.other.FragmentAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyOrderListActivity extends AppCompatActivity {

    @BindView(R.id.my_order_tb)
    Toolbar      mMyOrderTb;
    @BindView(R.id.goods_order_tv)
    TextView     mGoodsOrderTv;
    @BindView(R.id.id_goods_order_ll)
    LinearLayout mIdGoodsOrderLl;
    @BindView(R.id.rush_order_tv)
    TextView     mRushOrderTv;
    @BindView(R.id.id_rush_order_ll)
    LinearLayout mIdRushOrderLl;
    @BindView(R.id.server_order_tv)
    TextView     mServerOrderTv;
    @BindView(R.id.tab_server_order_ll)
    LinearLayout mTabServerOrderLl;
    @BindView(R.id.tab_line_iv)
    ImageView    mTabLineIv;
    @BindView(R.id.my_order_vp)
    ViewPager    mMyOrderVp;

    private List<Fragment> mFragmentList = new ArrayList<>();
    private String type;

    /**
     * 屏幕的宽度
     */
    private int screenWidth;

    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;

    private FragmentAdapter mFragmentAdapter;
    private MyListener      mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_list);
        ButterKnife.bind(this);
        setSupportActionBar(mMyOrderTb);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        ToolBarBaseActivity.setMiuiStatusBarDarkMode(this,true);
        initEvent();
        initTabLineWidth();
        init();
    }

    private void initEvent() {
        mListener = new MyListener();
        mMyOrderVp.setOnPageChangeListener(mListener);
    }

    private void init() {
        // 给mFragmentList添加fragment
        mFragmentList.add(new GoodsOrderFragment());
        mFragmentList.add(new RushCarOrderFragment());
        mFragmentList.add(new ServerOrderFragment());

        // 设置ViewPager的默认选中页,要根据上个页面传过来的值决定
//        int index = getIntent().getIntExtra("index",0);
        currentIndex = getIntent().getIntExtra("index",0);
        type = getIntent().getStringExtra("type");
        mMyOrderVp.setOffscreenPageLimit(3);

        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(),mFragmentList);
        mMyOrderVp.setAdapter(mFragmentAdapter);
        mMyOrderVp.setCurrentItem(currentIndex);
        if (currentIndex == 2) {
            resetTextView();
            mServerOrderTv.setTextColor(getResources().getColor(R.color.themeColor));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                    .getLayoutParams();
                lp.leftMargin = currentIndex * (screenWidth / 3);
            mTabLineIv.setLayoutParams(lp);
        }
    }

    private void resetTextView() {
        mGoodsOrderTv.setTextColor(Color.parseColor("#626262"));
        mRushOrderTv.setTextColor(Color.parseColor("#626262"));
        mServerOrderTv.setTextColor(Color.parseColor("#626262"));
    }

    @OnClick({R.id.iv_back_my_order, R.id.id_goods_order_ll, R.id.id_rush_order_ll, R.id.tab_server_order_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_my_order:
                if ("ProductDetailsAct".equals(type)) {
                    finish();
                } else {
                    startActivity(new Intent(this, MainAct.class));
                    EventBus.getDefault().post(new HomeItemChangeEvent(2));
                    finish();
                }
                break;
            case R.id.id_goods_order_ll:
                mMyOrderVp.setCurrentItem(0);
                break;
            case R.id.id_rush_order_ll:
                mMyOrderVp.setCurrentItem(1);
                break;
            case R.id.tab_server_order_ll:
                mMyOrderVp.setCurrentItem(2);
                break;
        }
    }

    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv.getLayoutParams();
        lp.width = screenWidth / 3;
        mTabLineIv.setLayoutParams(lp);
    }

    class MyListener implements ViewPager.OnPageChangeListener {
        /**
         * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
         * offsetPixels:当前页面偏移的像素位置
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                    .getLayoutParams();
            /**
             * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
             * 设置mTabLineIv的左边距 滑动场景：
             * 记3个页面,
             * 从左到右分别为0,1,2
             * 0->1; 1->2; 2->1; 1->0
             */

            if (currentIndex == 0 && position == 0)// 0->1
            {
                lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3) + currentIndex
                        * (screenWidth / 3));

            } else if (currentIndex == 1 && position == 0) // 1->0
            {
                lp.leftMargin = (int) (-(1 - positionOffset)
                        * (screenWidth * 1.0 / 3) + currentIndex
                        * (screenWidth / 3));

            } else if (currentIndex == 1 && position == 1) // 1->2
            {
                lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3) + currentIndex
                        * (screenWidth / 3));
            } else if (currentIndex == 2 && position == 1) // 2->1
            {
                lp.leftMargin = (int) (-(1 - positionOffset)
                        * (screenWidth * 1.0 / 3) + currentIndex
                        * (screenWidth / 3));
            }
            mTabLineIv.setLayoutParams(lp);
        }

        @Override
        public void onPageSelected(int position) {
            resetTextView();
            switch (position) {
                case 0:
                    mGoodsOrderTv.setTextColor(getResources().getColor(R.color.themeColor));
                    break;
                case 1:
                    mRushOrderTv.setTextColor(getResources().getColor(R.color.themeColor));
                    break;
                case 2:
                    mServerOrderTv.setTextColor(getResources().getColor(R.color.themeColor));
                    break;
            }
            currentIndex = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public void onBackPressed() {
        if ("ProductDetailsAct".equals(type)) {
            finish();
        } else {
            startActivity(new Intent(this, MainAct.class));
            EventBus.getDefault().post(new HomeItemChangeEvent(2));
            finish();
        }
    }
}
