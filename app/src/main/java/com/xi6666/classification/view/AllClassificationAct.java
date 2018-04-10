package com.xi6666.classification.view;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.classification.view.custom.TagCloudView;
import com.xi6666.classification.view.fragment.ServiceClassificationBrandFrgm;
import com.xi6666.classification.view.fragment.ServiceClassificationFrg;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/11 下午4:45.
 * 个人公众号 ardays
 */

public class AllClassificationAct extends BaseToolbarView {


    TabLayout mTab; //全部分类下的两个跳转
    ViewPager mVp;  //存放分类和品牌

    int mPosition = 0;


    @Override
    public String title() {
        return "全部分类";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_all_classification;
    }

    @Override
    public void setUp(View view) {
        initView(view);
        initValue();
        initViewPager();
        initTab();
    }

    /**
     * 初始化控件
     */
    private void initView(View view) {
        mTab = (TabLayout) view.findViewById(R.id.all_classification_tab);
        mVp = (ViewPager) view.findViewById(R.id.all_classification_vp);
    }

    private void initValue() {
        mPosition = getIntent().getIntExtra("position",0);
    }

    /**
     * 初始化Tab
     */
    private void initTab() {
        mTab.getTabAt(getIntent().getIntExtra("tab_position",0)).select();
    }

    /**
     * 初始化两个Tab切换
     */
    private void initViewPager() {
        //设置适配器
        mVp.setAdapter(new AllClassification(getSupportFragmentManager()));
        mTab.setupWithViewPager(mVp);
    }

    class AllClassification extends FragmentPagerAdapter {

        String[] tabName = {"分类", "品牌"};
        Fragment[] mClassificationFrgms = {ServiceClassificationFrg.getInstance(mPosition), ServiceClassificationBrandFrgm.getInstance()};

        public AllClassification(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mClassificationFrgms[position];
        }

        @Override
        public int getCount() {
            return mClassificationFrgms.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabName[position];
        }
    }


    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity, int tab_position) {
        Intent intent = new Intent(activity, AllClassificationAct.class);
        intent.putExtra("tab_position", tab_position);
        activity.startActivity(intent);
    }
    public static final void openActivity(Activity activity, int tab_position, int position) {
        Intent intent = new Intent(activity, AllClassificationAct.class);
        intent.putExtra("tab_position", tab_position);
        intent.putExtra("position",position);
        activity.startActivity(intent);
    }
}
