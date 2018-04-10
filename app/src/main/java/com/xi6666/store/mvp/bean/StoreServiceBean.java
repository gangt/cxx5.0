package com.xi6666.store.mvp.bean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/25 下午2:48.
 * 个人公众号 ardays
 * 门店列表数据bean
 */

public class StoreServiceBean {
    public boolean success;
    public String info;
    public List<DataBean> data;

    public class DataBean {
        /*
            "service_id": "服务套餐编码",

            "service_name": "服务名称",

            "service_price": "服务单价",

            "service_score": 评分,

            "shop_banner": "头像",

            "shop_city": "城市编码",

            "shop_name": "门店名称",

            "store_id": "门店编码",

            "shop_address": "门店地址",

            "shop_opentime": "门店开始营业时间",

            "shop_closetime": "门店结束营业时间"

            "sum": "评论条数",

            "has_go":是否访问过该店，是1否0

            "city": "该店的所在城市"

            "distance": "距离"

         */
        public String service_id;
        public String service_name;
        public String service_price;
        public String service_store_id;
        public String shop_banner;
        public String shop_city;
        public String shop_name;
        public String store_id;
        public String shop_address;
        public String shop_opentime;
        public String shop_closetime;
        public String sum;
        public float service_score;
        public float has_go;
        public String city;
        public String distance;

        @Override
        public String toString() {
            return "DataBean{" +
                    "service_id='" + service_id + '\'' +
                    ", service_name='" + service_name + '\'' +
                    ", service_price='" + service_price + '\'' +
                    ", shop_banner='" + shop_banner + '\'' +
                    ", shop_city='" + shop_city + '\'' +
                    ", shop_name='" + shop_name + '\'' +
                    ", store_id='" + store_id + '\'' +
                    ", shop_address='" + shop_address + '\'' +
                    ", shop_opentime='" + shop_opentime + '\'' +
                    ", shop_closetime='" + shop_closetime + '\'' +
                    ", sum='" + sum + '\'' +
                    ", service_score=" + service_score +
                    ", has_go=" + has_go +
                    ", city='" + city + '\'' +
                    ", distance='" + distance + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "StoreServiceBean{" +
                "success=" + success +
                ", info='" + info + '\'' +
                ", data=" + data +
                '}';
    }
}
