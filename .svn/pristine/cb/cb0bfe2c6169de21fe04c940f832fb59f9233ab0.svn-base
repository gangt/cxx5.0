package com.xi6666.car.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午2:41.
 * 个人公众号 ardays
 */

public class BorderTextView extends TextView {

    public BorderTextView(Context context) {
        this(context, null);
    }

    public BorderTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BorderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 在这初始化
     */
    private void init() {
        //设置padding值
        setPadding(50, 10, 50, 10);
        setTextSize(14);
        setBackgroundResource(R.drawable.bg_select_car);
        //设置字体居中
        setGravity(Gravity.CENTER);
        //设置字体颜色
        setTextColor(getResources().getColor(R.color.text_green));
        //设置左边距
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.rightMargin = 18;
        setLayoutParams(layoutParams);
    }

}
