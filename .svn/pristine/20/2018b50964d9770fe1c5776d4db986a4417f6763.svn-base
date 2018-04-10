package com.xi6666.store.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xi6666.store.fragment.StoreServiceFrgm;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/4 下午3:09.
 * 个人公众号 ardays
 */

public class StoreServiceAdapter extends FragmentPagerAdapter{


    public List<StoreServiceFrgm> mFragments;


    public StoreServiceAdapter(FragmentManager fm) {
        super(fm);
    }

    public StoreServiceAdapter(FragmentManager fm,List<StoreServiceFrgm> fg){
        super(fm);
        mFragments = fg;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).mDatas.cate_name;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
