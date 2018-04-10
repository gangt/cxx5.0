package com.xi6666.utils;

/**
 * Created by Mr_yang on 2016/10/13.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author peng
 * @data 创建时间:2016/10/13
 * @desc app的帮助工具类
 */
public class AppUitls {
    /**
     * 判断是否安装某一包名
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    /**
     * 打开应用商城去下载app
     */
    public static void startAppShop(Context context, String toastShow, String packgeName) {
        //显示手机上所有的market商店
        Toast.makeText(context, toastShow, Toast.LENGTH_SHORT).show();
        Uri uri = Uri.parse("market://details?id=" + packgeName);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    /**
     * @data 创建时间:2016/10/13
     * @author peng
     * @desc 判断当前的包是否是debug包
     * @version
     */
    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @data 创建时间:2016/10/17
     * @author peng
     * @desc 获取app版本名
     * @version
     */
    public static String getAppVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @data 创建时间:2016/10/17
     * @author peng
     * @desc 获取app版本信息
     * @version
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @data 创建时间:2016/10/17
     * @author peng
     * @desc 获取app版本号
     * @version
     */
    public static int getAppVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /***
     * @data 创建时间:2016/10/31
     * @author Sun
     * @desc 获取状态栏高度
     * @version
     */
    public static int getStuatsBarSize(Activity mActivity){
        int result = 0;
        int resourceId = mActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mActivity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    /**
     * @data 创建时间:2016/10/24
     * @author peng
     * @desc 判断是否打开了下载管理器
     * @version
     */
    public static boolean isDownloadManagerAvailable(Context context) {
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD
                    || context.getPackageManager()
                    .getApplicationEnabledSetting(
                            "com.android.providers.downloads") == context
                    .getPackageManager().COMPONENT_ENABLED_STATE_DISABLED_USER
                    || context.getPackageManager()
                    .getApplicationEnabledSetting(
                            "com.android.providers.downloads") == context
                    .getPackageManager().COMPONENT_ENABLED_STATE_DISABLED
                    || context.getPackageManager()
                    .getApplicationEnabledSetting(
                            "com.android.providers.downloads") == context
                    .getPackageManager().COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {

                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
