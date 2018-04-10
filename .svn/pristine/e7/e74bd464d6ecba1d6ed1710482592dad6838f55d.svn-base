package com.xi6666.login.view;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Spannable;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.xi6666.R;
import com.xi6666.app.ImmerseBaseAct;
import com.xi6666.app.ShowDialogService;
import com.xi6666.databean.AddOilPopuBean;
import com.xi6666.eventbus.LoginEvent;

import com.xi6666.login.adapter.LoginPagerAdapter;
import com.xi6666.login.contract.LoginContract;

import com.xi6666.login.presenter.LoginActPresenterImpl;
import com.xi6666.network.ApiRest;
import com.xi6666.network.cookie.PersistentCookieJar;
import com.xi6666.network.cookie.cache.SetCookieCache;
import com.xi6666.network.cookie.persistence.SharedPrefsCookiePersistor;

import com.xi6666.utils.SpUtils;
import com.xi6666.view.dialog.AddOilPromotionDialog;
import com.xi6666.view.dialog.CallDialog;
import com.xi6666.view.dialog.LoadingDialog;
import com.xi6666.view.dialog.PromotionDialogAct;
import com.xi6666.view.dialog.ReceiveSuccessDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.functions.Action1;

public class LoginAct extends ImmerseBaseAct implements ViewPager.
        OnPageChangeListener,
        LoginContract.LoginView {

    private static final String TAG = "LoginAct";
    @BindView(R.id.iv_login_cancle)
    ImageView mIvLoginCancle;
    @BindView(R.id.txt_login_title)
    TextView mTxtLoginTitle;
    @BindView(R.id.vp_login)
    ViewPager mVpLogin;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.txt_login_state)
    TextView mTxtLoginState;
    @BindView(R.id.txt_login_phone)
    TextView mTxtLoginPhone;

    private List<Fragment> fragments;

    private boolean isAccount = false;
    private LoginPagerAdapter mLoginPagerAdapter;
    private PhoneLoginFrgm mPhoneLoginFrgm;
    private AccountLoginFrgm mAccountLoginFrgm;

    private Dialog mDialog;
    private LoginActPresenterImpl mLoginActPresenter;

    private String titleOne = "账号密码登陆(商户)";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fragments = new ArrayList<>();
        mPhoneLoginFrgm = new PhoneLoginFrgm();
        mAccountLoginFrgm = new AccountLoginFrgm();
        fragments.add(mPhoneLoginFrgm);
        fragments.add(mAccountLoginFrgm);
        mLoginPagerAdapter = new LoginPagerAdapter(getSupportFragmentManager(), fragments);
        mVpLogin.setAdapter(mLoginPagerAdapter);
        mVpLogin.addOnPageChangeListener(this);

        mLoginActPresenter = new LoginActPresenterImpl();
        mLoginActPresenter.setApiRest(getApi());
        mLoginActPresenter.attachView(this);
        mLoginActPresenter.getTitle();
    }

    @OnClick({R.id.iv_login_cancle, R.id.txt_login_title, R.id.btn_login, R.id.txt_login_state, R.id.txt_login_phone})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_cancle:
                this.finish();
                break;
            case R.id.txt_login_title:
                isAccount = !isAccount;
                if (isAccount) {
                    mTxtLoginTitle.setText(titleOne);
                    mTxtLoginState.setText("手机登录");
                    mVpLogin.setCurrentItem(1);
                } else {
                    mTxtLoginTitle.setText("手机登录");
                    mTxtLoginState.setText(titleOne);
                    mVpLogin.setCurrentItem(0);
                }
                break;
            case R.id.btn_login:
                if (isAccount) {
                    String account = mAccountLoginFrgm.getAccount();
                    String pwd = mAccountLoginFrgm.getPwd();
                    if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd)) {
                        Toast.makeText(this, "亲~请输入正确的数据", Toast.LENGTH_SHORT).show();
                    } else {
                        mLoginActPresenter.accountSignIn(account, pwd);
                    }
                } else {
                    String phoneNum = mPhoneLoginFrgm.getPhoneNum();
                    String yzm = mPhoneLoginFrgm.getYzm();

                    if (TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(yzm) || (phoneNum.length() != 11)) {
                        Toast.makeText(this, "亲~请输入正确的数据", Toast.LENGTH_SHORT).show();
                    } else {
                        mLoginActPresenter.phoneSignIn(phoneNum, yzm);
                    }
                }
                break;
            case R.id.txt_login_state:
                isAccount = !isAccount;
                if (isAccount) {
                    mTxtLoginTitle.setText(titleOne);
                    mTxtLoginState.setText("手机登录");
                    mVpLogin.setCurrentItem(1);
                } else {
                    mTxtLoginTitle.setText("手机登录");
                    mTxtLoginState.setText(titleOne);
                    mVpLogin.setCurrentItem(0);
                }
                break;
            case R.id.txt_login_phone:
                //TODO
                RxPermissions.getInstance(this)
                        .request(Manifest.permission.CALL_PHONE)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {
                                    new CallDialog(LoginAct.this).MakePhoneCall("400-9999-353");
                                } else {
                                    Toast.makeText(LoginAct.this, "没有给予权限", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            isAccount = false;
            mTxtLoginTitle.setText("手机登录");
            mTxtLoginState.setText(titleOne);
        } else {
            isAccount = true;
            mTxtLoginTitle.setText(titleOne);
            mTxtLoginState.setText("手机登录");
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void showToast(String string) {
        Toast.makeText(LoginAct.this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeAct() {
        EventBus.getDefault().post(new LoginEvent("success"));
        //登录成功关闭界面
        finish();
    }

    @Override
    public void saveUserId(String userId) {
        SpUtils.setString(this, "userId", userId);
    }

    @Override
    public void showLodingDialog() {
        Log.d(TAG, "loading----------------------");
        mDialog = new LoadingDialog().LoadingDialog(this);
    }

    @Override
    public void hideLoadingDialog() {
        Log.d(TAG, "loadinghide----------------------");
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void showAddoilPopu(List<AddOilPopuBean.DataBean.CouponListBean> dataBeen) {
        Intent intent = new Intent(this, PromotionDialogAct.class);
        intent.putExtra("list", (Serializable) dataBeen);
        startActivity(intent);
    }

    @Override
    public void receiveSuccess(String content) {
        ReceiveSuccessDialog receiveSuccessDialog = new ReceiveSuccessDialog(this, R.style.transpant_bg_dialog);
        receiveSuccessDialog.show();
        receiveSuccessDialog.setContent(content);
    }

    @Override
    public void setTitle(String title) {
        titleOne = title;
        mTxtLoginState.setText(titleOne);
    }

    public ApiRest getApi() {
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
}
