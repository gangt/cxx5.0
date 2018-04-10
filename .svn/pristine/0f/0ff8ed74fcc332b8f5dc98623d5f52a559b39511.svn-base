package com.xi6666.carWash.mvp.bean;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.store.mvp.bean.TechnicianTeamBean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/25 下午9:04.
 * 个人公众号 ardays
 */

public class StoreDetailsBean extends BaseBean {
    public DataBean data;


    public class DataBean{

        public StoreInfo storeinfo;
        public List<ServiceListBean> servicelist;
        public List<TechnicianTeamBean> technicianinfo;
        public List<DiscussinfoBean> discussinfo;
        public WxShareBean wx_share;

        public class StoreInfo{
            public List<String> shop_bannerall;
            public String user_id;
            public String shop_name;
            public String shop_address;
            public String shop_opentime;
            public String shop_closetime;
            public String shop_tel;
            public String shop_lat;
            public String shop_lng;
            public String distance;
            public float service_score;
            public String sum;
            public String openstat;

            @Override
            public String toString() {
                return "StoreInfo{" +
                        "user_id='" + user_id + '\'' +
                        ", shop_banner='" + shop_bannerall.toString() + '\'' +
                        ", shop_name='" + shop_name + '\'' +
                        ", shop_address='" + shop_address + '\'' +
                        ", shop_opentime='" + shop_opentime + '\'' +
                        ", shop_closetime='" + shop_closetime + '\'' +
                        ", distance='" + distance + '\'' +
                        ", service_score='" + service_score + '\'' +
                        ", sum='" + sum + '\'' +
                        ", openstat='" + openstat + '\'' +
                        '}';
            }
        }

        public class ServiceListBean{
            public String service_cate_id;
            public String cate_name;
            public String cate_img;
        }

        public class DiscussinfoBean{
            public String zan;
            public String discuss_id;
            public String service_order_sn;
            public String comment_content;
            public String comment_level;
            public String add_datetime;
            public int service_cate_id;
            public String service_name;
            public String user_mobile;
            public String label_url;
            public String zandot;
            public List<String> pl_pics;

            @Override
            public String toString() {
                return "DiscussinfoBean{" +
                        "discuss_id='" + discuss_id + '\'' +
                        ", service_order_sn='" + service_order_sn + '\'' +
                        ", comment_content='" + comment_content + '\'' +
                        ", comment_level='" + comment_level + '\'' +
                        ", add_datetime='" + add_datetime + '\'' +
                        ", service_name='" + service_name + '\'' +
                        ", user_mobile='" + user_mobile + '\'' +
                        '}';
            }
        }

        public class WxShareBean{
            public String wx_share_title;
            public String wx_share_img_url;
            public String wx_share_desc;
            public String wx_share_link;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "storeinfo=" + storeinfo +
                    ", servicelist=" + servicelist +
                    ", technicianinfo=" + technicianinfo +
                    ", discussinfo=" + discussinfo +
                    '}';
        }
    }
}
