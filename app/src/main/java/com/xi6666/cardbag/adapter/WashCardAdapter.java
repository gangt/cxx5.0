package com.xi6666.cardbag.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xi6666.cardbag.view.WashCardDetialFrgm;
import com.xi6666.cardbag.view.WashCardInfoFrgm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr_yang on 2016/11/14.
 */

public class WashCardAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private String[] mStrings;

    public WashCardAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
        mFragments.add(new WashCardInfoFrgm());
        mFragments.add(new WashCardDetialFrgm());
        mStrings = new String[]{"洗车卡信息", "消费明细"};
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings[position];
    }
}
