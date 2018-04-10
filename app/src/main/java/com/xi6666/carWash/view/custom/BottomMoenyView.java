package com.xi6666.carWash.view.custom;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.utils.CxxUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/2 下午5:10.
 * 个人公众号 ardays
 * 底部去支付的按钮
 */

public class BottomMoenyView extends LinearLayout implements BottomMoenyViewImpl {
    @BindView(R.id.view_bottom_left_tv)
    TextView mLeftTv;   //左边按钮
    @BindView(R.id.view_bottom_right_tv)
    TextView mRightTv;

    public BottomMoenyView(Context context) {
        this(context, null);
    }

    public BottomMoenyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomMoenyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        View view = LinearLayout.inflate(context, R.layout.view_bottom_moeny, this);
        ButterKnife.bind(this, view);
        initView();
        initClick();
    }
    /**
     * 初始化View
     */
    private void initView() {
        //设置左边按钮
    }

    /**
     * 初始化点击事件
     */
    private void initClick() {
        mRightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBottomViewListener != null) mBottomViewListener.onRightClick(v);

            }
        });
    }



    @Override
    public void setMoeny(double moeny) {
        String str = "共计: ¥" + CxxUtils.getDoubleMoeny(moeny + "");

        //让金额变红色
        SpannableString ss = new SpannableString(str);
        //设置后面字体为17sp
        ss.setSpan(new RelativeSizeSpan(1.3f), 4, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        //设置后面字体为红色
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_red)), 3, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        //写入文本
        mLeftTv.setText(ss);
    }
    public void setMoeny(String moeny) {
        String str = "共计: ¥" + CxxUtils.getDoubleMoeny(moeny + "");

        //让金额变红色
        SpannableString ss = new SpannableString(str);
        //设置后面字体为17sp
        ss.setSpan(new RelativeSizeSpan(1.3f), 4, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        //设置后面字体为红色
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_red)), 3, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        //写入文本
        mLeftTv.setText(ss);
    }




    /*                              事件回调                              */


    public OnBottomViewListener mBottomViewListener;

    public void setOnBottomViewListener(OnBottomViewListener listener){
        this.mBottomViewListener = listener;
    }

    public interface OnBottomViewListener{
        /**
         * 单击事件
         */
        void onRightClick(View view);
    }
}
