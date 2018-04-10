package com.xi6666;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi6666.app.SuperAct;
import com.xi6666.cardbag.view.oilcard.ArrivalFragment;
import com.xi6666.cardbag.view.oilcard.OilRecordFragment;
import com.xi6666.common.BuriedPoint;
import com.xi6666.utils.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondAct extends SuperAct {


    private static final String TAG = "SecondAct";
    @BindView(R.id.btn_second)
    Button mBtnSecond;
    @BindView(R.id.fl_second)
    FrameLayout mFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        mBtnSecond.setOnClickListener(v -> {
            FragmentManager supportFragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            OilRecordFragment arrivalFragment = new OilRecordFragment();
            fragmentTransaction.replace(R.id.fl_second, arrivalFragment);
            fragmentTransaction.commit();
        });
    }
}
