package com.xi6666.store.custom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.carWash.base.view.CxxNotView;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.store.adapter.EvaluateAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建者 sunsun
 * 时间 16/11/8 上午11:33.
 * 个人公众号 ardays
 */
public class StoreDetailsEvaluateView extends LinearLayout {
    /**
     * 上下文
     */
    Context mContext;


    @BindView(R.id.store_details_evaluate_rv)
    RecyclerView mEvaluateRv;   //推荐评论
    @BindView(R.id.store_details_evaluate_num_tv)
    TextView mEvaluteNumTv;    //评论数量
    @BindView(R.id.store_details_evaluate_star_eb)
    EvaluateBar mEvaluateStarEb;//星星评价
    @BindView(R.id.store_details_evaluate_star_tv)
    TextView mEvaluateStarTv;   //总评分
    @BindView(R.id.view_store_details_evaluate_more_tv)
    View mMoreTv;   //更多
    @BindView(R.id.store_details_not_tv)
    CxxNotView mCxxNotView; //显示数据为空
    @BindView(R.id.store_details_evaluate_not_tv)
    TextView mEvaluateNotTv;    //你来成为第一个评论的人

    boolean isShownNotTv = true;


    private EvaluateAdapter mEvaluateAdapter;   //车主评价列表适配器

    public StoreDetailsEvaluateView(Context context) {
        this(context, null);
    }

    public StoreDetailsEvaluateView(Context context, AttributeSet a) {
        this(context, a, 0);
    }

    public StoreDetailsEvaluateView(Context context, AttributeSet a, int defStyleAttr) {
        super(context, a, defStyleAttr);
        this.mContext = context;
        init();
    }


    /**
     * 初始化
     */
    private void init() {
        View view = View.inflate(mContext, R.layout.view_store_details_evaluate, this);
        ButterKnife.bind(this, view);

        initRecommendEvaluate();
    }

    /**
     * 初始化推荐界面
     */
    private void initRecommendEvaluate() {
        mEvaluateRv.setLayoutManager(new LinearLayoutManager(mContext));
        mEvaluateAdapter = new EvaluateAdapter(mContext);
        mEvaluateRv.setAdapter(mEvaluateAdapter);

        mEvaluateAdapter.setOnEvaluateAdapterListener(new EvaluateAdapter.OnEvaluateAdapterListener() {
            @Override
            public void OnLikesClick(boolean likes, int position, String discuss_id) {
                if (mOnStoreDetailsEvaluateListener != null)
                    mOnStoreDetailsEvaluateListener.onLikesClick(likes, position, discuss_id);
            }
        });

    }


    /**
     * 添加评价
     */
    public void setCommentLv(List<StoreDetailsBean.DataBean.DiscussinfoBean> data) {
        mEvaluateAdapter.updata(data);
        if (data.size() == 0 && isShownNotTv) {
            mMoreTv.setVisibility(GONE);
            mCxxNotView.setVisibility(VISIBLE);
        } else {
            mMoreTv.setVisibility(VISIBLE);
            mCxxNotView.setVisibility(GONE);
        }
    }

    /**
     * 写入门店评价数据
     */
    public void setCommentData(StoreDetailsBean.DataBean.StoreInfo data) {
        //写入门店评价总量
        mEvaluteNumTv.setText(mContext.getString(R.string.store_details_comment_sum, data.sum));
        //写入星星数量
        mEvaluateStarEb.setStarMark(data.service_score);
        if (data.service_score > 0.0) {
            //写入评分
            mEvaluateStarTv.setText(data.service_score + "分");
        } else {
            mEvaluateStarTv.setVisibility(GONE);
            mEvaluateStarEb.setVisibility(GONE);
            mEvaluateNotTv.setVisibility(VISIBLE);
            mCxxNotView.setVisibility(GONE);
            isShownNotTv = false;
        }


    }


    @OnClick(R.id.view_store_details_evaluate_details_rl)
    void onDetailsClick(View view) {
        //点击车主评价
        if (mOnStoreDetailsEvaluateListener != null)
            mOnStoreDetailsEvaluateListener.onDetailsClick(view);
    }


    @OnClick(R.id.view_store_details_evaluate_more_tv)
    void onMoreClick(View view) {
        //点击更多评价
        if (mOnStoreDetailsEvaluateListener != null)
            mOnStoreDetailsEvaluateListener.onMoreClick(view);
    }


    //                              @代理方法

    public OnStoreDetailsEvaluateListener mOnStoreDetailsEvaluateListener;

    public void setOnStoreDetailsEvaluateListener(OnStoreDetailsEvaluateListener listener) {
        this.mOnStoreDetailsEvaluateListener = listener;
    }

    public void setLikes(boolean bol, int position) {
        mEvaluateAdapter.setLikes(bol, position);
    }

    public interface OnStoreDetailsEvaluateListener {
        /**
         * 点击车主评价条目
         */
        void onDetailsClick(View view);

        /**
         * 点击更多评价
         */
        void onMoreClick(View view);

        /**
         * 赞
         *
         * @param likes
         * @param position
         * @param discuss_id
         */
        void onLikesClick(boolean likes, int position, String discuss_id);
    }


}