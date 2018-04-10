package com.xi6666.carWash.base.api;

import android.content.Context;
import android.util.Log;

import com.xi6666.common.UserData;
import com.xi6666.utils.LogUtil;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 创建者 sunsun
 * 时间 16/11/23 下午2:19.
 * 个人公众号 ardays
 */

public class ParamsInterceptor implements Interceptor {
    private static final String TAG = "此次访问的接口参数--->";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request orgRequest = chain.request();

        RequestBody body = orgRequest.body();
        //收集请求参数，方便调试
        StringBuilder paramsBuilder = new StringBuilder();

        if (body != null) {
            RequestBody newBody = null;

            if (body instanceof FormBody) {
                newBody = addParamsToFormBody((FormBody) body, paramsBuilder);
            } else if (body instanceof MultipartBody) {
                newBody = addParamsToMultipartBody((MultipartBody) body, paramsBuilder);
            }


            if (null != newBody) {
                Log.e("TAG", "newBody?");
                //打印参数

                Request newRequest = orgRequest.newBuilder()
                        .url(orgRequest.url())
                        .method(orgRequest.method(), newBody)
                        .build();

                Log.e(TAG, newRequest.url().url().toString() + "?" + paramsBuilder.toString());
                return chain.proceed(newRequest);
            }
        }
        return chain.proceed(orgRequest);

    }

    /**
     * 为MultipartBody类型请求体添加参数
     *
     * @param body
     * @param paramsBuilder
     * @return
     */
    private MultipartBody addParamsToMultipartBody(MultipartBody body, StringBuilder paramsBuilder) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        //添加user_id 和 user_tokey
        String user_id = "11058";
        builder.addFormDataPart("user_id", user_id);
        paramsBuilder.append("user_id=" + user_id);

        String user_token = "35b64f785741b74ea2d04d1869b3fe85";
        builder.addFormDataPart("user_token", user_token);
        paramsBuilder.append("&");
        paramsBuilder.append("user_token=" + user_token);

        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addPart(body.part(i));
        }

        return builder.build();
    }

    /**
     * 为FormBody类型请求体添加参数
     *
     * @param body
     * @param paramsBuilder
     * @return
     */
    private FormBody addParamsToFormBody(FormBody body, StringBuilder paramsBuilder) {
        FormBody.Builder builder = new FormBody.Builder();
        //添加user_id 和 user_tokey
        String user_id = UserData.getUserId();
        builder.add("user_id", user_id);
        paramsBuilder.append("user_id=" + user_id);

        String user_token = UserData.getUserToken();
        builder.add("user_token", user_token);
        paramsBuilder.append("&");
        paramsBuilder.append("user_token=" + user_token);


        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addEncoded(body.encodedName(i), body.encodedValue(i));
            paramsBuilder.append("&");
            paramsBuilder.append(body.name(i));
            paramsBuilder.append("=");
            paramsBuilder.append(body.value(i));
        }

        return builder.build();
    }
}
