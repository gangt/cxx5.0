package com.xi6666.carWash.view;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.carWash.adapter.CarWashSearchAdapter;
import com.xi6666.carWash.view.custom.BaseSearchToolbarView;
import com.xi6666.carWash.view.mvp.CarWashSearchContract;
import com.xi6666.carWash.view.mvp.CarWashSearchModel;
import com.xi6666.carWash.view.mvp.CarWashSearchPresenter;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/3 下午4:06.
 * 个人公众号 ardays
 */

public class CarWashSearchAct extends BaseSearchToolbarView<CarWashSearchPresenter, CarWashSearchModel>
        implements CarWashSearchContract.View {

    View mHistoryLl;   //有历史记录的布局
    View mHistoryNullLl;//历史记录为空的布局
    ListView mHistoryItemLv;//历史记录的item
    TextView mHistoryClearBtn; //清除历史记录

    CarWashSearchAdapter mHistoryAdapter;   //历史记录适配器


    @Override
    public String searchTitle() {
        return "";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_carwash_search;
    }

    @Override
    public void setUp(View view) {
        //设置左边的按钮
        setLeftIcon(R.mipmap.ic_query_gray);
        //写入
        setSearchHint("请输入商家名称");
        //设置搜索能点击
        setSearchClicked(true);
        initView(view);
        initHistoryView();
        initClick();
    }


    /**
     * 初始化View
     */
    private void initView(View view) {
        mHistoryLl = view.findViewById(R.id.carWash_history_ll);
        mHistoryNullLl = view.findViewById(R.id.carWash_history_nil_ll);
        mHistoryItemLv = (ListView) view.findViewById(R.id.carWash_history_lv);
        mHistoryClearBtn = (TextView) view.findViewById(R.id.carWash_clear_history_btn);
    }

    /**
     * 初始化历史记录
     */
    private void initHistoryView() {
        mHistoryAdapter = new CarWashSearchAdapter(this);
        mHistoryItemLv.setAdapter(mHistoryAdapter);
    }


    /**
     * 初始化点击事件
     */
    private void initClick() {
        //清除历史记录点击事件
        mHistoryClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearHistory();
            }
        });

        //历史记录点击事件
        mHistoryAdapter.setOnCarWashItemHistoryListener(new CarWashSearchAdapter.OnCarWashItemHistoryListener() {
            @Override
            public void onHistoryItemClick(String keyWord) {
                clickSearch(keyWord);
            }
        });
    }


    @Override
    public void clickSearch(String keyWord) {
        super.clickSearch(keyWord);
        if (TextUtils.isEmpty(keyWord)) {
            make("请输入商家名称");
            return;
        }
        CarWashSearchResultAct.openActivity(this, keyWord);
    }


    /*                  @历史记录的逻辑条件              */
    @Override
    public void historyData(List<String> historyDatas) {
        super.historyData(historyDatas);
        Log.d("historyData", "historyData:--> " + historyDatas.toString());
        if (historyDatas.size() == 0) {
            //没有历史记录
            mHistoryLl.setVisibility(View.GONE);
            mHistoryNullLl.setVisibility(View.VISIBLE);
        } else {
            mHistoryLl.setVisibility(View.VISIBLE);
            mHistoryNullLl.setVisibility(View.GONE);
        }

        //更新
        mHistoryAdapter.update(historyDatas);
    }


    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity) {
        Intent intent = new Intent(activity, CarWashSearchAct.class);
        activity.startActivity(intent);
    }
}
