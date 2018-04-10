package com.xi6666.technician.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.technician.adapter.TechnicianAnswerAdapter;
import com.xi6666.technician.mvp.ItsAnswerContract;
import com.xi6666.technician.mvp.ItsAnswerModel;
import com.xi6666.technician.mvp.ItsAnswerPresenter;
import com.xi6666.technician.mvp.bean.ItsAnswerBean;
import com.xi6666.technician.view.custom.TechnicianAnswerView;

/**
 * 创建者 sunsun
 * 时间 16/11/9 下午8:29.
 * 个人公众号 ardays
 */

public class ItsAnswerAct extends BaseToolbarView<ItsAnswerPresenter, ItsAnswerModel>
        implements ItsAnswerContract.View {
    @Override
    public String title() {
        return "Ta的问答";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_its_answer;
    }

    TechnicianAnswerView mItsAnswerTav;     //列表

    int PAGE = 1;       //页数
    String mJsUserId = ""; //技师ID

    @Override
    public void setUp(View view) {
        initView(view);
        initValue();
        mPersenter.reuqestAnswerList(mJsUserId, PAGE);
    }


    /**
     * 初始化绑定
     */
    private void initView(View view) {
        mItsAnswerTav = (TechnicianAnswerView) view.findViewById(R.id.its_answer_tav);

        mItsAnswerTav.setTechnicianAnswerListener(
                new TechnicianAnswerAdapter.TechnicianAnswerAdapterListener() {
                    /**
                     * 点赞
                     */
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
        mJsUserId = getIntent().getStringExtra(JS_USER_ID);
    }


    //                          @网络数据返回
    @Override
    public void returnAnswerList(ItsAnswerBean bean) {

        if (bean.success) {
            PAGE++;
            mItsAnswerTav.setAnswerNum(bean.data.total);  //写入评论量
            mItsAnswerTav.setAnserContent(bean.data.list);  //写入评论
            mItsAnswerTav.setHiddenAnserbtn();            //隐藏技师更多的按钮
        } else {
            make(bean.info);
        }
    }

    @Override
    public void returnLikeResult(boolean bol, int position, BaseBean bean) {
        make(bean.info);
        if (bean.success) mItsAnswerTav.setLikesStatus(bol, position);
    }


    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Context activity, String js_user_id) {
        Intent intent = new Intent(activity, ItsAnswerAct.class);
        intent.putExtra(JS_USER_ID, js_user_id);
        activity.startActivity(intent);
    }

    private static final String JS_USER_ID = "com.xi6666.js_user_id";   //技师ID
}
