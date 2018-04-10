package com.xi6666.cardbag.view.mvp.bean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/23 下午6:17.
 * 个人公众号 ardays
 */

public class OilCardNotAlreadyBean {
    public boolean success;
    public String info;
    public List<DataBean> data;

    public class DataBean{
        /*

            "add_datetime": 购买时间,

            "package_cash": 返还金额,

            "package_return_number": 返还期数,

            "package_left_number": 剩余返回期数,

            "total_money": 总金额

         */
        public String order_paytime;
        public String package_cash;
        public String package_left_number;
        public String package_return_number;
        public String total_money;
        public long add_datetime;
    }
}
