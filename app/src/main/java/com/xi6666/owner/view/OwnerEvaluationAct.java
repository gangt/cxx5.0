package com.xi6666.owner.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.order.other.Utils;
import com.xi6666.owner.mvp.bean.OwnerEvaluationBean;
import com.xi6666.owner.mvp.OwnerEvaluationContract;
import com.xi6666.owner.mvp.OwnerEvaluationModel;
import com.xi6666.owner.mvp.OwnerEvaluationPresenter;
import com.xi6666.store.adapter.EvaluateAdapter;
import com.xi6666.store.custom.EvaluateBar;
import com.xi6666.view.custom.EvaluationTypeView;
import com.xi6666.view.superrecyclerview.XRecyclerView;


/**
 * 创建者 sunsun
 * 时间 16/11/10 下午12:01.
 * 个人公众号 ardays
 */

public class OwnerEvaluationAct extends BaseToolbarView<OwnerEvaluationPresenter, OwnerEvaluationModel>
        implements OwnerEvaluationContract.View {


    EvaluationTypeView mEvaluationEtv; //评价
    XRecyclerView mEvaluationXrv;   //评论列表
    EvaluateAdapter mEvaluateAdapter;   //评论适配器
    View mEvaluateNotLL;        //无评论列表
    EvaluateBar mEvaluateEb;        //评分(星星)
    TextView mEvaluateTv;     //评分(文本)


    int PAGE = 1;   //分页
    int EVALUATION_TYPE = 0;   //评价分类
    String mShopId;        //门店ID
    String mShopUserId;

    @Override
    public String title() {
        return "车主评价";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_owner_evaluation;
    }

    @Override
    public void setUp(View view) {
        initView(view);
        initValue();
        initDetails();
        showLoading();
        mPersenter.requestEvaluationList(EVALUATION_TYPE, PAGE, mShopId);
    }


    //初始化View
    private void initView(View view) {
        mEvaluationEtv = (EvaluationTypeView) view.findViewById(R.id.owner_evaluation_type_etv);
        mEvaluationXrv = (XRecyclerView) view.findViewById(R.id.owner_evaluation_evaluation_xrv);
        mEvaluateEb = (EvaluateBar) view.findViewById(R.id.owner_evaluation_type_fen_eb);
        mEvaluateTv = (TextView) view.findViewById(R.id.owner_evaluation_type_fen_tv);
        mEvaluateNotLL = view.findViewById(R.id.owner_evaluation_evaluation_not_ll);

        mEvaluationXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE = 1;
                mPersenter.requestEvaluationList(EVALUATION_TYPE, PAGE, mShopId);
            }

            @Override
            public void onLoadMore() {
                PAGE++;
                mPersenter.requestEvaluationList(EVALUATION_TYPE, PAGE, mShopId);
            }
        });
    }


    /**
     * 初始化值
     */
    private void initValue() {
        mShopId = getIntent().getStringExtra(SHOP_ID);
        mShopUserId = getIntent().getStringExtra(SHOP_ID);
    }


    /**
     * 初始化门店评价信息
     */
    private void initDetails() {

        /**
         * 监听评价分类点击事件
         */
        mEvaluationEtv.setOnEvaluationTypeViewListener(new EvaluationTypeView.OnEvaluationTypeViewListener() {
            @Override
            public void onSelectType(int type) {
                switch (type) {
                    case 0:
                        EVALUATION_TYPE = 0;
                        break;
                    case 1:
                        EVALUATION_TYPE = 1;
                        break;
                    case 2:
                        EVALUATION_TYPE = 2;
                        break;
                    case 3:
                        EVALUATION_TYPE = 3;
                        break;
                }
                PAGE = 1;
                showLoading();
                mPersenter.requestEvaluationList(EVALUATION_TYPE, PAGE, mShopId);
            }
        });


        mEvaluateAdapter = new EvaluateAdapter(this);
        mEvaluationXrv.setLayoutManager(new LinearLayoutManager(this));
        mEvaluationXrv.setAdapter(mEvaluateAdapter);

        //点赞回调
        mEvaluateAdapter.setOnEvaluateAdapterListener(new EvaluateAdapter.OnEvaluateAdapterListener() {
            @Override
            public void OnLikesClick(boolean likes, int position, String discuss_id) {
                mPersenter.requestLikesClick(likes, discuss_id, position, mShopUserId);
            }
        });
    }


    @Override
    public void returnOwnerEvaluationList(OwnerEvaluationBean bean) {
        hiddenLoading();    //隐藏
        mEvaluationXrv.refreshComplete();   //刷新成功
        if (bean.success) {
            if (PAGE == 1) {
                if (bean.data.discuss.size() == 0) {
                    mEvaluateNotLL.setVisibility(View.VISIBLE);
                    mEvaluationXrv.setVisibility(View.GONE);
                } else {
                    mEvaluationXrv.setVisibility(View.VISIBLE);
                    mEvaluateNotLL.setVisibility(View.GONE);
                }
                //写入数据
                mEvaluateAdapter.updata(bean.data.discuss);
                //写入赞的数量
                mEvaluationEtv.setData(bean.data.pingjia);
                //写入评分
                String score = Utils.getScore(bean.data.scoreshop.comment_level);
                mEvaluateEb.setStarMark(Float.parseFloat(score));
                if (!score.equals("0.0")) {
                    mEvaluateTv.setVisibility(View.VISIBLE);
                    mEvaluateTv.setText(bean.data.scoreshop.comment_level + "分");
                } else {
                    mEvaluateTv.setVisibility(View.GONE);
                }
            } else {
                //写入数据
                mEvaluateAdapter.addData(bean.data.discuss);
            }
        } else {
            make(bean.info);
        }
    }

    @Override
    public void returnLikeResult(boolean bol, int position, BaseBean bean) {
        make(bean.info);
        if (bean.success) mEvaluateAdapter.setLikes(bol, position);
    }


    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity, String shop_id, String shop_user_id) {
        Intent intent = new Intent(activity, OwnerEvaluationAct.class);
        intent.putExtra(SHOP_ID, shop_id);
        intent.putExtra(SHOP_USER_ID, shop_user_id);
        activity.startActivity(intent);
    }

    private static final String SHOP_ID = "com.xi6666.shop_id"; //门店ID
    private static final String SHOP_USER_ID = "com.xi6666.shop_user_id"; //门店UserID
}
