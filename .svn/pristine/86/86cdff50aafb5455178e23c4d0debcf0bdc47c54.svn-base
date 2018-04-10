package com.xi6666.technician.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/9 下午3:17.
 * 个人公众号 ardays
 *
 * 技师详情 - 提问
 */
public class QuestionsTechnicianView extends LinearLayout {
    /**
     * 上下文
     */
    Context mContext;

    @BindView(R.id.view_question_technician_ask_tv)
    TextView mAskTv;

    public QuestionsTechnicianView(Context context) {
        this(context, null);
    }

    public QuestionsTechnicianView(Context context, AttributeSet a) {
        this(context, a, 0);
    }

    public QuestionsTechnicianView(Context context, AttributeSet a, int defStyleAttr) {
        super(context, a, defStyleAttr);
        this.mContext = context;
        init();
    }


    /**
     * 初始化
     */
    private void init() {
        View view = View.inflate(mContext, R.layout.view_questions_technician, this);
        ButterKnife.bind(this, view);
    }


    /**
     * 设置点击提问的事件
     */
    public void setOnAskListener(OnClickListener listener){
        mAskTv.setOnClickListener(listener);
    }

}