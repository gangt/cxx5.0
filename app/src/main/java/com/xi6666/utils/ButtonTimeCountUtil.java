package com.xi6666.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

import com.xi6666.R;

/**
 * Created by Mr_yang on 2016/11/5.
 */

public class ButtonTimeCountUtil extends CountDownTimer {
    private Activity mActivity;
    private Button btn;        // 按钮

    // 这个类的构造函数是需要传递三个参数的.
    //一个是Activity，就是我们需要的在哪一个activity上面用就把他传入进来.
    //一个是总的时间millisInFuture.
    //一个是countDownInterval，这个参数是回调的间隔.可以设置隔多长的时间动态去显示时间.
    //Button需要让哪一个按钮来触发这个事件,就将这个button传递进来.
    public ButtonTimeCountUtil(Activity mActivity, long millisInFuture, long countDownInterval, Button btn) {
        super(millisInFuture, countDownInterval);
        this.mActivity = mActivity;
        this.btn = btn;
    }

    //带on的方法一般都是类自己去调用的
    @SuppressLint("NewApi")
    @Override
    public void onTick(long millisUntilFinished) {
        // 设置不能点击
        btn.setClickable(false);
        btn.setText(millisUntilFinished / 1000 + "s后重试");// 设置倒计时时间
        btn.setTextColor(mActivity.getResources().getColor(R.color.txtSettingVersion));
       // btn.setBackgroundResource(R.drawable.bg_login_yanzhengma_enble);
        btn.setBackground(mActivity.getResources().getDrawable(R.drawable.bg_login_yanzhengma_enble));
      /*  btn.setTextSize(DimenUtils.dip2px(mActivity, 4));*/
        // 设置按钮的背景图片或者是颜色
        //
        // 获取按钮的文字
        Spannable span = new SpannableString(btn.getText().toString());
        // 将倒计时时间显示为红色
        span.setSpan(new ForegroundColorSpan(Color.RED), 2, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //再将按钮的文字设置回去
        btn.setText(span);
    }

    //当计时完成以后调用的方法
    @SuppressLint("NewApi")
    @Override
    public void onFinish() {
        //给按钮重新设置显示的字体
        btn.setText("重新发送");
        //设置代码可以点击
        btn.setClickable(true);
        //还原背景颜色或者是图片
        btn.setBackground(mActivity.getResources().getDrawable(R.drawable.bg_login_yanzhengma));
        btn.setTextColor(Color.WHITE);
       // btn.setBackgroundResource(R.drawable.bg_login_yanzhengma);
        btn.setBackground(mActivity.getResources().getDrawable(R.drawable.bg_login_yanzhengma));
    }
}