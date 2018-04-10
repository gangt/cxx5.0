package com.xi6666.car.mp;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午2:55.
 * 个人公众号 ardays
 */

public interface SelectCarTypeInteractor {
    void getCarType();
    void getCarSpType(String brand_number);
    void getCarYearType(String brand_number);
    void getCarEngineType(String brand_number, String cartype_number);
}
