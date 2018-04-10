package com.xi6666.carWash.base.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xi6666.app.BaseApplication;
import com.xi6666.common.UserData;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.NetworkUtil;
import com.xi6666.utils.SpUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建人 孙孙啊i
 * 时间 2016/11/19 0019.
 * 功能
 */
public class Api {
    //默认服务器地址
//    public static final String BASE_URL = "http://dev-app.xiaoxi6.com";
    public static final String BASE_URL = ApiRest.baseUrl;



    public APIUrls mAppUrls;
    public Retrofit mRetrofit;


    //构造方法私有
    private Api() {
        //初始化拦截器
//        OkLogInterceptor interceptor = OkLogInterceptor.builder().build();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY); //设置身体拦截器

        //设置APP缓存地址
        File cacheFile = new File(BaseApplication.getmAppContext().getCacheDir(), "cache");
        //设置缓存大小(100MB)
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(7676, TimeUnit.MILLISECONDS)   //设置读取时间
                .connectTimeout(7676, TimeUnit.MILLISECONDS) //设置链接超时
//                .addInterceptor(interceptor)            //添加拦截器
                .addInterceptor(new ParamsInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor()) //添加网络拦截器(缓存拦截器)
                .cache(cache)                           //设置数据缓存地址
                .build();

        //创建gson格式
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:zzZ").serializeNulls().create();

        //初始化
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        //设置接口地址
        mAppUrls = mRetrofit.create(APIUrls.class);
    }

    /**
     * 网络拦截器
     */
    class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //判断当前网络连接
            if (!NetworkUtil.isConnected(BaseApplication.getmAppContext())) {
                //没网络的时候逻辑
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                LogUtil.d("Okhttp", "no network");
            }

            Response originalResponse = chain.proceed(request);
            if (NetworkUtil.isConnected(BaseApplication.getmAppContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200") //请求头
                        .removeHeader("Pragma")  //移除之前头部
                        .build();
            }
        }
    }


    //设置单利类
    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 添加拦截器
     */
    class CommonInterceptor implements Interceptor{


        @Override
        public Response intercept(Chain chain) throws IOException {
            //获取请求
            Request oldRequest = chain.request();
            //设置新的路径
            HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host())
                    .addQueryParameter("user_id", UserData.getUserId())
                    .addQueryParameter("user_token", UserData.getUserToken());


            //添加属性头
            Request newRequest = oldRequest.newBuilder()
                    .method(oldRequest.method(), oldRequest.body())
                    .url(authorizedUrlBuilder.build())
                    .build();

            //参数打印
            LogUtil.e("本次请求的地址是", newRequest.toString());
            LogUtil.e("本次请求的地址是", newRequest.body().toString());

            return chain.proceed(newRequest);
        }
    }
}
