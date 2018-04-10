package com.xi6666.password.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.app.ComponetBaseAct;
import com.xi6666.common.UserData;
import com.xi6666.eventbus.PassWordChangeEvent;
import com.xi6666.login.view.LoginAct;
import com.xi6666.password.DaggerPasswordComponent;
import com.xi6666.password.PassWordPresenterImpl;
import com.xi6666.password.contract.PasswordContract;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PassWordAct extends ComponetBaseAct
        implements View.OnClickListener, PasswordContract.View {

    private static final String TAG = "";

    @BindView(R.id.txt_password_setting)
    EditText mTxtPasswordSetting;
    @BindView(R.id.txt_password_setting_again)
    EditText mTxtPasswordSettingAgain;

    @Inject
    PassWordPresenterImpl mPassWordPresenter;
    @BindView(R.id.txt_password_accoutname)
    EditText mTxtPasswordAccoutname;
    @BindView(R.id.txt_password_prompt)
    TextView mTxtPasswordPrompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_word);
        ButterKnife.bind(this);
        setRightTxt("保存");
        mTxtRight.setOnClickListener(this);
        DaggerPasswordComponent.builder().appComponets(mAppComponets).build().Inject(this);
        mPassWordPresenter.attachView(this);
        mPassWordPresenter.getUserName();
    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "密码设置";
    }

    @Override
    public void onClick(View v) {
        String newPassWord = mTxtPasswordSetting.getText().toString().trim();
        String newPassWordAgain = mTxtPasswordSettingAgain.getText().toString().trim();
        String name = mTxtPasswordAccoutname.getText().toString().trim();

        if (TextUtils.isEmpty(newPassWord) || TextUtils.isEmpty(newPassWordAgain) || TextUtils.isEmpty(name) || newPassWord.length() < 6
                || newPassWord.length() > 20) {
            Toast.makeText(this, "请填上正确的数据", Toast.LENGTH_SHORT).show();
        } else {
            if (TextUtils.equals(newPassWord,
                    newPassWordAgain)) {
                mPassWordPresenter.savePassWord(UserData.getUserId(), newPassWord, UserData.getUserToken(), name);
            } else {
                Toast.makeText(this, "两次输入的密码不一致!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeAct() {
        finish();
    }

    @Override
    public void startLoginAct() {
        UserData.cleanUserData();
        EventBus.getDefault().post(new PassWordChangeEvent("success"));
        startActivity(new Intent(this, LoginAct.class));
        finish();
    }

    @Override
    public void setAcountName(String name) {
        mTxtPasswordAccoutname.setText(name);
        mTxtPasswordAccoutname.setSelection(mTxtPasswordAccoutname.getText().toString().trim().length());
    }

    @Override
    public void setState(String string) {
        mTxtPasswordPrompt.setText(string);
    }

    @Override
    public void canNotChange() {
        //  mTxtPasswordAccoutname.setFocusable(false);
    }
}
