package com.xi6666.carWash.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xi6666.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 2016/12/8 下午8:16.
 * 个人公众号 ardays
 */

public class CxxErrorView extends LinearLayout {

    @BindView(R.id.buttonError)
    Button mErrorBtn;

    private Context mContext;

    public CxxErrorView(Context context) {
        this(context, null);
    }

    public CxxErrorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CxxErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    /**
     * 网络加载错误
     */
    private void init() {
        View view = LinearLayout.inflate(mContext, R.layout.view_error, this);
        ButterKnife.bind(this, view);
    }

    /**
     * 点击网络错误回调
     */
    public void setOnErrorClickListener(OnClickListener listener) {
        mErrorBtn.setOnClickListener(listener);
    }
}
