package com.xi6666.carWash.mvp.bean;

import com.xi6666.carWash.base.network.BaseBean;

/**
 * 创建者 sunsun
 * 时间 2016/12/1 上午10:18.
 * 个人公众号 ardays
 */

public class DetermineOrderBean extends BaseBean {

    public DataBean data;


    public class DataBean{
        public String service_order_id;
        public String shop_name;
        public String cate_name;
        public String order_sn;
        public String service_cate_id;
        public String add_datetime;
        public String service_name;
        public String order_amount;

        @Override
        public String toString() {
            return "DataBean{" +
                    "service_order_id='" + service_order_id + '\'' +
                    ", shop_name='" + shop_name + '\'' +
                    ", cate_name='" + cate_name + '\'' +
                    ", order_sn='" + order_sn + '\'' +
                    ", service_cate_id='" + service_cate_id + '\'' +
                    ", add_datetime='" + add_datetime + '\'' +
                    ", service_name='" + service_name + '\'' +
                    ", order_amount='" + order_amount + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DetermineOrderBean{" +
                "data=" + data +
                '}';
    }
}
