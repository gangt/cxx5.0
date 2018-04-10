package com.xi6666.productdetails.view;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ImmerseBaseAct;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.bannerdetial.view.BannerDetialAct;
import com.xi6666.common.CircleTransform;
import com.xi6666.common.OnRecyclerItemClickListener;
import com.xi6666.common.ShareUtils;
import com.xi6666.common.UserData;
import com.xi6666.databean.ProductDetialBean;
import com.xi6666.databean.ProductTypeBean;
import com.xi6666.databean.PromotionBean;
import com.xi6666.databean.SkuListBean;
import com.xi6666.evaluate.activity.MyCollectionActivity;
import com.xi6666.login.view.LoginAct;
import com.xi6666.main.view.MainAct;
import com.xi6666.network.ApiRest;
import com.xi6666.order.activity.MyOrderListActivity;
import com.xi6666.order.activity.MyShoppingCartActivity;
import com.xi6666.productdetails.ProductSizeAdapter;
import com.xi6666.productdetails.adapter.ProductBannerAdapter;
import com.xi6666.productdetails.adapter.ProductDetialAdapter;
import com.xi6666.productdetails.adapter.ProductDetialColorAdapter;
import com.xi6666.productdetails.adapter.PromotionAdapter;
import com.xi6666.productdetails.contract.ProductDetialContract;
import com.xi6666.productdetails.di.DaggerProductDetialComponent;
import com.xi6666.productdetails.di.ProductDetialModule;
import com.xi6666.productdetails.presenter.ProductPresenterImpl;
import com.xi6666.utils.ButtonUtils;
import com.xi6666.utils.DimenUtils;
import com.xi6666.utils.LogUtil;
import com.xi6666.view.AlphaScrollView;
import com.xi6666.view.DragScrollDetailsLayout;
import com.xi6666.view.FullyGridLayoutManager;
import com.xi6666.view.FullyLinearLayoutManager;
import com.xi6666.view.MesureGrideView;
import com.xi6666.view.dialog.CallDialog;
import com.xi6666.view.popuwindow.ProductAddCarPopu;
import com.xi6666.view.popuwindow.ProductMorePopu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.xi6666.R.color.loginBtnColor;
import static com.xi6666.R.color.txtPhoneNum;
import static com.xi6666.R.id.rg_product_color;


/**
 * @author peng
 * @data 创建时间:2016/11/14
 * @desc 商品详情界面
 */
