package com.xi6666.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.owner.mvp.bean.OwnerEvaluationBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建者 sunsun
 * 时间 16/11/10 下午3:47.
 * 个人公众号 ardays
 * 评价分类View
 */
public class EvaluationTypeView extends LinearLayout {
    /**
     * 上下文
     */
    Context mContext;


    @BindView(R.id.view_evaluation_type_whole_tv)
    TextView mWholeTv;  //全部评价
    @BindView(R.id.view_evaluation_type_good_tv)
    TextView mGoodTv;  //好评
    @BindView(R.id.view_evaluation_type_commonly_tv)
    TextView mCommonlyTv;  //中评
    @BindView(R.id.view_evaluation_type_difference_tv)
    TextView mDifferenceTv;  //差评


    TextView mSelectTv; //选中的TextView


    public EvaluationTypeView(Context context) {
        this(context, null);
    }

    public EvaluationTypeView(Context context, AttributeSet a) {
        this(context, a, 0);
    }

    public EvaluationTypeView(Context context, AttributeSet a, int defStyleAttr) {
        super(context, a, defStyleAttr);
        this.mContext = context;
        init();
    }


    /**
     * 初始化
     */
    private void init() {
        View view = View.inflate(mContext, R.layout.view_evaluation_type, this);
        ButterKnife.bind(this, view);

        //设置默认选中的
        setSelectType(mWholeTv);
    }


    /**
     * 监听评价分类点击
     */
    @OnClick({R.id.view_evaluation_type_whole_tv, R.id.view_evaluation_type_good_tv, R.id.view_evaluation_type_commonly_tv, R.id.view_evaluation_type_difference_tv})
    void onTypeClick(TextView view)
    {
        setSelectType(view);
        if(mOnEvaluationTypeViewListener != null) {
            mOnEvaluationTypeViewListener.onSelectType(Integer.parseInt((String) view.getTag()));
        }
    }







    /**
     * 设置选中的评价分类，并取消上一个颜色
     */
    private void setSelectType(TextView tv){
        if(mSelectTv != null){
            //设置背景颜色
            mSelectTv.setTextColor(getResources().getColor(R.color.text_gray));
        }

        //设置选中的文本颜色 和 背景颜色
        tv.setTextColor(getResources().getColor(R.color.text_orange1));

        //把全局选中的文本更新一下
        mSelectTv = tv;
    }


    /**
     * 写入评价数据
     */
    public void setData(OwnerEvaluationBean.DataBean.PingJiaBean data) {
        mWholeTv.setText("全部评价\n" + data.all_count);
        mGoodTv.setText("好评\n" + data.good_count);
        mCommonlyTv.setText("中评\n" + data.middle_count);
        mDifferenceTv.setText("差评\n" + data.bad_count);
    }


    //                                  @代理方法
    public OnEvaluationTypeViewListener mOnEvaluationTypeViewListener;
    public void setOnEvaluationTypeViewListener(OnEvaluationTypeViewListener listener){
        this.mOnEvaluationTypeViewListener = listener;
    }
    public interface OnEvaluationTypeViewListener
    {
        /**
         * 选择评价类型返回
         * @param type  评价类型
         */
        void onSelectType(int type);
    }
}