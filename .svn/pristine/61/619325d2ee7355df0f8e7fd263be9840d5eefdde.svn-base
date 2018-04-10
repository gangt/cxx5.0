package com.xi6666.owner.mvp.bean;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 2016/11/30 上午11:55.
 * 个人公众号 ardays
 */

public class OwnerEvaluationBean extends BaseBean {
    public DataBean data;


    /*
         "comment_level": 门店评分,
         "sum": "评论条数"
         discuss_id": " 评论id",
         "service_order_sn": "订单号",
         "comment_content": "内容",
         "comment_level": "星数",
         "add_datetime": "评价时间",
         "service_name": "订单类型",
         "user_mobile": "手机号码"


         "all_count": "全部评价条数",
         "good_count": "好评条数",
         "middle_count": "中评条数",
         "bad_count": "差评条数"
     */
    public class DataBean {
        public ScoreShopBean scoreshop;
        public List<StoreDetailsBean.DataBean.DiscussinfoBean> discuss;
        public PingJiaBean pingjia;

        public class ScoreShopBean {
            public String comment_level;
            public String sum;
        }

        public class PingJiaBean {
            public String all_count;
            public String good_count;
            public String middle_count;
            public String bad_count;
        }
    }
}