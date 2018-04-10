package com.xi6666.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 创建者 sunsun
 * 时间 16/11/24 下午6:29.
 * 个人公众号 ardays
 */

public class AssetsUtils {

    /**
     * read file content
     *
     * @param context   the context
     * @param assetPath the asset path
     * @return String string
     */
    public static String readText(Context context, String assetPath) {
        try {
            return toString(context.getAssets().open(assetPath));
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * To string string.
     *
     * @param is the is
     * @return the string
     */
    public static String toString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
        } catch (IOException e) {
        }
        return sb.toString();
    }
}
