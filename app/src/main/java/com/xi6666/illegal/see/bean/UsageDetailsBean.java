package com.xi6666.illegal.see.bean;

import com.xi6666.carWash.base.network.BaseBean;

/**
 * 创建者 sunsun
 * 时间 2017/2/9 上午10:38.
 * 个人公众号 ardays
 */

public class UsageDetailsBean extends BaseBean {
    public DataBean data;

    /**
     * 测试接口：http://dev-app.xiaoxi6.com/Illegalcard/illegal_card_use_orderinfo?user_id=11058&user_token=&log_id=1
     * json数据：
     * {
         "success":true,
         "info":"获取订单详情成功",
         "data":{
             "log_id":"1",
             "order_sn":"123456",
             "add_datetime":"2017-02-07 21:19:30",
             "do_status":"1",
             "card_id":"1",
             "date_time":"2016-10-19 10:48:37",
             "area":"【广东广州】内环路A线珠江隧道出口",
             "act":"机动车违反禁止标线指示的",
             "fen":"3",
             "money":"200",
             "card_number":null,
             "surplus_number":null,
             "order_status":"待审核",
             "order_info":"客服会尽快与你联系，请保持电话畅通。如有疑问，请咨询客服电话：400-9999-353"
         },
         "version":"5"
     }
     */
    public class DataBean{
        public String log_id;
        public String order_sn;
        public String add_datetime;
        public String do_status;
        public String card_id;
        public String date_time;
        public String area;
        public String act;
        public String fen;
        public String money;
        public String card_number;
        public String surplus_number;
        public String order_status;
        public String order_info;
    }
}
