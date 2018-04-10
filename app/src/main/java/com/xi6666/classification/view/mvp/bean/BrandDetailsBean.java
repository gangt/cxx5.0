package com.xi6666.classification.view.mvp.bean;

import com.xi6666.carWash.base.network.BaseBean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午6:37.
 * 个人公众号 ardays
 */

public class BrandDetailsBean extends BaseBean {
    public DataBean data;

    public class DataBean {
        public String title;
        public String cart_num;
        public List<ListBean> list;

        public class ListBean {
            public String goods_id;
            public String goods_name;
            public String shop_price;
            public String market_price;
            public String goods_thumb_img;
            public String brand_id;
            public String cate_id;
        }
    }
}
