package com.xi6666.seckill.view;

import android.Manifest;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.xi6666.R;
import com.xi6666.address.DistributionShopAct;
import com.xi6666.app.baset.BaseTAct;
import com.xi6666.bannerdetial.view.BannerDetialAct;
import com.xi6666.common.CircleTransform;
import com.xi6666.common.ShareUtils;
import com.xi6666.common.UserData;
import com.xi6666.databean.ProductDetialBean;
import com.xi6666.databean.ProductTypeBean;
import com.xi6666.databean.SkuListBean;
import com.xi6666.login.view.LoginAct;
import com.xi6666.order.activity.GoodsOrderAffirmActivity;
import com.xi6666.productdetails.ProductSizeAdapter;
import com.xi6666.productdetails.adapter.ProductBannerAdapter;
import com.xi6666.productdetails.adapter.ProductDetialAdapter;
import com.xi6666.productdetails.adapter.ProductDetialColorAdapter;
import com.xi6666.productdetails.view.ProductDetialFrgm;
import com.xi6666.seckill.contract.SecKillContract;
import com.xi6666.seckill.model.SecKillModel;
import com.xi6666.seckill.presenter.SecKillPresenter;
import com.xi6666.utils.DimenUtils;
import com.xi6666.view.AlphaScrollView;
import com.xi6666.view.DragScrollDetailsLayout;
import com.xi6666.view.FullyGridLayoutManager;
import com.xi6666.view.MesureGrideView;
import com.xi6666.view.TimerText;
import com.xi6666.view.dialog.CallDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.xi6666.R.color.loginBtnColor;
import static com.xi6666.R.color.txtPhoneNum;


/**
 * @author peng
 * @data 创建时间:2017/1/14
 * @desc 秒杀活动界面
 */
