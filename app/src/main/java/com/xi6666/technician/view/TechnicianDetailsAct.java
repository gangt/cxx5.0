package com.xi6666.technician.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.technician.adapter.TechnicianAnswerAdapter;
import com.xi6666.technician.mvp.TechnicianDetailsContract;
import com.xi6666.technician.mvp.TechnicianDetailsModel;
import com.xi6666.technician.mvp.TechnicianDetailsPresenter;
import com.xi6666.technician.mvp.bean.ItsAnswerBean;
import com.xi6666.technician.mvp.bean.TechnicianDetailsBean;
import com.xi6666.technician.view.custom.QuestionsTechnicianView;
import com.xi6666.technician.view.custom.TechnicianAnswerView;
import com.xi6666.technician.view.custom.TechnicianProfileView;

/**
 * 创建者 sunsun
 * 时间 16/11/9 上午11:13.
 * 个人公众号 ardays
 */

public class TechnicianDetailsAct extends BaseToolbarView<TechnicianDetailsPresenter, TechnicianDetailsModel>
        implements TechnicianDetailsContract.View {

    @Override
    public String title() {
        return "";
    }

    @Override
    public int mainResId() {
        return R.layout.view_technician_details;
    }

    TechnicianProfileView mTechnicianTpv;       //技师简介
    QuestionsTechnicianView mTechnicianQtv;     //提问问题
    TechnicianAnswerView mTechnicianTav;        //Ta的回答


    String mTechnicianId;    //技师ID

    @Override
    public void setUp(View view) {
        initView(view);
        initValue();
        initAsk();
        showLoading();
        mPersenter.requestTechnicianDetails(mTechnicianId);
    }

    /**
     * 初始化评论模块
     */
    private void initAsk() {
        mTechnicianQtv.setOnAskListener(v ->{
            AskHimQuestionsAct.openActivity(this,mTechnicianId);
        });
    }

    /**
     * 初始化绑定控件
     */
    private void initView(View view) {
        mTechnicianTpv = (TechnicianProfileView) view.findViewById(R.id.view_technician_details_tpv);
        mTechnicianQtv = (QuestionsTechnicianView) view.findViewById(R.id.view_technician_details_qtv);
        mTechnicianTav = (TechnicianAnswerView) view.findViewById(R.id.view_technician_details_tav);

        mTechnicianTav.setTechnicianAnswerListener(new TechnicianAnswerView.TechnicianAnswerListener() {
            @Override
            public void onItsAnswerClick(View view) {
                ItsAnswerAct.openActivity(getActivity(), mTechnicianId);
            }

            @Override
            public void onMoreClick(View view) {
                ItsAnswerAct.openActivity(getActivity(), mTechnicianId);
            }
        });

        mTechnicianTav.setTechnicianAnswerListener(new TechnicianAnswerAdapter.TechnicianAnswerAdapterListener() {
            @Override
            public void onLikesClick(boolean likes, String ques_id, int position) {
                mPersenter.requestLikesClick(likes, ques_id, position);
            }
        });
    }

    /**
     * 初始化值
     */
    private void initValue() {
        mTechnicianId = getIntent().getStringExtra(TECHNICIAN_ID);
    }

    @Override
    public void returnTechnicianDetails(TechnicianDetailsBean bean) {
        hiddenLoading();
        if (bean.success) {
            TechnicianDetailsBean.DataBean data = bean.data;
            //写入技师头像，名字，简介
            mTechnicianTpv.setData(data.user_face, data.user_truename, data.js_desc);
            //写入帮助数
            mTechnicianTav.setAnswerNum(data.wenda_count);
            //写入帮助内容
            mTechnicianTav.setAnserContent(data.wenda_info);
            //隐藏
            mTechnicianTav.setHiddenAnserbtn();
        }else{
            make(bean.info);
        }
    }

    @Override
    public void returnLikeResult(boolean bol, int position, BaseBean bean) {
        make(bean.info);
        if(bean.success)
        mTechnicianTav.setLikesStatus(bol, position);
    }

    @Override
    public void returnError(String error) {
        hiddenLoading();
        make(error);
    }


    /**
     * BY:记得注册Activity
     *
     * @param technician_id 技师ID
     */
    public static final void openActivity(Activity activity, String technician_id) {
        Intent intent = new Intent(activity, TechnicianDetailsAct.class);
        intent.putExtra(TECHNICIAN_ID, technician_id);
        activity.startActivity(intent);
    }

    private static final String TECHNICIAN_ID = "com.xi6666.technician_id"; //技师ID

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == AskHimQuestionsAct.REQUEST_CODE){
            showLoading();
            //监听添加成功评论返回
            mPersenter.requestTechnicianDetails(mTechnicianId);
        }
    }
}
