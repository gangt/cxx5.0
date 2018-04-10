package com.xi6666.network;

import com.xi6666.databean.TokenBean;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Mr_yang on 2016/11/4.
 */

public interface Repository {

    Observable<ResponseBody> getMessage(String page, String userId,String token);

    Observable<TokenBean> getToken();

}
