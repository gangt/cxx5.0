package com.xi6666.common;

import com.xi6666.app.BaseApplication;
import com.xi6666.utils.SpUtils;


/**
 * Created by Mr_yang on 2016/6/4.
 */
public class CityBean {

    public static void setProvince(String province) {
        SpUtils.setString(BaseApplication.getmAppContext().getApplicationContext(), "province", province);
    }

    public static String getProvince() {

        return SpUtils.getString(BaseApplication.getmAppContext().getApplicationContext(), "province");
    }


    public static void setCity(String city) {
        SpUtils.setString(BaseApplication.getmAppContext().getApplicationContext(), "city", city);

    }

    public static String getCity() {
        return SpUtils.getString(BaseApplication.getmAppContext().getApplicationContext(), "city");
    }

    public static void setLng(String lng) {
        SpUtils.setString(BaseApplication.getmAppContext().getApplicationContext(), "lng", lng);
    }

    public static String getLng() {
        return SpUtils.getString(BaseApplication.getmAppContext().getApplicationContext(), "lng");
    }

    public static void setLat(String lat) {
        SpUtils.setString(BaseApplication.getmAppContext().getApplicationContext(), "lat", lat);
    }

    public static String getLat() {
        return SpUtils.getString(BaseApplication.getmAppContext().getApplicationContext(), "lat");
    }

    public static String getAddress() {
        return SpUtils.getString(BaseApplication.getmAppContext().getApplicationContext(), "address");
    }

    public static void setAddress(String addres) {
        SpUtils.setString(BaseApplication.getmAppContext().getApplicationContext(), "address", addres);
    }

    public static String getRegionId() {
        return SpUtils.getString(BaseApplication.getmAppContext().getApplicationContext(), "regionid");
    }

    public static void setRegionId(String regionid) {
        SpUtils.setString(BaseApplication.getmAppContext().getApplicationContext(), "regionid", regionid);
    }
}
