package com.xi6666.cardbag.view.oilcard;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.cardbag.view.mvp.OilCardNotAlreadyContract;
import com.xi6666.cardbag.view.mvp.OilCardNotAlreadyModel;
import com.xi6666.cardbag.view.mvp.OilCardNotAlreadyPresenter;
import com.xi6666.cardbag.view.mvp.bean.OilCardNotAlreadyBean;
import com.xi6666.cardbag.view.oilcard.adapter.OilCardNotAlreadyAdapter;

/**
 * 创建者 sunsun
 * 时间 16/11/24 上午10:17.
 * 个人公众号 ardays
 */

public class OilCardNotAlreadyAct extends BaseToolbarView<OilCardNotAlreadyPresenter, OilCardNotAlreadyModel> implements OilCardNotAlreadyContract.View {
    @Override
    public String title() {
        return "未到账明细";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_oil_card_not_already;
    }

    private RecyclerView mNotAlreadyRv;     //未到账列表
    private OilCardNotAlreadyAdapter mNotAlreadyAdapter;    //未到账适配器

    @Override
    public void setUp(View view) {
        initView(view);
        initNotAlreaday();
    }

    /**
     * 初始化绑定View
     */
    private void initView(View view) {
        mNotAlreadyRv = (RecyclerView) view.findViewById(R.id.oil_card_not_already_rv);
    }

    /**
     * 初始化未到账记录
     */
    private void initNotAlreaday() {
        mNotAlreadyRv.setLayoutManager(new LinearLayoutManager(this));
        mNotAlreadyAdapter = new OilCardNotAlreadyAdapter(this);
        mNotAlreadyRv.setAdapter(mNotAlreadyAdapter);
        mPersenter.requestNotAlready(getIntent().getStringExtra(CARD_ID));
    }

    /*              数据返回            */
    @Override
    public void returnNotAlready(OilCardNotAlreadyBean bean) {
        mNotAlreadyAdapter.addDatas(bean.data);
    }

    /**
     *  BY:记得注册Activity
     */
    public static final void openActivity(Activity activity, String card_id){
        Intent intent = new Intent(activity,OilCardNotAlreadyAct.class);
        intent.putExtra(CARD_ID, card_id);
        activity.startActivity(intent);
    }
    private static final String CARD_ID = "com.xi6666.card_id";
}
