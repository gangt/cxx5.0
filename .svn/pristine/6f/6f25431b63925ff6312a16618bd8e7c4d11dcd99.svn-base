package com.xi6666.carWash.base;

import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.app.SuperFrgm;

import butterknife.BindView;

/**
 * 创建者 sunsun
 * 时间 16/11/4 下午3:18.
 * 个人公众号 ardays
 * 测试类
 */

public abstract class BaseTestFrgm extends SuperFrgm {
    @BindView(R.id.textView)
    TextView mTv;   //测试文本


    /**
     * 测试文本
     */
    public String getTestTx(){
        return "";
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test;
    }

    @Override
    protected void init() {
        mTv.setText(getTestTx());
    }

}
