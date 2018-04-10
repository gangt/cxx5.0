package com.xi6666.illegal.other;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public class GsonUtils {


    //实体转换成json
    public static String objToJson(Object paramClass) {
        return new Gson().toJson(paramClass);
    }
    //json解析
    public static <T> T toEntityFromJson(String paramString, Class<T> paramClass) {
        return new Gson().fromJson(new String(paramString), paramClass);
    }


}
