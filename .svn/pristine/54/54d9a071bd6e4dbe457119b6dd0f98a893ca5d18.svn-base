package com.xi6666.app.baset;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Mr_yang on 2017/1/16.
 * 实例化泛型
 */

public class InstanceUtil {
    public static <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) o.getClass().getGenericSuperclass()).getActualTypeArguments()[i]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
