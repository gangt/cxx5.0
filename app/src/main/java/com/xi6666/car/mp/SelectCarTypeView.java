package com.xi6666.car.mp;

import com.xi6666.car.bean.CarEngineBean;
import com.xi6666.car.bean.CarBrandTypeBean;
import com.xi6666.car.bean.CarYearTypeBean;
import com.xi6666.car.bean.SelectCarBean;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午2:27.
 * 个人公众号 ardays
 */

public interface SelectCarTypeView {
    void carTypeList(SelectCarBean bean);

    /**
     * 汽车品牌返回
     */
    void myCarDataSpList(CarBrandTypeBean bean);
    /**
     * 汽车年限返回
     */
    void myCarYearDataList(CarYearTypeBean bean);
    /**
     * 汽车排油
     */
    void myCarEngineList(CarEngineBean bean);
}
