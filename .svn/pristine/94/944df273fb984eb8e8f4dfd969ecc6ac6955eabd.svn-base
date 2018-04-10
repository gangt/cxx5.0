package com.xi6666.carWash.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.carWash.base.view.CxxErrorView;
import com.xi6666.utils.TUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/10/31 下午4:14.
 * 个人公众号 ardays
 */

public abstract class BaseToolbarView<P extends BasePresenter,M
        extends BaseModel>
        extends BaseAct implements BaseToolbarViewImpl {


    /**
     * 设置标题
     */
    public abstract String title();

    /**
     * Main布局
     */
    public abstract int mainResId();

    /**
     * 侵入式布局
     */
    public int backgroundResId() {
        return 0;
    }

    /**
     * 初始化
     */
    public abstract void setUp(View view);


    @BindView(R.id.toolbar)
    public View mToolbarV; //标题栏
    @BindView(R.id.toolbar_rv)
    public View mToolbarRV; //标题栏颜色
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitleTv;   //标题名
    @BindView(R.id.toolbar_left_iv)
    ImageView mToolbarLeftIv;     //标题栏左按钮
    @BindView(R.id.toolbar_right_iv)
    ImageView mToolbarRightIv;    //标题栏右边按钮
    @BindView(R.id.toolbar_right_tv)
    TextView mToolbarRightTitleTv;   //右边文本
    @BindView(R.id.toolbar_line)
    View mToolbarLineV;//标题栏下面分割线
    @BindView(R.id.toolbar_right_number_tv)
    TextView mToolbarNumberTv;  //标题栏右边数字
    @BindView(R.id.toolbar_body)
    LinearLayout mBodyView; //身体内容
    @BindView(R.id.toolbar_background)
    LinearLayout mBackgroundView;//侵入式布局
    @BindView(R.id.toolbar_error_view)
    CxxErrorView mErrorView;     //网络加载错误的页面，用于一些全局加载

    public View mMainView;  //身体
    public P mPersenter; //业务逻辑层
    public M mModel;     //网络层


    @Override
    public int getLayoutId() {
        return R.layout.activity_toolbar_main;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        uiCreate();
        //沉侵式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        super.onCreate(savedInstanceState);
    }

    public void uiCreate(){

    }


    @Override
    public void init() {
        mPersenter = TUtils.getT(this, 0);
        mModel = TUtils.getT(this, 1);
        if(this instanceof BaseView) mPersenter.setVm(this, mModel); //添加MVP的View回调事件
        initToolbar();
        initLayout();
        setUp(mMainView);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPersenter != null) mPersenter.onDestory(); //解除MVP模式绑定
    }

    /**
     * 设置子布局
     */
    private void initLayout() {
        if (backgroundResId() == 0) {
            setMainView(mainResId());
            mMainView.setVisibility(View.VISIBLE);
        } else {
            setBackgroundView(backgroundResId());
            mBackgroundView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void setMainView(int resId) {
        mMainView = getLayoutInflater().inflate(resId, mBodyView);
    }

    @Override
    public void setBackgroundView(int resId) {
        mMainView = getLayoutInflater().inflate(resId, mBackgroundView);
    }


    /*                                     设置标题栏                                          */

    /**
     * 状态栏初始化
     */
    private void initToolbar() {
        //沉侵式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mToolbarV.setPadding(0, 60, 0, 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                setMiuiStatusBarDarkMode(this, true);
            else
                mToolbarV.setBackgroundColor(Color.rgb(240, 240, 240));
        }

        //设置标题
        mToolbarTitleTv.setText(title());
        //左按钮回调事件
        mToolbarLeftIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onToolbarLeftClick(v);
            }
        });
    }

    @Override
    public void setToolbarLeftIcon(int resId) {
        mToolbarLeftIv.setImageResource(resId);
    }


    @Override
    public void setToolbarTitleColor(int color) {
        mToolbarTitleTv.setTextColor(color);
    }

    @Override
    public void setToolbarRightIcon(int resId) {
        mToolbarRightIv.setImageResource(resId);
        mToolbarRightIv.setVisibility(View.VISIBLE);
        mToolbarRightIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onToolbarRightClick(v);
            }
        });
    }


    @Override
    public void setToolbarRightText(String text) {
        mToolbarRightTitleTv.setText(text);
        mToolbarRightTitleTv.setVisibility(View.VISIBLE);
        mToolbarRightTitleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onToolbarRightClick(v);
            }
        });
    }

    @Override
    public void setToolbarRightColor(int resId) {
        mToolbarRightTitleTv.setTextColor(resId);
    }

    @Override
    public void setToolbarRightNumberTitle(String number) {
        if(TextUtils.isEmpty(number) || number.equals("0")){
            mToolbarNumberTv.setVisibility(View.GONE);
        }else {
            mToolbarNumberTv.setVisibility(View.VISIBLE);
            mToolbarNumberTv.setText(number);
        }
    }

    @Override
    public void setToolbarTitle(String title) {
        mToolbarTitleTv.setText(title);
    }

    @Override
    public void onToolbarLeftClick(View view) {
        finish();
    }

    @Override
    public void onToolbarRightClick(View view) {

    }

    @Override
    public void setToolbarLeftColor(int color) {
    }

    @Override
    public void setToolbarColorTransparent(int i) {
        int transparent = i / 100.0 > 1 ? 255 : (int) (i / 100.0 * 255);
        Log.e("TAG","i--->" + transparent);

        int rbColor = Color.argb(transparent, 255, 255, 255);
        int lineColor = Color.argb(transparent, 226, 226, 226);
        mToolbarRV.setBackgroundColor(rbColor);
        mToolbarLineV.setBackgroundColor(lineColor);


        // 判断当前版本是否大于6.0如果大于就让他标题栏变白
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            mToolbarV.setBackgroundColor(rbColor);
        }else{
            int vColor = Color.argb(transparent, 240, 240, 240);
            mToolbarV.setBackgroundColor(vColor);
        }
    }


    /**
     * 适配miui系统标题栏颜色
     */
    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*                                     错误页面                                          */

    @Override
    public void showErrorView() {
        hiddenLoading();
        mErrorView.setVisibility(View.VISIBLE);
    }
    @Override
    public void hiddenErrorView() {
        mErrorView.setVisibility(View.GONE);
    }

    public void errorClick(View.OnClickListener listener){
        mErrorView.setOnErrorClickListener(listener);
    }


}
