package com.xi6666.addoil.view;


import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.xi6666.R;
import com.xi6666.addoil.AddOilLocalImageHolderView;
import com.xi6666.addoil.adapter.AccountDetailsAdp;
import com.xi6666.addoil.adapter.ChoiceAddOilAdp;
import com.xi6666.addoil.contract.AddOilContract;
import com.xi6666.addoil.di.AddOilModule;
import com.xi6666.addoil.di.DaggerAddOilCompnoent;
import com.xi6666.addoil.presenter.AddOilPresenterImpl;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ToolBarBaseAct;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.cardbag.view.CardBagAct;
import com.xi6666.cardbag.view.oilcard.AddOilCardAct;
import com.xi6666.common.BuriedPoint;
import com.xi6666.common.UserData;
import com.xi6666.databean.AddOilDataBean;
import com.xi6666.databean.AddOilPopuBean;
import com.xi6666.eventbus.LoginEvent;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.login.view.LoginAct;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.DimenUtils;
import com.xi6666.utils.LogUtil;
import com.xi6666.view.MesureGrideView;
import com.xi6666.view.MesureListView;
import com.xi6666.view.VerticalTextview;
import com.xi6666.view.dialog.AddOilServerDialog;
import com.xi6666.view.dialog.LoadingDialog;
import com.xi6666.view.dialog.ReceiveSuccessDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author peng
 * @data 创建时间:2016/11/15
 * @desc 加油界面
 */