public class ProductDetailsAct extends ImmerseBaseAct implements OnRecyclerItemClickListener,
        ProductDetialContract.View, View.OnClickListener, ViewPager.OnPageChangeListener,
        DragScrollDetailsLayout.OnSlideFinishListener, ProductBannerAdapter.
                BannerOnclickListener {
    private static final String TAG = "ProductDetailsAct";
    @BindView(R.id.iv_product_more)
    ImageView mIvProductMore;
    @BindView(R.id.rl_product_titlebar)
    RelativeLayout mRlProductTitlebar;
    @BindView(R.id.vp_product)
    ViewPager mVpProduct;
    @BindView(R.id.txt_product_pointer)
    TextView mTxtProductPointer;
    @BindView(R.id.txt_product_name)
    TextView mTxtProductName;
    @BindView(R.id.txt_product_xiaoxiprice)
    TextView mTxtProductXiaoxiprice;
    @BindView(R.id.txt_product_netprice)
    TextView mTxtProductNetprice;


    @BindView(R.id.txt_product_color)
    TextView mTxtProductColor;
    @BindView(rg_product_color)
    MesureGrideView mRgProductColor;

    @BindView(R.id.rg_product_size)
    RecyclerView mRgProductSize;


    @BindView(R.id.rl_product_store)
    RelativeLayout mRlProductStore;

    @BindView(R.id.iv_product_collection)
    ImageView mIvProductCollection;
    @BindView(R.id.txt_product_collection)
    TextView mTxtProductCollection;

    @BindView(R.id.txt_product_shopcar_point)
    TextView mTxtProductShopcarPoint;
    @BindView(R.id.txt_product_addcar)
    TextView mTxtProductAddcar;

    @BindView(R.id.dsdl_product)
    DragScrollDetailsLayout mDragScrollDetailsLayout;
    @BindView(R.id.tb_product)
    TabLayout mTbProduct;
    @BindView(R.id.vp_product_detial)
    ViewPager mVpProductDetial;

    @BindView(R.id.asl_product)
    AlphaScrollView mAlphaScrollView;
    @BindView(R.id.iv_product_back)
    ImageView mIvProductBack;

    @BindView(R.id.txt_product_title)
    TextView mTitle;

    @BindView(R.id.iv_product_back_top)
    ImageView mIvBack;

    @BindView(R.id.rl_product_collection)
    RelativeLayout mProductCollection;

    @BindView(R.id.rl_productsdetial_root)
    RelativeLayout mRelativeLayoutRoot;
    @BindView(R.id.txt_product_size)
    TextView mTxtProductSize;
    @BindView(R.id.iv_product_share)
    ImageView mIvProductShare;
    @BindView(R.id.txt_product_detial_off)
    LinearLayout mTxtProductDetialOff;

    @BindView(R.id.v_productdetial_div)
    View mDiv;
    @BindView(R.id.rl_productdetial_colorandsize)
    RelativeLayout rlProductdetialColorandsize;

    @BindView(R.id.v_product_store_div)
    View mStoreDiv;

    @BindView(R.id.iv_product_goods_anim)
    ImageView mIvGoodsAnim;

    @BindView(R.id.rl_productdetial_error)
    RelativeLayout mRlProductdetialError;

    @BindView(R.id.rv_product_promotion)
    RecyclerView mRecyclerViewProMo;


    private List<String> mBanner = new ArrayList<>();
    private FullyGridLayoutManager mFullyGridLayoutManager;

    private List<Fragment> mFragments = new ArrayList<>();

    private ProductTypeBean mProductTypeBean = new ProductTypeBean();

    private List<SkuListBean.ListBean> mColorData = new ArrayList<>();

    private List<SkuListBean.ListBean> mSizeData = new ArrayList<>();

    private List<PromotionBean.DiscountListBean> mPromotionBeen = new ArrayList<>();
    @Inject
    ProductPresenterImpl mProductPresenter;
    @Inject
    ApiRest mApiRest;
    private String mGoodsId;
    private ProductAddCarPopu mProductMorePopu;

    private String mWebPageUrl;
    private String mTitleText;
    private String mContent;
    private String mImageUrl;

    private ProductSizeAdapter mProductSizeAdapter;

    private View mView;
    private ProductDetialColorAdapter mProductDetialColorAdapter;
    private PromotionAdapter mPromotionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        mGoodsId = getIntent().getStringExtra("goodId");
       /* mGoodsId = "45449";*/
        LogUtil.d(TAG, "mGoodsId--->" + mGoodsId);

        //  mElProductsdetialState.setErrorButtonClickListener(this);

        mRgProductColor.setFocusable(false);
        mRgProductSize.setFocusable(false);


        mRlProductTitlebar.getBackground().setAlpha(0);


        mDragScrollDetailsLayout.setOnSlideDetailsListener(this);


        DaggerProductDetialComponent.builder().apiModule(new ApiModule((BaseApplication)
                getApplication())).productDetialModule(new ProductDetialModule()).
                build().Inject(this);
        mProductPresenter.attachView(this);
        mProductPresenter.setApiRest(mApiRest);
        mVpProduct.setOnClickListener(this);

        //促销活动
        mRecyclerViewProMo.setLayoutManager(new FullyLinearLayoutManager(this));
        mPromotionAdapter = new PromotionAdapter();
        mPromotionAdapter.setPromotionBeen(mPromotionBeen);
        mRecyclerViewProMo.setAdapter(mPromotionAdapter);
        //颜色sku
        mProductDetialColorAdapter = new ProductDetialColorAdapter();
        mProductDetialColorAdapter.setColorData(mColorData);
        mRgProductColor.setAdapter(mProductDetialColorAdapter);
        mRgProductColor.requestDisallowInterceptTouchEvent(true);

        //尺寸sku
        mFullyGridLayoutManager = new FullyGridLayoutManager(this, 3);
        mRgProductSize.setLayoutManager(mFullyGridLayoutManager);
        mRgProductSize.setNestedScrollingEnabled(false);
        mProductSizeAdapter = new ProductSizeAdapter();
        mProductSizeAdapter.setSizeData(mSizeData);
        mRgProductSize.setAdapter(mProductSizeAdapter);
        //mProductSizeAdapter.setHasStableIds();

        //数据
        mProductPresenter.attachView(this);
        mProductPresenter.setApiRest(mApiRest);
        mProductPresenter.loadData(mGoodsId, UserData.getUserId(), UserData.getUserToken());
        mProductPresenter.loadNearNearBy();
        hidePromotion();

    }

    @OnClick({R.id.iv_product_back, R.id.iv_product_more, R.id.iv_product_share,
            R.id.txt_product_addcar, R.id.iv_product_back_top, R.id.rl_product_collection, R.id.rl_product_shopcar, R.id.tv_try_again})
    void viewOnclick(View view) {
        switch (view.getId()) {
            //返回按钮
            case R.id.iv_product_back:
                finish();
                break;
            //更多
            case R.id.iv_product_more:
                ProductMorePopu productMorePopu = new ProductMorePopu(this);
                productMorePopu.setOnRecyclerItemClickListener(this);
                productMorePopu.showPopuWindow(mIvProductMore);
                break;
            //分享
            case R.id.iv_product_share:
                new ShareUtils(this, mWebPageUrl, mTitleText, mContent, mImageUrl).showDialog();
                break;

            //加入购物车
            case R.id.txt_product_addcar:
                if (TextUtils.isEmpty(UserData.getUserId())) {
                    gotoLogin();
                } else {
                    if (ButtonUtils.isFastClick(2000)) {
                        Toast.makeText(this, "亲~请不要过快点击!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (TextUtils.equals(mProductTypeBean.getSku_value_id(), "0")) {
                            Toast.makeText(this, "请选择商品属性.", Toast.LENGTH_SHORT).show();
                        } else {
                            mProductPresenter.addGoodsCard(mGoodsId,
                                    mProductTypeBean.getSku_value_id(),
                                    UserData.getUserId(),
                                    UserData.getUserToken()
                            );
                        }

                    }
                }
                break;
            //返回顶部按钮
            case R.id.iv_product_back_top:
                mDragScrollDetailsLayout.setBackTop();
                mAlphaScrollView.scrollTo(0, 0);
                break;
            case R.id.rl_product_collection:
                //判断是否登录
                if (TextUtils.isEmpty(UserData.getUserId())) {
                    gotoLogin();
                } else {
                    mProductPresenter.loadCollect(mGoodsId, UserData.getUserId(), UserData.getUserToken());
                }
                break;
            //购物车跳转
            case R.id.rl_product_shopcar:
                //判断是否登录
                if (TextUtils.isEmpty(UserData.getUserId())) {
                    gotoLogin();
                } else {
                    startActivity(new Intent(ProductDetailsAct.this, MyShoppingCartActivity.class));
                }
                break;
            case R.id.tv_try_again:
                mProductPresenter.loadData(mGoodsId, UserData.getUserId(), UserData.getUserToken());
                break;
        }
    }


    /**
     * 微信分享
     *
     * @param webPageUrl
     * @param title
     * @param content
     * @param imageUrl
     */
    @Override
    public void loadShare(String webPageUrl,
                          String title,
                          String content,
                          String imageUrl) {
        mWebPageUrl = webPageUrl;
        mTitleText = title;
        mContent = content;
        mImageUrl = imageUrl;
    }


    /**
     * 是否展示购物车的知识数量
     *
     * @param flag
     */
    @Override
    public void setGoodsCarPointShow(boolean flag) {
        if (flag) {
            mTxtProductShopcarPoint.setVisibility(View.VISIBLE);
        } else {
            mTxtProductShopcarPoint.setVisibility(View.GONE);
        }
    }

    /**
     * 展示加入购物车动画
     *
     * @param show
     */
    @Override
    public void showAddShopCarAnim(boolean show) {
        mIvGoodsAnim.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.cart_anim);
        mIvGoodsAnim.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIvGoodsAnim.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void showSkuPopu(String imageUrl) {

        showTypePopu(mView, imageUrl);

    }

    @Override
    public void setPromotion(List<PromotionBean.DiscountListBean> promotion) {
        mPromotionBeen.clear();
        mPromotionBeen.addAll(promotion);
        mPromotionAdapter.setPromotionBeen(mPromotionBeen);
    }

    //popu弹窗更多的点击事件
    @Override
    public void onRecyclerItemClick(int position) {
        switch (position) {
            //首页
            case 0:
                startActivity(new Intent(this, MainAct.class));
                finish();
                break;
            //我的收藏
            case 1:
                if (UserData.isLoginIn()) {
                    startActivity(new Intent(this, MyCollectionActivity.class));
                } else {
                    gotoLogin();
                }
                break;
            //商品订单
            case 2:
                if (!TextUtils.isEmpty(UserData.getUserId())) {
                    Intent intent = new Intent(this, MyOrderListActivity.class);
                    intent.putExtra("index", 0);
                    intent.putExtra("type", "ProductDetailsAct");
                    startActivity(intent);
                } else {
                    gotoLogin();
                }
                break;
            //客服电话
            case 3:
                RxPermissions.getInstance(this)
                        .request(Manifest.permission.CALL_PHONE)
                        .subscribe((Boolean success) -> {
                            if (success) {
                                new CallDialog(ProductDetailsAct.this).MakePhoneCall("400-9999-353");
                            } else {
                                Toast.makeText(ProductDetailsAct.this, "没有给予权限", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }

    //设置banner
    @Override
    public void setBanner(List<String> banner) {
        mBanner.addAll(banner);
        if (banner.size() > 0) {
            Glide.with(this).load(banner.get(0)).transform
                    (new CircleTransform(this)).
                    into(mIvGoodsAnim);
        }
        ProductBannerAdapter productBannerAdapter = new ProductBannerAdapter(mBanner);
        productBannerAdapter.setBannerOnclickListener(this);
        mVpProduct.setAdapter(productBannerAdapter);
        mVpProduct.addOnPageChangeListener(this);
        if (mBanner.size() > 0) {
            mTxtProductPointer.setText(1 + "/" + mBanner.size() + "图");
        }
    }

    //设置价格
    @Override
    public void setNamePrice(String name, String xiPrice, String netPrice) {
        mTxtProductName.setText(name);
        mTxtProductXiaoxiprice.setText(xiPrice);
        TextPaint paint = mTxtProductNetprice.getPaint();
        paint.setAntiAlias(true);
        paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mTxtProductNetprice.setText(netPrice);

    }


    //添加到收藏
    @Override
    public void setCollection(boolean isCollect) {
        if (isCollect) {
            mIvProductCollection.setImageResource(R.drawable.ic_product_collection_pre);
            ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mIvProductCollection,
                    PropertyValuesHolder.ofFloat("scaleX", 1.0f, 2.0f, 1.0f),
                    PropertyValuesHolder.ofFloat("scaleY", 1.0f, 2.0f, 1.0f));
            objectAnimator.start();
            mTxtProductCollection.setText("已收藏");
        } else {
            mIvProductCollection.setImageResource(R.drawable.ic_product_collection);
            mTxtProductCollection.setText("收藏");
        }
    }

    //打印吐司
    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    //设置购物车数量
    @Override
    public void setGoodsCarNum(String string) {
        if (Integer.parseInt(string) > 99) {
            mTxtProductShopcarPoint.setText("99+");
        } else {
            mTxtProductShopcarPoint.setText(string);
        }
    }


    //显示促销活动
    @Override
    public void showPromotion() {

    }

    //关闭促销
    @Override
    public void hidePromotion() {

    }

    //展示门店功能
    @Override
    public void showStore() {
        mRlProductStore.setVisibility(View.VISIBLE);
        mRlProductStore.setOnClickListener(this);
        mStoreDiv.setVisibility(View.VISIBLE);
    }

    //隐藏门店
    @Override
    public void hideStore() {
        mRlProductStore.setVisibility(View.GONE);
        mStoreDiv.setVisibility(View.GONE);
    }

    //是否展示颜色和尺寸
    @Override
    public void showColorText(String colorText) {
        if (!TextUtils.isEmpty(colorText)) {
            mTxtProductColor.setVisibility(View.VISIBLE);
            mTxtProductColor.setText(colorText);
        }
    }

    @Override
    public void showSizeText(String sizeText) {
        if (!TextUtils.isEmpty(sizeText)) {
            mTxtProductSize.setVisibility(View.VISIBLE);
            mTxtProductSize.setText(sizeText);
        }
    }

    //选择商品颜色
    @Override
    public void setColorType(final List<SkuListBean.ListBean> mColor,
                             final ProductDetialBean.DataBean.GoodsInfoBean goodsInfoBean) {
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
            mProductPresenter.loadSku(mGoodsId, mColorData.get(position).getSku_value_id());
            //设置sku值
            mProductTypeBean.setSku_value_id(mColor.get(position).getSku_value_id());
        });
    }

    //设置尺寸
    @Override
    public void setSizeType(final List<SkuListBean.ListBean> mSize,
                            final ProductDetialBean.DataBean.GoodsInfoBean goodsInfoBean) {

        mSizeData.clear();
        mSizeData.addAll(mSize);
        mProductSizeAdapter.setSizeData(mSizeData);
        mProductSizeAdapter.setOnRecycLerViewItemOlick((View view, int position) -> {
            mProductPresenter.loadSkuSize(mGoodsId, mSizeData.get(position).getSku_value_id());
            mProductTypeBean.setSku_value_id(mSizeData.get(position).getSku_value_id());
        });
    }

    //展示样式图片popu弹窗
    private void showTypePopu(View view, String imageUrl) {
        PopupWindow popupWindow = new PopupWindow(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        View popu = LayoutInflater.from(ProductDetailsAct.this).inflate(R.layout.popuwindow_product_color, null);
        ImageView imageView = (ImageView) popu.findViewById(R.id.iv_popu_product_color);
        Glide.with(ProductDetailsAct.this).load(imageUrl).into(imageView);
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

    //设置商品详情和商品参数
    @Override
    public void loadProductDetial(String picUrl, String pramUrl) {
        mFragments.add(ProductDetialFrgm.newInstance(picUrl));
        mFragments.add(ProductDetialFrgm.newInstance(pramUrl));

        ProductDetialAdapter productDetialAdapter = new ProductDetialAdapter(getSupportFragmentManager(), mFragments);
        mTbProduct.setupWithViewPager(mVpProductDetial);
        mVpProductDetial.setAdapter(productDetialAdapter);
        mTbProduct.setTabsFromPagerAdapter(productDetialAdapter);
        mTbProduct.setSelectedTabIndicatorColor(getResources().getColor(loginBtnColor));
    }

    //展示商品下架
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
        mRelativeLayoutRoot.addView(textView);

        mTxtProductAddcar.setBackgroundColor(getResources().getColor(R.color.txthomeGoodsOld));
        mTxtProductAddcar.setClickable(false);

        mProductCollection.setClickable(false);
    }

    //添加到购物车成功
    @Override
    public void showAddCarSuccess() {
        mProductMorePopu = new ProductAddCarPopu(this);
        mProductMorePopu.showPopu(mTxtProductAddcar);
        mProductMorePopu.setOnBuyNowClickLisntner(() ->
                startActivity(new Intent(ProductDetailsAct.this, MyShoppingCartActivity.class)));
    }

    //设置sku值
    @Override
    public void setSkuValue(String skuValue) {
        mProductTypeBean.setSku_value_id(skuValue);
    }

    //隐藏添加到购物车的popu
    @Override
    public void hideAddCarSuccessPopu() {
        mProductMorePopu.dismissPopu();
    }

    //影藏颜色和尺寸sku
    @Override
    public void hideColorAndSize() {
        rlProductdetialColorandsize.setVisibility(View.GONE);
        mDiv.setVisibility(View.GONE);
    }

    @Override
    public void showColorSku() {

    }

    @Override
    public void showSizeSku() {

    }

    @Override
    public void showLoading() {
        // mElProductsdetialState.showLoading();
        mRlProductdetialError.setVisibility(View.GONE);

    }

    @Override
    public void showError() {
        mRlProductdetialError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideState() {
        mRlProductdetialError.setVisibility(View.GONE);
        //  mElProductsdetialState.hide();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //门店按钮
            case R.id.rl_product_store:
                startActivity(new Intent(this, DistributionShopAct.class));
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mBanner.size() > 0) {
            mTxtProductPointer.setText((position + 1) + "/" + mBanner.size() + "图");
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {


    }

    @Override
    public void onStatueChanged(DragScrollDetailsLayout.CurrentTargetIndex status) {
        //设置tab头部与顶部的距离
        if (DragScrollDetailsLayout.CurrentTargetIndex.UPSTAIRS == status) {
            //上半部分
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams1.topMargin = DimenUtils.dip2px(ProductDetailsAct.this, 0);
            layoutParams1.height = DimenUtils.dip2px(ProductDetailsAct.this, 40);
            mTbProduct.setLayoutParams(layoutParams1);
            mTitle.setVisibility(View.GONE);
            mRlProductTitlebar.getBackground().setAlpha(0);
            mIvBack.setVisibility(View.GONE);
        } else {
            //下半部分
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams1.topMargin = DimenUtils.dip2px(ProductDetailsAct.this, 65);
            layoutParams1.height = DimenUtils.dip2px(ProductDetailsAct.this, 40);
            mTbProduct.setLayoutParams(layoutParams1);
            mRlProductTitlebar.getBackground().setAlpha(255);
            mTitle.setVisibility(View.VISIBLE);

            mIvBack.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProductPresenter.loadGoodsCarNum(UserData.getUserId(), UserData.getUserToken());
    }

    @Override
    public void bannerOnclick() {
        Intent intent = new Intent(this, BannerDetialAct.class);
        intent.putExtra("images", (Serializable) mBanner);
        startActivity(intent);
    }

    //去登陆
    private void gotoLogin() {
        Intent intent = new Intent(this, LoginAct.class);
        startActivity(intent);
    }
}
