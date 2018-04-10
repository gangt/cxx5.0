package com.xi6666.feedback.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ToolBarBaseAct;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.feedback.contract.FeedBackContact;
import com.xi6666.feedback.di.DaggerFeedBackComponent;
import com.xi6666.feedback.di.FeedBackModule;
import com.xi6666.feedback.presenter.FeedBackPresenterImpl;
import com.xi6666.network.ApiRest;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author peng
 * @data 创建时间:2016/11/25
 * @desc 意见反馈界面
 */
public class FeedBackAct extends ToolBarBaseAct implements
        View.OnClickListener, FeedBackContact.View, TextWatcher {

    @BindView(R.id.et_feedback)
    EditText mEtFeedback;
    @BindView(R.id.tv_feedback)
    TextView mTextView;
    @Inject
    ApiRest mApiRest;
    @Inject
    FeedBackPresenterImpl mFeedBackPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);
        mTxtRight.setText("提交");
        mTxtRight.setTextColor(getResources().getColor(R.color.themeColor));
        mTxtRight.setOnClickListener(this);
        mTxtRight.setVisibility(View.VISIBLE);
        DaggerFeedBackComponent.builder().apiModule(new ApiModule((BaseApplication) getApplication())).
                feedBackModule(new FeedBackModule()).build().Inject(this);
        mFeedBackPresenter.attachView(this);
        mFeedBackPresenter.setApiRest(mApiRest);
        mEtFeedback.addTextChangedListener(this);
    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "意见反馈";
    }

    @Override
    public void onClick(View v) {
        String trim = mEtFeedback.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {

            mFeedBackPresenter.sendData(trim);
        } else {
            Toast.makeText(this, "请输入反馈数据!", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void showToast(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeAct() {
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mTextView.setText(s.length() + "/" + "250");

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
