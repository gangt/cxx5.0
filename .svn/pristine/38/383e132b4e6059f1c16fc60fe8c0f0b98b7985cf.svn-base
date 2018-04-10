package com.xi6666.illegal.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xi6666.R;
import com.xi6666.common.UserData;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.illegal.see.view.UsageRecordAct;
import com.xi6666.network.ApiRest;

public class CommitSuccessActivity extends ToolBarBaseActivity implements View.OnClickListener {

    private Button mBtnSeeRecord;
    private String card_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_success);
        mBtnSeeRecord = (Button) findViewById(R.id.btn_see_use_record);
        mBtnSeeRecord.setOnClickListener(this);
        card_id = getIntent().getStringExtra("card_id");
    }

    @Override
    public void setToolbarIconDo() {
        // 进入违章卡活动主页
        HtmlAct.unsealActivity(this, ApiRest.ILLEGA + "?get_device_type=android" + "&user_id=" + UserData.getUserId()
                + "&user_token=" + UserData.getUserToken());
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "提交成功";
    }

    @Override
    public void onClick(View v) {
        // 跳转到使用记录界面
        UsageRecordAct.openActivity(this,card_id);
        finish();
    }

    @Override
    public void onBackPressed() {
        // 进入违章卡活动主页
        HtmlAct.unsealActivity(this, ApiRest.ILLEGA + "?get_device_type=android" + "&user_id=" + UserData.getUserId()
                + "&user_token=" + UserData.getUserToken());
        finish();
    }
}
