package com.xi6666.illegal.Activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.xi6666.R;
import com.xi6666.app.baset.BaseTAct;
import com.xi6666.common.UserData;
import com.xi6666.databean.IllegaHomeListBean;
import com.xi6666.eventbus.IllegaQuerySuccessEvent;
import com.xi6666.eventbus.LoginEvent;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.illegal.Adapter.IllegaHomeAdapter;
import com.xi6666.illegal.other.IllegaBannerHolderView;
import com.xi6666.illegal.other.IllegaHomeContract;
import com.xi6666.illegal.other.IllegaHomeModel;
import com.xi6666.illegal.other.IllegaHomePresenter;
import com.xi6666.login.view.LoginAct;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.DimenUtils;
import com.xi6666.view.CompatToolbar;
import com.xi6666.view.EmptyLayout;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author peng
 * @data 创建时间:2017/2/7
 * @desc 违章查询主页面
 */
public class IllegalHomeAct extends BaseTAct<IllegaHomePresenter,
        IllegaHomeModel> implements IllegaHomeContract.View,
        XRecyclerView.LoadingListener, OnItemClickListener {
    @BindView(R.id.xrc_illegalhome)
    XRecyclerView mXrcIllegalhome;
    @BindView(R.id.el_illegalhome)
    EmptyLayout mElIllegalhome;
    @BindView(R.id.btn_illegalhome)
    TextView mBtnIllegalhome;
    @BindView(R.id.txt_illegalhome_empty)
    TextView mTxtEmpty;
    @BindView(R.id.txt_basetoolbar_left)
    TextView mTxtBasetoolbarLeft;
    @BindView(R.id.txt_basetoolbar_title)
    TextView mTxtBasetoolbarTitle;
    @BindView(R.id.txt_basetoolbar_right)
    TextView mTxtBasetoolbarRight;
    @BindView(R.id.base_tb)
    CompatToolbar mBaseTb;

    private List<IllegaHomeListBean.DataBean> mListData = new ArrayList<>();
    private ArrayList<IllegaHomeListBean.BannerBean> mBannerBeen = new ArrayList<>();


    private IllegaHomeAdapter mIllegaHomeAdapter;
    private int mPage = 1;
    private ConvenientBanner mBanner;

    @Override
    public int getLayoutId() {
        return R.layout.activity_illgal_home;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void querySuccess(IllegaQuerySuccessEvent illegaQuerySuccessEvent) {
        mPage = 1;
        mPresenter.getIllageHomeList(UserData.getUserId(), UserData.getUserToken(), mPage + "");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginSuccess(LoginEvent loginEvent) {
        mPage = 1;
        mPresenter.getIllageHomeList(UserData.getUserId(), UserData.getUserToken(), mPage + "");
    }

    @Override
    public void init() {
        EventBus.getDefault().register(this);
        //tollbar设置
        mTxtBasetoolbarLeft.setVisibility(View.GONE);
        mTxtBasetoolbarRight.setVisibility(View.GONE);
        mTxtBasetoolbarTitle.setText("违章查询");
        mBaseTb.setNavigationIcon(R.drawable.ic_back);
        mBaseTb.setNavigationOnClickListener(v -> {
            finish();
        });
        //错误按钮
        mElIllegalhome.setErrorButtonClickListener(v -> {
            mPresenter.getIllageHomeList(UserData.getUserId(), UserData.getUserToken(), mPage + "");
        });
        //头部banner
        mBanner = new ConvenientBanner(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.height = DimenUtils.dip2px(this, 153);
        mBanner.setLayoutParams(layoutParams);
        mBanner.setBackgroundResource(R.drawable.bg_home_banner_default);
        mXrcIllegalhome.addHeaderView(mBanner);

        //列表
        mXrcIllegalhome.setLayoutManager(new LinearLayoutManager(this));
        mIllegaHomeAdapter = new IllegaHomeAdapter();
        mIllegaHomeAdapter.setListData(mListData);
        //点击
        mIllegaHomeAdapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(this, IllegalFindResultAct.class);
            intent.putExtra("userid", UserData.getUserId());
            intent.putExtra("car_no", mListData.get(position).getCar_no());
            intent.putExtra("city_code", mListData.get(position).getCity_code());
            intent.putExtra("engineno", mListData.get(position).getEngineno());
            intent.putExtra("classno", mListData.get(position).getClassno());
            intent.putExtra("province_code", mListData.get(position).getProvince_code());
            intent.putExtra("query_id", mListData.get(position).getQuery_id());
            startActivity(intent);
        });
        //侧滑
        mIllegaHomeAdapter.setOnItemSwipeDeleteListnner(position -> {
            mPresenter.deleteList(mListData.get(position).getCar_no(),
                    mListData.get(position).getCity_code(),
                    mListData.get(position).getProvince_code(), UserData.getUserId());

        });
        mXrcIllegalhome.setAdapter(mIllegaHomeAdapter);
        mXrcIllegalhome.setLoadingListener(this);
        mPresenter.getIllageHomeList(UserData.getUserId(), UserData.getUserToken(), mPage + "");
    }

    @Override
    public void showLoading() {
        mElIllegalhome.showLoading();
    }

    @Override
    public void showError() {
        mElIllegalhome.showError();
    }

    @Override
    public void hideEmptyLayout() {
        mElIllegalhome.hide();
    }

    @Override
    public void showOrCloseEmpty(boolean flag) {
        if (flag) {
            mTxtEmpty.setVisibility(View.VISIBLE);
        } else {
            mTxtEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public void setListData(List<IllegaHomeListBean.DataBean> data) {
        if (mPage == 1) {
            mListData.clear();
        }
        mListData.addAll(data);
        mIllegaHomeAdapter.setListData(mListData);
    }

    @Override
    public void refreshFinish() {
        mXrcIllegalhome.refreshComplete();
    }

    @Override
    public void loadMoreFinish() {
        mXrcIllegalhome.loadMoreComplete();
    }

    @Override
    public void hasMoreData(boolean flag) {
        mXrcIllegalhome.setNoMore(!flag);
    }

    @Override
    public void setBanner(List<IllegaHomeListBean.BannerBean> bannerData) {
        mBannerBeen.clear();
        mBannerBeen.addAll(bannerData);

        mBanner.setPages(new CBViewHolderCreator<IllegaBannerHolderView>() {
            @Override
            public IllegaBannerHolderView createHolder() {
                return new IllegaBannerHolderView(IllegalHomeAct.this);
            }
        }, mBannerBeen).setOnItemClickListener(this);
        if (mBannerBeen.size() > 1) {
            mBanner.setCanLoop(true);
            mBanner.setPointViewVisible(true)    //设置指示器是否可见
                    .setPageIndicator(new int[]{R.drawable.enablefalse, R.drawable.enabletrue})   //设置指示器圆点
                    .startTurning(5000);     //设置自动切换（同时设置了切换时间间隔）
        }

    }

    @Override
    public void refreshList() {
        mPage = 1;
        mPresenter.getIllageHomeList(UserData.getUserId(), UserData.getUserToken(), mPage + "");
    }

    @OnClick({R.id.btn_illegalhome})
    public void viewOnclick(View view) {
        switch (view.getId()) {
            //添加查询按钮
            case R.id.btn_illegalhome:
                if (TextUtils.isEmpty(UserData.getUserId())) {
                    // 登录
                    startActivity(new Intent(this, LoginAct.class));
                } else {
                    startActivity(new Intent(this, IllegalFindActivity.class));
                }
                break;
        }
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        mPresenter.getIllageHomeList(UserData.getUserId(), UserData.getUserToken(), mPage + "");
    }

    @Override
    public void onLoadMore() {
        mPage++;
        mPresenter.getIllageHomeList(UserData.getUserId(), UserData.getUserToken(), mPage + "");
    }

    @Override
    public void onItemClick(int position) {
        HtmlAct.unsealActivity(this, mBannerBeen.get(position).getLink() + "?get_device_type=android" + "&user_id=" + UserData.getUserId()
                + "&user_token=" + UserData.getUserToken());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
