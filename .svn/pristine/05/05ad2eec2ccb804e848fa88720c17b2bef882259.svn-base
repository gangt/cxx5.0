package com.xi6666.store.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.view.custom.EvaluationTypeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建者 sunsun
 * 时间 16/11/17 上午10:25.
 * 个人公众号 ardays
 */
public class EvaluateItemView extends LinearLayout {
    /**
     * 上下文
     */
    Context mContext;


    @BindView(R.id.view_evaluation_type_whole_tv)
    TextView mWholeTv;  //洗车
    @BindView(R.id.view_evaluation_type_good_tv)
    TextView mGoodTv;  //美容
    @BindView(R.id.view_evaluation_type_commonly_tv)
    TextView mCommonlyTv;  //保养
    @BindView(R.id.view_evaluation_type_difference_tv)
    TextView Difference;  //违章


    TextView mSelectTv; //选中的TextView

    public EvaluateItemView(Context context) {
        this(context, null);
    }

    public EvaluateItemView(Context context, AttributeSet a) {
        this(context, a, 0);
    }

    public EvaluateItemView(Context context, AttributeSet a, int defStyleAttr) {
        super(context, a, defStyleAttr);
        this.mContext = context;
        init();
    }


    /**
     * 初始化
     */
    private void init() {
        View view = View.inflate(mContext, R.layout.view_evaluation_item, this);
        ButterKnife.bind(this, view);

        //设置默认选中的
        setSelectType(mWholeTv);
    }


    /**
     * 监听评价分类点击
     */
    @OnClick({R.id.view_evaluation_type_whole_tv, R.id.view_evaluation_type_good_tv, R.id.view_evaluation_type_commonly_tv, R.id.view_evaluation_type_difference_tv})
    void onTypeClick(TextView view) {
        setSelectType(view);
        if (mOnEvaluationTypeViewListener != null) {
            mOnEvaluationTypeViewListener.onSelectType(Integer.parseInt((String) view.getTag()));
        }
    }


    /**
     * 设置选中的评价分类，并取消上一个颜色
     */
    private void setSelectType(TextView tv) {
        if (mSelectTv != null) {
            //设置背景颜色
            mSelectTv.setBackgroundResource(R.drawable.bg_radius_gray);
            mSelectTv.setTextColor(getResources().getColor(R.color.text_gray));
        }

        //设置选中的文本颜色 和 背景颜色
        tv.setBackgroundResource(R.drawable.bg_radius_orange);
        tv.setTextColor(getResources().getColor(R.color.text_orange1));

        //把全局选中的文本更新一下
        mSelectTv = tv;
    }


    //                                  @代理方法
    public EvaluationTypeView.OnEvaluationTypeViewListener mOnEvaluationTypeViewListener;

    public void setOnEvaluationTypeViewListener(EvaluationTypeView.OnEvaluationTypeViewListener listener) {
        this.mOnEvaluationTypeViewListener = listener;
    }

    public interface OnEvaluationTypeViewListener {
        /**
         * 选择评价类型返回
         *
         * @param type 评价类型
         */
        void onSelectType(int type);
    }

}