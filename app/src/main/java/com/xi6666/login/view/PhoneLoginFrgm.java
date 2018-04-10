package com.xi6666.login.view;


import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.app.SuperFrgm;
import com.xi6666.login.contract.LoginContract;
import com.xi6666.login.presenter.LoginPresenterImpl;
import com.xi6666.network.ApiRest;
import com.xi6666.network.cookie.PersistentCookieJar;
import com.xi6666.network.cookie.cache.SetCookieCache;
import com.xi6666.network.cookie.persistence.SharedPrefsCookiePersistor;
import com.xi6666.utils.ButtonTimeCountUtil;
import com.xi6666.view.CountdownButton;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr_yang on 2016/11/5.
 */

public class PhoneLoginFrgm extends SuperFrgm implements LoginContract.View, TextWatcher {
    private static final String TAG = "PhoneLoginFrgm";
    @BindView(R.id.et_phonelogin_phone)
    EditText mEtPhoneloginPhone;
    @BindView(R.id.iv_phoneloging_clean)
    ImageView mIvPhonelogingClean;
    @BindView(R.id.et_phonelogin_yzm)
    EditText mEtPhoneloginYzm;
    @BindView(R.id.btn_phonelogin_getyzm)
    CountdownButton mBtnPhoneloginGetyzm;

    private String token = "";
    private LoginPresenterImpl mLoginPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragm_login_phone;
    }

    @Override
    protected void init() {
        mBtnPhoneloginGetyzm.setEnabled(false);
        mBtnPhoneloginGetyzm.setTextColor(getResources().getColor(R.color.gray));
        mBtnPhoneloginGetyzm.setBackgroundResource(R.drawable.bg_login_yanzhengma_enble);

        mLoginPresenter = new LoginPresenterImpl();
        mLoginPresenter.attachView(this);
        mLoginPresenter.setApiRest(getApiRest());
        mLoginPresenter.getToken();
        mEtPhoneloginPhone.addTextChangedListener(this);


    }

    @OnClick({R.id.iv_phoneloging_clean, R.id.btn_phonelogin_getyzm})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_phoneloging_clean:
                mEtPhoneloginPhone.setText("");
                break;
            case R.id.btn_phonelogin_getyzm:
                if (TextUtils.isEmpty(mEtPhoneloginPhone.getText().toString().trim()) || (mEtPhoneloginPhone.getText().toString().trim().length() != 11)) {
                    Toast.makeText(mActivity, "亲,请输入正确的手机号码!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断是否有token
                if (!TextUtils.isEmpty(token)) {
                    mLoginPresenter.getYzm(mEtPhoneloginPhone.getText().toString().trim(), token);
                } else {
                    Toast.makeText(mActivity, "token获取失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public String getPhoneNum() {
        return mEtPhoneloginPhone.getText().toString().trim();
    }

    public String getYzm() {
        return mEtPhoneloginYzm.getText().toString().trim();
    }

    @Override
    public void setToken(String string) {
        token = string;
    }

    @Override
    public void sendYzm() {
        ButtonTimeCountUtil buttonTimeCountUtil = new ButtonTimeCountUtil(mActivity, 60000, 1000, mBtnPhoneloginGetyzm);
        buttonTimeCountUtil.start();
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(mActivity, string, Toast.LENGTH_SHORT).show();
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
                        (new SetCookieCache(), new SharedPrefsCookiePersistor(mActivity))).build();

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() == 11) {
            mBtnPhoneloginGetyzm.setEnabled(true);
            mBtnPhoneloginGetyzm.setTextColor(Color.WHITE);
            mBtnPhoneloginGetyzm.setBackgroundResource(R.drawable.bg_login_yanzhengma);
        } else {
            mBtnPhoneloginGetyzm.setEnabled(false);
            mBtnPhoneloginGetyzm.setTextColor(getResources().getColor(R.color.gray));
            mBtnPhoneloginGetyzm.setBackgroundResource(R.drawable.bg_login_yanzhengma_enble);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
