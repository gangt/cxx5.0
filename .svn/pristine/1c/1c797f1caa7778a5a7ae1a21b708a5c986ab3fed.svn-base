package com.xi6666.login.view;


import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.xi6666.R;
import com.xi6666.app.SuperFrgm;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mr_yang on 2016/11/5.
 */

public class AccountLoginFrgm extends SuperFrgm {
    @BindView(R.id.et_accountlogin_account)
    EditText mEtAccountloginAccount;
    @BindView(R.id.et_accountlogin_clean)
    ImageView mEtAccountloginClean;
    @BindView(R.id.et_accountlogin_pwd)
    EditText mEtAccountloginPwd;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragm_login_account;
    }

    @Override
    protected void init() {

    }

    public String getAccount() {
        return mEtAccountloginAccount.getText().toString().trim();
    }

    public String getPwd() {
        return mEtAccountloginPwd.getText().toString().trim();
    }

    @OnClick(R.id.et_accountlogin_clean)
    void onClick(View view) {
        mEtAccountloginAccount.setText("");
    }
}
