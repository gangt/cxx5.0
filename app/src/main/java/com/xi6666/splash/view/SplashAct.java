package com.xi6666.splash.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.app.SuperAct;
import com.xi6666.guide.view.GuideAct;
import com.xi6666.main.view.MainAct;
import com.xi6666.order.activity.GoodsEvaluateActivity;
import com.xi6666.splash.di.componets.DaggerSplashComponets;
import com.xi6666.splash.di.modules.SplashModule;
import com.xi6666.splash.presenter.SplashPersenter;
import com.xi6666.utils.AppUitls;
import com.xi6666.utils.SpUtils;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * @author peng
 * @data 创建时间:2016/11/17
 * @desc 闪屏界面
 */
public class SplashAct extends SuperAct implements SplashView {

    @BindView(R.id.btn_splash)
    Button mBtnSplash;
    @BindView(R.id.txt_splash_countdowntime)
    TextView mTxtSplashCountdowntime;
    @BindView(R.id.activity_splash)
    RelativeLayout mActivitySplash;
    @Inject
    SplashPersenter splashPersenter;
    @BindView(R.id.txt_splash_version)
    TextView mTxtVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        DaggerSplashComponets.builder().splashModule(new SplashModule()).build().inject(this);
        splashPersenter.attachView(this);
        splashPersenter.countdownTime();
        setVersionName(AppUitls.getVersionName(this));
    }

    @Override
    public void startNextAct() {
        boolean isFirst = SpUtils.getBoolean(this, "start" + AppUitls.getAppVersionName(this));
        if (isFirst) {
            startActivity(new Intent(this, MainAct.class));
            overridePendingTransition(R.anim.outer, R.anim.inter);
        } else {
            startActivity(new Intent(this, GuideAct.class));
        }
    }

    @Override
    public void finishAct() {
        finish();
    }

    @Override
    public void showCountDownTime(String time) {
        mTxtSplashCountdowntime.setText(time);
    }

    @Override
    public void setVersionName(String versionName) {
        mTxtVersion.setText("V" + versionName);
    }
}
