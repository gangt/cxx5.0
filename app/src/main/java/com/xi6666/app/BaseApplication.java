package com.xi6666.app;

/**
 * Created by Mr_yang on 2016/10/11.
 */

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;
import com.xi6666.common.Constant;
import com.xi6666.utils.AppUitls;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.SpUtils;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * @author peng
 * @data 创建时间:2016/10/11
 * @desc 程序的入口
 */
public class BaseApplication extends Application {
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;
    private static Context mAppContext;
    private AppComponets mAppComponets;

    private static BaseApplication mbaBaseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mbaBaseApplication = this;
        mAppContext = getApplicationContext();
        //  UBKAd.initialize(this); //调用SDK初始化方法
        initLog();//初始化log信息
        initJpush(); //极光推送
        // initCrashUtils();  //异常抓取
        getScreenSize();//获取屏幕信息
        setStrictMode();//设置严格检查模式
        // initComponent();
        initBugly();

        // LeakCanary.install(this);
        //debug地址切换（上线以后删除）
        if (!TextUtils.isEmpty(SpUtils.getString(this, "baseUrl"))) {
            Constant.BaseUrl = SpUtils.getString(this, "baseUrl");
        }
    }

    /**
     * 初始化bugly
     */
    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext());
    }

    private void initLog() {
        if (!AppUitls.isApkInDebug(this)) {
            LogUtil.isDebug = false;
        }
    }

    private void initCrashUtils() {
        //判断是否是dubug包
        if (!AppUitls.isApkInDebug(this)) {
            CrashUtils.getInstance().init(this);
        }
    }

    private void initJpush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        JPushInterface.setAlias(this, "110",
                (int i, String s, Set<String> set) -> Log.d("BaseAppliation", "得到的状态码是===" + i));
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    private void setStrictMode() {
        if (AppUitls.isApkInDebug(this) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }
    }

    private void initComponent() {
        mAppComponets = DaggerAppComponets.builder().appModule(new AppModule(this)).apiModule(new ApiModule(this)).build();
    }

    public static Context getmAppContext() {
        return mAppContext;
    }

    public static BaseApplication getApplication() {
        return mbaBaseApplication;
    }


    public AppComponets getAppComponets() {
        return mAppComponets;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
