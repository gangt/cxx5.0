package com.xi6666.car.http;

import android.text.TextUtils;

/**
 * 创建人 孙孙啊i
 * 时间 2016/6/12 0012.
 * 功能  网络请求帮助类
 */
public class HttpUtils {
    /**
     * 判断是否成功返回
     */
    public static boolean isSuccess(String success){
        return TextUtils.equals(success,"true");
    }
}
