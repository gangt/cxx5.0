package com.xi6666.car.adapter;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;


/**
 * 创建者 sunsun
 * 时间 16/11/15 下午2:34.
 * 个人公众号 ardays
 */
public class AssortPinyinList {

    private HashList<String, String> hashList = new HashList<String, String>(new KeySort<String, String>() {
        public String getKey(String value) {
            return getFirstChar(value);
        }
    });

    //首字母
    public static String getFirstChar(String value) {
        if(TextUtils.isEmpty(value)){
            return "#";
        }

        char firstChar = 0;
        try {
            firstChar = value.charAt(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String first = null;

        String[] print = PinyinHelper.toHanyuPinyinStringArray(firstChar);

        if (print == null) {


            if ((firstChar >= 97 && firstChar <= 122)) {
                firstChar -= 32;
            }
            if (firstChar >= 65 && firstChar <= 90) {
                first = String.valueOf(firstChar);
            } else {

                first = "#";
            }
        } else {

            first = String.valueOf((char) (print[0].charAt(0) - 32));
        }
        if (first == null) {
            first = "?";
        }
        return first;
    }

    public HashList<String, String> getHashList() {
        return hashList;
    }

}