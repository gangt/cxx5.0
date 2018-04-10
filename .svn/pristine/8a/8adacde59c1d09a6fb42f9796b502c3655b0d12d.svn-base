package com.xi6666.utils;

import java.lang.reflect.ParameterizedType;

/**
 * 创建人 孙孙啊i
 * 时间 2016/11/21 0021.
 * 功能
 */
public class TUtils {
    /**
     * 强制实例化泛型
     * @param o
     * @param i
     * @param <T>
     * @return
     */
    public static<T> T getT(Object o,int i){
        try {
            return ((Class<T>)((ParameterizedType) o.getClass().getGenericSuperclass()).getActualTypeArguments()[i]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e){
            e.printStackTrace();
        }
        return null;
    }
}
