package com.xi6666.cardbag.view.mvp.bean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/24 上午9:40.
 * 个人公众号 ardays
 */

public class OilCardChargeDetailsBean {
    public boolean success;
    public String info;
    public DataBean data;

    public class DataBean{
        public boolean success;
        public String info;
        public String count_num;        //"count_num":总记录数
        public List<ChargeDetailsData> data;

        public class ChargeDetailsData{
            /*
                "add_datetime": 充值时间,

                "package_name": 套餐类型,

                "package_amount": 充值金额
             */
            public String order_paytime;
            public String package_name;
            public String package_amount;
        }

    }
}
