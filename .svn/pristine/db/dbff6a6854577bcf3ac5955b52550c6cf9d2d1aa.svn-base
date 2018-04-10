package com.xi6666.order.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi6666.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RedPackageOpenActivity extends AppCompatActivity {


    @BindView(R.id.iv_rp_open_back)
    ImageView mIvRpOpenBack;
    @BindView(R.id.rp_open_tb)
    Toolbar   mRpOpenTb;
    @BindView(R.id.tv_xidou_count)
    TextView  mTvXidouCount;
    private int xidou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_package_open);
        ButterKnife.bind(this);
        setSupportActionBar(mRpOpenTb);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        xidou = intent.getIntExtra("xidou", 0);
        mTvXidouCount.setText(xidou + "");
    }

    @OnClick(R.id.iv_rp_open_back)
    public void onClick() {
        // 进入订单列表商品订单Tab
        startActivity(new Intent(RedPackageOpenActivity.this,MyOrderListActivity.class));
        finish();
    }
}
