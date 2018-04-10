package com.xi6666.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.DecimalFormat;

/**
 * 创建者 sunsun
 * 时间 16/11/2 下午5:41.
 * 个人公众号 ardays
 * 一些日常便于开发封装的方法
 */

public class CxxUtils {


    /**
     * 保留两位小数
     * 例如,传进来20 自动转换返回20.00
     */
    private static DecimalFormat mDoubleDf = new DecimalFormat("######0.00");
    public static String getDoubleMoeny(String moeny){
        return mDoubleDf.format(Double.parseDouble(moeny));
    }


    /**
     * 虚拟键盘缩下
     */
    public static void hideSoftInput(Context context, View editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }





}