public class SecKillAct extends BaseTAct<SecKillPresenter, SecKillModel> implements
        SecKillContract.View, DragScrollDetailsLayout.OnSlideFinishListener,
        ProductBannerAdapter.BannerOnclickListener,
        ViewPager.OnPageChangeListener, View.OnClickListener {

    @BindView(R.id.vp_seckkill)
    ViewPager mVpSeckkill;
    @BindView(R.id.txt_seckkill_pointer)
    TextView mTxtSeckkillPointer;
    @BindView(R.id.txt_seckkill_xiaoxiprice)
    TextView mTxtSeckkillXiaoxiprice;
    @BindView(R.id.txt_seckkill_netprice)
    TextView mTxtSeckkillNetprice;
    @BindView(R.id.tt_seckkill_timer)
    TimerText mTtSeckkillTimer;
    @BindView(R.id.txt_seckkill_name)
    TextView mTxtSeckkillName;

    @BindView(R.id.txt_seckkill_yuanprice)
    TextView mTxtYuanPrice;

    @BindView(R.id.v_seckkill_div)
    View mVSeckkillDiv;
    @BindView(R.id.txt_seckkill_color)
    TextView mTxtSeckkillColor;
    @BindView(R.id.rg_product_color)
    MesureGrideView mRgProductColor;
    @BindView(R.id.txt_product_size)
    TextView mTxtProductSize;
    @BindView(R.id.rg_seckkill_size)
    RecyclerView mRgSeckkillSize;
    @BindView(R.id.rl_seckkill_colorandsize)
    RelativeLayout mRlSeckkillColorandsize;
    @BindView(R.id.rl_seckkill_store)
    RelativeLayout mRlSeckkillStore;
    @BindView(R.id.v_seckkill_store_div)
    View mVSeckkillStoreDiv;
    @BindView(R.id.asl_seckkill)
    AlphaScrollView mAslSeckkill;
    @BindView(R.id.tb_seckkill)
    TabLayout mTbSeckkill;
    @BindView(R.id.vp_seckkill_detial)
    ViewPager mVpSeckkillDetial;
    @BindView(R.id.dsdl_seckkill)
    DragScrollDetailsLayout mDsdlSeckkill;
    @BindView(R.id.iv_seckkill_back_top)
    ImageView mIvSeckkillBackTop;
    @BindView(R.id.iv_seckkill_goods_anim)
    ImageView mIvSeckkillGoodsAnim;
    @BindView(R.id.iv_seckkill_error)
    ImageView mIvSeckkillError;
    @BindView(R.id.tv_seckkill_again)
    TextView mTvSeckkillAgain;
    @BindView(R.id.rl_seckkill_error)
    RelativeLayout mRlSeckkillError;
    @BindView(R.id.iv_seckkill_back)
    ImageView mIvSeckkillBack;
    @BindView(R.id.iv_seckkill_share)
    ImageView mIvSeckkillShare;
    @BindView(R.id.txt_seckkill_title)
    TextView mTxtSeckkillTitle;
    @BindView(R.id.rl_seckkill_titlebar)
    RelativeLayout mRlSeckkillTitlebar;
    @BindView(R.id.activity_sec_kill)
    RelativeLayout mActivitySecKill;
    @BindView(R.id.txt_seckkill_state)
    TextView mSeckkillState;

    @BindView(R.id.btn_seckill_sure)
    Button mSecKillSure;
    private String mProduct;

    private String mSecKillId;
    private ProductDetialColorAdapter mProductDetialColorAdapter;
    private FullyGridLayoutManager mFullyGridLayoutManager;
    private List<SkuListBean.ListBean> mColorData = new ArrayList<>();
    private ProductSizeAdapter mProductSizeAdapter;
    private List<SkuListBean.ListBean> mSizeData = new ArrayList<>();
    private List<String> mBanner = new ArrayList<>();
    private View mView;
    private ProductTypeBean mProductTypeBean = new ProductTypeBean();
    private List<Fragment> mFragments = new ArrayList<>();

    private String mWebPageUrl;
    private String mTitleText;
    private String mContent;
    private String mImageUrl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sec_kill;
    }

    @Override
    public void init() {
        mSecKillId = getIntent().getStringExtra("goodId");
        mRgProductColor.setFocusable(false);
        mRgSeckkillSize.setFocusable(false);
        mRlSeckkillTitlebar.getBackground().setAlpha(0);
        mDsdlSeckkill.setOnSlideDetailsListener(this);

        //颜色sku
        mProductDetialColorAdapter = new ProductDetialColorAdapter();
        mProductDetialColorAdapter.setColorData(mColorData);
        mRgProductColor.setAdapter(mProductDetialColorAdapter);
        mRgProductColor.requestDisallowInterceptTouchEvent(true);


        //尺寸sku
        mFullyGridLayoutManager = new FullyGridLayoutManager(this, 3);
        mRgSeckkillSize.setLayoutManager(mFullyGridLayoutManager);
        mRgSeckkillSize.setNestedScrollingEnabled(false);
        mProductSizeAdapter = new ProductSizeAdapter();
        mProductSizeAdapter.setSizeData(mSizeData);
        mRgSeckkillSize.setAdapter(mProductSizeAdapter);

        //获取商品数据
        mPresenter.loadData(mSecKillId, UserData.getUserId(), UserData.getUserToken());
        //获取附近的门店
        mPresenter.loadNearNearBy();
    }

    @OnClick({R.id.iv_seckkill_back, R.id.iv_seckkill_share,
            R.id.tv_seckkill_again, R.id.iv_seckkill_more})
    void viewOnclick(View view) {
        switch (view.getId()) {
            //返回按钮
            case R.id.iv_seckkill_back:
                finish();
                break;
            //分享
            case R.id.iv_seckkill_share:
                new ShareUtils(this, mWebPageUrl, mTitleText, mContent, mImageUrl).showDialog();
                break;
            //重试
            case R.id.tv_seckkill_again:
                mPresenter.loadData(mSecKillId, UserData.getUserId(), UserData.getUserToken());
                break;
            //打电话
            case R.id.iv_seckkill_more:
                RxPermissions.getInstance(this).request(Manifest.permission.CALL_PHONE).subscribe(aBoolean -> {
                    if (aBoolean) {
                        new CallDialog(this).MakePhoneCall("400-9999-353");
                    } else {
                        Toast.makeText(this, "没有给予权限", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    @Override
    public void setBanner(List<String> banner) {
        mBanner.addAll(banner);
        if (banner.size() > 0) {
            Glide.with(this).load(banner.get(0)).transform
                    (new CircleTransform(this)).
                    into(mIvSeckkillGoodsAnim);
        }
        ProductBannerAdapter productBannerAdapter = new ProductBannerAdapter(mBanner);
        productBannerAdapter.setBannerOnclickListener(this);
        mVpSeckkill.setAdapter(productBannerAdapter);
        mVpSeckkill.addOnPageChangeListener(this);
        if (mBanner.size() > 0) {
            mTxtSeckkillPointer.setText(1 + "/" + mBanner.size() + "图");
        }
    }

    @Override
    public void setNamePrice(String name, String xiPrice, String netPrice, String yuanPrice) {
        //名称
        mTxtSeckkillName.setText(name);
        //秒杀价
        mTxtSeckkillXiaoxiprice.setText(xiPrice);
        //网购价
        mTxtSeckkillNetprice.setText(netPrice);
        //原价
        mTxtYuanPrice.setText(yuanPrice);
        TextPaint paint = mTxtYuanPrice.getPaint();
        paint.setAntiAlias(true);
        paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

    }

    @Override
    public void showError() {
        mRlSeckkillError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideState() {
        mRlSeckkillError.setVisibility(View.GONE);
    }

    //展示门店
    @Override
    public void showStore() {
        mRlSeckkillStore.setVisibility(View.VISIBLE);
        mRlSeckkillStore.setOnClickListener(this);
        mVSeckkillStoreDiv.setVisibility(View.VISIBLE);
    }

    //隐藏门店
    @Override
    public void hideStore() {
        mRlSeckkillStore.setVisibility(View.GONE);
        mVSeckkillStoreDiv.setVisibility(View.GONE);
    }

    //设置sku的名称
    @Override
    public void showColorText(String colorText) {
        if (!TextUtils.isEmpty(colorText)) {
            mTxtSeckkillColor.setVisibility(View.VISIBLE);
            mTxtSeckkillColor.setText(colorText);
        }
    }

    //设置sku的名称
    @Override
    public void showSizeText(String sizeText) {
        if (!TextUtils.isEmpty(sizeText)) {
            mTxtProductSize.setVisibility(View.VISIBLE);
            mTxtProductSize.setText(sizeText);
        }
    }

    //设置sku
    @Override
    public void setColorType(List<SkuListBean.ListBean> mColor, ProductDetialBean.DataBean.GoodsInfoBean goodsInfoBean) {
        mColorData.clear();
        mColorData.addAll(mColor);
        mProductDetialColorAdapter.setColorData(mColorData);
        new Handler().postDelayed(() -> {
            if (mRgProductColor.getChildCount() > 0) {
                mView = mRgProductColor.getChildAt(0);
            }
        }, 1000);
        mRgProductColor.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            for (int i = 0; i < parent.getChildCount(); i++) {
                ViewGroup view1 = (ViewGroup) parent.getChildAt(i);
                view1.getChildAt(0).setBackgroundResource(R.drawable.bg_product_color_gray);
                ((Button) view1.getChildAt(0)).setTextColor(getResources().getColor(txtPhoneNum));
            }
            mView = view;
            ((ViewGroup) view).getChildAt(0).setBackgroundResource(R.drawable.bg_product_color_green);
            ((Button) ((ViewGroup) view).getChildAt(0)).setTextColor(getResources().getColor(loginBtnColor));
            //重新去加载数据
            mPresenter.loadSku(mSecKillId, mColorData.get(position).getSku_value_id());
            //设置sku值
            mProductTypeBean.setSku_value_id(mColor.get(position).getSku_value_id());
        });
    }

    //设置sku
    @Override
    public void setSizeType(List<SkuListBean.ListBean> SizeData, ProductDetialBean.DataBean.GoodsInfoBean goodsInfoBean) {
        mSizeData.clear();
        mSizeData.addAll(SizeData);
        mProductSizeAdapter.setSizeData(mSizeData);
        mProductSizeAdapter.setOnRecycLerViewItemOlick((View view, int position) -> {
            mPresenter.loadSkuSize(mSecKillId, mSizeData.get(position).getSku_value_id());
            mProductTypeBean.setSku_value_id(mSizeData.get(position).getSku_value_id());
        });
    }

    //设置图文详情
    @Override
    public void loadProductDetial(String picUrl, String pramUrl) {
        mFragments.add(ProductDetialFrgm.newInstance(picUrl));
        mFragments.add(ProductDetialFrgm.newInstance(pramUrl));
        ProductDetialAdapter productDetialAdapter = new ProductDetialAdapter(getSupportFragmentManager(), mFragments);
        mTbSeckkill.setupWithViewPager(mVpSeckkillDetial);
        mVpSeckkillDetial.setAdapter(productDetialAdapter);
        mTbSeckkill.setTabsFromPagerAdapter(productDetialAdapter);
        mTbSeckkill.setSelectedTabIndicatorColor(getResources().getColor(loginBtnColor));
    }

    //是指是否下架
    @Override
    public void showOffShelf() {
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.
                        MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.height = DimenUtils.dip2px(this, 34);
        layoutParams.bottomMargin = DimenUtils.dip2px(this, 46);
        TextView textView = new TextView(this);
        textView.setText("此商品已下架");
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        textView.setTextColor(getResources().getColor(R.color.txxtSettingSingout));
        textView.setBackgroundColor(getResources().getColor(R.color.offlineColor));
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(layoutParams);
        mActivitySecKill.addView(textView);

    }

    @Override
    public void setSkuValue(String skuValue) {
        mProductTypeBean.setSku_value_id(skuValue);
    }

    //隐藏sku
    @Override
    public void hideColorAndSize() {
        mRlSeckkillColorandsize.setVisibility(View.GONE);
        mVSeckkillDiv.setVisibility(View.GONE);
    }

    //拿到share的数据
    @Override
    public void loadShare(String webPageUrl, String title, String content, String imageUrl) {
        mWebPageUrl = webPageUrl;
        mTitleText = title;
        mContent = content;
        mImageUrl = imageUrl;
    }

    //展示sku的颜色
    @Override
    public void showSkuPopu(String imageUrl) {
        showTypePopu(mView, imageUrl);
    }

    @Override
    public void setTime(long mday, long mhour, long mmin, long msecond, String flag) {
        mSeckkillState.setText(flag);
        mTtSeckkillTimer.setTimes(mday, mhour, mmin, msecond);
        mTtSeckkillTimer.start();
        //报名
        mSecKillSure.setOnClickListener(v -> {
            //判断是否登录
            if (UserData.isLoginIn()) {
                mPresenter.signUp(UserData.getUserId(), UserData.getUserToken(), "android", mSecKillId);
            } else {
                startActivity(new Intent(this, LoginAct.class));
            }
        });
        //监听倒计时结束
        mTtSeckkillTimer.setOnTimeEndListener(() -> {
            //立即秒杀
            mSecKillSure.setOnClickListener(v -> {
                //判断是否登录
                if (UserData.isLoginIn()) {
                    mPresenter.secKillSure(UserData.getUserId(), UserData.getUserToken(), "android", mSecKillId);

                } else {
                    startActivity(new Intent(this, LoginAct.class));
                }
            });
            mSecKillSure.setText("立即秒杀");
            mSeckkillState.setText("正在秒杀");
        });
    }

    @Override
    public void goToSecKill(boolean flag) {
        if (flag) {
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(this, GoodsOrderAffirmActivity.class);
                intent.putExtra("secID", mSecKillId);
                intent.putExtra("type", "secKill");
                startActivity(intent);
            }, 1500);
        } else {
            if (!TextUtils.isEmpty(mProduct)) {
                mPresenter.addGoodsCard(mProduct, "", UserData.getUserId(), UserData.getUserToken());
            } else {
                showToast("商品Id为空");
            }

        }
    }

    @Override
    public void orderPay() {

        if (!TextUtils.isEmpty(mProduct)) {
            Intent intent = new Intent(this, GoodsOrderAffirmActivity.class);
            intent.putExtra("secID", mProduct);
            intent.putExtra("type", "surprised");
            startActivity(intent);
        } else {
            showToast("商品Id为空");
        }
    }

    @Override
    public void setProductId(String productID) {
        this.mProduct = productID;
    }

    @Override
    public void onStatueChanged(DragScrollDetailsLayout.CurrentTargetIndex status) {
        //设置tab头部与顶部的距离
        if (DragScrollDetailsLayout.CurrentTargetIndex.UPSTAIRS == status) {
            //上半部分
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.
                    LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams1.topMargin = DimenUtils.dip2px(this, 0);
            layoutParams1.height = DimenUtils.dip2px(this, 40);
            mTbSeckkill.setLayoutParams(layoutParams1);
            mTxtSeckkillTitle.setVisibility(View.GONE);
            mRlSeckkillTitlebar.getBackground().setAlpha(0);
            //   mIvBack.setVisibility(View.GONE);
        } else {
            //下半部分
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams1.topMargin = DimenUtils.dip2px(this, 65);
            layoutParams1.height = DimenUtils.dip2px(this, 40);
            mTbSeckkill.setLayoutParams(layoutParams1);
            mRlSeckkillTitlebar.getBackground().setAlpha(255);
            mTxtSeckkillTitle.setVisibility(View.VISIBLE);
            //    mIvBack.setVisibility(View.VISIBLE);
        }
    }

    //展示样式图片popu弹窗
    private void showTypePopu(View view, String imageUrl) {
        PopupWindow popupWindow = new PopupWindow(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        View popu = LayoutInflater.from(this).inflate(R.layout.popuwindow_product_color, null);
        ImageView imageView = (ImageView) popu.findViewById(R.id.iv_popu_product_color);
        Glide.with(this).load(imageUrl).into(imageView);
        popu.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = popu.getMeasuredWidth();
        int popupHeight = popu.getMeasuredHeight();
        popupWindow.setContentView(popu);
        popupWindow.setOutsideTouchable(true);
        int[] location = new int[2];
        // 获得位置
        view.getLocationOnScreen(location);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, (location[0] + view.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
    }

    @Override
    public void bannerOnclick() {
        Intent intent = new Intent(this, BannerDetialAct.class);
        intent.putExtra("images", (Serializable) mBanner);
        startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mBanner.size() > 0) {
            mTxtSeckkillPointer.setText((position + 1) + "/" + mBanner.size() + "图");
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //门店按钮
            case R.id.rl_seckkill_store:
                startActivity(new Intent(this, DistributionShopAct.class));
                break;
        }
    }
}
