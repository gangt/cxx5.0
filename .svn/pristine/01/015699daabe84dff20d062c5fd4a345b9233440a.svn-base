package com.xi6666.setting.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.app.ActManager;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ComponetBaseAct;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.common.Constant;
import com.xi6666.common.OnRecyclerItemClickListener;
import com.xi6666.common.UserData;
import com.xi6666.eventbus.LoginEvent;
import com.xi6666.eventbus.PassWordChangeEvent;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.login.view.LoginAct;
import com.xi6666.network.ApiRest;
import com.xi6666.network.cookie.PersistentCookieJar;
import com.xi6666.network.cookie.cache.SetCookieCache;
import com.xi6666.network.cookie.persistence.SharedPrefsCookiePersistor;
import com.xi6666.password.view.PassWordAct;
import com.xi6666.setting.SettingAdapter;

import com.xi6666.setting.SettingPresenterImpl;
import com.xi6666.setting.contract.SettingContract;

import com.xi6666.setting.di.DaggerSettingComponent;
import com.xi6666.setting.di.SettingModule;
import com.xi6666.utils.AppUitls;
import com.xi6666.utils.SpUtils;
import com.xi6666.view.SettingDivDecoration;
import com.xi6666.view.dialog.BaseDialog;
import com.xi6666.view.dialog.LoadingDialog;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingAct extends ComponetBaseAct implements
        OnRecyclerItemClickListener, SettingContract.View {

    private static final String TAG = "SettingAct";
    @BindView(R.id.iv_setting_logo)
    ImageView mIvSettingLogo;
    @BindView(R.id.iv_setting_about)
    ImageView mIvSettingAbout;
    @BindView(R.id.txt_setting_version)
    TextView mTxtSettingVersion;
    @BindView(R.id.iv_setting_rlv)
    RecyclerView mIvSettingRlv;
    @BindView(R.id.txt_setting_signout)
    TextView mTxtSettingSignout;
    @BindView(R.id.activity_setting)
    LinearLayout mActivitySetting;
    private List<String> mStrings = new ArrayList<>();
    @Inject
    SettingPresenterImpl mSettingPresenter;
    private Dialog mDialog;
    private int count = 0;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void passWordChange(PassWordChangeEvent passWordChangeEvent) {
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        mIvSettingRlv.setLayoutManager(new LinearLayoutManager(this));
        mStrings.add("密码设置");
        mStrings.add("客服帮助");
        mStrings.add("给我评分");
        mStrings.add("关于车小喜");
        SettingAdapter settingAdapter = new SettingAdapter();
        settingAdapter.setMdaList(mStrings);
        mIvSettingRlv.setAdapter(settingAdapter);
        mIvSettingRlv.addItemDecoration(new SettingDivDecoration(this));
        settingAdapter.setOnRecyclerItemClickListener(this);
        DaggerSettingComponent.builder().settingModule(new SettingModule()).apiModule(new ApiModule((BaseApplication)
                getApplication())).build().Inject(this);
        mSettingPresenter.attachView(this);
        mSettingPresenter.setApiRest(getApiRest());
        mSettingPresenter.sigState();

        //TODO 调试使用后续删除
        if (TextUtils.equals("http://dev-app.xiaoxi6.com/", ApiRest.baseUrl)) {
            mTxtSettingVersion.setText("V测试-" + AppUitls.getAppVersionName(this));
        } else {
            mTxtSettingVersion.setText("V" + AppUitls.getAppVersionName(this));
        }


    }

    @Override
    public void setToolbarIconDo() {
        finish();

    }

    @Override
    public String setToolbarTitle() {
        return "设置";
    }

    @Override
    public void onRecyclerItemClick(int position) {
        switch (position) {
            case 0:
                //设置密码
                if (TextUtils.isEmpty(UserData.getUserId())) {
                    //登录
                    startActivity(new Intent(this, LoginAct.class));

                } else {
                    startActivity(new Intent(SettingAct.this, PassWordAct.class));
                }
                break;
            case 1:
                //TODO客服
                HtmlAct.unsealActivity(this, ApiRest.baseUrl + ApiRest.SERVER);
                break;
            case 2:
                try {
                    Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception e) {

                }

                break;
            case 3:
                //TODO关于车小喜
                HtmlAct.unsealActivity(this, ApiRest.baseUrl + ApiRest.ABOUTCXX);
                break;
        }
    }

    @OnClick({R.id.txt_setting_signout, R.id.iv_setting_logo})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_setting_signout:
                BaseDialog baseDialog = new BaseDialog(this);
                baseDialog.setTitle("亲,你确定要退出吗?");
                baseDialog.setLeftAndRight("取消", "确定");
                baseDialog.setDialogButtonOnclick(new BaseDialog.DialogButtonOnclick() {
                    @Override
                    public void onLeftOnclick() {
                        baseDialog.dismiss();
                    }

                    @Override
                    public void onRightOncklick() {
                        //退出登录
                        mSettingPresenter.signout(UserData.getUserId(), UserData.getUserToken());
                        baseDialog.dismiss();
                    }
                });
                baseDialog.show();
                break;
            case R.id.iv_setting_logo:
                count++;
                if (count == 5) {
                    final EditText inputServer = new EditText(this);
                    inputServer.setInputType(InputType.TYPE_CLASS_NUMBER);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("是否切换服务器").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
                            .setNegativeButton("取消", null);
                    builder.setPositiveButton("确定", (dialog, which) -> {
                        String s = inputServer.getText().toString();
                        if (TextUtils.equals("203980", s)) {

                            if (TextUtils.equals(SpUtils.getString(SettingAct.this, "baseUrl"), "http://dev-app.xiaoxi6.com/")) {
                                //如果是测试服就修改成正式服
                                SpUtils.setString(SettingAct.this, "baseUrl", "http://app.xiaoxi6.com/");
                                if (UserData.isLoginIn()) {
                                    UserData.cleanUserData();
                                }
                                ActManager.getAppManager().finishAllActivity();
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(0);//正常退出App
                            } else {
                                //如果是正式服就修改成测试服
                                SpUtils.setString(SettingAct.this, "baseUrl", "http://dev-app.xiaoxi6.com/");
                                if (UserData.isLoginIn()) {
                                    UserData.cleanUserData();
                                }
                                ActManager.getAppManager().finishAllActivity();
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(0);//正常退出App

                            }

                        }
                    });
                    builder.show();
                    count = 0;
                }
                break;
        }
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setVersionName(String versionName) {

    }

    @Override
    public void startHtmlAct() {

    }

    @Override
    public void showSigOut() {
        mTxtSettingSignout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSigOut() {
        mTxtSettingSignout.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingDialog() {
        mDialog = new LoadingDialog().LoadingDialog(this);
    }

    @Override
    public void hideLoadingDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void showAlertDialog() {


    }

    @Override
    public void closeAndJumpToLogin() {
        finish();
    }

    //打开web界面
    public void setHtmlAct(String url) {
        //打开Html界面
        HtmlAct.unsealActivity(this, url);
    }

    public ApiRest getApiRest() {
        return new Retrofit.Builder().baseUrl(ApiRest.baseUrl).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(makeHttpClient())
                .build().create(ApiRest.class);

    }

    private OkHttpClient makeHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS).cookieJar(new PersistentCookieJar
                        (new SetCookieCache(), new SharedPrefsCookiePersistor(this))).build();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
