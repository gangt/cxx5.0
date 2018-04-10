package com.xi6666.mine.view;


import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.xi6666.R;

import com.xi6666.app.BaseFrgm;
import com.xi6666.cardbag.view.CardBagAct;
import com.xi6666.common.CircleTransform;
import com.xi6666.common.UserData;
import com.xi6666.evaluate.activity.MyCollectionActivity;
import com.xi6666.evaluate.activity.MyEvaluateAndAnswerActivity;
import com.xi6666.eventbus.CardNumEvent;
import com.xi6666.eventbus.ChangeUserDataEvent;
import com.xi6666.eventbus.LoginEvent;
import com.xi6666.eventbus.PointerChangeEvent;
import com.xi6666.feedback.view.FeedBackAct;
import com.xi6666.happybeans.view.HappyBeansAct;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.login.view.LoginAct;
import com.xi6666.message.view.MessageAct;
import com.xi6666.mine.DaggerMineComponent;
import com.xi6666.mine.MineFunctionAdapter;
import com.xi6666.databean.UserDataBean;
import com.xi6666.mine.MineModule;
import com.xi6666.mine.contract.MineContract;
import com.xi6666.mine.presenter.MinePresenterImpl;
import com.xi6666.network.ApiRest;
import com.xi6666.order.activity.MyOrderListActivity;
import com.xi6666.order.activity.MyShoppingCartActivity;
import com.xi6666.setting.view.SettingAct;
import com.xi6666.userdata.view.UserDataAct;
import com.xi6666.view.MesureGrideView;
import com.xi6666.view.dialog.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

import static com.xi6666.R.id.view;


/**
 * Created by Mr_yang on 2016/10/18.
 */