public class AddoOilAct extends ToolBarBaseAct implements
        AddOilContract.View, AdapterView.OnItemClickListener,
        View.OnClickListener {
    private static final String TAG = "AddoOilAct";
    @BindView(R.id.gaosline_bannar)
    ConvenientBanner mGaoslineBannar;
    @BindView(R.id.rl_server_iconone)
    RelativeLayout mRlServerIconone;
    @BindView(R.id.rl_server_icontwo)
    RelativeLayout mRlServerIcontwo;
    @BindView(R.id.rl_server_iconthr)
    RelativeLayout mRlServerIconthr;
    @BindView(R.id.txt_card_type)
    TextView mTxtCardType;
    @BindView(R.id.iv_next)
    ImageView mIvNext;
    @BindView(R.id.txt_card_num)
    TextView mTxtCardNum;
    @BindView(R.id.layout_myoilcard)
    RelativeLayout mLayoutMyoilcard;
    @BindView(R.id.txt_addoilcard)
    TextView mTxtAddoilcard;
    @BindView(R.id.layout_addoilcard)
    RelativeLayout mLayoutAddoilcard;
    @BindView(R.id.iv_min_money)
    ImageView mIvMinMoney;
    @BindView(R.id.txt_money)
    TextView mTxtMoney;
    @BindView(R.id.iv_add_money)
    ImageView mIvAddMoney;
    @BindView(R.id.mgv_choice)
    MesureGrideView mMgvChoice;
    @BindView(R.id.txt_oldprice)
    TextView mTxtOldprice;
    @BindView(R.id.txt_newprice)
    TextView mTxtNewprice;
    @BindView(R.id.txt_savemoney)
    TextView mTxtSavemoney;
    @BindView(R.id.txt_data)
    TextView mTxtData;
    @BindView(R.id.txt_data_desc)
    TextView mTxtDataDesc;
    @BindView(R.id.iv_open_arraw)
    ImageView mIvOpenArraw;
    @BindView(R.id.txt_data_all)
    TextView mTxtDataAll;
    @BindView(R.id.mlv_list)
    MesureListView mMlvList;
    @BindView(R.id.btn_rechage)
    Button mBtnRechage;
    @BindView(R.id.scv_root)
    ScrollView mRootView;
    @BindView(R.id.txt_recharge_people)
    TextView mTxtRechargePeople;
    @BindView(R.id.txt_recharge_state)
    VerticalTextview mTxtRechargeState;

    @BindView(R.id.txt_addoil_savemoney)
    TextView mTvSaveMoney;

    @BindView(R.id.txt_addoil_waht)
    TextView mTvThat;

    private int mMaxMoney = 1000;

    private List<AddOilDataBean.DataBean> mData = new ArrayList<>();
    private ChoiceAddOilAdp mChoiceAddOilAdp;
    private List<AddOilDataBean.BannerBean> mBannerBean = new ArrayList<>();

    private ArrayList<String> textBanner = new ArrayList<>();


    //加油折扣
    private double disCount;

    //月份
    private int month;

    private AccountDetailsAdp mAccountDetailsAdp;

    //加油套餐
    private String chargeType = "";

    //卡号
    private String oilCardId;

    private String mQuanCunUrl;


    @Inject
    AddOilPresenterImpl mAddOilPresenter;

    @Inject
    ApiRest mApiRest;
    private Dialog mDialog;

    //登录成功
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void LoginSucces(LoginEvent loginEvent) {
        String userId = UserData.getUserId();
        String userToken = UserData.getUserToken();
        mAddOilPresenter.loadDefaultOilCard(userId, userToken);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addo_oil);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mTxtMoney.setText("1000");


        mTxtRight.setBackgroundResource(R.drawable.ic_addoil_what);
        mTxtRight.setVisibility(View.VISIBLE);
        mTxtRight.setText("");
        mTxtRight.setOnClickListener(this);

        //设置下滑线
        TextPaint paint = mTvThat.getPaint();
        paint.setAntiAlias(true);
        paint.setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //注入
        DaggerAddOilCompnoent.builder().apiModule(new ApiModule((BaseApplication)
                getApplication())).addOilModule(new AddOilModule()).build().Inject(this);
        mAddOilPresenter.attachView(this);
        mAddOilPresenter.setApiRest(mApiRest);
        //加载套餐数据
        mAddOilPresenter.loadData();
        //加载人数
        mAddOilPresenter.loadPeoleState();
        //加载是否有活动
        String userId = UserData.getUserId();
        String userToken = UserData.getUserToken();
        mAddOilPresenter.loadDefaultOilCard(userId, userToken);

        mMgvChoice.setFocusable(false);
        mMgvChoice.setOnItemClickListener(this);

        View headView = getLayoutInflater().inflate(R.layout.head_account, null);
        mMlvList.addHeaderView(headView);


        mTxtOldprice.getPaint().setAntiAlias(true);
        mTxtOldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);


    }

    @Override
    public void setBanner(List<AddOilDataBean.BannerBean> bannerBean) {
        Log.d(TAG, "bannerBean--->" + bannerBean.get(0).getLink());
        mBannerBean.clear();
        mBannerBean.addAll(bannerBean);
        mGaoslineBannar.setPages(new CBViewHolderCreator<AddOilLocalImageHolderView>() {
            @Override
            public AddOilLocalImageHolderView createHolder() {
                return new AddOilLocalImageHolderView();
            }
        }, bannerBean);

        if (bannerBean.size() > 1) {
            mGaoslineBannar.setPointViewVisible(true)    //设置指示器是否可见
                    .setPageIndicator(new int[]{R.drawable.point, R.drawable.point_pre})   //设置指示器圆点
                    .startTurning(5000);     //设置自动切换（同时设置了切换时间间隔）
        }

    }

    @Override
    public void showDefaultOilCard() {
        mLayoutMyoilcard.setVisibility(View.VISIBLE);
        mLayoutAddoilcard.setVisibility(View.GONE);
    }

    @Override
    public void showAddOilCard() {
        mLayoutMyoilcard.setVisibility(View.GONE);
        mLayoutAddoilcard.setVisibility(View.VISIBLE);
    }

    @Override
    public void setDefaultOilCard(String type, String cardnum, String cardId) {
        mTxtCardType.setText(type);
        mTxtCardNum.setText(cardnum);
        oilCardId = cardId;
    }

    @Override
    public void setMaxMoney(int maxMoney) {
        mMaxMoney = maxMoney;
    }

    @Override
    public void setListData(List<AddOilDataBean.DataBean> data) {
        mData.clear();
        mData.addAll(data);
        Log.d(TAG, "mData--->" + mData.size());
        //设置默认的月份和折扣
        month = Integer.parseInt(mData.get(0).getPackage_return_number());
        disCount = mData.get(0).getPackage_discount();

        int money = Integer.parseInt(mTxtMoney.getText().toString().trim());
        //设置原价
        mTxtOldprice.setText((money * month) + "");
        //设置折后价
        mTxtNewprice.setText((int) ((money * month) * disCount) + "");
        //设置节省钱数
        mTxtSavemoney.setText(((money * month) - (int) ((money * month) * disCount) + ""));

        //设置到账个月数
        mTxtData.setText("分" + mData.get(0).getPackage_return_number() + "个月到账");
        mTxtDataAll.setText("共" + mData.get(0).getPackage_return_number() + "期");
        mTxtDataDesc.setText("首月金额" + money + "元  于充值后2小时内到账");

        //设置数据适配器
        mChoiceAddOilAdp = new ChoiceAddOilAdp(mData, this);
        mMgvChoice.setAdapter(mChoiceAddOilAdp);
        mChoiceAddOilAdp.setMySelection(0);

        //设置分期明细
        mAccountDetailsAdp = new AccountDetailsAdp(money, month, this);
        mMlvList.setAdapter(mAccountDetailsAdp);

        chargeType = mData.get(0).getPackage_id();//设置支付的type
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLodingDialog() {
        mDialog = new LoadingDialog().LoadingDialog(this);

    }

    @Override
    public void hideLoadingDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void setPeopleNum(String num) {
        mTxtRechargePeople.setText(num);
        String trim = mTxtRechargePeople.getText().toString().trim();
        SpannableString spannableString = new SpannableString(trim);
        spannableString.setSpan(new AbsoluteSizeSpan(DimenUtils.sp2px(this, 13)),
                trim.length() - 1, trim.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTxtRechargePeople.setText(spannableString);


    }

    @Override
    public void setSaveMoney(String money) {
        mTvSaveMoney.setText(money);
        String trim = mTvSaveMoney.getText().toString().trim();
        SpannableString spannableString = new SpannableString(trim);
        spannableString.setSpan(new AbsoluteSizeSpan(DimenUtils.sp2px(this, 13)), trim.length() - 1, trim.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvSaveMoney.setText(spannableString);
    }

    @Override
    public void setPeopleState(ArrayList<String> content) {
        textBanner.clear();
        textBanner.addAll(content);
        mTxtRechargeState.setTextList(textBanner);
        mTxtRechargeState.setText(14, 5, this.getResources().getColor(R.color.orange));//设置属性
        mTxtRechargeState.setTextStillTime(5000);//设置停留时长间隔
        mTxtRechargeState.setAnimTime(300);//设置进入和退出的时间间隔
        mTxtRechargeState.startAutoScroll();
    }

    @Override
    public void setQuanCunUrl(String url) {
        this.mQuanCunUrl = url;
    }

    @Override
    public void startPay() {
        int payMoney = Integer.parseInt(mTxtMoney.getText().toString().trim());
        //  String trim = mTxtCardNum.getText().toString().trim();
        LogUtil.d(TAG, "金额==" + payMoney + "\n类型==" + chargeType + "\n油卡卡号==" + oilCardId + "\n折扣==" + disCount);
        Intent intent = new Intent(this, AddOilPayAct.class);
        intent.putExtra("money", payMoney);
        intent.putExtra("chargeType", chargeType);
        intent.putExtra("cardId", oilCardId);
        intent.putExtra("descount", disCount);
        intent.putExtra("month", month);
        startActivity(intent);
    }

    @OnClick({R.id.layout_addoilcard, R.id.rl_server_iconone, R.id.rl_server_icontwo,
            R.id.rl_server_iconthr, R.id.iv_min_money, R.id.iv_add_money,
            R.id.layout_open, R.id.btn_rechage, R.id.layout_myoilcard, R.id.txt_addoil_waht})
    void viewOnclik(View view) {
        switch (view.getId()) {
            case R.id.layout_addoilcard:
                //判断是否登录
                if (TextUtils.isEmpty(UserData.getUserId())) {
                    startActivity(new Intent(this, LoginAct.class));
                } else {
                    startActivity(new Intent(this, AddOilCardAct.class));
                }
                break;
            case R.id.rl_server_iconone:
                AddOilServerDialog addOilServerDialog = new AddOilServerDialog(this, R.style.transpant_bg_dialog);
                addOilServerDialog.show();
                Drawable drawable = getResources().getDrawable(R.drawable.addoil_server_dia_one);
                addOilServerDialog.setBg(drawable);
                addOilServerDialog.setLookGone();
                break;
            case R.id.rl_server_icontwo:
                AddOilServerDialog addOilServerDialogTwo = new AddOilServerDialog(this, R.style.transpant_bg_dialog);
                addOilServerDialogTwo.show();
                addOilServerDialogTwo.setBg(getResources().getDrawable(R.drawable.addoil_server_dia_two));
                addOilServerDialogTwo.setLookGone();
                break;
            case R.id.rl_server_iconthr:
                AddOilServerDialog addOilServerDialogThr = new AddOilServerDialog(this, R.style.transpant_bg_dialog);
                addOilServerDialogThr.show();
                addOilServerDialogThr.setBg(getResources().getDrawable(R.drawable.addoil_server_dia_thr));
                break;
            //减少金额
            case R.id.iv_min_money:
                sendToServer("jian", "jian");
                mIvAddMoney.setImageResource(R.drawable.gaosline_add_money);
                String money = mTxtMoney.getText().toString().trim();
                int mon = Integer.parseInt(money);
                if (mon == 100) {
                    return;
                }
                int newMony;
                if (mon > 1000) {
                    newMony = mon - 500;
                } else {
                    newMony = mon - 100;
                }
                if (newMony == 100) {
                    mIvMinMoney.setImageResource(R.drawable.gaosline_min_money_not);
                }
                //设置回显
                mTxtMoney.setText(newMony + "");
                //原价
                mTxtOldprice.setText((month * newMony) + "");
                //折后
                mTxtNewprice.setText((int) ((month * newMony) * disCount) + "");
                //节省
                mTxtSavemoney.setText((int) ((month * newMony) - ((month * newMony) * disCount)) + "");
                //设置首月金额
                mTxtDataDesc.setText("首月金额" + newMony + "元  于充值后2小时内到账");
                //设置分期明细
                mAccountDetailsAdp = new AccountDetailsAdp(newMony, month, this);
                mMlvList.setAdapter(mAccountDetailsAdp);
                break;
            //增加金额
            case R.id.iv_add_money:
                sendToServer("jia", "jia");
                mIvMinMoney.setImageResource(R.drawable.gaosline_min_money);
                String moneyAdd = mTxtMoney.getText().toString().trim();
                int monAdd = Integer.parseInt(moneyAdd);

                if (monAdd >= mMaxMoney) {
                    return;
                }
                if (monAdd >= mMaxMoney) {
                    mIvAddMoney.setImageResource(R.drawable.gaosline_add_money_not);
                }
                int newMonyAdd;
                if (monAdd >= 1000) {
                    newMonyAdd = monAdd + 500;
                } else {
                    newMonyAdd = monAdd + 100;
                }
                if (newMonyAdd == mMaxMoney) {
                    mIvAddMoney.setImageResource(R.drawable.gaosline_add_money_not);
                }
                //设置回显
                mTxtMoney.setText(newMonyAdd + "");
                //原价
                mTxtOldprice.setText((month * newMonyAdd) + "");
                //折后
                mTxtNewprice.setText((int) ((month * newMonyAdd) * disCount) + "");
                //节省
                mTxtSavemoney.setText((int) ((month * newMonyAdd) - ((month * newMonyAdd) * disCount)) + "");
                //设置首月金额
                mTxtDataDesc.setText("首月金额" + newMonyAdd + "元  于充值后2小时内到账");
                //设置分期明细
                mAccountDetailsAdp = new AccountDetailsAdp(newMonyAdd, month, this);
                mMlvList.setAdapter(mAccountDetailsAdp);
                break;
            //展开箭头
            case R.id.layout_open:
                int visibility = mMlvList.getVisibility();
                if (visibility == View.GONE) {
                    sendToServer("tcmx", "tcmx");
                    mMlvList.setVisibility(View.VISIBLE);
                    ObjectAnimator anim = ObjectAnimator.ofFloat(mIvOpenArraw, "rotation", 0f, 180f);
                    anim.setDuration(500);
                    anim.start();
                    //使得scrollview向下滑动一段距离
                  /*  new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int scrollY = mRootView.getScrollY();
                            int i = DimenUtils.dip2px(AddoOilAct.this, 350);
                            mRootView.smoothScrollTo(0, scrollY + i);
                        }
                    }, 100);*/
                } else {
                    mMlvList.setVisibility(View.GONE);
                    ObjectAnimator anim = ObjectAnimator.ofFloat(mIvOpenArraw, "rotation", 180f, 360f);
                    anim.setDuration(500);
                    anim.start();
                }

                break;
            //立即充值
            case R.id.btn_rechage:
                sendToServer("ljcz", "ljcz");
                //判断是否登录
                if (!TextUtils.isEmpty(UserData.getUserId())) {
                    //判断是否有默认油卡
                    if (mLayoutMyoilcard.getVisibility() == View.GONE) {

                        startActivity(new Intent(this, AddOilCardAct.class));
                    } else {
                        mAddOilPresenter.testingAddOil(chargeType, UserData.getUserId(), UserData.getUserToken());
                    }
                } else {
                    startActivity(new Intent(this, LoginAct.class));
                }
                break;
            //跳转到油卡列表界面
            case R.id.layout_myoilcard:
                startActivity(new Intent(this, CardBagAct.class));
                break;
            //跳转到什么是圈存的界面
            case R.id.txt_addoil_waht:
                HtmlAct.unsealActivity(this, mQuanCunUrl);
                break;
        }
    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "加油充值";
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        sendToServer("tc", "tc" + position);
        mChoiceAddOilAdp.setMySelection(position);
        month = Integer.parseInt(mData.get(position).getPackage_return_number());
        disCount = mData.get(position).getPackage_discount();

        int money = Integer.parseInt(mTxtMoney.getText().toString().trim());
        //设置原价
        mTxtOldprice.setText((money * month) + "");
        //设置折后价
        mTxtNewprice.setText((int) ((money * month) * disCount) + "");
        //设置节省钱数
        mTxtSavemoney.setText(((money * month) - (int) ((money * month) * disCount) + ""));
        //设置分期
        mTxtData.setText("分" + mData.get(position).getPackage_return_number() + "个月到账");
        mTxtDataAll.setText("共" + mData.get(position).getPackage_return_number() + "期");
        mTxtDataDesc.setText("首月金额" + money + "元  于充值后2小时内到账");
        //设置分期
        mAccountDetailsAdp = new AccountDetailsAdp(money, month, this);
        mMlvList.setAdapter(mAccountDetailsAdp);
        //设置支付的type
        chargeType = mData.get(position).getPackage_id();
    }

    @Override
    public void onClick(View v) {
        //加油说明
        sendToServer("cksm", "cksm");
        HtmlAct.unsealActivity(this, ApiRest.baseUrl + ApiRest.ADDOILTHAT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAddOilPresenter.loadDefaultOilCard(UserData.getUserId(), UserData.getUserToken());
    }

    /**
     * @data 创建时间:2017/1/18
     * @author peng
     * @desc 数据埋点统计
     * @version
     */
    public void sendToServer(String name, String remark) {
        new BuriedPoint().sendToServer("jyk", name, remark);
    }
}
