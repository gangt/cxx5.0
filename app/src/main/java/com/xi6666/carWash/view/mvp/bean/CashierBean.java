package com.xi6666.carWash.view.mvp.bean;

import com.xi6666.carWash.base.network.BaseBean;

/**
 * 创建者 sunsun
 * 时间 2016/12/2 下午11:36.
 * 个人公众号 ardays
 */

public class CashierBean extends BaseBean {
    public DataBean data;

    public class DataBean {
        /*
            "out_trade_no":"2016120721410707854",       //要拼接
            "pay_id":"",
            "body":"服务订单支付",                        //要拼接
            "attach":"service_pay",                       //要拼接
            "user_id":"1485405",
            "service_name":"普洗5座－7座",
            "order_id":"524142",
            "washcar_zhekou":"http://dev-app.xiaoxi6.com//images/washcar_zhekou.jpg",
            "can_use_money":0,
            "washcard_id":0,
            "order_amount":"20.00",
            "total_fee":2000,                           //要拼接
            "interface_url":"http://dev-app.xiaoxi6.com/Pay/apppay",
            "zhifubao_url":"http://dev-app.xiaoxi6.com/Pay/notify_url"
        */
        public String out_trade_no;
        public String pay_id;
        public String body;
        public String attach;
        public String service_name;
        public String order_id;
        public String washcar_zhekou;
        public float can_use_money;
        public String order_amount;
        public long total_fee;
        public String interface_url;
        public String zhifubao_url;
        public String washcard_id;
        public String washcard_next_used_time;
        public String washcard_cash_amount;
        public int is_used;
    }
}
