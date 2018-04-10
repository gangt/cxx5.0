package com.xi6666.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mr_yang on 2016/8/1.
 */
public class RetrofitCallBackUtil<T> {
    private static final String TAG = "RetrofitCallBackUtil";
   /* private Call<T> mCall;*/
    private Context mContext;

    public RetrofitCallBackUtil(Context context/*, Call call*/) {
       /* mCall = call;*/
        mContext = context;
    }



    public void handleResponse(final ResponseListener listener,Call mCall) {
        mCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response != null && response.isSuccessful() && response.errorBody() == null && (response.code() == 200)) {
                    Log.d(TAG, "body==" + response.body());
                    listener.onSuccess((T) response.body());
                } else {
                    Toast.makeText(mContext, "亲，很抱歉服务器异常001，请稍后再试！", Toast.LENGTH_LONG).show();
                    listener.onFail();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.d(TAG, "error:" + t.getMessage());
                Toast.makeText(mContext, "网络请求出现异常002，请稍后再试！", Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface ResponseListener<T> {
        void onSuccess(T t);

        void onFail();
    }
}
