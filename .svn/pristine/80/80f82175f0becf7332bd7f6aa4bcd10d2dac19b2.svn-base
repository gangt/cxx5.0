package com.xi6666.cardbag.view;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.app.ActManager;
import com.xi6666.carWash.base.BaseFrgm;
import com.xi6666.carWash.base.api.APIUrls;
import com.xi6666.carWash.base.api.Api;

import com.xi6666.cardbag.view.mvp.OilCardContract;
import com.xi6666.cardbag.view.mvp.OilCardModel;
import com.xi6666.cardbag.view.mvp.OilCardPresenter;
import com.xi6666.cardbag.view.mvp.bean.OilCardDeleteBean;
import com.xi6666.cardbag.view.mvp.bean.OilCardListBean;
import com.xi6666.cardbag.view.oilcard.AddOilCardAct;
import com.xi6666.cardbag.view.oilcard.OilRechargeDetailsAct;
import com.xi6666.cardbag.view.oilcard.adapter.MyOilCardListAdapter;
import com.xi6666.eventbus.CardNumEvent;
import com.xi6666.eventbus.ChoiceDefaultOilCardEvent;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.order.other.Utils;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author peng
 * @data 创建时间:2016/11/12
 * @desc 油卡界面
 */
public class AddOilCardFrgm extends BaseFrgm<OilCardPresenter, OilCardModel> implements OilCardContract.View {
    private static final String TAG = "AddOilCardFrgm";


    @BindView(R.id.oil_card_xrv)
    XRecyclerView mOilCardListXrv;
    @BindView(R.id.oil_card_not_rv)
    View mOidCardNotView;


    MyOilCardListAdapter mOilCardListAdapter;

    int PAGE = 1;


    //刷新地址
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void finiActi(ChoiceDefaultOilCardEvent addressEvent) {
        if (!addressEvent.getMsg().equals("change")) {
            ActManager.getAppManager().finishActivity(CardBagAct.class);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_addoilcard;
    }

    @Override
    protected void setUp() {
        EventBus.getDefault().register(this);
        initHttp();
        initOilCard();
    }


    /**
     * 初始化网络请求
     */
    private void initHttp() {
        mPersenter.requestOilCardList(PAGE);
    }

    /**
     * 初始化油卡列表
     */
    private void initOilCard() {
        mOilCardListXrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //适配器填写
        mOilCardListAdapter = new MyOilCardListAdapter(Glide.with(getActivity()), getActivity());
        mOilCardListXrv.setAdapter(mOilCardListAdapter);

        // 监听Adapter事件
        mOilCardListAdapter.setOnOilCardListener(new MyOilCardListAdapter.OnOilCardListener() {
            @Override
            public void onOilCardData(OilCardListBean.DataBean bean, int position) {
                //设置默认油卡
                mPersenter.requestDefaultOilCard(bean, position);
            }

            @Override
            public void onDeleteOilCard(OilCardListBean.DataBean bean, int position) {
                //删除文字
                String message = "确定要删除此油卡\n" + bean.card_number + "吗?";
                //删除事件


                //设置内容
                new AlertDialog.Builder(getContext())
                        .setTitle("删除提示")
                        .setMessage(message)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("删除", (d, v) -> {
                            mPersenter.deleteOilCard(bean, position);
                            showLoading();
                        })
                        .show();


//                .setOnDialogLeftClickListener(v -> mPersenter.deleteOilCard(bean, position))

            }


            @Override
            public void onItemClick(OilCardListBean.DataBean bean, int position) {
                //   OilRechargeDetailsAct.openActivity(getFragment(), bean.card_id);
            }

            //到账记录
            @Override
            public void onArrivalClick(OilCardListBean.DataBean bean, int position) {
                OilRechargeDetailsAct.openActivity(getFragment(), bean.card_id, 0);
            }

            //充值记录
            @Override
            public void onRechargeClick(OilCardListBean.DataBean bean, int position) {
                OilRechargeDetailsAct.openActivity(getFragment(), bean.card_id, 1);
            }

            @Override
            public void listNull() {
                mOidCardNotView.setVisibility(View.VISIBLE);
                mOilCardListXrv.setVisibility(View.GONE);
            }
        });

        // 监听油卡刷新事件
        mOilCardListXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE = 1;
                mPersenter.requestOilCardList(PAGE);
            }

            @Override
            public void onLoadMore() {
                PAGE++;
                mPersenter.requestOilCardList(PAGE++);
            }
        });
    }


    /**
     * 添加油卡
     */

    @OnClick(R.id.oil_card_add_btn)
    void onAddOilCardClick() {
        if (!isLogin()) {
            return;
        }
        AddOilCardAct.openActivity(this);
    }

    @OnClick(R.id.oil_card_error)
    void onErrorOilCardClick(View view) {
        HtmlAct.unsealActivity(getActivity(), Api.BASE_URL + APIUrls.HANDLE_OIL_CARD);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (AddOilCardAct.OIL_CARD_RESULT == requestCode) {    //刷新油卡列表
            PAGE = 1;
            mPersenter.requestOilCardList(PAGE);
            EventBus.getDefault().post(new CardNumEvent("true"));
        }
    }


    /*                  网络请求返回                  */
    @Override
    public void resultOilCardList(OilCardListBean res) {
        mOilCardListXrv.refreshComplete();
        if (res.success) {
            if (PAGE == 1) {
                if (Utils.isEmpty(res.data)) {
                    mOidCardNotView.setVisibility(View.VISIBLE);
                    mOilCardListXrv.setVisibility(View.GONE);
                } else {
                    mOidCardNotView.setVisibility(View.GONE);
                    mOilCardListXrv.setVisibility(View.VISIBLE);
                    mOilCardListAdapter.update(res.data);
                    //显示列表
                    Log.e(TAG, "resultOilCardList: ---> " + res.toString());
                }
            } else {
                mOilCardListAdapter.addDatas(res.data);
            }
        } else {
            make(res.info);
        }
    }

    @Override
    public void deleteOilCardStatus(OilCardDeleteBean bean, int position) {
        hiddenLoading();
        make(bean.info);
        if (bean.success) {
            EventBus.getDefault().post(new CardNumEvent("true"));
            EventBus.getDefault().post(new ChoiceDefaultOilCardEvent("change"));
            mOilCardListAdapter.remove(position);
        }
    }

    @Override
    public void resultDefaultOiLCardStatus(OilCardDeleteBean bean, int position) {
        mOilCardListXrv.refreshComplete();
        if (bean.success) {
            EventBus.getDefault().post(new ChoiceDefaultOilCardEvent("change"));
            mOilCardListAdapter.setDefaultOiLCard(position);
        } else {
            make(bean.info);
        }
    }

    @Override
    public void toastError(String error) {
        hiddenLoading();
        make(error);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
