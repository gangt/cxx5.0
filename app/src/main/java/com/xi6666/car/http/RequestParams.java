package com.xi6666.car.http;

import android.text.TextUtils;

import com.xi6666.app.BaseApplication;
import com.xi6666.carWash.base.api.Api;
import com.xi6666.common.UserData;
import com.xi6666.utils.SpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建人 孙孙啊i
 * 时间 2016/6/12 0012.
 * 功能  方便开发，用于存储URL 路径 和参数等
 */
public class RequestParams {

    private Map<String, Object> mUrlParmasp;
    //接口地址
    private String mPath;
    //url地址
    private String mUrl;


    public RequestParams() {
        //完成初始化框架
        mUrlParmasp = new HashMap<>();
    }


    /**
     * 写入开头连接
     */
    public void setPath(String path) {
        this.mPath = path;
//        mUrlParmasp.put("user_id", SpUtils.getString(BaseApplication.getmAppContext(),"userId"));
        mUrlParmasp.put("user_id", UserData.getUserId());
        mUrlParmasp.put("user_token", UserData.getUserToken());
        mUrlParmasp.put("user_phone_type", "android");
    }


    /**
     * url
     */
    public void setUrl(String url) {
        this.mUrl = url;
    }


    /**
     * 这里用来存储参数
     */
    public void put(String key, Object value) {
        mUrlParmasp.put(key, value);
    }

    /**
     * 这里是获取接口参数的东西
     */
    public String getUrl() {
        String url;
        if (TextUtils.isEmpty(mUrl)) {
            url = Api.BASE_URL;
            if (TextUtils.isEmpty(mPath)) {
                url += "?";
            } else {
                //这里写开头.
                url += mPath + "&";
            }
        } else {
            url = mUrl + "?";
        }

        if (mUrlParmasp.size() > 0) {
            /**
             * 遍历参数
             */
            for (String key : mUrlParmasp.keySet()) {
                if (TextUtils.isEmpty(mUrlParmasp.get(key) + "") || TextUtils.equals(mUrlParmasp.get(key) + "", "null")) {
                    continue;
                }

                url += key + "=" + mUrlParmasp.get(key) + "&";
            }
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }

    public void addUrl(String url) {
        mUrl = Api.BASE_URL + url;
        mUrlParmasp.put("user_id", UserData.getUserId());
        mUrlParmasp.put("user_token", UserData.getUserToken());
        mUrlParmasp.put("user_phone_type", "android");
    }

}
