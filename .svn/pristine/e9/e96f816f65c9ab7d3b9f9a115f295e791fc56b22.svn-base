package com.xi6666.common;

import android.text.TextUtils;

import com.xi6666.app.BaseApplication;
import com.xi6666.utils.SpUtils;

/**
 * Created by Mr_yang on 2016/11/12.
 */

public class UserData {
    private static String userName = "userId";

    private static String userDataName = "userData";

    private static String userTokenName = "userToken";

    //清除用户本地配置的数据
    public static void cleanUserData() {
        setUserId("");
        setUserData("");
        setUserToken("");
    }

    public static String getUserId() {
        return SpUtils.getString(BaseApplication.getmAppContext(), userName);
    }

    public static void setUserId(String userId) {
        SpUtils.setString(BaseApplication.getmAppContext(), userName, userId);
    }

    public static String getUserData() {

        return SpUtils.getString(BaseApplication.getmAppContext(), userDataName);
    }

    public static void setUserData(String userData) {
        SpUtils.setString(BaseApplication.getmAppContext(), userDataName, userData);
    }

    public static String getUserToken() {
        return SpUtils.getString(BaseApplication.getmAppContext(), userTokenName);
    }

    public static void setUserToken(String userToken) {
        SpUtils.setString(BaseApplication.getmAppContext(), userTokenName, userToken);
    }

    public static boolean isLoginIn() {
        return (TextUtils.isEmpty(getUserId()) && TextUtils.isEmpty(getUserToken())) ? false : true;
    }
}
