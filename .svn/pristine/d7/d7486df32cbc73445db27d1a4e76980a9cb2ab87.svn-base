package com.xi6666.illegal.other;

import android.text.TextUtils;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

/**
 * 
 * @作者: Mr.yang
 * 
 * @创建时间:2014-12-2下午7:33:35
 * @描述: 对于String字符串进行操作
 * 
 */
public class StringUtil {

	public static final String	URL_REG_EXPRESSION		= "^(https?://)?([a-zA-Z0-9_-]+\\.[a-zA-Z0-9_-]+)+(/*[A-Za-z0-9/\\-_&:?\\+=//.%]*)*";
	public static final String	EMAIL_REG_EXPRESSION	= "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";
	public static final String TEL_REG_EXPRESSION = "(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$|(^(13[0-9]|15[0-9]|18[0-9])\\d{8}$)";

	/**
	 * @desc 判断该字符串是url
	 * @param s
	 * @return
	 */
	public static boolean isUrl(String s) {
		if (s == null) {
			return false;
		}
		return Pattern.matches(URL_REG_EXPRESSION, s);
	}

	/**
	 * @desc 判断该字符串是否是email
	 * @param s
	 * @return
	 */
	public static boolean isEmail(String s) {
		if (s == null) {
			return true;
		}
		return Pattern.matches(EMAIL_REG_EXPRESSION, s);
	}

	/**
	 * @desc 判断该字符串是否为空
	 * @param s
	 * @return
	 */
	public static boolean isBlank(String s) {
		if (s == null) {
			return true;
		}
		return Pattern.matches("\\s*", s);
	}

	public static String join(String spliter, Object[] arr) {
		if (arr == null || arr.length == 0) {
			return "";
		}
		if (spliter == null) {
			spliter = "";
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i == arr.length - 1) {
				break;
			}
			if (arr[i] == null) {
				continue;
			}
			builder.append(arr[i].toString());
			builder.append(spliter);
		}
		return builder.toString();
	}


	/**
	 * 电话正则判断
	 * @param tel	电话参数
	 * @return	是否电话参数
     */
	public static boolean isTel(String tel){
		if(TextUtils.isEmpty(tel))//如果电话为空或者"";
		{
			return false;
		}
		return tel.matches(TEL_REG_EXPRESSION);
	}
	public static boolean isTel(TextView tv){
		return isTel(tv.getText().toString());
	}
}
