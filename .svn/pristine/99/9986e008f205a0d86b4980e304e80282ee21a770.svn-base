package com.xi6666.cardbag.view.oilcard;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.addoil.view.AddoOilAct;
import com.xi6666.app.baset.BaseTAct;
import com.xi6666.cardbag.view.mvp.OilRechargeDetailsContract;
import com.xi6666.cardbag.view.mvp.OilRechargeDetailsModel;
import com.xi6666.cardbag.view.mvp.OilRechargeDetailsPresenter;
import com.xi6666.cardbag.view.mvp.bean.OilCardDeleteBean;
import com.xi6666.cardbag.view.oilcard.adapter.OilRechargeDetialAdapter;
import com.xi6666.cardbag.view.oilcard.adapter.RechargeRecordAdapter;
import com.xi6666.common.Constant;
import com.xi6666.common.UserData;
import com.xi6666.databean.RechargeDetialBean;
import com.xi6666.databean.RechargeListBean;
import com.xi6666.eventbus.ChoiceDefaultOilCardEvent;
import com.xi6666.utils.DimenUtils;
import com.xi6666.view.CompatToolbar;
import com.xi6666.view.MesureExpandListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.R.attr.type;

/**
 * 创建者 peng
 * 时间 16/11/23 下午3:55.
 * 个人公众号 ardays
 * 充值明细
 */

