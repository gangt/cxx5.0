package com.xi6666.car.mp;

import com.xi6666.car.bean.CarEngineBean;
import com.xi6666.car.bean.CarBrandTypeBean;
import com.xi6666.car.bean.CarYearTypeBean;
import com.xi6666.car.bean.SelectCarBean;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午2:54.
 * 个人公众号 ardays
 */

public interface SelectCarListener {
    void myCarDataList(SelectCarBean bean);
    void myCarDataSpList(CarBrandTypeBean bean);
    void myCarYearDataList(CarYearTypeBean bean);
    void myCarEngineList(CarEngineBean bean);
}
