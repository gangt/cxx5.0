package com.xi6666.productdetails.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.xi6666.cardbag.view.WashCardInfoFrgm;
import com.xi6666.productdetails.view.ProductDetialFrgm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr_yang on 2016/11/15.
 */

public class ProductDetialAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private String[] mStrings = {"图文详情", "参数规格"};
    private View mCurrentView;

    public ProductDetialAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
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
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            mCurrentView = (View) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            mCurrentView = fragment.getView();
        }
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings[position];
    }
}
