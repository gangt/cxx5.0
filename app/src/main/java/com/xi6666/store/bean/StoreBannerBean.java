package com.xi6666.store.bean;

import com.xi6666.carWash.base.network.BaseBean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 2016/12/1 下午5:07.
 * 个人公众号 ardays
 */

public class StoreBannerBean extends BaseBean {
    public List<StoreBannerData> data;


    public class StoreBannerData{
        public String title;
        public String link;
        public String img;
    }
}
