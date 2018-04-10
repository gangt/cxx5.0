package com.xi6666.car.mp;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午2:52.
 * 个人公众号 ardays
 */

public interface SelectCarTypePresenter {
    /**
     * 获取汽车品牌列表
     */
    void getCarTypeList();
    /**
     * 获取汽车品牌种类列表
     */
    void getCarTypeSpList(String brand_number);
    /**
     * 获取汽车年限
     */
    void getCarYearTypeList(String cartype_number);
    /**
     * 获取汽车年限
     */
    void getCarEngineList(String brand_number, String cartype_number);
}
