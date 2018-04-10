package com.xi6666.cardbag.view;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.BaseFrgm;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.cardbag.adapter.WashCardAdapter;
import com.xi6666.cardbag.constract.CarWashCardContract;
import com.xi6666.cardbag.di.component.DaggerCarWashCardComponent;
import com.xi6666.cardbag.di.module.CarWashCardModule;
import com.xi6666.cardbag.persenter.CarWashCardPresenterImpl;
import com.xi6666.common.UserData;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.network.ApiRest;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/14.
 */

public class CarWashCardFrgm extends BaseFrgm implements CarWashCardContract.View, View.OnClickListener {
    private static final String TAG = "CarWashCardFrgm";


    @BindView(R.id.tl_carwashfrgm)
    TabLayout mTlCarwashfrgm;
    @BindView(R.id.vp_carwashfrgm)
    ViewPager mVpCarwashfrgm;

    @Inject
    ApiRest mApiRest;
    @Inject
    CarWashCardPresenterImpl mCarWashCardPresenter;
    @BindView(R.id.ll_carwashfrgm_data)
    LinearLayout mLlCarwashfrgmData;
    @BindView(R.id.txt_carwashfrgm_buying)
    TextView mTxtCarwashfrgmBuying;
    @BindView(R.id.ll_carwashfrgm_empty)
    LinearLayout mLlCarwashfrgmEmpty;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_carwash;
    }

    @Override
    protected void init() {
        DaggerCarWashCardComponent.builder().apiModule(new ApiModule((BaseApplication) mActivity.getApplication())).
                carWashCardModule(new CarWashCardModule()).build().Inject(this);
        mCarWashCardPresenter.attachView(this);
        mCarWashCardPresenter.setApiRest(mApiRest);
        mCarWashCardPresenter.loadData();

        mTxtCarwashfrgmBuying.setOnClickListener(this);

        Spannable span = new SpannableString(mTxtCarwashfrgmBuying.getText());
        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.themeColor)),
                mTxtCarwashfrgmBuying.length() - 4,
                mTxtCarwashfrgmBuying.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTxtCarwashfrgmBuying.setText(span);

        Log.d(TAG, "CarWashCardFrgm--->" + getChildFragmentManager());
        WashCardAdapter washCardAdapter = new WashCardAdapter(getChildFragmentManager());
        mVpCarwashfrgm.setAdapter(washCardAdapter);
        mTlCarwashfrgm.setupWithViewPager(mVpCarwashfrgm);
        mTlCarwashfrgm.setTabsFromPagerAdapter(washCardAdapter);
    }

    @Override
    public void showEmpty() {
        mLlCarwashfrgmEmpty.setVisibility(View.VISIBLE);
        mLlCarwashfrgmData.setVisibility(View.GONE);
    }

    @Override
    public void showData() {
        mLlCarwashfrgmEmpty.setVisibility(View.GONE);
        mLlCarwashfrgmData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        //洗车卡
        HtmlAct.unsealActivity(mActivity, ApiRest.baseUrl + ApiRest.WASHCARD + "&user_id=" + UserData.getUserId()
                + "&user_token=" + UserData.getUserToken());
    }
}
