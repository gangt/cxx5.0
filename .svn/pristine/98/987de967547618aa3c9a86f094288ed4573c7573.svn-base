package com.xi6666.utils;

/**
 * @作者: Mr.yang
 * @创建时间:2013-12-2下午7:27:54
 * @描述: log信息的管理工具
 * @使用方式: 在开发的时候将isDebug设置为true, 就可以显示调试的信息.发布app的时候将isDebug修改为false.则不会将所有的log信息输出
 */

public class LogUtil {
    //正式版将此字段修改为false即可
    public static boolean isDebug = true;

    public static void v(String tag, String msg) {
        if (isDebug)
            android.util.Log.v(tag, msg);
    }

    public static void v(String tag, String msg, Throwable t) {
        if (isDebug)
            android.util.Log.v(tag, msg, t);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            android.util.Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable t) {
        if (isDebug)
            android.util.Log.d(tag, msg, t);
    }

    public static void i(String tag, String msg) {
        if (isDebug)
            android.util.Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable t) {
        if (isDebug)
            android.util.Log.i(tag, msg, t);
    }

    public static void w(String tag, String msg) {
        if (isDebug)
            android.util.Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable t) {
        if (isDebug)
            android.util.Log.w(tag, msg, t);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            android.util.Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable t) {
        if (isDebug)
            android.util.Log.e(tag, msg, t);
    }
}
