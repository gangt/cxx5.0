package com.xi6666.technician.view.custom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.carWash.base.view.CxxNotView;
import com.xi6666.carWash.base.view.LoadMoreRecyclerView;
import com.xi6666.technician.adapter.TechnicianAnswerAdapter;
import com.xi6666.technician.mvp.bean.TechnicianDetailsBean;
import com.xi6666.technician.view.ItsAnswerAct;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建者 sunsun
 * 时间 16/11/9 下午3:30.
 * 个人公众号 ardays
 */
public class TechnicianAnswerView extends LinearLayout {
    /**
     * 上下文
     */
    Context mContext;

    @BindView(R.id.technician_answer_tv)
    TextView mTechnicianAnswerTv;     //评论数量
    @BindView(R.id.technician_answer_content_rv)
    LoadMoreRecyclerView mTechnicianAnswerRv;   //评论列表
    @BindView(R.id.technician_answer_more_tv)
    View mMoreView;
    @BindView(R.id.technician_answer_content_not_view)
    CxxNotView mNotView;    //暂无提问的页面
    @BindView(R.id.technician_click_iv)
    View mClickView;


    TechnicianAnswerAdapter mTechnicianAnswerAdapter;   //评论适配器

    public TechnicianAnswerView(Context context) {
        this(context, null);
    }

    public TechnicianAnswerView(Context context, AttributeSet a) {
        this(context, a, 0);
    }

    public TechnicianAnswerView(Context context, AttributeSet a, int defStyleAttr) {
        super(context, a, defStyleAttr);
        this.mContext = context;
        init();
    }


    /**
     * 写入点击事件
     */
    public void setTechnicianAnswerListener(TechnicianAnswerAdapter.TechnicianAnswerAdapterListener listener) {
        mTechnicianAnswerAdapter.setTechnicianAnswerAdapterListener(listener);
    }


    /**
     * 写入点赞状态
     */
    public void setLikesStatus(boolean bol, int position){
        mTechnicianAnswerAdapter.setLikes(bol, position);
    }

    /**
     * 初始化
     */
    private void init() {
        View view = View.inflate(mContext, R.layout.view_technician_answer, this);
        ButterKnife.bind(this, view);

        mTechnicianAnswerRv.setLayoutManager(new LinearLayoutManager(mContext));
        mTechnicianAnswerAdapter = new TechnicianAnswerAdapter(mContext);
        mTechnicianAnswerRv.setAdapter(mTechnicianAnswerAdapter);
    }

    /**
     * 写入技师回答数
     */
    public void setAnswerNum(int num) {
        mTechnicianAnswerTv.setText("Ta的回答(已帮助" + num + "人)");
    }

    public void setAnswerNum(String num) {
        mTechnicianAnswerTv.setText("Ta的回答(已帮助" + num + "人)");
    }

    /**
     * 写入技师评论内容
     */
    public void setAnserContent(List<TechnicianDetailsBean.DataBean.WendaInfoBean> data) {
        mTechnicianAnswerAdapter.addData(data);
        if (mTechnicianAnswerAdapter.getSize() > 0){
            mNotView.setVisibility(GONE);
        }else{
            mNotView.setVisibility(VISIBLE);
        }
    }

    /**
     * 隐藏技师评论的更多按钮
     */
    public void setHiddenAnserbtn() {
        mMoreView.setVisibility(GONE);
        mClickView.setVisibility(INVISIBLE);
    }


    @OnClick(R.id.technician_answer_rl)
    void onItsAnswerClick(View v) {
        if (mTechnicianAnswerListener != null) mTechnicianAnswerListener.onItsAnswerClick(v);
    }

    @OnClick(R.id.technician_answer_more_tv)
    void onMoreClick(View v) {
        if (mTechnicianAnswerListener != null) mTechnicianAnswerListener.onMoreClick(v);
    }


    private TechnicianAnswerListener mTechnicianAnswerListener;

    public void setTechnicianAnswerListener(TechnicianAnswerListener listener) {
        this.mTechnicianAnswerListener = listener;
    }


    public interface TechnicianAnswerListener {
        /**
         * 点击TA的回答逻辑
         */
        void onItsAnswerClick(View view);

        /**
         * 点击查看更多逻辑
         */
        void onMoreClick(View view);
    }
}