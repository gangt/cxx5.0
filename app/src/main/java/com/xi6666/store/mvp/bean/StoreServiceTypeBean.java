package com.xi6666.store.mvp.bean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/25 上午11:45.
 * 个人公众号 ardays
 */

public class StoreServiceTypeBean {
    public boolean success;
    public String info;
    public List<DataBean> data;


    public class DataBean{
        public String service_cate_id;
        public String cate_name;
    }
}
