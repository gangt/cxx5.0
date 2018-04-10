package com.xi6666.network;


import android.content.Context;


import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr_yang on 2016/8/1.
 */
public class RetrofitUtil {
    private static RetrofitUtil retrofitUtil;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private String BaseUrl = ApiRest.baseUrl;
    private Context mContext;

    //加锁单例
    public static RetrofitUtil getInstance(Context context) {
        synchronized (RetrofitUtil.class) {
            if (retrofitUtil == null) {
                retrofitUtil = new RetrofitUtil(context);
            }
        }
        return retrofitUtil;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        mOkHttpClient = okHttpClient;
    }

    public String getBaseUrl() {
        return BaseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        BaseUrl = baseUrl;
    }

    public void setRetrofit(Retrofit retrofit) {
        synchronized (this) {
            this.mRetrofit = retrofit;
        }
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    /**
     * 初始化构建
     */
    public RetrofitUtil(Context context) {
        mContext = context;
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(15, TimeUnit.SECONDS)/*.cookieJar(*//*new CookiesManager()*//*)*/.build();
        }
        if (mRetrofit == null) {
            //retrofit 对象是可以单例的，因为 retrofit.create 每次创建的服务都是不一样的，可以单独取消请求
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .client(mOkHttpClient).addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
    }
    /*private class CookiesManager implements CookieJar {

        private final PersistentCookieStore cookieStore = new PersistentCookieStore(mContext.getApplicationContext());

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if (cookies != null && cookies.size() > 0) {
                // Log.d("CookiesManager", "saveFromResponse-->url=" + url + "\n cookies==" + cookies.toString());
                for (Cookie item : cookies) {
                    cookieStore.add(url, item);
                }
            }
        }
        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url);
            if (cookies != null && cookies.size() > 0) {
                //  Log.d("CookiesManager", "loadForRequest-->url=" + url + "\n cookies==" + cookies.toString());
            }
            return cookies;
        }
    }*/

    public <T> T dataService(Class<T> cls) {
        T service = mRetrofit.create(cls);
        return service;
    }
}
