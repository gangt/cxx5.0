package com.xi6666.carWash.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.libray.widget.HeaderScrollHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 2016/12/8 下午8:16.
 * 个人公众号 ardays
 * <p>
 * 空白页
 */

public class CxxNotView extends LinearLayout {

    @BindView(R.id.view_not_tv)
    TextView mNotTv;

    private Context mContext;

    public CxxNotView(Context context) {
        this(context, null);
    }

    public CxxNotView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CxxNotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
        initValue(attrs);
    }


    /**
     */
    private void init() {
        View view = LinearLayout.inflate(mContext, R.layout.view_not, this);
        ButterKnife.bind(this, view);
    }

    private void initValue(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.CxxNotView);
        String errorTv = a.getString(R.styleable.CxxNotView_text);
        mNotTv.setText(errorTv);
        a.recycle();
    }
}
