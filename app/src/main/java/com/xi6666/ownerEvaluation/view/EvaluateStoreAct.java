package com.xi6666.ownerEvaluation.view;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.store.custom.EvaluateBar;

/**
 * 创建者 sunsun
 * 时间 16/11/10 下午5:30.
 * 个人公众号 ardays
 */

public class EvaluateStoreAct extends BaseToolbarView {

    //门店服务项目
    private final String[] mServiceTypeStr = {"洗车", "美容", "保养", "推送处理"};


    TabLayout mEvaluateStoreTab;   //选择门店服务类型Tab
    EvaluateBar mEvaluateStoreStarEb;   //评分(星星)
    TextView  mEvaluateStoreStarTv; //评分（文字)

    @Override
    public void uiCreate() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
            WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.uiCreate();
    }

    @Override
    public String title() {
        return "评价门店";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_evaluate_store;
    }

    @Override
    public void setUp(View view) {
        initView(view);
        initToolbar();
        initSelStore();
        initStar();
    }
    /**
     * 初始化控件
     */
    private void initView(View view) {
//        mEvaluateStoreTab = (TabLayout) view.findViewById(R.id.evaluate_store_tab);
        mEvaluateStoreStarEb = (EvaluateBar) view.findViewById(R.id.evaluate_store_star_eb);
        mEvaluateStoreStarTv = (TextView) view.findViewById(R.id.evaluate_store_star_tv);
    }


    /**
     * 初始化标题栏
     */
    private void initToolbar(){
        setToolbarRightText("提交");
    }

    /**
     *  选择门店服务
     */
    private void initSelStore() {
        for(String str: mServiceTypeStr){
            TabLayout.Tab tab = mEvaluateStoreTab.newTab().setText(str);
            mEvaluateStoreTab.addTab(tab);
        }
    }

    /**
     * 初始化星星
     */
    private void initStar() {
        /**
         * 监听星星滚动
         */
        mEvaluateStoreStarEb.setOnStarChangeListener(new EvaluateBar.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {
                //把星星拖动的值赋值在上面
                mEvaluateStoreStarTv.setText(mark + "分");
            }
        });
    }




    @Override
    public void onToolbarRightClick(View view) {
        super.onToolbarRightClick(view);
        make("你点击了提交");
    }

    /**
     *  BY:记得注册Activity
     */
    public static final void openActivity(Activity activity){
        Intent intent = new Intent(activity,EvaluateStoreAct.class);
        activity.startActivity(intent);
    }
}