public class OilRechargeDetailsAct extends BaseTAct<OilRechargeDetailsPresenter, OilRechargeDetailsModel>
        implements OilRechargeDetailsContract.View {

    private static final String CARD_ID = "com.xi6666.card_id";

    @BindView(R.id.txt_basetoolbar_left)
    TextView mTxtBasetoolbarLeft;
    @BindView(R.id.txt_basetoolbar_title)
    TextView mTxtBasetoolbarTitle;
    @BindView(R.id.txt_basetoolbar_right)
    TextView mTxtBasetoolbarRight;
    @BindView(R.id.base_tb)
    CompatToolbar mBaseTb;
    @BindView(R.id.wdz_tv)
    TextView mWdzTv;
    @BindView(R.id.oil_recharge_w_details_moeny)
    TextView mOilRechargeWDetailsMoeny;
    @BindView(R.id.item_oil_recharge_details_ck)
    TextView mItemOilRechargeDetailsCk;
    @BindView(R.id.txt_ljcz)
    TextView mTxtLjcz;
    @BindView(R.id.oil_recharge_details_topay_btn)
    Button mOilRechargeDetailsTopayBtn;
    private int mPage = 1;
    private String mCardId;
    private int mType = 0;
    // private OilRechargeDetialAdapter mOilRechargeDetialAdapter;
   /* private List<RechargeDetialBean.DataBeanX.BackListBean> mDataBeen = new ArrayList<>();
    private List<RechargeDetialBean.DataBeanX.ShouldDataBean> mShouldDataBeen = new ArrayList<>();


    private RechargeRecordAdapter mRechargeRecordAdapter;
    private List<RechargeListBean.DataBeanX.DataBean> mRechargeList = new ArrayList<>();*/

    @OnClick({R.id.oil_recharge_details_topay_btn})
    public void viewOnclick(View view) {
        switch (view.getId()) {
            //去充值按钮
            case R.id.oil_recharge_details_topay_btn:
                mPresenter.requestDefaultOilCard(mCardId);
                break;
        }
    }

    public static void openActivity(Activity activity, String card) {
        Intent intent = new Intent(activity, OilRechargeDetailsAct.class);
        intent.putExtra(CARD_ID, card);
        activity.startActivity(intent);
    }

    public static void openActivity(Fragment activity, String card, int type) {
        Intent intent = new Intent(activity.getActivity(), OilRechargeDetailsAct.class);
        intent.putExtra(CARD_ID, card);
        intent.putExtra("type", type);
        activity.startActivityForResult(intent, AddOilCardAct.OIL_CARD_RESULT);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_oil_recharge_details;
    }

    @Override
    public void init() {
        if (getIntent() != null) {
            //界面展示
            mType = getIntent().getIntExtra("type", 0);
        }
        //获取加油卡ID
        mCardId = getIntent().getStringExtra(CARD_ID);

        //toolbar设置
        mTxtBasetoolbarLeft.setVisibility(View.GONE);
        mTxtBasetoolbarTitle.setText("充值明细");
        mTxtBasetoolbarRight.setVisibility(View.VISIBLE);
        mTxtBasetoolbarRight.setTextColor(getResources().getColor(R.color.themeColor));
        mBaseTb.setNavigationIcon(R.drawable.ic_back);
        mBaseTb.setNavigationOnClickListener(v -> {
            finish();
        });


        //加载数据
        mPresenter.getRechargeDetial(UserData.getUserId(), mCardId + "", UserData.getUserToken());
        mPresenter.getRechargeList(UserData.getUserId(), mCardId + "", mPage, UserData.getUserToken());
//arr

        //到账记录
        if (mType == 0) {

            Fragment fragment = ArrivalFragment.newInstance(mCardId);
            initView(fragment, "到账记录", "充值记录");

        }
        //充值记录
        if (mType == 1) {
            Fragment fragment = OilRecordFragment.newIntance(mCardId);

            initView(fragment, "充值记录", "到账记录");
        }

        mTxtBasetoolbarRight.setOnClickListener(v -> {
            if (mType == 0) {
                Fragment fragment = OilRecordFragment.newIntance(mCardId);
                initView(fragment, "充值记录", "到账记录");
                mType = 1;
            } else {
                Fragment fragment = ArrivalFragment.newInstance(mCardId);
                initView(fragment, "到账记录", "充值记录");
                mType = 0;

            }
        });


     /*   mElvChargeDetial.setGroupIndicator(null);
        mOilRechargeDetialAdapter = new OilRechargeDetialAdapter(this);
        mOilRechargeDetialAdapter.setDataBeen(mDataBeen, mShouldDataBeen);
        mElvChargeDetial.setAdapter(mOilRechargeDetialAdapter);
        //充值记录
        MesureExpandListView expandableListView = new MesureExpandListView(this);
        expandableListView.setGroupIndicator(null);
        mRechargeRecordAdapter = new RechargeRecordAdapter(this);
        mRechargeRecordAdapter.setDataBeen(mRechargeList);
        expandableListView.setAdapter(mRechargeRecordAdapter);
        mElvChargeDetial.addFooterView(expandableListView);
        //展开充值记录
        expandableListView.expandGroup(0);


        // 监听listview滚到最底部
        mElvChargeDetial.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                            mPage++;
                            mPresenter.getRechargeList(UserData.getUserId(), mCardId + "", mPage, UserData.getUserToken());


                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });*/
    }

    private void initView(Fragment fragment, String title, String right) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_oil_recharge, fragment);
        fragmentTransaction.commit();
        mTxtBasetoolbarTitle.setText(title);
        mTxtBasetoolbarRight.setText(right);
    }

    @Override
    public void setData(RechargeDetialBean rechargeDetialBean) {

       /* mOilRechargeDetialAdapter.setDataBeen(rechargeDetialBean.getData().getBack_list(), rechargeDetialBean.getData().getShould_data());
        for (int x = 1; x < rechargeDetialBean.getData().getBack_list().size(); x++) {
            if (rechargeDetialBean.getData().getBack_list().get(x - 1).getIs_show() == 1) {
                mElvChargeDetial.expandGroup(x);
            }
        }*/
    }

    @Override
    public void setMoney(String arrival, String total) {
        mOilRechargeWDetailsMoeny.setText(arrival);
        mTxtLjcz.setText(total);
        Spannable span = new SpannableString(mOilRechargeWDetailsMoeny.getText());
        span.setSpan(new AbsoluteSizeSpan(DimenUtils.sp2px(this, 13)), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mOilRechargeWDetailsMoeny.setText(span);
        Spannable span2 = new SpannableString(mTxtLjcz.getText());
        span2.setSpan(new AbsoluteSizeSpan(DimenUtils.sp2px(this, 13)), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTxtLjcz.setText(span2);
    }

    @Override
    public void setRechargeList(List<RechargeListBean.DataBeanX.DataBean> rechargeList) {
      /*  if (rechargeList.size() > 0) {
            mRechargeList.addAll(rechargeList);
            mRechargeRecordAdapter.setDataBeen(mRechargeList);
        }*/
    }

    @Override
    public void resultDefaultOiLCardStatus(OilCardDeleteBean bean) {
        setResult(RESULT_OK);
        if (TextUtils.isEmpty(Constant.fromAddOilH5)) {
            Intent intent = new Intent(this, AddoOilAct.class);
            startActivity(intent);
        } else {
            EventBus.getDefault().post(new ChoiceDefaultOilCardEvent("success"));
            finish();
        }
    }
}