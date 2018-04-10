package com.xi6666.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @项目名称: 车小喜
 * @包名: com.xiaoxilexiang.chexiaoxi.activity.utils
 * @作者: Mr.yang
 * 
 * @创建时间:2015-8-18上午9:53:10
 * @描述: sharedperences类用来保存数据的类
 * 
 * @当前的版本号: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: TODO
 */
public class SpUtils {
	//sp的名称
	private final static String			SP_NAME	= "cxx";
	private static SharedPreferences	sp;

	private static SharedPreferences getSp(Context context) {
		if (sp == null) {
			sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		}
		return sp;
	}

	/**
	 * 获取boolean 数据
	 * 
	 * @param context
	 * @param key
	 * @return 如果没有值，返回false
	 */
	public static boolean getBoolean(Context context, String key) {
		SharedPreferences sp = getSp(context);
		return sp.getBoolean(key, false);
	}

	/**
	 * 获取boolean 数据
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static boolean getBoolean(Context context, String key, boolean defValue) {
		SharedPreferences sp = getSp(context);
		return sp.getBoolean(key, defValue);
	}

	/**
	 * 存boolean缓存
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void setBoolean(Context context, String key, boolean value) {
		SharedPreferences sp = getSp(context);
		Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 获取String 数据
	 * 
	 * @param context
	 * @param key
	 * @return 如果没有值，返回null
	 */
	public static String getString(Context context, String key) {
		SharedPreferences sp = getSp(context);
		return sp.getString(key, "");
	}

	/**
	 * 获取String 数据
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getString(Context context, String key, String defValue) {
		SharedPreferences sp = getSp(context);
		return sp.getString(key, defValue);
	}

	/**
	 * 存String缓存
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void setString(Context context, String key, String value) {
		SharedPreferences sp = getSp(context);
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 获取int 数据
	 * 
	 * @param context
	 * @param key
	 * @return 如果没有值，返回-1
	 */
	public static int getInt(Context context, String key) {
		SharedPreferences sp = getSp(context);
		return sp.getInt(key, -1);
	}

	/**
	 * 获取int 数据
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getInt(Context context, String key, int defValue) {
		SharedPreferences sp = getSp(context);
		return sp.getInt(key, defValue);
	}

	/**
	 * 存int缓存
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void setInt(Context context, String key, int value) {
		SharedPreferences sp = getSp(context);
		Editor editor = sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}
}
