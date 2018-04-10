package com.xi6666.address.fragment.mvp.bean;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xi6666.app.BaseApplication;
import com.xi6666.utils.AssetsUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建者 sunsun
 * 时间 2016/12/3 上午11:06.
 * 个人公众号 ardays
 */

public class CityGeographyBean {
    public String name;     //城市名字
    public List<String> geoCoord;   //经纬度

    private static List<CityGeographyBean> mCityGeographyBean;

    public static List<CityGeographyBean> getInstance() {
        if (mCityGeographyBean == null) {
            //获取json数据
            String json_str = AssetsUtils.readText(BaseApplication.getmAppContext(), "CityGeography");
            Log.e("TAG","json_str--->" + json_str);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<CityGeographyBean>>() {
            }.getType();
            //解析json数据
            mCityGeographyBean = gson.fromJson(json_str, type);
        }
        return mCityGeographyBean;
    }


    /**
     * 获取经纬度
     */
    public static List<String> getLocation(String city) {
        //遍历数组判断数组中是否存在该城市
        for (CityGeographyBean data : CityGeographyBean.getInstance()) {
            if (TextUtils.equals(data.name, city)) {
                return data.geoCoord;
            }
        }
        return null;
    }
}
