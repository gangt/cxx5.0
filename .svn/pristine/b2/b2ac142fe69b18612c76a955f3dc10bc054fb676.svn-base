package com.xi6666.classification.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.car.view.custom.LetterView;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.classification.view.adapter.AllBrandAdapter;
import com.xi6666.classification.view.mvp.AllBrandContract;
import com.xi6666.classification.view.mvp.AllBrandModel;
import com.xi6666.classification.view.mvp.AllBrandPresenter;
import com.xi6666.classification.view.mvp.bean.AllBrandBean;

/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午4:08.
 * 个人公众号 ardays
 */

public class AllBrandAct extends BaseToolbarView<AllBrandPresenter, AllBrandModel> implements AllBrandContract.View {
    @Override
    public String title() {
        return "全部品牌";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_all_brand;
    }

    LetterView mAllBrandLev;
    ExpandableListView mAllBrandLv;
    TextView mFdjTv;    //放大镜


    AllBrandAdapter mAllBrandAdapter;   //商品适配器


    @Override
    public void setUp(View view) {
        initView(view);
        initBrandList();
        initFdj();
        mPersenter.requestAllBrand();
    }


    /**
     * 初始化绑定控件
     */
    private void initView(View view) {
        mAllBrandLev = (LetterView) view.findViewById(R.id.all_brand_lev);
        mAllBrandLv = (ExpandableListView) view.findViewById(R.id.all_brand_lv);
        mFdjTv = (TextView) view.findViewById(R.id.fdj_tv);
    }

    /**
     * 初始化商品列表
     */
    private void initBrandList() {
        mAllBrandAdapter = new AllBrandAdapter(this);
        mAllBrandAdapter.setOnItemClassifyClick(new AllBrandAdapter.OnItemClassifyClick() {
            @Override
            public void onItemView(AllBrandBean.DataBean bean) {
                //点击的列表时
                BrandDetailsAct.openActivity(getActivity(), bean.brand_id, "", bean.brand_name);
            }
        });

        //适配器
        mAllBrandLv.setAdapter(mAllBrandAdapter);
    }


    private void initFdj() {
        mAllBrandLev.setOnTouchAssortListener(new LetterView.OnTouchAssortListener() {
            @Override
            public void onTouchAssortXyListener(float x, float y) {
                mFdjTv.setVisibility(View.VISIBLE);
                mFdjTv.setY(y - mFdjTv.getHeight() / 2);
            }

            @Override
            public void onTouchAssortListener(String s) {
                mFdjTv.setText(s);
                int index = mAllBrandAdapter.getAssortList().indexOfKey(s);
                if (index != -1) {
                    mAllBrandLv.setSelectedGroup(index);
                }
            }

            @Override
            public void onTouchAssortUP() {
                mFdjTv.setVisibility(View.GONE);
            }
        });
    }


    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Context activity) {
        Intent intent = new Intent(activity, AllBrandAct.class);
        activity.startActivity(intent);
    }


    //                              @网络数据返回
    @Override
    public void returnAllBrand(AllBrandBean bean) {
        mAllBrandAdapter.update(bean.data);
        //展开所有
        for (int i = 0, length = mAllBrandAdapter.getGroupCount(); i < length; i++) {
            mAllBrandLv.expandGroup(i);
        }
    }
}