public class MineFrgm extends BaseFrgm implements MineContract.View,
        AdapterView.OnItemClickListener {

    @BindView(R.id.iv_mine_setting)
    ImageView mIvMineSetting;
    @BindView(R.id.iv_mine_bell)
    ImageView mIvMineBell;
    @BindView(R.id.iv_mine_head)
    ImageView mIvMineHead;
    @BindView(R.id.btn_mine_sign)
    Button mBtnMineSign;
    @BindView(R.id.txt_mine_usename)
    TextView mTxtMineUsename;
    @BindView(R.id.txt_mine_nickname)
    TextView mTxtMineNickname;
    @BindView(R.id.rll_mine_users)
    RelativeLayout mRllMineUsers;
    @BindView(R.id.txt_mine_order_title)
    TextView mTxtMineOrderTitle;
    @BindView(R.id.txt_mine_order_goods)
    TextView mTxtMineOrderGoods;
    @BindView(R.id.txt_mine_order_clean)
    TextView mTxtMineOrderClean;
    @BindView(R.id.txt_mine_order_server)
    TextView mTxtMineOrderServer;
    @BindView(R.id.rll_mine_shoppingcar)
    RelativeLayout mRllMineShoppingcar;
    @BindView(R.id.mgv_mine)
    MesureGrideView mMgvMine;

    @Inject
    MinePresenterImpl mMinePresenter;
    private Dialog mDialog;
    private MineFunctionAdapter mMineFunctionAdapter;
    private Badge mBadgeAddOil;
    private Badge mBadgeClean;
    private Badge mBadgeDisCount;


    //登录状态改变
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginStateChange(LoginEvent mlLoginEvent) {
        if (!TextUtils.isEmpty(UserData.getUserId())) {
            mMinePresenter.loadUserData();
            mMinePresenter.loadCardNum();
            mMinePresenter.loadPointer();
        } else {
            //退出登录
            setPointer(false, false, false, false);
            showLoginButton();
            setCardNum("加油卡", "洗车卡", "优惠券");
            mMinePresenter.setNoIllegalCard();
        }
    }

    //修改用户资料
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void UserDataChange(ChangeUserDataEvent changeUserDataEvent) {
        if (!TextUtils.isEmpty(UserData.getUserId())) {
            mMinePresenter.loadUserData();
        } else {
            showLoginButton();
        }
    }

    //卡券包里面的卡券数量改变
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void numChange(CardNumEvent cardNumEvent) {
        mMinePresenter.loadCardNum();
    }

    //
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void pointerChange(PointerChangeEvent pointerChangeEvent) {
        mMinePresenter.loadPointer();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override

    protected void init() {
        EventBus.getDefault().register(this);

        mBadgeClean = new QBadgeView(mActivity);
        mBadgeDisCount = new QBadgeView(mActivity).setBadgeNumber(-1).setGravityOffset(11, 1, true).setBadgePadding(5, true).
                setShowShadow(false).setBadgeBackgroundColor(getResources().getColor(R.color.red_text));


        DaggerMineComponent.builder().appComponets(mAppComponets).mineModule(new MineModule()).build().Inject(this);
        mMinePresenter.attachView(this);

        if (!TextUtils.isEmpty(UserData.getUserId())) {
            mMinePresenter.readData();
        //    mMinePresenter.loadUserData();
            mMinePresenter.loadCardNum();
            mMinePresenter.loadPointer();
        }
        //功能区数据
        mMinePresenter.fucationData();
        mMgvMine.setOnItemClickListener(this);
    }

    @OnClick({R.id.iv_mine_setting, R.id.iv_mine_bell, R.id.iv_mine_head,
            R.id.btn_mine_sign, R.id.txt_mine_order_goods,
            R.id.txt_mine_order_clean, R.id.txt_mine_order_server,
            R.id.rll_mine_shoppingcar, R.id.rl_main_order})
    void viewOnclik(View view) {
        if (view.getId() != R.id.iv_mine_setting) {
            //判断是否登录
            if (TextUtils.isEmpty(UserData.getUserId())) {
                jumpAct(LoginAct.class);
                return;
            }
        }
        switch (view.getId()) {
            case R.id.iv_mine_setting:
                //设置界面
                jumpAct(SettingAct.class);
                break;
            case R.id.iv_mine_bell:
                //我的消息界面
                jumpAct(MessageAct.class);
                break;
            case R.id.iv_mine_head:
                //个人资料
                jumpAct(UserDataAct.class);
                break;
            case R.id.btn_mine_sign:
                //登录按钮
                jumpAct(LoginAct.class);
                break;
            //加油卡
            case R.id.txt_mine_order_goods:
                openCardBag(0);
                break;
            //洗车卡
            case R.id.txt_mine_order_clean:
                openCardBag(1);
                break;
            //优惠券
            case R.id.txt_mine_order_server:
                openCardBag(2);
                break;
            //购物车
            case R.id.rll_mine_shoppingcar:
                jumpAct(MyShoppingCartActivity.class);
                break;
            //我的订单
            case R.id.rl_main_order:
                Intent intent5 = new Intent(mActivity, MyOrderListActivity.class);
                intent5.putExtra("index", 0);
                startActivity(intent5);

                break;
        }
    }

    /**
     * 打开卡券包界面
     *
     * @param value
     */
    private void openCardBag(int value) {
        Intent intent = new Intent(mActivity, CardBagAct.class);
        intent.putExtra("type", value);
        startActivity(intent);
    }

    @Override
    public void addData(UserDataBean userDataBean) {
        if (TextUtils.isEmpty(userDataBean.getData().getUser_nickname())) {
            if (userDataBean.getData().getUser_mobile().length() == 11) {
                mTxtMineUsename.setText(userDataBean.getData().getUser_mobile().substring(0, 3)
                        + "****" + userDataBean.getData().getUser_mobile().substring(7, 11));
            }
        } else {
            mTxtMineUsename.setText(userDataBean.getData().getUser_nickname());
        }
        Glide.with(this)
                .load(userDataBean.getData().getUser_face()).centerCrop().placeholder(R.drawable.ic_mine_head)
                .transform(new CircleTransform(mActivity))
                .into(mIvMineHead);
    }

    @Override
    public void showLoginButton() {
        mBtnMineSign.setVisibility(View.VISIBLE);
        mRllMineUsers.setVisibility(View.GONE);
        Glide.with(this)
                .load(R.drawable.ic_mine_head)
                .transform(new CircleTransform(mActivity))
                .into(mIvMineHead);
    }

    @Override
    public void hideLoginButton() {
        mBtnMineSign.setVisibility(View.GONE);
        mRllMineUsers.setVisibility(View.VISIBLE);
    }

    @Override
    public void addFucationData(String[] names, int[] images) {
        mMineFunctionAdapter = new MineFunctionAdapter(names, images);
        mMgvMine.setAdapter(mMineFunctionAdapter);
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(mActivity, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingDialog() {
        mDialog = new LoadingDialog().LoadingDialog(mActivity);
    }

    @Override
    public void hideLoadingDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void setCardNum(String addOil, String clean, String discount) {
        mTxtMineOrderGoods.setText(addOil);
        mTxtMineOrderClean.setText(clean);
        mTxtMineOrderServer.setText(discount);
    }

    @Override
    public void setPointer(boolean one, boolean two, boolean thr, boolean fore) {
        //徽章
        if (one) {
            mBadgeAddOil = new QBadgeView(mActivity).setBadgeNumber(-1).setGravityOffset(11, 1, true).setBadgePadding(5, true).
                    setShowShadow(false).setBadgeBackgroundColor(getResources().getColor(R.color.red_text)).bindTarget(mTxtMineOrderGoods);
        } else {
            if (mBadgeAddOil != null)
                mBadgeAddOil.hide(true);
        }
        if (two) {
            mBadgeClean = new QBadgeView(mActivity).setBadgeNumber(-1).setGravityOffset(11, 1, true).setBadgePadding(5, true).
                    setShowShadow(false).setBadgeBackgroundColor(getResources().getColor(R.color.red_text)).bindTarget(mTxtMineOrderClean);
        } else {
            if (mBadgeClean != null)
                mBadgeClean.hide(true);
        }
        if (thr) {
            mBadgeDisCount = new QBadgeView(mActivity).setBadgeNumber(-1).setGravityOffset(11, 1, true).setBadgePadding(5, true).
                    setShowShadow(false).setBadgeBackgroundColor(getResources().getColor(R.color.red_text)).bindTarget(mTxtMineOrderServer);
        } else {
            if (mBadgeDisCount != null)
                mBadgeDisCount.hide(true);
        }
        mMineFunctionAdapter.setPointer(fore);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position != 7) {
            //判断是否登录
            if (TextUtils.isEmpty(UserData.getUserId())) {
                jumpAct(LoginAct.class);
                return;
            }
        }

        switch (position) {
            //违章卡
            case 0:
                openCardBag(3);
                break;
            case 1:
                //收藏
                jumpAct(MyCollectionActivity.class);
                break;
            case 2:
                //评价和回复
                jumpAct(MyEvaluateAndAnswerActivity.class);

                break;
            case 3:
                //喜豆
                jumpAct(HappyBeansAct.class);
                break;
            case 4:
                //邀请好友
                HtmlAct.unsealActivity(mActivity, ApiRest.baseUrl + ApiRest.INVITFRI + "&user_id=" + UserData.getUserId());
                break;
            case 5:
                //联盟店
                HtmlAct.unsealActivity(mActivity, ApiRest.baseUrl + ApiRest.UNION + "?get_device_type=android");
                break;
            case 6:
                //意见反馈
                mActivity.startActivity(new Intent(mActivity, FeedBackAct.class));
                break;
            case 7:
                //客服中心
                HtmlAct.unsealActivity(mActivity, ApiRest.KEFU);
                break;
        }
    }

    //界面跳转
    public void jumpAct(Class mc) {
        startActivity(new Intent(mActivity, mc));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
