package com.xi6666.common;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.math.BigDecimal;

/**
 * Created by Mr_yang on 2015/6/4.
 * 百度定位功能的简单封装
 */
public class LocationServiceS {
    private String TAG = "LocationService";
    public static final int DATA = 6;
    private LocationClient client;
    private LocationClientOption option;
    private  OnLocationListener mOnLocationListener;

    public void setOnLocationListener(OnLocationListener onLocationListener) {
        mOnLocationListener = onLocationListener;
    }

    /**
     * 构造函数
     */
    public LocationServiceS(Context context) {
        client = new LocationClient(context);// 实例化定位类
        client.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                if (location != null) {
                    int locType = location.getLocType();// 获取定位类型，161网络定位
                    // 61GPS定位
                    Log.w(TAG, "Location type = " + locType);
                    if (locType == BDLocation.TypeNetWorkLocation
                            || locType == BDLocation.TypeGpsLocation) {
                        double latitude = location.getLatitude();// 返回维度
                        double longitude = location.getLongitude();// 返回经度
                        String address = location.getAddrStr();// 返回地理信息
                        String city = location.getCity();
                        String province = location.getProvince();
                        String citycode = location.getCityCode();
                        float radius = -1;
                        if (location.hasRadius()) {
                            radius = location.getRadius();// 获取定位精度半径，单位是米
                            BigDecimal b = new BigDecimal(radius);
                            radius = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                        }

                        StringBuffer json = new StringBuffer();
                        json.append("{\"locType\":\"")
                                .append(locType == BDLocation.TypeNetWorkLocation ? "网络定位"
                                        : "GPS定位").append("\",");
                        json.append("\"latitude\":\"").append(latitude).append("\",");
                        json.append("\"longitude\":\"").append(longitude).append("\",");
                        json.append("\"radius\":\"").append(radius).append("\",");
                        json.append("\"address\":\"").append(address).append("\",");
                        json.append("\"city\":\"").append(city).append("\",");
                        json.append("\"cityCode\":\"").append(citycode).append("\",");
                        json.append("\"province\":\"").append(province).append("\"}");

                        Log.d(TAG, "定位结果------------> " + json.toString());
                        mOnLocationListener.locationSuccess(json.toString());

                    }
                }
            }
        });// 设置监听
        option = new LocationClientOption();// 实例化定位参数
        option.setAddrType("all");// 设置返回地理信息
        option.setOpenGps(true);
        // option.setPriority(LocationClientOption.GpsFirst);
        option.setCoorType("bd09ll");// 设置为百度坐标系
        option.disableCache(true);// 不使用缓存
        client.setLocOption(option);// 设置参数
    }

    public interface OnLocationListener {
        void locationSuccess(String string);
    }


    /**
     * 开启定位
     */
    public void start() {
        if (client.isStarted()) {
            Log.w(TAG, "定位服务--->正在运行");
        } else {
            Log.d(TAG, "--->开启定位");
            client.start();
        }
    }

    /**
     * 是否正在定位
     *
     * @return
     */
    public boolean isStarted() {
        return client.isStarted();
    }

    /**
     * 停止定位
     */
    public void stop() {
        Log.d(TAG, "--->定位服务 关闭");
        client.stop();

    }
    /**
     * 通过经纬度获取地理信息
     *
     * @param latitude
     * @param longitude
     * @return
     */
    /*public synchronized String getAddress(String latitude, String longitude) {
        HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(String.format(
				"http://api.map.baidu.com/geocoder/v2/?ak=%s&location=%s,%s&output=%s&pois=%s",
				"C2ab471c7883b11890e509c2abb27b56", latitude, longitude, "json", "0"));
		try {
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String json = EntityUtils.toString(entity, "UTF-8");
				JSONObject object = new JSONObject(json);
				int status = object.getInt("status");
				if (status == 0) {
					String address = object.getJSONObject("result").getString("formatted_address");
					if (address != null && !"".equals(address.trim())) {
						return address;
					}
				}
			}
		} catch (Exception e) {
			Log.e(TAG, "", e);
		}
		return null;
	}
*/
}
