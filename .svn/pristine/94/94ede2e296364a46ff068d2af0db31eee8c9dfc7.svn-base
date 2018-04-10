package com.xi6666.car.http;


import android.app.Activity;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.xi6666.utils.LogUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 创建人 孙孙啊i
 * 时间 2016/6/12 0012.
 * 功能 这里主要实现了网络请求框架
 */
public class OkHttp {

    static OkHttpClient mOkHttpClient = new OkHttpClient();

    /**
     * get请求
     */
    public static <T>void get(RequestParams params, final Class<T> bean, final OkHttpCallBack<T> callBack){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(params.getUrl())
                .build();

        Call call = mOkHttpClient.newCall(request);

        LogUtil.e("网络请求","此次访问的URL是-->" + params.getUrl());

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson  = new Gson();
                String jsonStr = response.body().string();
                LogUtil.e("TAG", "json数据--->" + jsonStr);
                try {
                    callBack.onResponse(gson.fromJson(jsonStr, bean));
                }catch (JsonSyntaxException e){
                    Log.e("TAG","json数据解析错误");
                }
            }
        });

    }

    /**
     * get请求
     */
    public static <T>void get(final RequestParams params, final Class<T> bean, final Activity context, final OkHttpCallBack<T> callBack){
        final Request request = new Request.Builder()
                .url(params.getUrl())
                .build();

        Call call = mOkHttpClient.newCall(request);

        LogUtil.e("网络请求","此次访问的URL是-->" + params.getUrl());

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if(Looper.myLooper() == null)
                {
                    Looper.prepare();
                }
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if(Looper.myLooper() == null)
                {
                    Looper.prepare();
                }
                final Gson gson = new Gson();
                try {
                    final String jsonStr = response.body().string();
                    LogUtil.e("TAG", "json数据--->" + jsonStr);
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onResponse(gson.fromJson(jsonStr,bean));
                        }
                    });
                } catch (IOException e) {

                }catch (JsonSyntaxException e) {
                }
            }
        });
    }

    /**
     * get请求
     */
    public static <T>void getStr(final RequestParams params, final Activity context, final OkHttpCallBackStr callBack){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(params.getUrl())
                .build();

        Call call = mOkHttpClient.newCall(request);

        LogUtil.e("网络请求","此次访问的URL是-->" + params.getUrl());

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if(Looper.myLooper() == null)
                {
                    Looper.prepare();
                }
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if(Looper.myLooper() == null)
                {
                    Looper.prepare();
                }
                final Gson gson = new Gson();
                try {
                    final String jsonStr = response.body().string();
                    LogUtil.e("TAG", "json数据--->" + jsonStr);
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onResponse(jsonStr);
                        }
                    });
                } catch (IOException e) {

                }catch (JsonSyntaxException e) {
                }
            }
        });
    }


    /**
     * 这是post
     */
    public static <T>void post(RequestParams params, final Class<T> bean, final Activity context, final OkHttpCallBack<T> callBack){

        RequestBody formBody = new FormBody.Builder()
                .build();

        /**
         * url =
         */

        Request request = new Request.Builder()
                .url(HttpConstants.WEB_URL)
                .post(formBody)
                .build();

        Call call = mOkHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if(Looper.myLooper() == null)
                {
                    Looper.prepare();
                }
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(Looper.myLooper() == null)
                {
                    Looper.prepare();
                }
                Gson gson  = new Gson();
                String jsonStr = response.body().string();
                LogUtil.e("TAG", "json数据--->" + jsonStr);
                try {
                    callBack.onResponse(gson.fromJson(jsonStr, bean));
                }catch (JsonSyntaxException e){
                    Log.e("TAG","json数据解析错误");
                }
            }
        });
    }


    public interface OkHttpCallBackStr{
        void onFailure(Exception e);
        void onResponse(String bean);
    }


    public interface OkHttpCallBack<T>{
        void onFailure(Exception e);
        void onResponse(T bean);
    }
}
