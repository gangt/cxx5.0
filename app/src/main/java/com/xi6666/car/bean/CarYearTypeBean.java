package com.xi6666.car.bean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午2:28.
 * 个人公众号 ardays
 */

public class CarYearTypeBean {
    public boolean success;
    public String info;
    public List<TypeBean> data;


    public class TypeBean{
        public String year_number;
        public String year_name;
        public String cartype_number;

        @Override
        public String
        toString() {
            return "TypeBean{" +
                    "year_number='" + year_number + '\'' +
                    ", year_name='" + year_name + '\'' +
                    ", cartype_number='" + cartype_number + '\'' +
                    '}';
        }
    }
}
