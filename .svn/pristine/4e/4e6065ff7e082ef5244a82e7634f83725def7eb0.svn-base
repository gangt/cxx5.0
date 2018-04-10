package com.xi6666.order.activity;

import android.content.Intent;
import android.os.Bundle;
import com.xi6666.R;
import com.xi6666.illegal.other.ToolBarBaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayFailedActivity extends ToolBarBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_failed);
        ButterKnife.bind(this);
    }

    @Override
    public void setToolbarIconDo() {
        //  回到我的订单商品订单Tab
        startActivity(new Intent(this,MyOrderListActivity.class));
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "支付失败";
    }

    @OnClick(R.id.btn_to_pay)
    public void onClick() {
        // 回到收银台
        finish();
    }

    @Override
    public void onBackPressed() {
        //  回到我的订单商品订单Tab
        startActivity(new Intent(this,MyOrderListActivity.class));
        finish();
    }
}
